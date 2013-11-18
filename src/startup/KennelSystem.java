package startup;

import commands.KennelInitializationCommand;
import commands.AddOwnerCommand;
import commands.AddDogCommand;
import commands.AddCatCommand;
import commands.PensInfoCommand;
import commands.AssignPenCommand;
import commands.DischargePetCommand;
import commands.SystemInfoCommand;
import userInterfaces.InputOutputInterface;
import userInterfaces.DialogIO;
import userInterfaces.ConsoleIO;

/** The class to run the kennel system application.  */
public class KennelSystem 
{ 
	/**
	 * The interface to be used to read input from the user and output results to the user.
	 */
	private InputOutputInterface ioInterface;
	
	/**
	 * Initialize the system by creating the kennel object.
	 */
	public void initialize()
	{
		ioInterface = new DialogIO();
		String option = ioInterface.readString("Should dialog boxes be used for I/O? (Y/N) ");
		if (option != null)
			if (option.charAt(0) == 'N' || option.charAt(0) == 'n')
				ioInterface = new ConsoleIO();
		int size = ioInterface.readInt("Enter the size for the kennel: ");
		new KennelInitializationCommand(size);
	}

	/**
	 * Run the kennel system: initialize, and then accept and carry out operations.
	 * Output the kennel contents when finishing.
	 */
	public void run()
	{
		initialize();
		String[ ] options = new String[] {"quit", "add a new owner", "add a new dog",
                "add new cat", "display the contents of the pens", "assign a pet a pen",
                "discharge a pet", "display current system state"};
		int opId = ioInterface.readChoice(options);
		while (opId != 0)
		{
			try
			{
				switch (opId)
				{
				case 1:
					addOwner();
					break;
				case 2:
					addDog();
					break;
				case 3:
					addCat();
					break;
				case 4:
					displayPens();
					break;
				case 5:
					assignPen();
					break;
				case 6:
					dischargePet();
					break;
				case 7:
					displaySystemInfo();;
					break;
				default:
					ioInterface.outputString("Invalid int value; try again\n");
				}
			} catch (RuntimeException e)
			{
				ioInterface.outputString(e.getMessage());
			}
			
			opId = ioInterface.readChoice(options);
		}
		
		displaySystemInfo();
		System.exit(0);
	}

	/**
	 * Read the information for a new owner and then add the owner
	 * to the dictionary of all owners for the kennel.
	 */
	public void addOwner()
	{
		String name = ioInterface.readString("Enter the name of the owner: ");
		String address = ioInterface.readString("Enter the address of the owner: ");
		AddOwnerCommand cmd = new AddOwnerCommand();
		cmd.addOwner(name, address);
		if (!cmd.wasSuccessful())
		{
			ioInterface.outputString(cmd.getErrorMessage());
		}
	}

	/**
	 * Read the information for a new dog and then add the dog
	 * to the list of pets for its owner.
	 */
	public void addDog()
	{
		String ownerName = ioInterface.readString("Enter the name of the owner for the dog: ");
		String name = ioInterface.readString("Enter the name of the dog: ");
		String breed = ioInterface.readString("Enter the breed of the dog: ");
		AddDogCommand cmd = new AddDogCommand();
		cmd.addDog(name, ownerName, breed);
		if (!cmd.wasSuccessful())
		{
			ioInterface.outputString(cmd.getErrorMessage());
		}
	}

	/**
	 * Read the information for a new cat and then add the cat
	 * to the list of pets for its owner.
	 */
	public void addCat()
	{
		String ownerName = ioInterface.readString("Enter the name of the owner for the cat: ");
		String name = ioInterface.readString("Enter the name of the cat: ");
		String colour = ioInterface.readString("Enter the colour of the cat: ");
		AddCatCommand cmd = new AddCatCommand();
		cmd.addCat(name, ownerName, colour);
		if (!cmd.wasSuccessful())
		{
			ioInterface.outputString(cmd.getErrorMessage());
		}
	}

	/**
	 * Display the pet in each of the pens of the kennel.
	 */
	public void displayPens()
	{
		PensInfoCommand cmd = new PensInfoCommand();
		cmd.accessPensInfo();
		if (!cmd.wasSuccessful())
		{
			ioInterface.outputString(cmd.getErrorMessage());
		}
		else
			ioInterface.outputString(cmd.getPensInfo());
	}

	/**
	 * Read the name of an owner, the name of a pet for the owner, 
	 * and the number for pen, and then assign the pet to the pen.
	 */
	public void assignPen()
	{
		String ownerName = ioInterface.readString("Enter the name of the owner: ");
		String petName = ioInterface.readString("Enter the name of the pet: ");
		int penNumber = ioInterface.readInt("Enter the number for the pen of the pet: ");
		AssignPenCommand cmd = new AssignPenCommand();
		cmd.assignPen(ownerName, petName, penNumber);
		if (!cmd.wasSuccessful())
		{
			ioInterface.outputString(cmd.getErrorMessage());
		}
	}

	/**
	 * Read the name of a pet, and discharge the pet from its pen.
	 */
	public void dischargePet()
	{
		String petName = ioInterface.readString("Enter the name of the pet: ");
		DischargePetCommand cmd = new DischargePetCommand();
		cmd.dischargePet(petName);
		if (!cmd.wasSuccessful())
		{
			ioInterface.outputString(cmd.getErrorMessage());
		}
	}

	/**
	 * Display the information for the system.
	 */
	public void displaySystemInfo()
	{
		SystemInfoCommand cmd = new SystemInfoCommand();
		cmd.accessSystemInfo();
		if (!cmd.wasSuccessful())
		{
			ioInterface.outputString(cmd.getErrorMessage());
		}
		else
			ioInterface.outputString("The system is as follows: " + cmd.getSystemInfo());
	}

	/** Start and run the kennel system.  */
	public static void main(String[] args) 
	{
		KennelSystem sys = new KennelSystem();
		sys.run();
	}
}
