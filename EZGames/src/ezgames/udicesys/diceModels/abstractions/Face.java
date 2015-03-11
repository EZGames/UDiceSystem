package ezgames.udicesys.diceModels.abstractions;

import ezgames.annotations.Immutable;
import ezgames.utils.collections.Stackable;
import ezgames.utils.collections.simple.SimpleCollection;

/**
 * A {@code Face} represents the face of a die.  A {@code Face} is made up of
 * {@link FaceValue}s and {@link Effect}s.
 */
//TODO add documentation stating to have implementation tests inherit from FaceTest
@Immutable
public interface Face extends Iterable<FaceValue>, Stackable<Face>
{
	/**
	 * @return the name of the {@code Face}
	 */
	String name();
//	/**
//	 * Given a {@code Relationship}, returns the value associated with it on this
//	 * {@code Face}
//	 * @param aRelationship - the {@code Relationship} to get the value for
//	 * @return the value associated with the given {@code Relationship}, or 0 if
//	 * the {@code Relationship} is null or isn't in the Face.
//	 */
//	Integer getValueForRelationship(Relationship aRelationship);
//	/**
//	 * @param aRelationship - the {@code Relationship} to check for
//	 * @return whether the {@code Relationship} exists on this {@code Face}
//	 */
//	Boolean hasRelationship(Relationship rel);
//	/**
//	 * @return {@code true} if there is only one {@code FaceValue}, and that
//	 {@code FaceValue} is numeric
//	 */
//	Boolean isNumericFace();
//	/**
//	 * @return {@code true} if there is only one {@code FaceValue} on this
//	 * {@code Face}
//	 */
//	Boolean isSingleValueFace();
	/**
	 * @return a {@link SimpleCollection} containing all the {@code Relationship}s
	 * on this {@code Face}
	 */
	SimpleCollection<Relationship> listRelationships();
//	// TODO: someday, add Effects, maybe
//	/**
//	 * @return a {@code SimpleCollection containing all the {@code Effect}s on
//	 * this {@code Face}
//	 */
//	SimpleCollection<Effect> listEffects();
	/**
	 * @return a {@code SimpleCollection containing all the {@code FaceValue}s on
	 * this {@code Face}
	 */
	SimpleCollection<FaceValue> listFaceValues();
}

//TODO: if any of the commented out methods are needed, uncomment them
