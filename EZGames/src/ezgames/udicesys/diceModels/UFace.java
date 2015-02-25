package ezgames.udicesys.diceModels;

import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;
import ezgames.annotations.Immutable;
import ezgames.udicesys.diceModels.abstractions.Face;
import ezgames.udicesys.diceModels.abstractions.FaceValue;
import ezgames.udicesys.diceModels.abstractions.Relationship;
import ezgames.utils.Weighted;
import ezgames.utils.collections.simple.SimpleCollection;

//DOC
@Immutable
public class UFace implements Face, Weighted<Face>
{
	//***************************************************************************
	//Public static factory methods
	//***************************************************************************
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
	 * @return
	 */	
	public static UFace with(String name, SimpleCollection<FaceValue> faceValues)
	{
		return with(name, faceValues, 1);
	}
	
	public static UFace with(String name, SimpleCollection<FaceValue> faceValues, int weight)
	{
		return new UFace(name, faceValues, weight);
	}
	
	public static UFace blank()
	{
		return with("blank", null, 1);
	}
	
	public static UFace blank(String name)
	{
		return with(name, null, 1);
	}
	
	//***************************************************************************
	// Public API methods
	//***************************************************************************
	public Iterator<FaceValue> iterator()
	{
		return faceValues.iterator();
	}

	public String name()
	{
		return name;
	}

	public SimpleCollection<Relationship> listRelationships()
	{
		Set<Relationship> rels = faceValues.stream()
													  .map(fv -> fv.getRelationship())
													  .collect(Collectors.toSet());
		
		return SimpleCollection.from(rels);
	}

	public SimpleCollection<FaceValue> listFaceValues()
	{
		return faceValues;
	}
	
	public int weight()
	{
		return weight;
	}
	
	//***************************************************************************
	// PP constructor
	//***************************************************************************
	UFace(String name, SimpleCollection<FaceValue> faceValues, int weight)
	{
		this.weight = weight;
		this.name = name;
		this.faceValues = faceValues;
	}
	
	//***************************************************************************
	// Private fields
	//***************************************************************************
	private final String name;
	private final SimpleCollection<FaceValue> faceValues;
	private final int weight;
}
