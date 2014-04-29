package ezgames.utils.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

class SimpleCollectionBuilder<T> implements SimpleCollection<T>
{
	//************************************************************************
	// Public constructors
	//************************************************************************
	public SimpleCollectionBuilder(Iterable<T> iter)
	{
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

	public T get(int index)
	{
		//could throw IndexOutOfBoundsException
		if (iterable instanceof List<?>) 
		{ 
			return ((List<T>) iterable).get(index); 
		}
		
		return elementAt(iterable, index);
	}

	public int indexOf(T obj)
	{
		int index = 0;
		for (T t : iterable)
		{
			if (t.equals(obj)) { return index; }
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
	
	//************************************************************************
	// Private helper methods
	//************************************************************************
	private T elementAt(Iterable<T> iterable, int index)
	{
		checkIndex(iterable, index);
		
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
		
		return out;
	}
	
	private void checkIndex(Iterable<T> iterable, int index)
	{
		if (index > this.size() - 1 || index < 0) 
		{ 
			throw new IndexOutOfBoundsException("Index " + index + " is out of the bounds of " + iterable);
		}
	}
}
