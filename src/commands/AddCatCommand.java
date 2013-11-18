package commands;

import containers.KennelAccess;
import entities.Kennel;
import entities.PetOwner;
import entities.Cat;

/** The command to add a cat to the system. */
public class AddCatCommand extends CommandStatus 
{
	/** Add a new cat with the specified name, owner, and colour. 
	 * @param name       the name for the new cat
	 * @param ownerName  the name of the owner for the new cat
	 * @param colour     the colour for the new cat  */
	public void addCat(String name, String ownerName, String colour)
	{
		try
		{
			Kennel kennel = KennelAccess.getKennel();
			PetOwner owner = kennel.getOwner(ownerName);
			Cat c = new Cat(name, owner, colour);
			owner.addPet(c);
			successful = true;
		}  catch (Exception e)
		{
			successful = false;
			errorMessage = e.getMessage();

		}
	}

}
