package ezgames.udicesys.diceModels.abstractions;

import ezgames.annotations.Immutable;
import ezgames.udicesys.ConflictingRelationshipException;
import ezgames.utils.collections.Stackable;

/**
 * A {@code FaceValue} is part of a {@link Face}, representing individual 
 * symbols or meanings on the face of a die. For more information, check out my
 * article explaining the whole of a die at 
 * <a href="http://ezgames.github.io/UDiceSystem/dice/">The Basic Dice Model</a>
 * or just the explanation of 
 * <a href="http://ezgames.github.io/UDiceSystem/dice/parts/FaceValue/">Face Values
 * </a>.
 * <p>
 * When implementing a new {@code Face} class and writing tests for it, make
 * certain that your tests inherit from {@link FaceTest} to ensure that your
 * implementation keeps all invariants.</p>
 */
@Immutable
public interface FaceValue extends Stackable<FaceValue>
{
	/**
	 * Returns the value assigned to this
	 * @return the value assigned to this
	 */
   int getValue();
   /**
    * Returns the {@code Relationship} assigned to this
    * @return the {@code Relationship} assigned to this
    */
   Relationship getRelationship();
   /**
    * Creates a new {@code FaceValue}, combining this and {@code other} into one
    * with the same {@code Relationship} and a sum of the two {@code FaceValue}s'
    * values.
    * <p>
    * This method should be mostly <i>symmetric</i>, meaning that the result
    * should be the same for either <code>x.combinedWith(y)</code> or <code>y.combinedWith(x)</code>.
    * The exception to this is if {@code x} and {@code y} are different 
    * implementations, meaning that the returned {@code FaceValue} could be of
    * different implementations, depending on which one is called from and which
    * one is passed to the call. In many cases, though, if the two are of different
    * types, an {@code IllegalArgumentException} should be thrown.</p>
    * @param other - the other {@code FaceValue} to combine with
    * @return a new {@code FaceValue} that is a combination of this and {@code other}
    * @throws ConflictingRelationshipException if this and {@code other} do not
    * have the same {@code Relationship}
    * @throws IllegalArgumentException if there is any other reason the two
    * {@code FaceValue}s cannot be combined, such as if they have opposite values
    * (which would result in creating a {@code FaceValue} with a value of 0) or the
    * two are of two incompatible implementations
    */
   FaceValue combinedWith(FaceValue other) throws ConflictingRelationshipException, IllegalArgumentException;
   
   /**
    * SYMMETRIC
    * @param other
    * @return
    */
   default boolean hasSameRelationshipAs(FaceValue other)
   {
   	return this.getRelationship().equals(other.getRelationship());
   }
}