package gui;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import commands.CreateProblemDogCommand;


/**
 * A window to add a problem dog for the owner whose name is passed in as a parameter
 * for the constructor.  The user is given a field to enter the dog's problems,
 * the information for the pet is entered by the user, and the pet is created 
 * and entered into the kennel.
 */
public class AddProblemDogFrame extends JFrame 
{
	/** Initialize the frame and set it visible. */
	public AddProblemDogFrame()
	{
		setTitle("Add Problem Dog");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(500, 350);
		setLocation(400, 400);
		setVisible(true);
		add(new ProblemDogPanel());
	}


	/** The panel to enter the information for a new dog, and create the pet. */
	private class ProblemDogPanel extends JPanel
	{


		/** The field for the entry of the dog's name. */
		private JTextField nameField;
		
		/** The field for the entry of the dog's owner. */
		private JTextField ownerField;
		
		/** The area for entering the dog's problem		*/
		private JTextArea problemArea;
				
		/** Initialize the dog panel to receive the information for the new problem dog, 
		 *  and add the listener to create the dog when the submit button is clicked. */
		public ProblemDogPanel()
		{
			JPanel panel = new JPanel();
			panel.setLayout(new BorderLayout());
			
			JPanel namePanel = new JPanel();
			add(namePanel, BorderLayout.PAGE_START);
			namePanel.add(new JLabel("Name for the dog"));
         nameField = new JTextField(15);
         namePanel.add(nameField);
         
         JPanel ownerPanel = new JPanel();
         add(ownerPanel, BorderLayout.PAGE_START);
         ownerPanel.add(new JLabel("Owner of the dog"));
         ownerField = new JTextField(15);
         ownerPanel.add(ownerField);
         
         JPanel problemPanel = new JPanel();
         add(problemPanel, BorderLayout.PAGE_START);
         problemPanel.add(new JLabel("Problem with dog"));
         problemArea = new JTextArea(5,20);
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
							CreateProblemDogCommand cmd = new CreateProblemDogCommand();
							cmd.createDog(nameField.getText(), ownerField.getText(), problemArea.getText());
							if (!cmd.wasSuccessful())
							{
								JOptionPane.showMessageDialog(null, cmd.getErrorMessage());
							}
							else
							{
								getTopLevelAncestor().setVisible(false);
								new OperationsFrame();
							}
         			}
         		});
         JButton exitButton = new JButton("Cancel");
         submitPanel.add(exitButton);
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
