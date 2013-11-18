package commands;

import containers.KennelAccess;
import entities.ExtendedKennel;
import entities.PetOwner;
import entities.Pet;
import entities.Dog;

/** The command to create a problem dog and add it to the kennel. */
public class CreateProblemDogCommand extends CommandStatus 
{
	/**
	 * Create and store a problem dog.
	 * @param dogName   the name of the problem dog
	 * @param ownerName the name of the owner
	 * @param problem   the descriptor of the problem with the dog
	 */
	public void createDog(String dogName, String ownerName, String problem)
	{
		try
		{
			ExtendedKennel kennel = KennelAccess.getKennel();
			PetOwner owner = kennel.getOwner(ownerName);
			// the dog should already belong to the owner
			Pet p = owner.getPet(dogName);
			kennel.addProblemDog((Dog) p, problem);
			successful = true;
		}  catch (Exception e)
		{
			successful = false;
			errorMessage = e.getMessage();
		}
	}
}
