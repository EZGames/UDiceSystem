package ezgames.utils.collections.simple;

import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Stream;
import ezgames.utils.DataChecker;
import ezgames.utils.exceptions.NullArgumentException;

/**
 * 
 *
 * @param <T>
 */
class SimpleCollectionImpl<T> implements SimpleCollection<T>
{
	//************************************************************************
	// Public constructors
	//************************************************************************
	public SimpleCollectionImpl(Collection<T> iter) throws NullArgumentException
	{
		DataChecker.checkDataNotNull(iter, "Cannot create SimpleList from null Iterable");
		this.collection = iter;
	}

	//************************************************************************
	// Public API methods
	//************************************************************************
	public Iterator<T> iterator()
	{
		return collection.iterator();
	}

	public boolean contains(T obj)
	{
		return collection.contains(obj);
	}

	public T get(int index)
	{
		checkIndex(index);
		
		int currIndex = 0;
		T out = null;
		
		for(T obj : collection)
		{
			if(currIndex == index)
			{
				out = obj;
				break;
			}
			++currIndex;
		}
		
		return out;
	}
	
	public Collection<T> getOriginalCollection()
	{
		return collection;
	}

	public Optional<Integer> indexOf(T obj)
	{
		int index = 0;
		for (T t : collection)
		{
			if (t.equals(obj)) { return Optional.of(index); }
		}
		return Optional.empty();
	}

	public int size()
	{
		return collection.size();
	}
	
	public Stream<T> stream()
	{
		return collection.stream();
	}
	
	//************************************************************************
	// Private fields
	//************************************************************************
	private Collection<T> collection;
	
	//************************************************************************
	// Private helper methods
	//************************************************************************
	private void checkIndex(int index)
	{
		if (index > this.size() - 1 || index < 0) 
		{ 
			throw new IndexOutOfBoundsException("Index " + index + " is out of the bounds of " + collection);
		}
	}
}
