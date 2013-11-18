package entities;
/**
 * A dog with a name, an owner, and a breed.
 */
public class Dog extends Pet 
{
	/** The breed of the dog. */
	private String breed;

	/** Initialize an instance with the given name, owner and breed. 
	 * @param name   the name of the dog
	 * @param owner  the owner of the dog  
	 * @param breed  the breed of the dog
	 * @precond name != null
	 * @precond owner != null*/
	public Dog(String name, Person owner, String breed)
	{
		super(name, owner);
		if (name == null)
			throw new RuntimeException("The name of the dog cannot be null");
		if (owner == null)
			throw new RuntimeException("The owner of the dog cannot be null");
		this.breed = breed;
	}
	
	/** @return the breed of the dog. */
	public String getBreed()
	{
		return breed;
	}
	
	/** @return the string representation of the dog */
	public String toString()
	{
		return super.toString() + "   Breed: " + breed;
	}
	
	/**
	 * A method to test the Dog class.
	 */
	public static void main(String[] args) 
	{
		int numErrors = 0;
		Person p = new Person("Pete", "Sutherland");
		Dog dog = new Dog("Ebony", p, "black lab");
		System.out.println("The black lab called Ebony with owner Pete is " + dog);
		if (! dog.getName().equals("Ebony"))
		{
			System.out.println("The constructor or getName failed for Ebony"
                               + "\nThe name obtained was " + dog.getName());
			numErrors++;
		}
		if (!dog.getOwner().getName().equals("Pete"))
		{
			System.out.println("The constructor or getOwner failed for owner Pete"
                               + "\nThe owner's name obtained was " + dog.getOwner().getName());
			numErrors++;
		}
		if (! dog.getBreed().equals("black lab"))
		{
			System.out.println("The constructor or getBreed failed for Ebony");
			numErrors++;
		}
		System.out.println("\nThe number of errors found is " + numErrors);
	}

}
