package ezgames.utils.collections.rec;

import ezgames.annotations.Immutable;
import ezgames.math.random.EZRandom;
import ezgames.math.random.SimpleRandom;
import ezgames.utils.collections.simple.SimpleCollection;

// TEST
/**
 * A {@link RandomElementChooser} that works with the Strategy pattern, allowing
 * the user to simply provide a {@link ChooserStrategy} (which is a Functional
 * Interface) to quickly design new {@code RandomElementChoosers} without having
 * to do a full class definition.
 * @param <E>
 */
@Immutable
public final class OpenRandomElementChooser<E> extends RandomElementChooser<E>
{
	//***************************************************************************
	// Public constructor
	//***************************************************************************
	/**
	 * Constructs a new {@code RandomElementChooser} that chooses an element in
	 * {@code coll} using the given {@code ChooserStrategy} and {@code SimpleRandom}
	 * number generator.
	 * @param coll - collection to pull the random element from
	 * @param rand - {@code SimpleRandom} random number generator
	 * @param strategy - {@code ChooserStrategy} strategy for how to use the number
	 * generator to get a random element. 
	 */
	public OpenRandomElementChooser(SimpleCollection<E> coll, SimpleRandom rand, ChooserStrategy<E> strategy)
	{
		super(coll, rand);
		this.strategy = strategy;
	}
	
	/**
	 * Constructs a new {@code RandomElementChooser} that chooses an element in
	 * {@code coll} using the given {@code ChooserStrategy} and an {@code EZRandom}
	 * number generator.
	 * @param coll - collection to pull the random element from
	 * @param strategy - {@code ChooserStrategy} strategy for how to use the number
	 * generator to get a random element. 
	 */
	public OpenRandomElementChooser(SimpleCollection<E> coll, ChooserStrategy<E> strategy)
	{
		super(coll, new EZRandom());
		this.strategy = strategy;
	}

	//specified by RandomElementChooser
	public E choose()
	{
		return strategy.choose(coll, rand);
	}
	
	private final ChooserStrategy<E> strategy;
}
