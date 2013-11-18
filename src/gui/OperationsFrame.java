package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import commands.SystemInfoCommand;

/**
 * The frame to display the window with the options for the user: show pen information, 
 * add an owner, display owner information, display system state, and quit.
 */
public class OperationsFrame extends JFrame 
{
	/** The constructor to initialize and set visible the frame. */
	public OperationsFrame()
	{
		setTitle("Kennel System");
		setSize(600, 350);
		setLocation(400, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		OperationPanel  panel = new OperationPanel();
		add(panel);
		setVisible(true);
	}

	public static final long serialVersionUID = 1;
	
	/** 
	 * The class for the panel that presents the options to the user.
	 */
	public class OperationPanel extends JPanel 
	{
		
		/** 
		 * Initialize the panel with the options from which a user can select:
		 * show pen information, add an owner, display owner information, 
		 * display system state, and quit.
		 */
		public OperationPanel()
		{
			/* Align the components vertically in the middle, with the prompt
			 * on the left, and the options on the right. */
			setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
			
			add(Box.createVerticalGlue());
			/* Equal vertical spacing between the components of the window */
			
			JLabel prompt = new JLabel("Please select a task:");
			prompt.setMaximumSize(prompt.getPreferredSize());
			add(prompt);
			prompt.setAlignmentX(Component.RIGHT_ALIGNMENT);
			// align the right hand side of the prompt with the left hand side 
			// of the other components
			add(Box.createVerticalGlue());
			
			addPensButton();
			add(Box.createVerticalGlue());
			
			addAddOwnerButton();
			add(Box.createVerticalGlue());
			
			addAccessOwnerPanel();
			add(Box.createVerticalGlue());
			
			addStateButton();
			add(Box.createVerticalGlue());
			
			addQuitButton();
			add(Box.createVerticalGlue());
		}
		
		/** 
		 * Add a button to access the pens information.
		 */
		protected void addPensButton()
		{
			JButton pensButton = new JButton("Access Pens");
			pensButton.setMaximumSize(pensButton.getPreferredSize());
			pensButton.setAlignmentX(Component.LEFT_ALIGNMENT);
			add(pensButton);
			pensButton.addActionListener(new
					ActionListener()
					{
						public void actionPerformed (ActionEvent event)
						{
							new PensFrame();
							getTopLevelAncestor().setVisible(false);
						}
					});
		}

		/** 
		 * Add a button to add an owner.
		 */
		protected void addAddOwnerButton()
		{
			JButton addOwnerButton = new JButton("Add Owner");
			addOwnerButton.setMaximumSize(addOwnerButton.getPreferredSize());
			add(addOwnerButton);
			addOwnerButton.setAlignmentX(Component.LEFT_ALIGNMENT);
			addOwnerButton.addActionListener(new
					ActionListener()
					{
						public void actionPerformed (ActionEvent event)
						{
							new AddOwnerFrame();
							getTopLevelAncestor().setVisible(false);
						}
					});
		}
		
		/** 
		 * Add a panel to access a specific owner.
		 */
		protected void addAccessOwnerPanel()
		{
			OwnerSelectionPanel selectOwnerPanel = new OwnerSelectionPanel();
			selectOwnerPanel.setMaximumSize(selectOwnerPanel.getPreferredSize());
			add(selectOwnerPanel);
			selectOwnerPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		}

		/** 
		 * Add a button to display the state of the system.
		 */
		protected void addStateButton()
		{
			JButton stateButton = new JButton("Show System State");
			stateButton.setMaximumSize(stateButton.getPreferredSize());
			add(stateButton);
			stateButton.setAlignmentX(Component.LEFT_ALIGNMENT);
			stateButton.addActionListener(new
					ActionListener()
					{
						public void actionPerformed (ActionEvent event)
						{
							SystemInfoCommand cmd = new SystemInfoCommand();
							cmd.accessSystemInfo();
							if (!cmd.wasSuccessful())
							{
								JOptionPane.showMessageDialog(null, cmd.getErrorMessage());
							}
							else
								JOptionPane.showMessageDialog(null, "The system is as follows: " 
								                                    + cmd.getSystemInfo());
						}
					});
		}

		/** 
		 * Add a button to quit the interactions with the current user.
		 */
		protected void addQuitButton()
		{
			JButton quitButton = new JButton("Quit");
			quitButton.setMaximumSize(quitButton.getPreferredSize());
			add(quitButton);
			quitButton.setAlignmentX(Component.LEFT_ALIGNMENT);
			quitButton.addActionListener(new
					ActionListener()
					{
						public void actionPerformed (ActionEvent event)
						{
							getTopLevelAncestor().setVisible(false);
							System.exit(0);
						}
					});
		}

		
		/**
		 * The panel to contain the components to initiate the frame to access
		 * the information for a specific owner.  The components include a 
		 * prompt to enter the name of the owner,  and a field for the entry of
		 * the name.
		 */
		private class OwnerSelectionPanel extends JPanel
		{
			/** Create the panel for the selection of the pet owner to handle. */
			private OwnerSelectionPanel()
			{
				add(new JLabel("Access owner:   name"));
				
				final JTextField nameField = new JTextField(15);
				// nameField must be final to be accessed by the anonymous ActionListener
				add(nameField);
				nameField.addActionListener(new
						ActionListener()
						{
							/** Access the name from the name field, and create a
							 *  window to display the information for the owner
							 *  and operations related to the owner.  If the creation
							 *  of the window fails, display an error message in a 
							 *  dialog box. */
							public void actionPerformed (ActionEvent event)
							{
								String name = nameField.getText();
								OwnerFrame ownerFrame = new OwnerFrame(name);
								if (ownerFrame.successfullyCreated)
								{
									getTopLevelAncestor().setVisible(false);
									ownerFrame.setVisible(true);
								}
								else
								{
									JOptionPane.showMessageDialog(getTopLevelAncestor(), 
									        "No owner with name " + name,
									        "Error", JOptionPane.ERROR_MESSAGE);
									return;
								}
							}
						});

			}
			
			public static final long serialVersionUID = 1;
		}
		
		public static final long serialVersionUID = 1;
	}

}
