package entities;
/**
 * The model of a person who has a name and an address.
 */
public class Person 
{
	/** The name of the person. */
	private String name;
	
	/** The address of the person. */
	private String address;
	
	/** 
	 * Initialize an instance with the given name and address. 
	 * @param name   the name of the person
	 * @param address  the address of the person  
	 * @precond name != null  */
	public Person(String name, String address)
	{
		if (name == null)
			throw new RuntimeException("The name of the person cannot be null");
		this.name = name;
		this.address = address;
	}
	
	/** @return the name of the person */
	public String getName()
	{
		return name;
	}
	
	/** @return the address of the person */
	public String getAddress()
	{
		return address;
	}
	
	/** 
	 * Change the address of the person. 
	 * @param  newAddress   the new address for the person  */
	public void setAddress(String newAddress)
	{
		address = newAddress;
	}
	
	/** @return a string representation of the person */
	public String toString()
	{
		return "Name: " + name + "     Address: " + address;
	}
	
	/** A method to test the Person class. */
	public static void main(String[] args)
	{
		int numErrors = 0;
		Person p = new Person("Pete", "Sutherland");
		System.out.println("The person called Pete in Sutherland is " + p);
		if (! p.getName().equals("Pete"))
		{
			System.out.println("The constructor or getName failed for Pete"
			                   + "The name returned was " + p.getName());
			numErrors++;
		}
		if (!p.getAddress().equals("Sutherland"))
		{
			System.out.println("The constructor or getAddress failed for Sutherland"
	                           + "The address returned was " + p.getAddress());
			numErrors++;
		}
		p.setAddress("Greystone Heights");
		if (! p.getAddress().equals("Greystone Heights"))
		{
			System.out.println("setAddress failed for Greystone Heights"
                               + "The address returned was " + p.getAddress());
			numErrors++;
		}
		
		p = new Person("Mary", "Nutana");
		System.out.println("The person called Mary in Nutana is " + p);
		if (! p.getName().equals("Mary"))
		{
			System.out.println("The constructor or getName failed for Mary"
	                           + "The name returned was " + p.getName());
			numErrors++;
		}
		if (! p.getAddress().equals("Nutana"))
		{
			System.out.println("The constructor or getAddress failed for Nutana"
                               + "The address returned was " + p.getAddress());
			numErrors++;
		}
		p.setAddress("Varsity View");
		if (! p.getAddress().equals("Varsity View"))
		{
			System.out.println("setAddress failed for Varsity View"
                               + "The address returned was " + p.getAddress());	
			numErrors++;
		}
		System.out.println("\nThe number of errors found is " + numErrors);
	}
}
