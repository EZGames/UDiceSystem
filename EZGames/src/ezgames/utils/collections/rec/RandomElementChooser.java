package ezgames.utils.collections.rec;

import ezgames.math.random.SimpleRandom;
import ezgames.utils.Weighted;
import ezgames.utils.collections.simple.SimpleCollection;

// TEST
/**
 *  Abstract class for building objects designed to choose a random element from
 *  a collection.
 *  @see OpenRandomElementChooser
 */
public abstract class RandomElementChooser<E>
{
	//***************************************************************************
	// Public static default factory methods
	//***************************************************************************
	/**
	 * Creates a simple random element chooser that simply gets a random number, 
	 * {@code n} and returns the {@code n}th element.
	 * @param coll - the collection to choose the random element from
	 * @return a simple random element chooser
	 */
	public static <E> RandomElementChooser<E> simpleElementChooser(SimpleCollection<E> coll)
	{
		return new OpenRandomElementChooser<E>(coll, new SimpleChooserStrategy<E>());
	}
	
	/**
	 * Creates a random element chooser that takes each element's weight into account,
	 * allowing for an uneven distribution of how often each element is chosen.
	 * @param coll - the collection of weighted elements to choose the random element from
	 * @return a weighted random element chooser
	 */
	public static <E> RandomElementChooser<Weighted<E>> weightedElementChooser(SimpleCollection<Weighted<E>> coll)
	{
		return new OpenRandomElementChooser<Weighted<E>>(coll, new WeightedChooserStrategy<E>());
	}
	
	//**************************************************************************
	// Public API methods
	//**************************************************************************
	/**
	 * @return a random element from the supplied collection
	 */
	public abstract E choose();
	
	//***************************************************************************
	// Protected Constructors
	//***************************************************************************
	/**
	 * Constructs the abstract {@code RandomElementChooser}
	 * @param coll - collection to choose the random element from
	 * @param rand - {@code SimpleRandom} random number generator
	 */
	protected RandomElementChooser(SimpleCollection<E> coll, SimpleRandom rand)
	{
		this.coll = coll;
		this.rand = rand;
	}
	
	//***************************************************************************
	// Protected fields
	//***************************************************************************
	/**
	 * {@code SimpleCollection} provided in the constructor
	 */
	protected final SimpleCollection<E> coll;
	/**
	 * {@code SimpleRandom} random number generator provided in the constructor
	 */
	protected final SimpleRandom rand;
}
