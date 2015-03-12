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
   int getValue();
   Relationship getRelationship();
   //DOC mostly same results in reverse
   FaceValue combinedWith(FaceValue other) throws ConflictingRelationshipException;
   
   //DOC same results in reverse
   default boolean hasSameRelationshipAs(FaceValue other)
   {
   	return this.getRelationship().equals(other.getRelationship());
   }
}