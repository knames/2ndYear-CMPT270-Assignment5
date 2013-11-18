package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.Box;

import commands.AssignPenCommand;
import containers.KennelAccess;
import entities.Kennel;
import entities.PetOwner;
import entities.Pet;

/**
 * The class to display the information for an owner.  The information includes 
 * the information for the owner (name and address), plus a list of the pets for
 * the owner.  If a pet is already in a pen, the pen is given.  If a pet is not 
 * already in a pen, there is a field into which a pen number can be entered.  
 * Entering the pen number into the field will place the pet in that pen.  A
 * button is also given to add a new pet for the owner.  Finally, buttons are 
 * given to return to the options window or to go the the pens information window.
 */
public class OwnerFrame extends JFrame 
{
	/** Was the frame successfully created? */
	public boolean successfullyCreated = false;
	
	/** The kennel with the information about the system, including a dictionary
	 *  of all owners. */
	private Kennel kennel;
	
	/** The pet owner whose information is being presented.  */
	private PetOwner owner;
	
	/** Initialize and set visible the frame to display the information for the owner
	 * with the name specified.
	 * @param name   the name of the owner whose information is displayed */
	public OwnerFrame(String name)
	{
		kennel = KennelAccess.getKennel();
		try
		{
			owner = kennel.getOwner(name);
		} catch (Exception e)
		{
			successfullyCreated = false;
			return;
		}
		successfullyCreated = true;
		setTitle("Owner Information");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(300, 200 + 25*owner.petArray().length);
		setLocation(400, 200);
		add(new InfoPanel());
		setVisible(true);
	}

	public static final long serialVersionUID = 1;
	
	/** The class for the panel to contain all the components of the window. */
	private class InfoPanel extends JPanel
	{
		/** Initialize the panel with the components for the window. */
		public InfoPanel()
		{
			/* The panel is organized into three segments: the owner information,
			 * the pet information, and the options to go to another window.  */
			
			setLayout(new BorderLayout());
			JPanel namePanel = new JPanel();
			add(namePanel, BorderLayout.PAGE_START);
			namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.PAGE_AXIS));
			JLabel nameLabel = new JLabel(owner.getName());
			nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			namePanel.add(nameLabel);
			JLabel addressLabel = new JLabel(owner.getAddress());
			addressLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			namePanel.add(addressLabel);
			
			add(new PetsPanel(), BorderLayout.CENTER);

			add(new ExitPanel(), BorderLayout.PAGE_END);
		}
		
		public static final long serialVersionUID = 1;
		
		/** 
		 * The panel to contain a list of pets for the owner, 
		 * and the pen information for the pet.  Also, include a button
		 * to add another pet.  */
		private class PetsPanel extends JPanel
		{
			/** Initialize the panel to display the pets of the owner,
			 *   and a button to add another pet. */
			public PetsPanel()
			{
				/* Align the pets along the left hand side of the panel. */
				setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
				add(Box.createVerticalGlue());
				/* Space above the pets list. */
				
				add(new JLabel("Pets:"));
				for (Pet p: owner.petArray())
				{
					PetPanel petPanel = new PetPanel(p);
					petPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
					petPanel.setMaximumSize(petPanel.getPreferredSize());
					add(petPanel);
				}
				
				add(Box.createVerticalGlue());
				/* Space between the pets and the add pet button. */
				
				JButton addPetButton = new JButton("Add pet");
				addPetButton.setAlignmentX(Component.LEFT_ALIGNMENT);
				add(addPetButton);
				addPetButton.addActionListener(new
					ActionListener()
					{
						public void actionPerformed (ActionEvent event)
						{
							getTopLevelAncestor().setVisible(false);
							new AddPetFrame(owner.getName());
						}
					});
				
				add(Box.createVerticalGlue());
				/* Space after the add pet button. */
			}
			public static final long serialVersionUID = 1;
		}
		
		/** 
		 * The panel to include the information for one pet.  It includes the 
		 * pet name, and either the pen of the pet, or a field for the entry
		 * of a pen number into which the pet is to be placed.
		 */
		private class PetPanel extends JPanel
		{
			/** Initialize the panel to contain the components for the pet. */
			public PetPanel(final Pet p)
			{
				setLayout(new BorderLayout());
				if (kennel.hasPet(p.getName()))
				{
					add(new JLabel(p.getName() + "   in pen " 
					               + kennel.penNumberOf(p.getName()))
					               , BorderLayout.LINE_START);
				}
				else
				{
					final JLabel label = new JLabel(p.getName() + "             assign pen ");
					add(label, BorderLayout.LINE_START);
					final JTextField petNumField = new JTextField(5);
					petNumField.setMaximumSize(petNumField.getPreferredSize());
					add(petNumField, BorderLayout.CENTER);
					petNumField.addActionListener(new
							ActionListener()
							{
								public void actionPerformed (ActionEvent event)
								{
									/* Parse the field to obtain the pen number,  
									 * insert the pet into the pen, and update the panel. */
									int pen = 0;
									Scanner inputScanner = new Scanner(petNumField.getText());
									try
									{
										pen = inputScanner.nextInt();
										inputScanner.close();
									} catch (Exception e)
									{
										petNumField.setText("Not an integer");
										inputScanner.close();
										return;
									} 
									AssignPenCommand cmd = new AssignPenCommand();
									cmd.assignPen(p.getOwner().getName(), p.getName(), pen);
									if (cmd.wasSuccessful())
									{
										remove(petNumField);
										label.setText(p.getName() + "   in pen " 
									              + kennel.penNumberOf(p.getName()));
										validate();
									}
									else
										JOptionPane.showMessageDialog(getTopLevelAncestor(), 
												cmd.getErrorMessage(), "Error", 
												JOptionPane.ERROR_MESSAGE);
								}
							});
				}
			}
			
			public static final long serialVersionUID = 1;
		}
	
	
		/** 
		 * The panel with the options to leave this window.  One option is to return
		 * to the options window, while the second option is to go to the pens 
		 * information window.
		 */
		private class ExitPanel extends JPanel
		{
			/** Initialize the panel with the buttons and their listeners. */
			public ExitPanel()
			{
				JButton exitButton = new JButton("Exit");
				add(exitButton);
				exitButton.addActionListener(new
					ActionListener()
					{
						public void actionPerformed (ActionEvent event)
						{
							getTopLevelAncestor().setVisible(false);
							new OperationsFrame();
						}
					});
				
				JButton pensButton = new JButton("Pens");
				add(pensButton);
				pensButton.addActionListener(new
						ActionListener()
						{
							public void actionPerformed (ActionEvent event)
							{
								getTopLevelAncestor().setVisible(false);
								new PensFrame();
							}
						});

			}
			public static final long serialVersionUID = 1;
		}
	}
}
