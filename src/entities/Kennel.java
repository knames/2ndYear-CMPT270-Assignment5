package entities;
import java.util.TreeMap;
import java.util.Iterator;

public class Kennel extends BasicKennel 
{
	/** A keyed-dictionary of the owners of pets who have stayed in the kennel. */
	private TreeMap<String, PetOwner> owners;
	
	/** 
	 * Initialize the Kennel to the specified size.  
	 * @param size   the size, i.e., the number of pens, for the kennel 
	 * @precond size > 0  */
	public Kennel(int size)
	{
		super(size);
		if (size < 1)
			throw new RuntimeException("The number of pens cannot be less than 1. "
			                           + "\nThe number given is " + size);
		owners = new TreeMap<String, PetOwner>();
	}
	
	/** 
	 * @param name   the name of the owner to determine membership in the kennel
	 * @precond name != null
	 * @return Does the kennel have an owner with the specified name. */
	public boolean hasOwner(String name)
	{
		if (name == null)
			throw new RuntimeException("The owner being sought cannot have null name");
		return owners.containsKey(name);
	}
	
	/** 
	 * Add an owner to the dictionary of owners. 
	 * @param owner   the owner to be added to the owners dictionary  
	 * @precond owner != null  &&  ! hasOwner(owner.getName())  */
	public void addOwner(PetOwner owner)
	{
		if (owner == null)
			throw new RuntimeException("The owner to be added cannot be null");
		PetOwner prevOwner = owners.put(owner.getName(), owner);
		if (prevOwner != null)
		{
			// return the previous owner back to the dictionary
			owners.put(prevOwner.getName(), prevOwner);
			throw new RuntimeException("There is already an owner by the name " + owner.getName()
			                           + "\nThe existing owner is " + prevOwner);
		}
	}
	
	/** 
	 * Obtain the owner with a specific name. 
	 * @param name   the name of the owner to be obtained  
	 * @precond name != null && hasOwner(name)
	 * @return the owner with the specified name that is in the kennel dictionary  */
	public PetOwner getOwner(String name)
	{
		if (name == null)
			throw new RuntimeException("The owner being sought cannot have null name");
		PetOwner result = owners.get(name);
		if (result == null)
			throw new RuntimeException("There is no owner with the name " + name
			                           + "\nThe dictionary contents is " + owners);
		return result;
	}
	
	/** @return the string representation of the kennel. */
	public String toString()
	{
		String result = super.toString();
		result = result + "\nThe pet owners are \n";
		Iterator<PetOwner> iter = owners.values().iterator();
		while (iter.hasNext())
			result = result + iter.next() + "\n";
		return result;
	}
	
	/** @return the string representation of the BasicKennel aspects of the kennel,
	 *  i.e., the pet in each pen. */
	public String toStringOfBasicKennel()
	{
		return super.toString();
	}
	
	/** The routine to test the class. */
	public static void main(String[] args)
	{
		int numErrors = 0;
		PetOwner owner = new PetOwner("Pete", "Sutherland");
		Kennel kennel = new Kennel(5);
		kennel.addOwner(owner);
		if (! kennel.hasOwner("Pete"))
		{
			numErrors = numErrors + 1;
			System.out.println("Pete cannot be found after insertion.");
		}
		PetOwner retrievedOwner = kennel.getOwner("Pete");
		if (retrievedOwner != owner)
		{
			numErrors = numErrors + 1;
			System.out.println("Unable to retrieve Pete after insertion.");
		}
		owner = new PetOwner("Mary", "Nutana");
		kennel.addOwner(owner);
		retrievedOwner = kennel.getOwner("Mary");
		if (retrievedOwner != owner)
		{
			numErrors = numErrors + 1;
			System.out.println("Unable to retrieve Mary after insertion.");
		}
		System.out.println("The kennel with owners Pete and Mary, and no pets is " + kennel);
		System.out.println("\nThe number of errors found is " + numErrors);
	}
}
