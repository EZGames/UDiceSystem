package ezgames.utils.collections.rec;

import ezgames.math.random.SimpleRandom;
import ezgames.utils.collections.simple.SimpleCollection;
import ezgames.utils.exceptions.NullArgumentException;

//TODO: document and test
public class OpenRandomElementChooser<E> extends RandomElementChooser<E>
{
	//***************************************************************************
	// Public constructor
	//***************************************************************************
	public OpenRandomElementChooser(SimpleCollection<E> coll, SimpleRandom rand, ChooserStrategy<E> strategy) throws NullArgumentException
	{
		super(coll, rand);
		this.strategy = strategy;
	}

	@Override
	public E choose()
	{
		return strategy.choose(coll, rand);
	}
	
	protected final ChooserStrategy<E> strategy;
}
