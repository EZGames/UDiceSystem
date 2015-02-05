package ezgames.udicesys.diceModels;

import java.util.Iterator;
import ezgames.annotations.Immutable;
import ezgames.udicesys.diceModels.abstractions.Effect;
import ezgames.udicesys.diceModels.abstractions.Face;
import ezgames.udicesys.diceModels.abstractions.FaceValue;
import ezgames.udicesys.diceModels.abstractions.Relationship;
import ezgames.utils.Weighted;
import ezgames.utils.collections.simple.SimpleCollection;

@Immutable
public class UFace implements Face, Weighted<Face>
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
		return with(name, faceValues, effects, 1);
	}
	
	public static Face with(String name, SimpleCollection<FaceValue> faceValues, SimpleCollection<Effect> effects, int weight)
	{
		return new UFace(name, faceValues, effects, weight);
	}
	
	public static Face blank()
	{
		return with("blank", null, null, 1);
	}
	
	public static Face blank(String name)
	{
		return with(name, null, null, 1);
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
	
	public int weight()
	{
		// TODO Auto-generated method stub
		return 0;
	}
	
	private final String name;
	private final SimpleCollection<FaceValue> faceValues;
	private final SimpleCollection<Effect> effects;
	private final int weight;
	
	private UFace(String name, SimpleCollection<FaceValue> faceValues, SimpleCollection<Effect> effects, int weight)
	{
		this.weight = weight;
		this.name = name;
		this.faceValues = faceValues;
		this.effects = effects;
	}
}
