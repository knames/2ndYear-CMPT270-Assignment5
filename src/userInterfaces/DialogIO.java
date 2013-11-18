package userInterfaces;

import javax.swing.JOptionPane;

/**
 * A class with the input and output methods to read a String, read an int, 
 * output a message, and display a list of choices from which the user must
 * select the index of a choice.  The input and output is done via dialog boxes.
 */
public class DialogIO extends AbstractDialogIO 
{

	/**
	 * Display a prompt and read the string entered.
	 * @param prompt the string to be displayed as a prompt
	 * @return  the next line of input from the console
	 */
	public String readString(String prompt)
	{
		return JOptionPane.showInputDialog(null, prompt);
	}
	
	/**
	 * Display a prompt, and read and return the int entered.
	 * @param prompt the string to be displayed as a prompt
	 * @return  the int read
	 */
	public int readInt(String prompt)
	{
		String valueAsString = JOptionPane.showInputDialog(null, prompt);
		int value;
		if (valueAsString != null && valueAsString.length() > 0) 
		{
			try
			{
				value = Integer.parseInt(valueAsString);
			} catch (NumberFormatException e) 
			{
				outputString("You entered " + valueAsString + " that is not an an int." 
                             + "\nPlease try again: \n");
				value = readInt(prompt);
			}
		}
		else
		{
			outputString("You must enter a value into the input field. \n");
			value = readInt(prompt);
		}
		return value;
	}
	
	/**
	 * Output the String parameter.
	 * @param outString  the string whose value is to be displayed
	 */
	public void outputString(String outString) 
	{
		JOptionPane.showMessageDialog(null, outString);
	}

	/** 
	 * Simple tests of the methods.
	 * @param args  not used
	 */
	public static void main(String[] args)
	{
		InputOutputInterface ioHandler = new DialogIO();
		ioHandler.outputString("This will test the input and output methods.\n");
		String result = ioHandler.readString("Enter a string: ");
		ioHandler.outputString("The string read was " + result + "\n");
		int i = ioHandler.readInt("Enter an integer value: ");
		ioHandler.outputString("The integer read was " + i + "\n");
		String[] options = {"First", "Second", "Third"};
		int intOption = ioHandler.readChoice(options);
		ioHandler.outputString("The option selected was " + options[intOption] + "\n");
	}
}
