package ezgames.udicesys.diceModels.abstractions;

import ezgames.annotations.Immutable;

@Immutable
public interface FaceValue
{
   public int getValue();
   public Relationship getRelationship();
}
