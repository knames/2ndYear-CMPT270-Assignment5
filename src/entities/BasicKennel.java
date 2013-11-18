package entities;
/**
 * Basic kennel to house pets in pens.
 */
public class BasicKennel 
{
	/** The pens to hold the pets. */
	private Pet[] pens;
	
	/** 
	 * Initialize an instance to hold size pets. 
	 * @param size   the size, i.e., the number of pens, for the kennel 
	 * @precond size > 0  */	
	public BasicKennel(int size)
	{
		if (size < 1)
			throw new RuntimeException("The number of pens cannot be less than 1. "
			                           + "\nThe number given is " + size);
		pens = new Pet[size];
	}
	
	/** 
	 * Insert the pet p into pen i. 
	 * @param p  the pet to be inserted into a pen
	 * @param i  the number of the pen into which the pet is to be placed
	 * @precond  1 <= i <= pens.length && !hasOccupant(i) && p != null   */
	public void insert(Pet p, int i)
	{
		if (p == null)
			throw new RuntimeException("The pet to be inserted cannot be null");
		if (i < 1 || i > pens.length)
			throw new RuntimeException("There is no pen with label " + i);
		/* The pens are numbered 1 to n, so place the pet in pen i-1. */
		if (pens[i-1] != null)
			throw new RuntimeException("Pen " + i + " is already occupied");
		pens[i-1] = p;
	}
	
	/** 
	 * Test whether the kennel has a pet with the specified name.
	 * @param name  the name of the pet to be tested for being in a pen  
	 * @return  Does the kennel have a pet with the name specified? */
	public boolean hasPet(String name)
	{
		boolean found = false;
		for (int i = 0; ! found && i < pens.length; i++)
			if (pens[i] != null && pens[i].getName().equals(name))
				found = true;
		return found;
	}
	
	/**
	 * Return the pen number of the pen that contains the pet with the specified name. 
	 * @param name  the name of the pet for which the pen number is to be found 
	 * @precond hasPet(name)
	 * @return the number of the pen containing the pet p */
	public int penNumberOf(String name)
	{
		boolean found = false;
		int arrayPosition = -1;
		for (int i = 0; ! found && i < pens.length; i++)
			if (pens[i] != null && pens[i].getName().equals(name))
			{
				found = true;
				arrayPosition = i;
			}
		if (arrayPosition == -1)
			throw new RuntimeException("The pet with name " + name 
						+ " is not in the kennel, so cannot return its pen number.");
		return arrayPosition + 1;  // pens numbered 1 through pen.length
	}
	
	/** 
	 * Remove from the kennel the pet with the specified name. 
	 * @param name  the name of the pet to be removed
	 * @precond hasPet(name)  */
	public void remove(String name)
	{
		if (! hasPet(name))
			throw new RuntimeException("The pet with name " + name 
					+ " is not in the kennel, so cannot be removed.");
		pens[penNumberOf(name)-1] = null;   // pens numbered 1 through pen.length
	}
	
	/** @return a string representation of the kennel. */
	public String toString()
	{
		String result = "\nThe pet in each pen of the kennel is ";
		for (int i = 0; i < pens.length; i++)
		{
			// pens numbered 1 through pen.length
			result = result + "\n" + (i+1) + ": ";
			if (pens[i] != null)
				result = result + pens[i];  
		}
		result = result + "\n";
		return result;
	}
	
	/** @return the size, i.e., number of pens, in the kennel */
	public int size()
	{
		return pens.length;
	}
	
	/** 
	 * @param i   the number for the pen to be tested for an occupant
	 * @ return Does pen i have a pet occupying it? */
	public boolean hasOccupant(int i)
	{
		if (pens[i-1] != null)  // pen i occupant is stored in location i-1 of the array
			return true;
		else
			return false;
	}
	
	/** 
	 * @param i  the number for the pen whose occupant to be found
	 * @precond hasOccupant(i)
	 * @return the pet in pen i. */
	public Pet occupantOfPen(int i)
	{
		if (! hasOccupant(i))
			throw new RuntimeException("Pen " + i + " is not occupied,"
			                           + " so cannot return occupant.");
		return pens[i-1];      // pen i occupant is stored in location i-1 of the array
	}
	
	/** A method to test the Pet class.  */
	public static void main(String[] args)
	{
		BasicKennel kennel = new BasicKennel(5);
		int numErrors = 0;
		Person p = new Person("Pete", "Sutherland");
		Pet pet = new Pet("Ebony", p);
		kennel.insert(pet, 2);
		if (! kennel.hasPet("Ebony"))
		{
			System.out.println("The insert or hasPet failed for Ebony");
			numErrors++;
		}
		if (kennel.hasPet("Tabby"))
		{
			System.out.println("The hasPet failed as Tabby shouldn't be present");
			numErrors++;
		}
		if (kennel.penNumberOf("Ebony") != 2)
		{
			System.out.println("The insert or penNumberOf failed for Ebony"
					           + "\nEbony is in pen " + kennel.penNumberOf("Ebony"));
			numErrors++;
		}
		
		p = new Person("Mary", "Nutana");
		pet = new Pet("Tabby", p);
		kennel.insert(pet, 5);
		if (! kennel.hasPet("Tabby"))
		{
			System.out.println("The insert or hasPet failed for Tabby");
			numErrors++;
		}
		if (kennel.hasPet("Ginger"))
		{
			System.out.println("The hasPet failed as Ginger shouldn't be present");
			numErrors++;
		}
		if (kennel.penNumberOf("Tabby") != 5)
		{
			System.out.println("The insert or penNumberOf failed for Tabby"
			                   + "\nTabby is in pen " + kennel.penNumberOf("Tabby"));
			numErrors++;
		}
		
		System.out.println("The kennel with Ebony (owner Pete) in pen 2 and "
				            + " Tabby (owner Mary) in pen 5 is " + kennel);

		kennel.remove("Tabby");
		if (kennel.hasPet("Tabby"))
		{
			System.out.println("The remove failed to remove Tabby");
			numErrors++;
		}
		kennel.remove("Ebony");
		if (kennel.hasPet("Ebony"))
		{
			System.out.println("The remove failed to remove Ebony");
			numErrors++;
		}
		
		System.out.println("\nThe empty kennel is " + kennel);
		System.out.println("\nThe number of errors found is " + numErrors);
		
		System.out.println("\nThe test Pet class.");
		Pet.main(null);
		System.out.println("\nThe test Person class.");
		Person.main(null);
	}
}
