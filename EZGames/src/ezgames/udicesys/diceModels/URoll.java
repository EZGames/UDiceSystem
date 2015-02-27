package ezgames.udicesys.diceModels;

import ezgames.annotations.Immutable;
import ezgames.udicesys.diceModels.abstractions.Die;
import ezgames.udicesys.diceModels.abstractions.Face;
import ezgames.udicesys.diceModels.abstractions.FaceValue;
import ezgames.udicesys.diceModels.abstractions.Relationship;
import ezgames.udicesys.diceModels.abstractions.Roll;
import ezgames.utils.collections.simple.SimpleCollection;

@Immutable
public final class URoll implements Roll
{
	//***************************************************************************
	// Public API methods
	//***************************************************************************
	public Die die()
	{
		return die;
	}
	
	public Face rolledFace()
	{
		return face;
	}
	
	public SimpleCollection<FaceValue> rolledFaceValues()
	{
		return face.listFaceValues();
	}
	
	public SimpleCollection<Relationship> rolledRelationships()
	{
		return face.listRelationships();
	}
	
	//***************************************************************************
	// Package-private constructor
	//***************************************************************************
	URoll(Die die, Face face)
	{
		this.die = die;
		this.face = face;
	}
	
	//***************************************************************************
	// Private fields
	//***************************************************************************
	private final Die die;
	private final Face face;
}
