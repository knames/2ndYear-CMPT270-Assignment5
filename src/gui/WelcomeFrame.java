package gui;

import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import commands.KennelInitializationCommand;

/**
 * The window to welcome a user to the kennel system, and prompt the user 
 * to enter the size (number of pens) of the kennel.
 */
public class WelcomeFrame extends JFrame 
{
	/** Initialize the frame and add a SizePanel to it.  */
	public WelcomeFrame()
	{
		setTitle("Kennel System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 125);
		setLocation(400, 200);
		add(new WelcomePanel());
		setVisible(true);
	}
	
	/** A panel with a welcome message, and a field to enter the size of the kennel.  
	 *  Entering a size will create the kennel, and open a window for the user
	 *  to enter various operations. */
	private class WelcomePanel extends JPanel
	{
		/** The text field for the size of the kennel. */
		private JTextField sizeField;
		
		/** The label to display the prompt for the kennel size, and to display
		 * an error message if an improper size is entered. */
		private JLabel promptLabel;
		
		/** Initialize the panel, and set up the listener for the entry of
		 *  integer into the size field.  */
		public WelcomePanel()
		{
			setLayout(new BorderLayout());
			JPanel salutationPanel = new JPanel();
			salutationPanel.add(new JLabel("Welcome to the Kennel System"));
			add(salutationPanel, BorderLayout.PAGE_START);
			JPanel sizepanel = new JPanel();
			promptLabel = new JLabel("Please enter the number of pens");
			sizepanel.add(promptLabel);
			sizeField = new JTextField(8);
			sizepanel.add(sizeField);
			sizeField.addActionListener(new SizeListener());
			add(sizepanel, BorderLayout.PAGE_END);
		}
		
		/** The listener for an entry of an integer into the size field.
		 *  When such an entry occurs, initialize the kennel, and open
		 *  a window for the user to select the next operation to do. */
		private class SizeListener implements ActionListener
		{
			/** If a valid integer is entered, initialize the kennel and
			 *  open a window for the user to select the next operation to do. 
			 *  If an invalid integer is entered, catch the exception and 
			 *  issue an error message in the prompt label.  */
			public void actionPerformed(ActionEvent e)
			{
				String textRead = sizeField.getText();
				Scanner inputScanner = new Scanner(textRead);
				try
				{
					int size = inputScanner.nextInt();
					inputScanner.close();
					new KennelInitializationCommand(size);
					new OperationsFrame();
					getTopLevelAncestor().setVisible(false);
				} catch (RuntimeException ex)
				{
					promptLabel.setText("***  Invalid int: enter size again");
					inputScanner.close();
				}
			}
		}
		
		public static final long serialVersionUID = 1;
	}

	public static final long serialVersionUID = 1;
}
