package ezgames.utils.collections.rec;

import ezgames.math.random.SimpleRandom;
import ezgames.utils.collections.simple.SimpleCollection;

/**
 * Used with {@link OpenRandomElementChooser} to dictate how it uses the given
 * collection and random number generator to choose an element.
 * Strategy Design Pattern
 * @param <E> the type of the elements in the collection
 */
@FunctionalInterface
public interface ChooserStrategy<E>
{
	/**
	 * @param coll - the collection from which to get the random element
	 * @param rand - the random number generator
	 * @return a random element from the collection
	 * @throws IllegalArgumentException if the ChooserStrategy has any problems
	 * with the arguments given. 
	 */
	public E choose(SimpleCollection<E> coll, SimpleRandom rand) throws IllegalArgumentException;
}
