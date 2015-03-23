package ezgames.udicesys;

import ezgames.udicesys.diceModels.abstractions.Relationship;

public class ConflictingRelationshipException extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	
	public ConflictingRelationshipException(Relationship one, Relationship two)
	{
		
	}
}
