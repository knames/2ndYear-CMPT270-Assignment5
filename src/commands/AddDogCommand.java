package commands;

import containers.KennelAccess;
import entities.Kennel;
import entities.PetOwner;
import entities.Dog;

/** The command to add a dog to the system. */
public class AddDogCommand extends CommandStatus 
{
	/** Add a new dog with the specified name, owner, and breed. 
	 * @param name       the name for the new dog
	 * @param ownerName  the name of the owner for the new dog
	 * @param breed      the breed for the new dog  */
	public void addDog(String name, String ownerName, String breed)
	{
		try
		{
			Kennel kennel = KennelAccess.getKennel();
			PetOwner owner = kennel.getOwner(ownerName);
			Dog d = new Dog(name, owner, breed);
			owner.addPet(d);
			successful = true;
		}  catch (Exception e)
		{
			successful = false;
			errorMessage = e.getMessage();

		}
	}
}
