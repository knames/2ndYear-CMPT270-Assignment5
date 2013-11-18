package containers;

import entities.ExtendedKennel;

/**	
 * A singleton class to access the kennel of the system. 
 */
public class KennelAccess 
{
	/** 
	 * Private constructor to ensure that no instance of this class is created. 
	 */
	private KennelAccess() {}

	/**	The kennel. */
	private static ExtendedKennel kennel;

	/** Initialize the kennel with the specified size.
	 * @precond kennel == null
	 * @param size   the number of pens for the kennel
	 */
	public static void initialize(int size)
	{
		if (kennel != null)
			throw new RuntimeException("initialize() must not be invoked twice.");
		kennel = new ExtendedKennel(size);
	}
	
	/**	
	 * Return the kennel.
	 * @precond kennel != null
	 * @return the kennel 
	 */ 
	public static ExtendedKennel getKennel()
	{
		if (kennel == null)
			throw new RuntimeException("initialize() must be invoked "
	                                   + "before any access of the kennel.");
		return kennel;
	}

}
