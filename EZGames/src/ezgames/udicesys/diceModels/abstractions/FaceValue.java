package ezgames.udicesys.diceModels.abstractions;

import ezgames.annotations.Immutable;
import ezgames.utils.collections.Stackable;

@Immutable
public interface FaceValue extends Stackable<FaceValue>
{
   int getValue();
   Relationship getRelationship();
}
