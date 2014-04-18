package ezgames.utils.collections;

import java.util.Iterator;

import ezgames.utils.Weighted;

public class WeightedIterator<O> implements Iterator<O>
{
	private final Iterator<Weighted<O>> iter;
	
	WeightedIterator(final Iterator<Weighted<O>> iterator)
	{
		iter = iterator;
	}
	
	public boolean hasNext()
	{
		return iter.hasNext();
	}
	
	public O next()
	{
		return iter.next().object();
	}
	
	public void remove()
	{
		iter.remove();
	}
}
