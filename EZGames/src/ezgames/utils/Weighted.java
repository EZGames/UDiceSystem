package ezgames.utils;

import ezgames.annotations.Immutable;

@Immutable
public final class Weighted<T>
{
	//***************************************************************************
	// Public constructors
	//***************************************************************************
	public Weighted(T obj, int weight)
	{
		DataChecker.checkDataNotNull(obj, "Cannot create a Weighted object from a null object");
		if(isInvalidWeight(weight))
		{
			throw new IllegalArgumentException("Cannot create a Weighted object with 0 or negative weight or weight greater than" + MAX_WEIGHT);
		}
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
	
	public T object()
	{
		return obj;
	}
	
	public int weight()
	{
		return weight;
	}
	
	//***************************************************************************
	//Private constants
	//***************************************************************************
	private static final int MAX_WEIGHT = 10000;
	
	//***************************************************************************
	// Private fields
	//***************************************************************************
	private final int weight;
	private final T obj;
	
	//***************************************************************************
	// Private static helper methods
	//***************************************************************************
	private static boolean isInvalidWeight(int weight)
	{
		return weight > MAX_WEIGHT || weight < 1;
	}
}
