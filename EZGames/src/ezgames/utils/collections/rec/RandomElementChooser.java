package ezgames.utils.collections.rec;

import ezgames.math.random.EZRandom;
import ezgames.math.random.SimpleRandom;
import ezgames.utils.Weighted;
import ezgames.utils.collections.simple.SimpleCollection;

//TODO: document and test
/*TODO: provide a Rule interface that the class can use to make sure that the chosen element follows certain rules,
 * such as never getting the same one twice, or not getting the same element twice in a row. 
 */
public abstract class RandomElementChooser<E>
{
	//***************************************************************************
	// Public static default factory methods
	//***************************************************************************
	/**
	 * 
	 * @param coll
	 * @return
	 * @throws NullArgumentException
	 */
	public static <E> RandomElementChooser<E> simpleElementChooser(SimpleCollection<E> coll)
	{
		return new OpenRandomElementChooser<E>(coll, new EZRandom(), new SimpleChooserStrategy<E>());
	}
	
	public static <E> RandomElementChooser<Weighted<E>> weightedElementChooser(SimpleCollection<Weighted<E>> coll)
	{
		return new OpenRandomElementChooser<Weighted<E>>(coll, new EZRandom(), new WeightedChooserStrategy<E>());
	}
	
	//**************************************************************************
	// Public API methods
	//**************************************************************************
	public abstract E choose() throws IllegalArgumentException;
	
	//***************************************************************************
	// Protected Constructors
	//***************************************************************************
	protected RandomElementChooser(SimpleCollection<E> coll, SimpleRandom rand)
	{
		this.coll = coll;
		this.rand = rand;
	}
	
	//***************************************************************************
	// Protected fields
	//***************************************************************************
	protected final SimpleCollection<E> coll;
	protected final SimpleRandom rand;
}
