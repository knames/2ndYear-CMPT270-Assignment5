package entities;

/**
 * A class to model a dog who has a problem associated with it.  
 */
public class ProblemDog extends Dog 
{
	/** A description of the problem associated with the dog. */
	private String problem;
	
	/** Initialize an instance with the dog and problem. 
	 * @param d       the dog with the problem
	 * @param problem the problem with the dog
	 * @precond  problem != null  && ! problem.equals("")  */
	public ProblemDog(Dog d, String problem)
	{
		super(d.getName(), d.getOwner(), d.getBreed());
		if (problem == null || problem.equals(""))
			throw new RuntimeException("The problem cannot be null or empty");
		this.problem = problem;
	}
	
	/** @return the description of the problem associated with the dog.  */
	public String getProblem()
	{
		return problem;
	}
	
	/** Associated a new description of the problem associated with the dog. 
	 * @param newProblem  the new description of the problem
	 */
	public void setProblem(String newProblem)
	{
		problem = newProblem;
	}
	
	/** @return a description of the properties of the dog. */
	public String toString()
	{ 
		return super.toString() + " Problem: " + problem;
	}
}
