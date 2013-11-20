package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import entities.ExtendedKennel;

import entities.ProblemDog;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import commands.ChangeProblemDogCommand;
import containers.KennelAccess;

/**
 * A window to modify a problem dog.
 * The user is given a field to modify the dog's problems.
 * The information for the pet is entered by the user, and the pet is modified. 
 */
public class ModifyProblemDogFrame extends JFrame
{
	/** The area for entering the dog's problem		*/
	private JTextArea problemArea;
	
	
	public ModifyProblemDogFrame(String dog)
	{
		setTitle("Modify Problem Dog");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(500, 350);
		setLocation(400, 200);
		setVisible(true);
		add(new ViewProblemDogPanel(dog));
	}
	

	/** Panel to hold the buttons for the window */
	private class ViewProblemDogPanel extends JPanel
	{
		/** Initialize the panel to display the information for each problem dog
		 *  also add a button to exit this window and return to operations window. */
		public ViewProblemDogPanel(String dog)
		{
			ExtendedKennel kennel = KennelAccess.getKennel();
			final ProblemDog pdog = kennel.getProblemDog(dog);
			
			JPanel panel = new JPanel();
			panel.setLayout(new BorderLayout());
			
			JLabel titleLabel = new JLabel("Problem Dog:");
			titleLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
			add(titleLabel);

			
			JPanel namePanel = new JPanel();
			add(namePanel, BorderLayout.PAGE_START);
			namePanel.add(new JLabel("Dog: " + dog));

         
         JPanel ownerPanel = new JPanel();
         add(ownerPanel, BorderLayout.NORTH);
         ownerPanel.add(new JLabel("Owner: "+ pdog.getOwner().getName()));

         
         JPanel problemPanel = new JPanel();
         add(problemPanel, BorderLayout.SOUTH);
         problemPanel.add(new JLabel("Problem with dog"));
         problemArea = new JTextArea(pdog.getProblem(),5,20);
         problemArea.setLineWrap(true);
         JScrollPane scrollPane = new JScrollPane(problemArea);
         problemPanel.add(scrollPane);
         
         JPanel submitPanel = new JPanel();
         add(submitPanel, BorderLayout.PAGE_END);
         JButton submitButton = new JButton("Submit");
         submitPanel.add(submitButton);
         submitButton.addActionListener(new
         		ActionListener()
         		{
         			public void actionPerformed (ActionEvent event)
         			{
							ChangeProblemDogCommand cmd = new ChangeProblemDogCommand();
							cmd.changeProblem(pdog.getName(), problemArea.getText());
							if (!cmd.wasSuccessful())
							{
								JOptionPane.showMessageDialog(null, cmd.getErrorMessage());
							}
							else
							{
								getTopLevelAncestor().setVisible(false);
								new ViewProblemDogFrame();
							}
         			}
         		});

			
			JButton exitButton = new JButton("Cancel");
			setAlignmentX(Component.LEFT_ALIGNMENT);
			submitPanel.add(exitButton);
			exitButton.addActionListener(new
				ActionListener()
				{
					public void actionPerformed (ActionEvent event)
					{
						getTopLevelAncestor().setVisible(false);
						new ViewProblemDogFrame();
					}
				});
		}
		
		
		public static final long serialVersionUID = 1;
	}
	public static final long serialVersionUID = 1;
}
