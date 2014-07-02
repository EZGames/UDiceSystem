package ezgames.utils.collections.rec;

import ezgames.math.random.EZRandom;
import ezgames.math.random.SimpleRandom;
import ezgames.utils.DataChecker;
import ezgames.utils.Weighted;
import ezgames.utils.collections.simple.SimpleCollection;
import ezgames.utils.exceptions.NullArgumentException;

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
	public static <E> RandomElementChooser<E> simpleElementChooser(SimpleCollection<E> coll) throws NullArgumentException
	{
		return new OpenRandomElementChooser<E>(coll, new EZRandom(), new SimpleChooserStrategy<E>());
	}
	
	public static <E> RandomElementChooser<Weighted<E>> weightedElementChooser(SimpleCollection<Weighted<E>> coll) throws NullArgumentException
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
	protected RandomElementChooser(SimpleCollection<E> coll, SimpleRandom rand) throws NullArgumentException
	{
		checkInput(coll, rand);
		
		this.coll = coll;
		this.rand = rand;
	}
	
	//***************************************************************************
	// Protected fields
	//***************************************************************************
	protected final SimpleCollection<E> coll;
	protected final SimpleRandom rand;
	
	//**************************************************************************
	// Private static helper methods
	//**************************************************************************
	private static void checkInput(SimpleCollection<?> coll, SimpleRandom rand) throws NullArgumentException
	{
		DataChecker.checkIterableNotEmptyOrNull(coll, "Cannot create new RandomElementChooser from an empty or null collection");
		DataChecker.checkDataNotNull(rand, "Cannot create new RandomElementChooser with a null random number generator");
	}
}
