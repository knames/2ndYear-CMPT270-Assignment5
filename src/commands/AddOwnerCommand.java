package commands;

import containers.KennelAccess;
import entities.Kennel;
import entities.PetOwner;

/** The command to add an owner to the system. */
public class AddOwnerCommand extends CommandStatus 
{
	/** Add a new owner with the specified name and address. 
	 * @param name     the name for the new owner
	 * @param address  the address for the new owner  */
	public void addOwner(String name, String address)
	{
		try
		{
			Kennel kennel = KennelAccess.getKennel();
			PetOwner owner = new PetOwner(name, address);
			kennel.addOwner(owner);
			successful = true;
		}  catch (Exception e)
		{
			successful = false;
			errorMessage = e.getMessage();

		}
	}
}
