package commands;

import containers.KennelAccess;
import entities.ExtendedKennel;
import entities.ProblemDog;

/** The command to change the problem associated with a problem dog. */
public class ChangeProblemDogCommand extends CommandStatus 
{
	/**
	 * Change the problem associated with a problem dog.
	 * @param name          the name of the dog with the new problem
	 * @param newProblem    the new description of the problem for the dog
	 */
	public void changeProblem(String name, String newProblem)
	{
		try
		{
			ExtendedKennel kennel = KennelAccess.getKennel();
			ProblemDog d = kennel.getProblemDog(name);
			d.setProblem(newProblem);
			successful = true;
		}  catch (Exception e)
		{
			successful = false;
			errorMessage = e.getMessage();
		}
	}
}
