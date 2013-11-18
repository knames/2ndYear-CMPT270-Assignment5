package junitTests;

import org.junit.*;

import commands.KennelInitializationCommand;
import commands.PensInfoCommand;
import commands.SystemInfoCommand;
import commands.AddOwnerCommand;
import commands.AddDogCommand;
import commands.AddCatCommand;
import commands.AssignPenCommand;
import commands.DischargePetCommand;
import commands.CreateProblemDogCommand;
import commands.ChangeProblemDogCommand;
import containers.KennelAccess;
import entities.Pet;
import entities.Person;
import entities.BasicKennel;
import entities.Dog;
import entities.Cat;
import entities.PetOwner;
import entities.Kennel;
import entities.ProblemDog;
import entities.ExtendedKennel;

/**
 * A class to test the commands of the bank application uses JUnit tests.
 */
public class CommandsTest 
{
	/**
	 * Initialize the system and check the setup.
	 */
	@BeforeClass
	public static void setUp()
	{
		System.out.println("****Test 1");
		// Initialize the kennel.
		new KennelInitializationCommand(5);
		Kennel kennel = KennelAccess.getKennel();
		Assert.assertTrue(kennel != null);
		Assert.assertTrue(kennel.size() == 5);
		PensInfoCommand cmdPensInfo = new PensInfoCommand();
		cmdPensInfo.accessPensInfo();
		String pens = cmdPensInfo.getPensInfo();
		Assert.assertTrue(pens != null);
		SystemInfoCommand cmdSystemInfo = new SystemInfoCommand();
		cmdSystemInfo.accessSystemInfo();
		String system = cmdSystemInfo.getSystemInfo();
		Assert.assertTrue(system != null);
	}

	@Test
	public void testEntities()
	{
		System.out.println("****Test 2");
		System.out.println("Test Pet class");
		Pet.main(null);
		System.out.println("\nTest BasicKennel class");
		BasicKennel.main(null);
		System.out.println("\nTest Person class");
		Person.main(null);
		System.out.println("\nTest Dog class");
		Dog.main(null);
		System.out.println("\nTest Cat class");
		Cat.main(null);
		System.out.println("\nTest PetOwner class");
		PetOwner.main(null);
		System.out.println("\nTest Kennel class");
		Kennel.main(null);
		System.out.println();
	}

	/**
	 * Test the AddOwnerCommand class.
	 */
	@Test 
	public void testAddOwnerCommand ()
	{
		System.out.println("****Test 3");
		AddOwnerCommand cmd = new AddOwnerCommand();
		cmd.addOwner("Fred", "Sutherland");
		Assert.assertTrue(cmd.wasSuccessful());
		Kennel kennel = KennelAccess.getKennel();
		PetOwner owner = kennel.getOwner("Fred");
		Assert.assertTrue(owner != null);
		Assert.assertTrue(owner.getName().equals("Fred"));
		Assert.assertTrue(owner.getAddress().equals("Sutherland"));
	}

	/**
	 * Test the AddDogCommand class.
	 */
	@Test 
	public void testAddDogCommand ()
	{
		System.out.println("****Test 4");
		AddOwnerCommand ownerCmd = new AddOwnerCommand();
		ownerCmd.addOwner("Mary", "Nutana");
		AddDogCommand dogCmd = new AddDogCommand();
		dogCmd.addDog("Ebony", "Mary", "Black Lab");
		Assert.assertTrue(dogCmd.wasSuccessful());
		Kennel kennel = KennelAccess.getKennel();
		PetOwner owner = kennel.getOwner("Mary");
		Dog d = (Dog) owner.getPet("Ebony");
		Assert.assertTrue(d != null);
		Assert.assertTrue(d.getName().equals("Ebony"));
		Assert.assertTrue(d.getBreed().equals("Black Lab"));
	}

	/**
	 * Test the AddCatCommand class.
	 */
	@Test 
	public void testAddCatCommand ()
	{
		System.out.println("****Test 5");
		AddOwnerCommand ownerCmd = new AddOwnerCommand();
		ownerCmd.addOwner("Peter", "University Heights");
		AddCatCommand catCmd = new AddCatCommand();
		catCmd.addCat("Tabby", "Peter", "orange");
		Assert.assertTrue(catCmd.wasSuccessful());
		Kennel kennel = KennelAccess.getKennel();
		PetOwner owner = kennel.getOwner("Peter");
		Cat c = (Cat) owner.getPet("Tabby");
		Assert.assertTrue(c != null);
		Assert.assertTrue(c.getName().equals("Tabby"));
		Assert.assertTrue(c.getColour().equals("orange"));
	}

	/**
	 * Test the AssignPenCommand class.
	 */
	@Test 
	public void testAssignPenCommand ()
	{
		System.out.println("****Test 6");
		AddOwnerCommand ownerCmd = new AddOwnerCommand();
		ownerCmd.addOwner("Sue", "Riversdale");
		Assert.assertTrue(ownerCmd.wasSuccessful());
		Kennel kennel = KennelAccess.getKennel();
		PetOwner owner = kennel.getOwner("Sue");
		Assert.assertTrue(owner != null);
		Assert.assertTrue(owner.getName().equals("Sue"));
		Assert.assertTrue(owner.getAddress().equals("Riversdale"));
		AddDogCommand dogCmd = new AddDogCommand();
		dogCmd.addDog("Airlie", "Sue", "Black Lab");
		Assert.assertTrue(dogCmd.wasSuccessful());
		Dog d = (Dog) owner.getPet("Airlie");
		Assert.assertTrue(d != null);
		Assert.assertTrue(d.getName().equals("Airlie"));
		Assert.assertTrue(d.getBreed().equals("Black Lab"));
		Assert.assertTrue(d.getOwner().getName().equals("Sue"));
		AssignPenCommand assignCmd = new AssignPenCommand();
		assignCmd.assignPen("Sue", "Airlie", 2);
		Assert.assertTrue(assignCmd.wasSuccessful());
		Assert.assertTrue(kennel.hasPet("Airlie"));
		Assert.assertTrue(kennel.penNumberOf("Airlie") == 2);
		Assert.assertTrue(kennel.hasOccupant(2));
		Pet p = kennel.occupantOfPen(2);
		Assert.assertTrue(p != null);
		Assert.assertTrue(p.getName().equals("Airlie"));
		Assert.assertTrue(p == d);
		PensInfoCommand cmdPensInfo = new PensInfoCommand();
		cmdPensInfo.accessPensInfo();
		String pens = cmdPensInfo.getPensInfo();
		Assert.assertTrue(pens != null);
		System.out.println("After assign pen, Airlie is in pen 2 with the others empty. \n" + pens);
	}

	/**
	 * Test the DischargePetCommand class.
	 */
	@Test 
	public void testDischargePetCommand ()
	{
		System.out.println("****Test 7");
		AddOwnerCommand ownerCmd = new AddOwnerCommand();
		ownerCmd.addOwner("Angela", "River Heights");
		AddDogCommand dogCmd = new AddDogCommand();
		dogCmd.addDog("Boo", "Angela", "Poodle");
		AssignPenCommand assignCmd = new AssignPenCommand();
		assignCmd.assignPen("Angela", "Boo", 5);
		DischargePetCommand dischargeCmd = new DischargePetCommand();
		dischargeCmd.dischargePet("Boo");
		Assert.assertTrue(dischargeCmd.wasSuccessful());
		Kennel kennel = KennelAccess.getKennel();
		Assert.assertTrue( ! kennel.hasPet("Boo"));
		Assert.assertTrue( ! kennel.hasOccupant(5));
	}

	/**
	 * Test the AssignPenCommand class.
	 */
	@Test
	public void testProblemDogCommands ()
	{
		System.out.println("****Test 8");
		AddOwnerCommand ownerCmd = new AddOwnerCommand();
		ownerCmd.addOwner("Doug", "Riversdale");
		Assert.assertTrue(ownerCmd.wasSuccessful());
		ExtendedKennel kennel = KennelAccess.getKennel();
		PetOwner owner = kennel.getOwner("Doug");
		Assert.assertTrue(owner != null);
		Assert.assertTrue(owner.getName().equals("Doug"));
		Assert.assertTrue(owner.getAddress().equals("Riversdale"));
		AddDogCommand dogCmd = new AddDogCommand();
		dogCmd.addDog("Angel", "Doug", "Yellow Lab");
		Assert.assertTrue(dogCmd.wasSuccessful());
		Dog d = (Dog) owner.getPet("Angel");
		Assert.assertTrue(d != null);
		Assert.assertTrue(d.getName().equals("Angel"));
		Assert.assertTrue(d.getBreed().equals("Yellow Lab"));
		Assert.assertTrue(d.getOwner().getName().equals("Doug"));
		CreateProblemDogCommand createProbDogCmd = new CreateProblemDogCommand();
		createProbDogCmd.createDog("Angel", "Doug", "Crying for not being home");
		Assert.assertTrue(createProbDogCmd.wasSuccessful());
		Assert.assertTrue(kennel.hasProblemDog("Angel"));
		ProblemDog pd = kennel.getProblemDog("Angel");
		Assert.assertTrue(pd != null);
		Assert.assertTrue(pd.getName().equals("Angel"));
		Assert.assertTrue(pd.getProblem().equals("Crying for not being home"));
		Assert.assertTrue(dogCmd.wasSuccessful());
		pd = (ProblemDog) owner.getPet("Angel");
		Assert.assertTrue(pd != null);
		Assert.assertTrue(pd.getName().equals("Angel"));
		ChangeProblemDogCommand changeDogCmd = new ChangeProblemDogCommand();
		changeDogCmd.changeProblem("Angel", "Being too freindly");
		pd = kennel.getProblemDog("Angel");
		Assert.assertTrue(pd != null);
		Assert.assertTrue(pd.getName().equals("Angel"));
		Assert.assertTrue(pd.getProblem().equals("Being too freindly"));
		AssignPenCommand assignCmd = new AssignPenCommand();
		assignCmd.assignPen("Doug", "Angel", 5);
		Assert.assertTrue(! assignCmd.wasSuccessful());
		Assert.assertTrue(! kennel.hasPet("Angel"));
		Assert.assertTrue(! kennel.hasOccupant(5));
	}

	/**
	 * Check the state of the system at completion.
	 */
	@AfterClass
	public static void finalState()
	{
		System.out.println("****Test 9");
		PensInfoCommand cmdPensInfo = new PensInfoCommand();
		cmdPensInfo.accessPensInfo();
		String pens = cmdPensInfo.getPensInfo();
		Assert.assertTrue(pens != null);
		SystemInfoCommand cmdSystemInfo = new SystemInfoCommand();
		cmdSystemInfo.accessSystemInfo();
		String system = cmdSystemInfo.getSystemInfo();
		Assert.assertTrue(system != null);
		System.out.println("At end of JUnit tests, Fred in Sutherland has no pets, "
							+ "\n\tMary in Nutana has a dog Ebony, "
				           + "\n\tPeter in University Heights has a cat Tabby,"
				           + "\n\tSue in Riversdale has a dog Airlie,"
				           + "\n\tAngela in River Heights has a dog Boo, "
				           + "\n\tand Airlie is in pen 2 with the others empty. \n\n" + system);
	}
}
