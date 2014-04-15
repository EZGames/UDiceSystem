package com.ezgames.udicesys.diceModels.abstractions;

import com.ezgames.utils.interfaces.TImmutable;

public interface Die extends TImmutable, Iterable<Face>
{
	public Roll roll();
   public String name();
   public int totalWeight();
   public int numSides();
   public Iterable<Relationship> listRelationships();
}
