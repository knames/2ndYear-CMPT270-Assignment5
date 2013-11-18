package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

import commands.AddOwnerCommand;

/** 
 * The frame to obtain the information for the creation of a pet owner.  
 * If valid information is entered, the owner is created and the window
 * for the owner is displayed.  */
public class AddOwnerFrame extends JFrame 
{
	/** Initialize the frame and add a panel to obtain the information
	 * for the new owner.  */
	public AddOwnerFrame()
	{
		setTitle("Add Owner");
		setSize(400, 150);
		setLocation(400, 200);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		AddOwnerPanel  panel = new AddOwnerPanel();
		add(panel);
		setVisible(true);
	}

	public static final long serialVersionUID = 1;
	
	/** 
	 * The panel to obtain the name and address of a new pet owner.
	 * A button is used to submit the information.  If creation of
	 * the owner is successful, a window displaying the information
	 * for the owner is displayed.  */
	private class AddOwnerPanel extends JPanel
	{
		/** The field to enter the owner's name. */
		JTextField nameField;
		
		/** The field to enter the owner's address. */
		JTextField addressField;
		
		/** Initialize the panel with the fields and the submit button. 
		 *  Add a listener for the button, that creates the new owner
		 *  and displays the window with the information for the new owner.  */
		public AddOwnerPanel()
		{
			setLayout(new BorderLayout());
			JPanel namePanel = new JPanel();
			namePanel.add(new JLabel("name "));
			nameField = new JTextField(20);
			namePanel.add(nameField);
			add(namePanel, BorderLayout.PAGE_START);
			
			JPanel addressPanel = new JPanel();
			addressPanel.add(new JLabel("address "));
			addressField = new JTextField(20);
			addressPanel.add(addressField);
			add(addressPanel, BorderLayout.CENTER);
			
			JPanel submitPanel = new JPanel();
			JButton submitButton = new JButton("Submit");
			submitPanel.add(submitButton);
			add(submitPanel, BorderLayout.PAGE_END);
			submitButton.addActionListener(new SubmitListener());
			JButton cancelButton = new JButton("Cancel");
			submitPanel.add(cancelButton);
			cancelButton.addActionListener(new
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
		
		/** The listener to the submit button of the panel. */
		private class SubmitListener implements ActionListener
		{
			/** When the submit button is pressed, create the new owner
			 *  and display a window with the information for the owner.  
			 *  If an error occurs, use a dialog box to display the error message.  */
			public void actionPerformed (ActionEvent event)
			{
				String name = nameField.getText();
				String address = addressField.getText();
				if (name.equals(""))
				{
					JOptionPane.showMessageDialog(getTopLevelAncestor(), 
							"An owner's name is required.", "Error", 
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				AddOwnerCommand cmd = new AddOwnerCommand();
				cmd.addOwner(name, address);
				if (!cmd.wasSuccessful())
				{
					JOptionPane.showMessageDialog(getTopLevelAncestor(), 
							cmd.getErrorMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					OwnerFrame ownerFrame = new OwnerFrame(name);
					if (ownerFrame.successfullyCreated)
					{
						getTopLevelAncestor().setVisible(false);
					}
					else
					{
						JOptionPane.showMessageDialog(getTopLevelAncestor(), 
						        "No owner with name " + name,
						        "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
	}
}
