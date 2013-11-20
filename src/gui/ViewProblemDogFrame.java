package gui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JLabel;

import containers.KennelAccess;

import entities.ExtendedKennel;
import entities.Kennel;
import entities.Pet;
import entities.ProblemDog;
import gui.PensFrame.PensPanel.ExitPanel;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

/**
 * A window to modify a problem dog.
 * The user is given a field to modify the dog's problems.
 * The information for the pet is entered by the user, and the pet is modified. 
 */
public class ViewProblemDogFrame extends JFrame
{
	public ViewProblemDogFrame()
	{
		setTitle("Add Problem Dog");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(500, 350);
		setLocation(400, 200);
		setVisible(true);
		add(new ViewProblemDogPanel());
	}
	
	/** Initialize the panel to display the information for each problem dog
	 *  also add a button to exit this window and return to operations window. */
	private class ViewProblemDogPanel extends JPanel
	{
		public ViewProblemDogPanel()
		{
			ExtendedKennel kennel = KennelAccess.getKennel();
			
			setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
			
			JLabel titleLabel = new JLabel("Problem Dogs");
			titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
			add(titleLabel);
			
			for (int i = 1; i <= kennel.problemDogCollection().size(); i++)
			{
				add(new PanelForProblemDog(i));
			}
			
			add(new ExitPanel());
		}
		public static final long serialVersionUID = 1;
	}
	
	/** Panel with information for specific problem dog*/
	private class PanelForProblemDog extends JPanel
	{
		/** Constant to store the size of strut used for spacing*/
		public static final int STRUT_WIDTH = 25;
		
		int pdogNumber;
		Component struct1;
		Pet pet;
		JButton petButton;
		Component struct2;
		
		/** Constructor for the problem dog panel to place components in the panel
		 * @param number  the number of the problem dog whos info is being displayed*/
		public PanelForProblemDog(int number)
		{
			pdogNumber = number;
			ExtendedKennel kennel = KennelAccess.getKennel();
			
			Iterator<ProblemDog> iter = kennel.problemDogCollection().iterator();
			for (int i = 1; i<=number;i++)
			{
				pet = iter.next();
			}
			
			setAlignmentX(Component.LEFT_ALIGNMENT);
			setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
			
			add(Box.createHorizontalStrut(STRUT_WIDTH));//indent
			add(new JLabel("" + pdogNumber + ":"));
			
			struct1 = Box.createHorizontalStrut(STRUT_WIDTH);
			add(struct1);
			petButton = new JButton("" + pet.getName());
			petButton.addActionListener(new PetAccessListener());
			add(petButton);
			struct2 = Box.createHorizontalStrut(STRUT_WIDTH);
			add(struct2);
			
			
		}
		
		/** The class to handle the button to access a pet.
		 * It displays the information for the pet.*/
		class PetAccessListener implements ActionListener
		{
			public void actionPerformed (ActionEvent event)
			{
				//GO TO MODIFYPROBLEMDOGFRAME
			}
		}
		
		/** A Panel with a button whose action is to hide the problem
		 * dog information window and return to operation window*/
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
	}
	public static final long serialVersionUID = 1;
}

