package ezgames.utils.collections.zarray;

import java.util.Iterator;
import java.util.Optional;
import ezgames.annotations.Immutable;
import ezgames.utils.collections.simple.SimpleCollection;

@Immutable
final class MultiValueZArray<E> implements ZArray<E>
{
	//***************************************************************************
	// Public constructors
	//***************************************************************************
	public MultiValueZArray(SimpleCollection<E> collectionToCopy)
	{
		int numOfElements = collectionToCopy.size();
		array = new Object[numOfElements];
		insertCollection(collectionToCopy);
	}
	
	@SafeVarargs
	public MultiValueZArray(E... collectionToCopy)
	{
		array = collectionToCopy;
	}
	
	//***************************************************************************
	// Public API methods
	//***************************************************************************
	@SuppressWarnings("unchecked")
	public E[] asArray()
	{
		return (E[])array;
	}
	
	public boolean contains(E obj)
	{
		return indexOf(obj).isPresent();
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
		MultiValueZArray<E> other;
		if (o instanceof MultiValueZArray<?>)
		{
			other = (MultiValueZArray<E>) o;
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
	public final E get(int index)
	{
		try
		{
			return (E) array[index];
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
	
	public Optional<Integer> indexOf(E obj)
	{
		int index = 0;
		for(Object item : array)
		{
			if(item.equals(obj))
			{
				return Optional.of(index);
			}
			index++;
		}
		return Optional.empty();		
	}
	
	public final Iterator<E> iterator()
	{
		return new MultiValueArrayIterator<E>(this);
	}
	
	public int size()
	{
		return array.length;
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
	private void insertCollection(SimpleCollection<E> collection)
	{
		int index = 0;
		for (E element : collection)
		{
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
