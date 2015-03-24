package ezgames.udicesys.diceModels.abstractions;

import java.util.Iterator;
import ezgames.annotations.Immutable;
import ezgames.utils.collections.Stackable;
import ezgames.utils.collections.Streamable;
import ezgames.utils.collections.simple.SimpleCollection;

/**
 * {@code Die} is the class representing individual dice. They have a name and a
 * collection of faces. You can roll a {@code Die} to get a {@code Roll}. You
 * can also get an iterator over the faces.
 * <h3>Testing Note</h3>
 * <p>
 * If you make your own implementation of Die with tests, be certain that your
 * test class extends {@link DieTest} 
 */
@Immutable
public interface Die extends Iterable<Face>, Streamable<Face>, Stackable<Die>
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
    * Returns an iterator over this {@code Die}'s {@link Face}s
    * @return an iterator over this {@code Die}'s {@code Face}s
    */
   @Override
   Iterator<Face> iterator();
   
   /**
    * @return a {@link SimpleCollection} of all the {@link Relationship}s for 
    * the Die
    */
   SimpleCollection<Relationship> listRelationships();
}
