package ezgames.udicesys.diceModels.abstractions;

import java.util.Iterator;
import ezgames.annotations.Immutable;
import ezgames.utils.collections.SimpleCollection;
import ezgames.utils.collections.Stackable;

@Immutable
public interface Die extends Iterable<Face>, Stackable<Die>
{
   /**
    * Does a random 'roll' of the Die, returning the {@link Roll}
    * @return a random Roll of the Die
    */
   Roll roll();
   
   /**
    * @return the name of the die
    */
   String name();
   
   //public int totalWeight();
   //public int numSides();
   /**
    * Returns an Iterator over the Die's {@link Face}s
    */
   @Override
   Iterator<Face> iterator();
   
   /**
    * @return an {@link Iterable} of all the {@link Relationship}s for the Die
    */
   SimpleCollection<Relationship> listRelationships();
}
