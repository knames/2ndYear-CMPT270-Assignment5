package commands;

import containers.KennelAccess;
import entities.Kennel;

public class DischargePetCommand extends CommandStatus 
{
	public void dischargePet(String petName)
	{
		try
		{
			Kennel kennel = KennelAccess.getKennel();
			kennel.remove(petName);
			successful = true;
		}  catch (Exception e)
		{
			successful = false;
			errorMessage = e.getMessage();
		}
	}
}
