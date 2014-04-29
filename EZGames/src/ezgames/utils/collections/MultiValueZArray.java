package ezgames.utils.collections;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import ezgames.utils.DataChecker;
import ezgames.utils.exceptions.NullArgumentException;

final class MultiValueZArray<T> implements SimpleCollection<T>
{
	//***************************************************************************
	// Public constructors
	//***************************************************************************
	public MultiValueZArray(SimpleCollection<T> collectionToCopy) throws NullArgumentException
	{
		DataChecker.checkIterableNotEmptyOrNull(collectionToCopy, "Cannot create a ZArray from a null or empty iterable");
		
		int numOfElements = collectionToCopy.size();
		array = new Object[numOfElements];
		insertCollection(collectionToCopy);
	}
	
	@SafeVarargs
	public MultiValueZArray(T... collectionToCopy) throws NullArgumentException
	{
		DataChecker.checkArrayNotEmptyOrNull(collectionToCopy, "Cannot create a ZArray from a null or empty list of elements");
		
		array = collectionToCopy;
	}
	
	//***************************************************************************
	// Public API methods
	//***************************************************************************
	public boolean contains(T obj)
	{
		return indexOf(obj) != -1;
	}
	
	@SuppressWarnings("unchecked")
	public final boolean equals(Object o)
	{
		// do the fast check first
		if (o == this)
		{
			return true;
		}
		
		// check type and cast if it's the same type
		MultiValueZArray<T> other;
		if (o instanceof MultiValueZArray<?>)
		{
			other = (MultiValueZArray<T>) o;
		}
		else
		{
			return false;
		}
		
		// check size
		if (other.array.length != array.length) return false;
		
		// check the items contained within
		int size = array.length;
		for (int i = 0; i < size; ++i)
		{
			if (!array[i].equals(other.array[i]))
			{
				return false;
			}
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public final T get(int index)
	{
		try
		{
			return (T) array[index];
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			throw new IndexOutOfBoundsException();
		}
	}
	
	public int hashCode()
	{
		int result = 31;
		int size = array.length;
		for (int i = 0; i < size; i++)
		{
			result = 31 * result + array[i].hashCode();
		}
		return result;
	}
	
	public int indexOf(T obj)
	{
		int index = 0;
		for(Object item : array)
		{
			if(item.equals(obj))
			{
				return index;
			}
			index++;
		}
		return -1;		
	}
	
	public final Iterator<T> iterator()
	{
		return new MultiValueArrayIterator<T>(this);
	}
	
	public int size()
	{
		return array.length;
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
	private final Object[] array;// An Object array is much easier to implement than
					// an array of type T. With all the public methods restricting
					// input to only objects of type T, this array is still typesafe
	
	//***************************************************************************
	// Private setters
	//***************************************************************************
	// puts the given elements at the current location in the array
	private void insertCollection(SimpleCollection<T> collection) throws NullArgumentException
	{
		int index = 0;
		for (T element : collection)
		{
			DataChecker.checkDataNotNull(element, "Cannot create a ZArray with a null element");
			array[index] = element;
			++index;
		}
	}
	
	//***************************************************************************
	// Private inner Iterator
	//***************************************************************************
	private static final class MultiValueArrayIterator<T> implements Iterator<T>
	{
		private final MultiValueZArray<T> coll;
		private int nextIndex = 0;
		
		protected MultiValueArrayIterator(MultiValueZArray<T> arr)
		{
			coll = arr;
		}
		
		public boolean hasNext()
		{
			return nextIndex < coll.array.length;
		}
		
		@SuppressWarnings("unchecked")
		public T next()
		{
			if (hasNext())
			{
				return (T) coll.array[nextIndex++];
			}
			else
			{
				return null;
			}
		}
		
		public void remove()
		{
			throw new UnsupportedOperationException("ZArray is Immutable and does not allow removing elements");
		}
	}
	
}
