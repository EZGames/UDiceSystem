package ezgames.udicesys.diceModels;

import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;
import ezgames.annotations.Immutable;
import ezgames.udicesys.diceModels.abstractions.Face;
import ezgames.udicesys.diceModels.abstractions.FaceValue;
import ezgames.udicesys.diceModels.abstractions.Relationship;
import ezgames.utils.Weighted;
import ezgames.utils.collections.MlList;
import ezgames.utils.collections.simple.SimpleCollection;

//DOC
/**
 * {@code UFace} is the default implementation of the {@code Face} interface. It
 * also applies the {@code Weighted} interface, though there isn't a {@code Die}
 * implementation that uses {@code Weighted<Face>}s yet.
 */
@Immutable
public class UFace implements Face, Weighted<Face>
{
	//***************************************************************************
	//Public static factory methods
	//***************************************************************************
	/**
	 * Static factory method that creates a {@code UFace} with the given name and
	 * face values and a weight of 1.
	 * @param name - name of the face
	 * @param faceValues - collection of {@code FaceValue}s that represent the
	 * value of the face. If the face is supposed to be blank, check out one of
	 * {@link #blank() the} {@link #blank(int) blank} 
	 * {@link #blank(String) factory} {@link #blank(String, int) methods}.
	 * @return a new UFace object with the designated properties
	 */
	public static UFace with(String name, SimpleCollection<FaceValue> faceValues)
	{
		return with(name, faceValues, 1);
	}
	
	/**
	 * Static factory method that creates a {@code UFace} with the given name and
	 * face values and a weight of 1.
	 * @param name - name of the face
	 * @param faceValues - collection of {@code FaceValue}s that represent the
	 * value of the face. If the face is supposed to be blank, check out one of
	 * {@link #blank() the} {@link #blank(int) blank} 
	 * {@link #blank(String) factory} {@link #blank(String, int) methods}.
	 * @param weight - weight of the face, for "cheat" dice, or to represent
	 * multiples of the same face(only works with a {@code Die} that specifically
	 * works with {@code Weighted<Face>}s
	 * @return a new UFace object with the designated properties
	 */
	public static UFace with(String name, SimpleCollection<FaceValue> faceValues, int weight)
	{
		return new UFace(name, faceValues, weight);
	}
	
	/**
	 * Static factory method that creates a {@code UFace} with the name, "blank",
	 * an empty collection of face values and a weight of 1.
	 * @return a new UFace object with the designated properties
	 */
	public static UFace blank()
	{
		return with("blank", MlList.empty(), 1);
	}
	
	/**
	 * Static factory method that creates a {@code UFace} with the given name, an
	 * empty collection of face values and a weight of 1.
	 * @param name - name of the face
	 * @return a new UFace object with the designated properties
	 */
	public static UFace blank(String name)
	{
		return with(name, MlList.empty(), 1);
	}
	
	/**
	 * Static factory method that creates a {@code UFace} with the given name and
	 * weight, and an empty collection of face values
	 * @param name - name of the face
	 * @param weight - weight of the face, for "cheat" dice, or to represent
	 * multiples of the same face(only works with a {@code Die} that specifically
	 * uses {@code Weighted<Face>}s
	 * @return a new UFace object with the designated properties
	 */
	public static UFace blank(String name, int weight)
	{
		return with(name, MlList.empty(), weight);
	}
	
	/**
	 * Static factory method that creates a {@code UFace} with the name, "blank",
	 * an empty collection of face values and the given weight
	 * @param weight - weight of the face, for "cheat" dice, or to represent
	 * multiples of the same face(only works with a {@code Die} that specifically
	 * uses {@code Weighted<Face>}s
	 * @return a new UFace object with the designated properties
	 */
	public static UFace blank(int weight)
	{
		return with("blank", MlList.empty(), weight);
	}
	
	//***************************************************************************
	// Public API methods
	//***************************************************************************
	/**
	 * Returns an iterator over all the {@code FaceValue}s.
	 * @return an iterator
	 */
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
	// PP constructor
	//***************************************************************************
	UFace(String name, SimpleCollection<FaceValue> faceValues, int weight)
	{
		if("".equals(name))
			throw new IllegalArgumentException("Cannot create a Face with an empty name");
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
