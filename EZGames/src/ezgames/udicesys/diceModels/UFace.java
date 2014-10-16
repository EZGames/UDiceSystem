package ezgames.udicesys.diceModels;

import java.util.Iterator;
import ezgames.udicesys.diceModels.abstractions.Effect;
import ezgames.udicesys.diceModels.abstractions.Face;
import ezgames.udicesys.diceModels.abstractions.FaceValue;
import ezgames.udicesys.diceModels.abstractions.Relationship;
import ezgames.utils.collections.simple.SimpleCollection;

public class UFace implements Face
{
	/**
	 * A static factory for building a {@code Face}. The only thing required is
	 * the {@code name} field. Other than the {@code name}, including just the
	 * {@code faceValues} will make the {@code Face} a typical die face, with just
	 * a value associated with it.  Including just the {@code effects} (other than
	 * the {@code name}) is rare, but it is possible that you'd only want an
	 * effect, such as reroll, to be triggered. Most of the time, if an
	 * {@code effect} is included on a {@code Face}, it will be alongside  
	 * @param name
	 * @param faceValues
	 * @param effects
	 * @return
	 */
	
	public static Face with(String name, SimpleCollection<FaceValue> faceValues, SimpleCollection<Effect> effects)
	{
		return new UFace(name, faceValues, effects);
	}
	
	public static Face blank()
	{
		return new UFace("blank", null, null);
	}
	
	public static Face blank(String name)
	{
		return new UFace(name, null, null);
	}
	
	public Iterator<FaceValue> iterator()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public String name()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public SimpleCollection<Relationship> listRelationships()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public SimpleCollection<Effect> listEffects()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public SimpleCollection<FaceValue> listFaceValues()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	private final String name;
	private final SimpleCollection<FaceValue> faceValues;
	private final SimpleCollection<Effect> effects;
	
	private UFace(String name, SimpleCollection<FaceValue> faceValues, SimpleCollection<Effect> effects)
	{
		this.name = name;
		this.faceValues = faceValues;
		this.effects = effects;
	}
}
