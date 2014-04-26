package ezgames.udicesys.diceModels.abstractions;

import ezgames.annotations.Immutable;

@Immutable
public interface FaceValue
{
   int getValue();
   Relationship getRelationship();
}
