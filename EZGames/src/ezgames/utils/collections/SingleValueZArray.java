package ezgames.utils.collections;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import ezgames.hashing.HashGenerator;
import ezgames.utils.DataChecker;
import ezgames.utils.exceptions.NullArgumentException;

final class SingleValueZArray<T> implements SimpleCollection<T>
{
	//***************************************************************************
	// Public constructors
	//***************************************************************************
	public SingleValueZArray(T o) throws NullArgumentException
	{
		DataChecker.checkDataNotNull(o, "Cannot create a ZArray from a null element");
		item = o;
	}
	
	//***************************************************************************
	// Public API methods
	//***************************************************************************
	public boolean contains(T obj)
	{
		return item.equals(obj);
	}
	
	@SuppressWarnings("unchecked")
	public boolean equals(Object o)
	{
		// quick check
		if (o == item)
		{
			return true;
		}
		
		// type check
		SingleValueZArray<T> other;
		if (o instanceof SingleValueZArray<?>)
		{
			other = (SingleValueZArray<T>) o;
			return item.equals(other.item);
		}
		else
		{
			return false;
		}
	}
	
	public T get(int index)
	{
		if (index != 0)
		{
			throw new IndexOutOfBoundsException();
		}
		return item;
	}
	
	public int hashCode()
	{
		HashGenerator hasher = HashGenerator.createDefaultHashUtil();
		int curr = hasher.getStartingValue();
		return hasher.hash(item, curr);
	}
	
	public int indexOf(T obj)
	{
		if(item.equals(obj))
		{
			return 0;
		}
		else
		{
			return -1;
		}
	}
	
	public Iterator<T> iterator()
	{
		return new SingleValueArrayIterator<T>(this);
	}
	
	public int size()
	{
		return 1;
	}
	
	public Stream<T> stream()
	{
		Spliterator<T> split = Spliterators.spliterator(iterator(), size(), Spliterator.IMMUTABLE);
		return StreamSupport.stream(split, false);
	}
	
	// TODO toString()
	//***************************************************************************
	// Private fields
	//***************************************************************************
	private final T item;
	
	//***************************************************************************
	// Private Iterator Class
	//***************************************************************************
	private static final class SingleValueArrayIterator<T> implements Iterator<T>
	{
		private final SingleValueZArray<T> arr;
		private boolean hasNext = true;
		
		public SingleValueArrayIterator(SingleValueZArray<T> inArr)
		{
			arr = inArr;
		}
		
		
		public boolean hasNext()
		{
			return hasNext;
		}
		
		
		public T next()
		{
			if (hasNext)
			{
				hasNext = false;
				return arr.item;
			}
			return null;
		}
		
		
		public void remove()
		{
			throw new UnsupportedOperationException("ZArray is Immutable and does not allow removing elements");
		}
	}
}
