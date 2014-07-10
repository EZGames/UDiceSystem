package ezgames.utils.collections.rec;

import ezgames.math.random.EZRandom;
import ezgames.math.random.SimpleRandom;
import ezgames.utils.Weighted;
import ezgames.utils.collections.simple.SimpleCollection;
import ezgames.utils.exceptions.NullArgumentException;


//TODO: document and test
//TODO: convert constructors to static factories
public class WeightedOpenRandomElementChooser<E> extends OpenRandomElementChooser<Weighted<E>>
{

	WeightedOpenRandomElementChooser(SimpleCollection<Weighted<E>> coll, SimpleRandom rand, ChooserStrategy<Weighted<E>> strategy) throws NullArgumentException
	{
		super(coll, rand, strategy);
	}
	
	WeightedOpenRandomElementChooser(SimpleCollection<Weighted<E>> coll, SimpleRandom rand) throws NullArgumentException
	{
		super(coll, rand, new WeightedChooserStrategy<E>());
	}
	
	WeightedOpenRandomElementChooser(SimpleCollection<Weighted<E>> coll) throws NullArgumentException
	{
		super(coll, new EZRandom(), new WeightedChooserStrategy<E>());
	}
	
	
	public E chooseObject()
	{
		return choose().object();
	}
}