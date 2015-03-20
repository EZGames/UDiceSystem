package ezgames.utils.collections.rec;

import ezgames.math.random.SimpleRandom;
import ezgames.utils.collections.simple.SimpleCollection;

public class SimpleChooserStrategy<E> implements ChooserStrategy<E>
{
	
	@Override
	public E choose(SimpleCollection<E> coll, SimpleRandom rand)
	{
		int randIndex = rand.randBetween(0, coll.size() - 1);
		
		return coll.get(randIndex);
	}
	
}
