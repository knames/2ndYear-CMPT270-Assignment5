package commands;

import containers.KennelAccess;
import entities.Kennel;

/** The command to access and return the information about the pens in the system. */
public class PensInfoCommand extends CommandStatus 
{
	/** The information about the pens in the system. */
	private String pensInfo;
	
	/** Obtain the information about the pens and assign it to the pensInfo field. */
	public void accessPensInfo()
	{
		Kennel kennel = KennelAccess.getKennel();
		pensInfo = kennel.toStringOfBasicKennel();
		successful = true;
	}
	
	/** 
	 * @precond wasSuccessful()
	 * @return the information about the pens in the system
	 */
	public String getPensInfo()
	{
		if (!wasSuccessful())
			throw new RuntimeException("Cannot obtain the pen information unless the "
			                           + "pensInfo() method has been successfully executed.");

		return pensInfo;
	}
}
