package ezgames.udicesys.diceModels;

import ezgames.udicesys.ConflictingRelationshipException;
import ezgames.udicesys.diceModels.abstractions.FaceValue;
import ezgames.udicesys.diceModels.abstractions.Relationship;

public final class UFaceValue implements FaceValue
{
	//***************************************************************************
	// Public static factory methods
	//***************************************************************************
	public static UFaceValue from(Relationship rel, int value)
	{
		return new UFaceValue(rel, value);
	}
	
	//***************************************************************************
	// Public API methods
	//***************************************************************************
	@Override
	public int getValue()
	{
		return value;
	}
	
	@Override
	public Relationship getRelationship()
	{
		return rel;
	}
	
	@Override
	public FaceValue combinedWith(FaceValue other) throws ConflictingRelationshipException
	{
		if(this.hasSameRelationshipAs(other))
			return UFaceValue.from(rel, value + other.getValue());
		throw new ConflictingRelationshipException(this.rel, other.getRelationship());
	}
	
	public boolean equals(Object obj)
	{
		//TODO apache commons helper
		return super.equals(obj);		
	}
	
	@Override
	public int hashCode()
	{
		//TODO apache commons helper
		return super.hashCode();
	}
	
	//***************************************************************************
	// PP Constructor
	//***************************************************************************
	UFaceValue(Relationship rel, int value)
	{
		if(value == 0)
			throw new IllegalArgumentException("Cannot create a FaceValue with a value of 0");
		this.value = value;
		this.rel = rel;
	}
	
	//***************************************************************************
	// Private fields
	//***************************************************************************
	private final Relationship rel;
	private final int value;
}
