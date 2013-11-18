package entities;

import java.util.LinkedList;
import java.util.Iterator;
import java.util.ListIterator;

/** Model a person who has a list of pets.  */
public class PetOwner extends Person 
{
	/** The list of pets for the owner. */
	private LinkedList<Pet> petList;
	
	/** Initialize an instance with the given name and address. 
	 * @param name   the name of the pet owner
	 * @param address  the address of the pet owner  
	 * @precond name != null*/
	public PetOwner(String name, String address)
	{
		super(name, address);
		if (name == null)
			throw new RuntimeException("The name of the pet owner cannot be null");
		petList = new LinkedList<Pet>();
	}
	
	/** 
	 * Add a pet to the list of pets for the owner. 
	 * @param p   the pet to be added to the pet list of the pet owner  
	 * @precond p != null and ! hasPet(p.getName()) */
	public void addPet(Pet p)
	{
		if (p == null)
			throw new RuntimeException("The pet being added cannot be null");
		if (hasPet(p.getName()))
			throw new RuntimeException("The pet cannot already be in the list");
		petList.addLast(p);
	}
	
	/** 
	 * @param name  the name of the pet that the iterator is to be moved to
	 * @return  a ListIterator positioned so that the next item is the desired pet. */
	private ListIterator<Pet> accessPet(String name)
	{
		ListIterator<Pet> listIterator = petList.listIterator();
		boolean found = false;
		while (! found && listIterator.hasNext())
		{
			if (listIterator.next().getName().equals(name))
				found = true;
		}
		if (found)
			// Move back so that next() is the desired pet.
			listIterator.previous();
		return listIterator;
	}
	
	/** 
	 * @param name  the name of pet to be tested for belonging to the owner
	 * @return Does the owner have a pet with the specified name? */
	public boolean hasPet(String name)
	{
		ListIterator<Pet> listIterator = accessPet(name);
		if (listIterator.hasNext())
			return true;
		else 
			return false;
	}
	
	/** 
	 * @param name   the name of the pet to be returned
	 * @precond hasPet(name)
	 * @return the pet with the specified name. */
	public Pet getPet(String name)
	{
		ListIterator<Pet> listIterator = accessPet(name);
		if (listIterator.hasNext())
			return listIterator.next();
		else 
			throw new RuntimeException("There is no pet with name " + name);
	}
	
	/** Remove the pet with the specified name from the pet list. 
	 * @param name   the name of the pet to be removed
	 * @precond hasPet(name)   */
	public void removePet(String name)
	{
		ListIterator<Pet> listIterator = accessPet(name);
		if (listIterator.hasNext())
		{
			listIterator.next();
			listIterator.remove();
		}
		else 
			throw new RuntimeException("There is no pet to remove with name " + name);
	}
	
	/** @return a string representation of the pet owner. */
	public String toString()
	{
		String result = super.toString() + "     with pets: ";
		Iterator<Pet> iter = petList.iterator();
		while (iter.hasNext())
		{
			result = result + " " + iter.next().getName();
		}
		return result;
	}
	
	/** 
	 * @return an array with the pets of this owner
	 */
	public Pet[] petArray()
	{
		return petList.toArray(new Pet[0]); // dummy array parameter to have the correct
		                                    // type returned by the toArray method
	}

	/** The routine to test the class.  */
	public static void main(String[] args) 
	{
		int numErrors = 0;
		PetOwner owner = new PetOwner("Pete", "Sutherland");
		Dog dog = new Dog("Ebony", owner, "black lab");
		owner.addPet(dog);
		if (!owner.hasPet("Ebony"))
		{
			numErrors = numErrors + 1;
			System.out.println("Unable to find the dog that whose entry was attempted.");
		}
		if (owner.getPet("Ebony") != dog)
		{
			numErrors = numErrors + 1;
			System.out.println("Unable to obtain the dog that was entered.");
		}
		Cat cat = new Cat("Tabby", owner, "orange");
		owner.addPet(cat);
		if (!owner.hasPet("Tabby"))
		{
			numErrors = numErrors + 1;
			System.out.println("Unable to find the cat that whose entry was attempted.");
		}
		if (owner.getPet("Tabby") != cat)
		{
			numErrors = numErrors + 1;
			System.out.println("Unable to obtain the cat that was entered.");
		}
		System.out.println("Pete from Sutherland should have Ebony and Tabby as pets. \n"
		                   + owner);
		Pet[] pets = owner.petArray();
		if (pets.length != 2)
		{
			numErrors = numErrors + 1;
			System.out.println("The petArray has size " + pets.length + 
			                   " when it should have size 2.");
		}
		if (!pets[0].getName().equals("Ebony"))
		{
			numErrors = numErrors + 1;
			System.out.println("The first pet is " + pets[0].getName() +
			                   " when it should be Ebony.");
		}
		if (!pets[1].getName().equals("Tabby"))
		{
			numErrors = numErrors + 1;
			System.out.println("The seond pet is " + pets[1].getName() +
			                   " when it should be Tabby.");
		}
	
		owner.removePet("Ebony");  
		owner.removePet("Tabby");  
		pets = owner.petArray();
		if (pets.length != 0)
		{
			numErrors = numErrors + 1;
			System.out.println("The petArray has size " + pets.length + 
			                   " when it should have size 0.");
		}
		System.out.println("\nPete from Sutherland should have no pets. \n" + owner);
		System.out.println("\nThe number of errors found is " + numErrors);
	}
}
