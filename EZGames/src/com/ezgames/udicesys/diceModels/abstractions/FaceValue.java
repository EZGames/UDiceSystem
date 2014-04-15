package com.ezgames.udicesys.diceModels.abstractions;

import com.ezgames.utils.interfaces.TImmutable;

public interface FaceValue extends TImmutable
{
   public int getValue();
   public Relationship getRelationship();
}
