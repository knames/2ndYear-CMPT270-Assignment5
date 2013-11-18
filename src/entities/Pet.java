package entities;
/**
 * A pet with a name and an owner.
 */
public class Pet 
{
	/** The name of the pet. */
	private String name;
	
	/** The owner of the pet. */
	private Person owner;
	
	/** 
	 * Initialize an instance with the given name and owner.
	 * @param name  the name of the pet
	 * @param owner the owner of the pet  
	 * @precond name != null
	 * @precond owner != null */
	public Pet(String name, Person owner)
	{
		if (name == null)
			throw new RuntimeException("The name of the pet cannot be null");
		if (owner == null)
			throw new RuntimeException("The owner of the pet cannot be null");
		this.name = name;
		this.owner = owner;
	}
	
	/** @return the name of the pet */
	public String getName()
	{
		return name;
	}
	
	/** @return the owner of the pet */
	public Person getOwner()
	{
		return owner;
	}
	
	/** @return a string representation of the pet */
	public String toString()
	{
		return "Name: " + name + "     Owner: " + owner.getName();
	}
	
	/**
	 * A method to test the Pet class.
	 */
	public static void main(String[] args)
	{
		int numErrors = 0;
		Person p = new Person("Pete", "Sutherland");
		Pet pet = new Pet("Ebony", p);
		System.out.println("The pet called Ebony with owner Pete is " + pet);
		if (! pet.getName().equals("Ebony"))
		{
			System.out.println("The constructor or getName failed for Ebony"
	                           + "\nThe name obtained was " + pet.getName());
			numErrors++;
		}
		if (!pet.getOwner().getName().equals("Pete"))
		{
			System.out.println("The constructor or getOwner failed for owner Pete"
	                           + "\nThe owner's name obtained was " + pet.getOwner().getName());
			numErrors++;
		}
		
		p = new Person("Mary", "Nutana");
		pet = new Pet("Tabby", p);
		System.out.println("The pet called Tabby with owner Mary is " + pet);
		if (! pet.getName().equals("Tabby"))
		{
			System.out.println("The constructor or getName failed for Tabby"
	                           + "\nThe name obtained was " + pet.getName());
			numErrors++;
		}
		if (!pet.getOwner().getName().equals("Mary"))
		{
			System.out.println("The constructor or getOwner failed for owner Mary"
	                           + "\nThe owner's name obtained was " + pet.getOwner().getName());
			numErrors++;
		}
		System.out.println("\nThe number of errors found is " + numErrors);
	}
}
