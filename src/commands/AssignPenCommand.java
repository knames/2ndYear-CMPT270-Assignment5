package commands;

import containers.KennelAccess;
import entities.Kennel;
import entities.Pet;
import entities.PetOwner;

/** The command to assign a pet to a specific pen in the kennel. */
public class AssignPenCommand extends CommandStatus 
{
	public void assignPen(String ownerName, String petName, int penNumber)
	{
		try
		{
			Kennel kennel = KennelAccess.getKennel();
			PetOwner owner = kennel.getOwner(ownerName);
			Pet p = owner.getPet(petName);
			kennel.insert(p, penNumber);
			successful = true;
		}  catch (Exception e)
		{
			successful = false;
			errorMessage = e.getMessage();
		}
	}
}
