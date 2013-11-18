package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import containers.KennelAccess;
import entities.Kennel;
import entities.Pet;
import commands.DischargePetCommand;

/** 
 * The window to display the information for the pens of the kennel.
 * In particular, for each pen, if it is not occupied a button
 * is shown that can be used to insert a pet, via the OwnerFrame.
 * If a pen is occupied, a button can be used to display the information
 * for the pet, and another button to discharge the pet from the pen.
 * After the pens, an exit button will return to the operations window.
 */
public class PensFrame extends JFrame 
{
	/** The kennel with information about the pens. */
	Kennel kennel;

	/** The constructor to initialize the frame and set it visible. */
	public PensFrame()
	{
		setTitle("Kennel Pens");
		kennel = KennelAccess.getKennel();
		setSize(300, 50 + 50*kennel.size());
		setLocation(400, 200);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		PensPanel  panel = new PensPanel();
		add(panel);
		setVisible(true);
	}


	public static final long serialVersionUID = 1;
	
	/** 
	 * The panel to hold all the information for the window. 
	 */
	public class PensPanel extends JPanel
	{
		/** Initialize the panel to display the information for each pen,
		 *  and a button to exit this window and return to the operations window. */
		public PensPanel()
		{
			setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
			
			JLabel titleLabel = new JLabel("Pens");
			titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
			add(titleLabel);
			
			for (int i = 1; i <= kennel.size(); i++)
			{
				add(new PanelForPen(i));
			}
			
			add(new ExitPanel());
		}
		
		/**
		 * The panel with the information for a specific pen.
		 */
		private class PanelForPen extends JPanel
		{
			/** A constant to storage the size of a strut used for spacing. */
			public static final int STRUT_WIDTH = 25;
			
			// Various components of the panel, defined as fields so that
			// inner classes can access them.
			int penNumber;
			Component struct1;
			Pet pet;
			JButton petButton;
			Component struct2;
			JButton dischargeButton;
			
			/** Constructor for the pen panel to place the components in the panel.
			 * @param number  the number of the pen whose information is being displayed
			 */
			public PanelForPen(int number)
			{
				penNumber = number;
				setAlignmentX(Component.LEFT_ALIGNMENT);
				setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

				add(Box.createHorizontalStrut(STRUT_WIDTH));  // indent the pen number
				add(new JLabel("" + penNumber + ":"));
				
				if (kennel.hasOccupant(penNumber))
				{
					struct1 = Box.createHorizontalStrut(STRUT_WIDTH);
					add(struct1);
					pet = kennel.occupantOfPen(penNumber);
					petButton = new JButton("" + pet.getName());
					petButton.addActionListener(new PetAccessListener());
					add(petButton);
					struct2 = Box.createHorizontalStrut(STRUT_WIDTH);
					add(struct2);
					dischargeButton = new JButton("Discharge");
					dischargeButton.addActionListener(new DischargeButtonListener());
					add(dischargeButton);
				}
				else
					addInsertButton();
			}
			
			/** The class to handle the button to access a pet.  
			 *  It displays the information for the pet. */
			class PetAccessListener implements ActionListener
			{
				public void actionPerformed (ActionEvent event)
				{
					JOptionPane.showMessageDialog(null, 
							"Pen: " + penNumber + "\n" + pet.toString());
				}
			}
			
			/** The class to handle the button to discharge a pet.  
			 *  It removes the pet from its pen, and then reconstructs 
			 *  the panel to show the insert button rather than the
			 *  discharge button.  */
			class DischargeButtonListener implements ActionListener
			{
				public void actionPerformed (ActionEvent event)
				{
					DischargePetCommand cmd = new DischargePetCommand();
					cmd.dischargePet(pet.getName());
					if (!cmd.wasSuccessful())
					{
						JOptionPane.showMessageDialog(null, 
								cmd.getErrorMessage());
					}
					else
					{
						remove(struct1);
						remove(petButton);
						remove(struct2);
						remove(dischargeButton);
						addInsertButton();
						getTopLevelAncestor().validate();
					}
				}
			}
				
			/** Add an insert button to the pet panel, and add its
			 * listener to hide this window, display a dialog box to
			 * obtain the owner's name for an insertion, and display
			 * the window with the information for the owner. */
			private void addInsertButton()
			{
				add(Box.createHorizontalStrut(STRUT_WIDTH));
				final JButton insertButton = new JButton("insert");
				add(insertButton);
				insertButton.addActionListener(new
						ActionListener()
						{
							public void actionPerformed (ActionEvent event)
							{
								/** Obtain the name of the owner whose pet is to be
								 * placed in a pen, and then display the window for
								 * the owner.  */
								String ownerName = JOptionPane.showInputDialog(null, 
								             "Enter the owner's name");
								
								OwnerFrame ownerFrame = new OwnerFrame(ownerName);
								if (ownerFrame.successfullyCreated)
								{
									getTopLevelAncestor().setVisible(false);
									ownerFrame.setVisible(true);
								}
								else
								{
									JOptionPane.showMessageDialog(getTopLevelAncestor(), 
									        "No owner with name " + ownerName,
									        "Error", JOptionPane.ERROR_MESSAGE);
								}
							}
						});
			}

			public static final long serialVersionUID = 1;
		}

		
		/** 
		 * A panel with a button whose action is to hide the pens information window 
		 * and return to the operations window.
		 */
		private class ExitPanel extends JPanel
		{
			public ExitPanel()
			{
				setAlignmentX(Component.LEFT_ALIGNMENT);
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
			}
			public static final long serialVersionUID = 1;
		}

		public static final long serialVersionUID = 1;
	}
}
