package ezgames.utils;

import ezgames.annotations.Immutable;

/**
 * The {@code Weighted} class is used to add a weight attribute to any object.
 * It's main purpose was for the UDice System to make weighted dice where 
 * faces would have a higher or lower chance of being rolled, but it can 
 * technically be used to add an integer field to any object.
 * 
 * @param <T> the type of object gaining the weight attribute
 */
@Immutable
public final class Weighted<T>
{
	//***************************************************************************
	// Public constructors
	//***************************************************************************
	/**
	 * Creates a new {@code Weighted} object from a given object and weight
	 * <p>
	 * When using this for weighted {@code Face}s in the UDiceSystem, be certain
	 * that the value given is not negative.  It could result in some interesting
	 * behavior.
	 * @param obj - the object to add the weight to
	 * @param weight - the amount of weight to give to the object
	 */
	public Weighted(T obj, int weight)
	{
		this.obj = obj;
		this.weight = weight;
	}
	
	//***************************************************************************
	// Public API methods
	//***************************************************************************
	public boolean equals(Object o)
	{
		if (this == o) { return true; }
		
		if (o == null) { return false; }
		
		if (getClass() != o.getClass()) { return false; }
		
		Weighted<?> other = (Weighted<?>) o;
		
		return obj.equals(other.obj) && weight == other.weight;
	}
	
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((obj == null) ? 0 : obj.hashCode());
		result = prime * result + weight;
		return result;
	}
	
	/**
	 * @return the original object without the weight
	 */
	public T object()
	{
		return obj;
	}
	
	/**
	 * @return the weight of the object
	 */
	public int weight()
	{
		return weight;
	}
	
	//***************************************************************************
	// Private fields
	//***************************************************************************
	private final int weight;
	private final T obj;
}
