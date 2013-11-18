package commands;

import containers.KennelAccess;

/** The command to initialize the kennel to have a specified size. */
public class KennelInitializationCommand 
{
	/** 
	 * Initialize the kennel access so that the kennel accessed has the specified size.
	 * @param size   the size for the kennel
	 */
	public KennelInitializationCommand(int size)
	{
		KennelAccess.initialize(size);
	}
}
