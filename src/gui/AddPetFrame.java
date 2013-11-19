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
import javax.swing.JComboBox;

import commands.AddDogCommand;
import commands.AddCatCommand;

/**
 * A window to add a pet for the owner whose name is passed in as a parameter
 * for the constructor.  The user is given the choice of adding a dog or a cat,
 * the information for the pet is entered by the user, and the pet is created 
 * and entered into the kennel.
 */
public class AddPetFrame extends JFrame 
{
	/** The name of the owner to have the new pet. */
	private String ownerName;
	
	/** The panel to give the user the choice of a dog or cat.  */
	private ChoicePanel choicePanel;
	
	/** Initialize the frame and set it visible. */
	public AddPetFrame(String ownerName)
	{
		this.ownerName = ownerName;
		setTitle("Add Pet");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(400, 150);
		setLocation(400, 400);
		choicePanel = new ChoicePanel();
		add(choicePanel);
		setVisible(true);
	}

	/** The panel to give the user the choice of which kind of pet to create. */
	private class ChoicePanel extends JPanel
	{
		public ChoicePanel()
		{
			final JComboBox<String> choiceBox = new JComboBox<String>();
			add(choiceBox);
			final String dogLabel = "          Dog          ";
			// spaces to make the combo box look better, not so narrow.
			final String catLabel = "          Cat          ";
			choiceBox.addItem(dogLabel);
			choiceBox.addItem(catLabel);
			choiceBox.addActionListener(new
				ActionListener()
				{
					public void actionPerformed (ActionEvent event)
					{
						/** Obtain the choice made, insert a panel into 
						 * the frame to read the data for the new pet, 
						 * and get the frame to redraw the window. */
						String label = (String) choiceBox.getSelectedItem();
						AddPetFrame frame = (AddPetFrame) getTopLevelAncestor();
						frame.remove(choicePanel);
						if (label.equals(dogLabel))
							frame.add(new DogPanel());
						else
							frame.add(new CatPanel());
						frame.validate();
						// set invisible and visible to get the focus in the new panel
						frame.setVisible(false);
						frame.setVisible(true);
					}
				});
		}
		
		public static final long serialVersionUID = 1;
	}

	/** The panel to enter the information for a new dog, and create the pet. */
	private class DogPanel extends JPanel
	{
		/** The field for the entry of the dog's name. */
		private JTextField nameField;
		
		/** The field for the entry of the dog's breed. */
		private JTextField breedField;
				
		/** Initialize the dog panel to receive the information for the new dog, 
		 *  and add the listener to create the dog when the submit button is clicked. */
		public DogPanel()
		{
			setLayout(new BorderLayout());
			JPanel namePanel = new JPanel();
			add(namePanel, BorderLayout.PAGE_START);
			namePanel.add(new JLabel("Name for the dog"));
			nameField = new JTextField(15);
			namePanel.add(nameField);

			JPanel breedPanel = new JPanel();
			add(breedPanel, BorderLayout.CENTER);
			breedPanel.add(new JLabel("Breed for the dog"));
			breedField = new JTextField(15);
			breedPanel.add(breedField);
			
			JPanel submitPanel = new JPanel();
			add(submitPanel, BorderLayout.PAGE_END);
			JButton submitButton = new JButton("Submit");
			submitPanel.add(submitButton);
			submitButton.addActionListener(new
				ActionListener()
				{
					public void actionPerformed (ActionEvent event)
					{
						AddDogCommand cmd = new AddDogCommand();
						cmd.addDog(nameField.getText(), ownerName, breedField.getText());
						if (!cmd.wasSuccessful())
						{
							JOptionPane.showMessageDialog(null, cmd.getErrorMessage());
						}
						else
						{
							getTopLevelAncestor().setVisible(false);
							new OwnerFrame(ownerName);
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
						new OwnerFrame(ownerName);
					}
				});
		}
		
		public static final long serialVersionUID = 1;
	}

	/** The panel to enter the information for a new cat, and create the pet. */
	private class CatPanel extends JPanel
	{
		/** The field for the entry of the cat's name. */
		private JTextField nameField;
		
		/** The field for the entry of the cat's colour. */
		private JTextField colourField;
				
		/** Initialize the cat panel to receive the information for the new cat, 
		 *  and add the listener to create the cat when the submit button is clicked. */
		public CatPanel()
		{
			setLayout(new BorderLayout());
			JPanel namePanel = new JPanel();
			add(namePanel, BorderLayout.PAGE_START);
			namePanel.add(new JLabel("Name for the cat"));
			nameField = new JTextField(15);
			namePanel.add(nameField);

			JPanel colourPanel = new JPanel();
			add(colourPanel, BorderLayout.CENTER);
			colourPanel.add(new JLabel("Colour for the cat"));
			colourField = new JTextField(15);
			colourPanel.add(colourField);
			
			JPanel submitPanel = new JPanel();
			add(submitPanel, BorderLayout.PAGE_END);
			JButton submitButton = new JButton("Submit");
			submitPanel.add(submitButton);
			submitButton.addActionListener(new
				ActionListener()
				{
					public void actionPerformed (ActionEvent event)
					{
						AddCatCommand cmd = new AddCatCommand();
						cmd.addCat(nameField.getText(), ownerName, colourField.getText());
						if (!cmd.wasSuccessful())
						{
							JOptionPane.showMessageDialog(null, cmd.getErrorMessage());
						}
						else
						{
							getTopLevelAncestor().setVisible(false);
							new OwnerFrame(ownerName);
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
						new OwnerFrame(ownerName);
					}
				});
		}
		
		public static final long serialVersionUID = 1;
	}

	public static final long serialVersionUID = 1;
}
