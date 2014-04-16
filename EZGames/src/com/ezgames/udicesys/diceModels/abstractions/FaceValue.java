package com.ezgames.udicesys.diceModels.abstractions;

import com.ezgames.annotations.Immutable;

@Immutable
public interface FaceValue
{
   public int getValue();
   public Relationship getRelationship();
}
