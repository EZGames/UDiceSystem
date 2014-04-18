package ezgames.udicesys.diceModels.abstractions;

import java.util.Iterator;

import ezgames.annotations.Immutable;

@Immutable
public interface Die extends Iterable<Face>
{
   /**
    * Does a random 'roll' of the Die, returning the {@link Roll}
    * @return a random Roll of the Die
    */
   public Roll roll();
   /**
    * @return the name of the die
    */
   public String name();
   //public int totalWeight();
   //public int numSides();
   /**
    * Returns an Iterator over the Die's {@link Face}s
    */
   @Override
   public Iterator<Face> iterator();
   /**
    * @return an {@link Iterable} of all the {@link Relationship}s for the Die
    */
   public Iterable<Relationship> listRelationships();
}
