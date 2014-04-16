package com.ezgames.udicesys.diceModels.abstractions;

import java.util.Iterator;
import com.ezgames.annotations.Immutable;

@Immutable
public interface Die extends Iterable<Face>
{
   public Roll roll();
   public String name();
   //public int totalWeight();
   //public int numSides();
   /**
    * Returns an Iterator over the Die's {@link Face}s
    */
   @Override
   public Iterator<Face> iterator();
   public Iterable<Relationship> listRelationships();
}
