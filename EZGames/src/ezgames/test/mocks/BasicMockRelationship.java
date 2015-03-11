package ezgames.test.mocks;

import java.util.Iterator;
import java.util.stream.Stream;
import ezgames.udicesys.diceModels.abstractions.OutputRange;
import ezgames.udicesys.diceModels.abstractions.Relationship;

/**
 * A simple mock {@code Relationship} class, created solely for passing around
 * and checking equality. It implements simple stubs of {@code getName()} and 
 * {@code getOutputFor()} for debugging sake.
 */
public class BasicMockRelationship implements Relationship
{
	public final Integer id;
	
	/**
	 * Create a new {@code BasicMockRelationship} using the given {@code id}
	 * @param id - an integer to distinguish this Relationship from others
	 */
	public BasicMockRelationship(int id)
	{
		this.id = id;
	}
	
	// Null Methods
	/**
	 * @return null
	 */
	public Iterator<OutputRange> iterator() { return null; }
	/**
	 * @return null
	 */
	public Stream<OutputRange> stream() { return null; }
	/**
	 * @return null
	 */
	public OutputRange getRangeForValue(int rangeValue) { return null; }
	
	/**
	 * @return a string version of the id given
	 */
	public String getName()
	{
		return id.toString();
	}

	/**
	 * @return a string version of the id given
	 */
	public String getOutputFor(int total)
	{
		return id.toString();
	}
	
	public boolean equals(Object obj)
	{
		if(obj instanceof BasicMockRelationship)
			return this.id.equals(((BasicMockRelationship)obj).id);
		else
			return false;
	}
}
