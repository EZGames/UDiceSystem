package ezgames.udicesys;

import ezgames.udicesys.diceModels.abstractions.Relationship;

public class ConflictingRelationshipException extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	
	public ConflictingRelationshipException(Relationship rel1, Relationship rel2)
	{
		super(String.format("Relationships %s and %s are not the same", rel1, rel2));		
	}
	
	public ConflictingRelationshipException(Relationship rel1, Relationship rel2, String message)
	{
		super(String.format("Relationships %s and %s are not the same: " + message, rel1, rel2));
	}
}
