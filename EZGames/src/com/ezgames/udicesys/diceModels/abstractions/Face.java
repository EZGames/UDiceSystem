package com.ezgames.udicesys.diceModels.abstractions;

import com.ezgames.utils.interfaces.TImmutable;

public interface Face extends TImmutable, Iterable<FaceValue>
{
   public String name();
   public int getValueForRelationship(Relationship aRelationship) throws IllegalArgumentException;
   public boolean hasRelationship(Relationship rel);
   /**
    * @return {@code true} if there is only one {@code FaceValue}, and that {@code FaceValue} is numeric 
    */
   public boolean isNumericFace();
   public boolean isSingleValueFace();
   public Iterable<Relationship> listRelationships();
   public Iterable<Effect> listEffects();
}
