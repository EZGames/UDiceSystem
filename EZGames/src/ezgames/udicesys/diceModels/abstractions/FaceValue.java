package ezgames.udicesys.diceModels.abstractions;

import ezgames.annotations.Immutable;
import ezgames.utils.collections.Stackable;

//DOC
//TODO document that tests of implementations should extend FaceValueTest
@Immutable
public interface FaceValue extends Stackable<FaceValue>
{
   int getValue();
   Relationship getRelationship();
   
   default boolean hasSameRelationshipAs(FaceValue other)
   {
   	return this.getRelationship().equals(other.getRelationship());
   }
}
