package ezgames.udicesys.diceModels;

import ezgames.annotations.Immutable;
import ezgames.udicesys.diceModels.abstractions.Die;
import ezgames.udicesys.diceModels.abstractions.Effect;
import ezgames.udicesys.diceModels.abstractions.Face;
import ezgames.udicesys.diceModels.abstractions.FaceValue;
import ezgames.udicesys.diceModels.abstractions.Relationship;
import ezgames.udicesys.diceModels.abstractions.Roll;
import ezgames.utils.DataChecker;
import ezgames.utils.collections.simple.SimpleCollection;
import ezgames.utils.exceptions.NullArgumentException;

@Immutable
public class URoll implements Roll
{	
	URoll(Die die, Face face) throws NullArgumentException
	{
		//TODO: add messages
		DataChecker.checkDataNotNull(die, "");
		DataChecker.checkDataNotNull(face, "");
		this.die = die;
		this.face = face;
	}
	
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
		try
		{
			return SimpleCollection.from(face);
		}
		catch (NullArgumentException e)
		{ 
			return null;
		}
	}
	public SimpleCollection<Relationship> rolledRelationships()
	{
		return face.listRelationships();
	}
	public SimpleCollection<Effect> rolledEffects()
	{
		//TODO:return face.listEffects() 
		return null;
	}
	
	private Die die;
	private Face face;
}
