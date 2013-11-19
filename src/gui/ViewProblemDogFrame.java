package gui;

import gui.AddProblemDogFrame.ProblemDogPanel;

import javax.swing.JFrame;
import javax.swing.JPanel;

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
	
	private class ViewProblemDogPanel extends JPanel
	{
		
	}

	public static final long serialVersionUID = 1;
}

