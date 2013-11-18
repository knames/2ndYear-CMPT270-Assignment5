package entities;
/**
 * A cat with a name, an owner, and a colour.
 */
public class Cat extends Pet 
{
	/** The colour of the cat. */
	private String colour;

	/** 
	 * Initialize an instance with the given name, owner and colour. 
	 * @param name    the name of the cat
	 * @param owner   the owner of the cat
	 * @param colour  the colour of the cat 
	 * @precond name != null
	 * @precond owner != null */
	public Cat(String name, Person owner, String colour)
	{
		super(name, owner);
		if (name == null)
			throw new RuntimeException("The name of the cat cannot be null");
		if (owner == null)
			throw new RuntimeException("The owner of the cat cannot be null");
		this.colour = colour;
	}
	
	/** @return the colour of the cat. */
	public String getColour()
	{
		return colour;
	}
	
	/** @return the string representation of the cat */
	public String toString()
	{
		return super.toString() + "   Colour: " + colour;
	}
	
	/** A method to test the cat class. */
	public static void main(String[] args) 
	{
		int numErrors = 0;
		Person p = new Person("Mary", "Nutana");
		Cat cat = new Cat("Tabby", p, "orange");
		System.out.println("The orange cat called Tabby with owner Mary is " + cat);
		if (! cat.getName().equals("Tabby"))
		{
			System.out.println("The constructor or getName failed for Tabby"
                               + "\nThe name obtained was " + cat.getName());
			numErrors++;
		}
		if (!cat.getOwner().getName().equals("Mary"))
		{
			System.out.println("The constructor or getOwner failed for owner Mary"
                               + "\nThe owner's name obtained was " + cat.getOwner().getName());
			numErrors++;
		}
		if (! cat.getColour().equals("orange"))
		{
			System.out.println("The constructor or getColour failed for Tabby");
			numErrors++;
		}
		System.out.println("\nThe number of errors found is " + numErrors);
	}

}
