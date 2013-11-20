package gui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;


import containers.KennelAccess;

import entities.ExtendedKennel;
import entities.ProblemDog;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;


/**
 * A window to view problem dogs.
 * The user is given a list of Problem dogs.
 * They can select one to modify, or return to operations. 
 */
public class ViewProblemDogFrame extends JFrame
{
	/** Initialize the window to display all problem dogs
	 * which can be modified by clicking their named button
	 * and a button to return to operations*/
	public ViewProblemDogFrame()
	{
		setTitle("Problem Dogs");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(500, 350);
		setLocation(400, 200);
		setVisible(true);
		add(new ViewProblemDogPanel());
	}
	
	/** Panel to hold the buttons for the window */
	private class ViewProblemDogPanel extends JPanel
	{
		/** Initialize the panel to display the information for each problem dog
		 *  also add a button to exit this window and return to operations window. */
		public ViewProblemDogPanel()
		{
			ExtendedKennel kennel = KennelAccess.getKennel();
			
			setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
			
			JLabel titleLabel = new JLabel("Problem Dogs:");
			titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
			add(titleLabel);
			
			
			for (int i = 1; i <= kennel.problemDogCollection().size(); i++)
			{
				add(new PanelForProblemDog(i));
				add(Box.createVerticalGlue());
			}
			setAlignmentX(Component.LEFT_ALIGNMENT);
			JButton exitButton = new JButton("Cancel");
			add(exitButton);
			add(Box.createVerticalGlue());
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
	
	/** Panel with information for specific problem dog*/
	private class PanelForProblemDog extends JPanel
	{
		/** Constant to store the size of strut used for spacing*/
		public static final int STRUT_WIDTH = 25;
		
		int pdogNumber;
		Component struct1;
		ProblemDog pet;
		JButton petButton;
		
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
			
			add(Box.createHorizontalStrut(STRUT_WIDTH));
			add(new JLabel("" + pdogNumber + ":"));
			
			struct1 = Box.createHorizontalStrut(STRUT_WIDTH);
			add(struct1);
			petButton = new JButton("" + pet.getName());
			petButton.addActionListener(new PetAccessListener());
			add(petButton);
			

		}
		
		/** The class to handle the button to access a pet.
		 * It sends the user to the ModifyProblemDogFrame
		 * where they can edit the dog's problem*/
		class PetAccessListener implements ActionListener
		{
			public void actionPerformed (ActionEvent event)
			{
				getTopLevelAncestor().setVisible(false);
				new ModifyProblemDogFrame(pet.getName());
			}
		}

		public static final long serialVersionUID = 1;
	}
	public static final long serialVersionUID = 1;
}

