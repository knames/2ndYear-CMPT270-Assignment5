package entities;

import java.util.TreeMap;
import java.util.Iterator;
import java.util.Collection;

/**
 * A class to model a kennel with pens to house pets.  The kennel also has a container 
 * of the owners of the pets, and a container of dog who have caused problems.
 */
public class ExtendedKennel extends Kennel 
{
	/** A keyed-dictionary of the dogs who have caused problems for the kennel. */
	private TreeMap<String, ProblemDog> problemDogs;
	
	/** 
	 * Initialize the Kennel to the specified size.  
	 * @param size   the size, i.e., the number of pens, for the kennel 
	 * @precond size > 0  */
	public ExtendedKennel(int size)
	{
		super(size);
		problemDogs = new TreeMap<String, ProblemDog>();
	}
	
	/** 
	 * Insert the pet p into pen i, if it isn't a problem dog. 
	 * @param p  the pet to be inserted into a pen
	 * @param i  the number of the pen into which the pet is to be placed
	 * @precond  1 <= i <= pens.length && !hasOccupant(i) && p != null
	 *           && ! problemDogs.contains(p.getName())   */
	public void insert(Pet p, int i)
	{
		if (problemDogs.containsKey(p.getName()))
			throw new RuntimeException("Dog not entered because the dog has been a problem. " + p);
		super.insert(p,  i);
	}

	/**
	 * Create a problem dog and add it to the container of problem dogs.
	 * @param d        the dog who has the problem
	 * @param problem  the problem with the dog
	 */
	public void addProblemDog(Dog d, String problem)
	{
		ProblemDog pd = new ProblemDog(d, problem);
		String name = pd.getName();
		problemDogs.put(name, pd);
		// replace the Dog by the ProblemDog in owner's list
		PetOwner owner = (PetOwner) pd.getOwner();
		owner.removePet(name);
		owner.addPet(pd);
	}
	
	/** 
	 * Does the kennel have a problem dog with the specified name?
	 * @param name   the name of the dog to determine if it has been a problem
	 * @precond name != null
	 * @return Does the kennel have a problem dog with the specified name? */
	public boolean hasProblemDog(String name)
	{
		if (name == null)
			throw new RuntimeException("The dog being sought cannot have null name");
		return problemDogs.containsKey(name);
	}
	
	/** 
	 * Obtain the problem dog with a specific name. 
	 * @param name   the name of the dog to be obtained  
	 * @precond hasProblemDog(name)
	 * @return the problem dog with the specified name   */
	public ProblemDog getProblemDog(String name)
	{
		ProblemDog result = problemDogs.get(name);
		if (result == null)
			throw new RuntimeException("There is no problem dog with the name " + name
			                           + "\nThe dictionary contents is " + problemDogs);
		return result;
	}
	
	/**
	 * A collection of the problem dogs of the kennel.
	 * @return a collection of the problem dogs of the kennel.
	 */
	public Collection<ProblemDog> problemDogCollection()
	{
		return problemDogs.values();
	}
	
	/** 
	 * The string representation of the kennel.
	 * @return the string representation of the kennel. 
	 */
	public String toString()
	{
		String result = super.toString();
		result = result + "\nThe problem dogs are \n";
		Iterator<ProblemDog> iter = problemDogs.values().iterator();
		while (iter.hasNext())
			result = result + iter.next() + "\n";
		return result;
	}
	
}
