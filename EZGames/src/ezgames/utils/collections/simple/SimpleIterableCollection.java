package ezgames.utils.collections.simple;

import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import ezgames.utils.DataChecker;
import ezgames.utils.exceptions.NullArgumentException;

class SimpleIterableCollection<T> implements SimpleCollection<T>
{
	//************************************************************************
	// Public constructors
	//************************************************************************
	public SimpleIterableCollection(Iterable<T> iter) throws NullArgumentException
	{
		DataChecker.checkDataNotNull(iter, "Cannot create SimpleList from null Iterable");
		this.iterable = iter;
	}

	//************************************************************************
	// Public API methods
	//************************************************************************
	public Iterator<T> iterator()
	{
		return iterable.iterator();
	}

	public boolean contains(T obj)
	{
		return indexOf(obj) != -1;
	}

	@Override
	public T get(int index) throws IndexOutOfBoundsException
	{
		int currIndex = 0;
		T out = null;
		
		for(T obj : iterable)
		{
			if(currIndex == index)
			{
				out = obj;
				break;
			}
			++currIndex;
		}
		
		if(null == out)
		{
			throw new IndexOutOfBoundsException("Index " + index + " is out of the bounds of " + iterable);
		}
		
		return out;
	}

	public int indexOf(T obj)
	{
		int index = 0;
		for (T t : iterable)
		{
			if (t.equals(obj)) { return index; }
			index++;
		}
		return -1;
	}

	public int size()
	{
		if (iterable instanceof Collection<?>) { return ((Collection<?>) iterable).size(); }
		
		int size = 0;
		for (@SuppressWarnings("unused")	Object o : iterable)
		{
			++size;
		}
		return size;
	}
	
	public Stream<T> stream()
	{
		Spliterator<T> split = Spliterators.spliterator(iterator(), size(), Spliterator.SIZED);
		return StreamSupport.stream(split, false);
	}
	
	//************************************************************************
	// Private fields
	//************************************************************************
	private Iterable<T> iterable;
}
