package ezgames.utils.collections;

import java.util.Iterator;
import ezgames.annotations.Immutable;
import ezgames.math.hashing.HashUtil;
import ezgames.utils.DataChecker;
import ezgames.utils.IterableUtil;

@Immutable
public abstract class ZArray<T> implements Iterable<T>
{
	// ***************************************************************************
	// Public factory constructors *************************************
	// ***************************************************************************
	/**
	 * Creates an instance of a {@code ZRollerArray> containing the the items
	 * contained within the {@code Iterable} specified
	 * 
	 * @param inList - an {@code Iterable} containing items of type T to be
	 *            inserted into the returned {@code ZRollerArray} instance
	 * @throws ClientDataException if an element in the specified
	 *             {@code Iterable} is null
	 */
	public static <E> ZArray<E> createWithMultipleValues(Iterable<E> inList) throws IllegalArgumentException
	{
		DataChecker.checkDataNotNull(inList, "Cannot create a ZArray from a null Iterable");
		
		if (inList instanceof ZArray<?>)
		{
			return (ZArray<E>) inList;
		}
		
		int numElements = IterableUtil.sizeOf(inList);
		if (numElements == 0)
		{
			throw new IllegalArgumentException("Cannot create a ZArray with 0 elements");
		}
		if (numElements == 1)
		{
			Iterator<E> iter = inList.iterator();
			return new SingleValueZArray<E>(iter.next());
		}
		return new MultiValueZArray<E>(inList);
	}
	
	@SafeVarargs
	public static <E> ZArray<E> createWithMultipleValues(E... inList)
	{
		DataChecker.checkArrayNotEmptyOrNull(inList, "Cannot create a ZArray from a null collection of elements");
		DataChecker.checkArrayDataNotNull(inList, "Cannot create a ZArray from a collection with a null element");
		
		if(inList.length == 1)
		{
			return new SingleValueZArray<E>(inList[0]);
		}
		
		return new MultiValueZArray<E>(inList);
	}
	
/**
    * Creates an instance of a {@code ZRollerArray> containing the 
    * the item specified 
    * @param item - the single element stored within the {@code ZRollerArray}
    * @throws ClientDataException if the item specified is null
    */
	public static <E> ZArray<E> createWithSingleValue(E item) throws IllegalArgumentException
	{
		return new SingleValueZArray<E>(item);
	}
	
	// Public API methods **********************************************
	/**
	 * Method overridden to check that both {@code ZRollerArrays} contain the
	 * same data, rather than checking that they're simply the same object
	 * 
	 * @param o - the object to compare against this object
	 * @return true if and only if o is a {@code ZRollerArray} containing
	 *         objects in it in the same order as this one that all return
	 *         {@code true} when compared to each other with {@code equals()}
	 */
	@Override
	public abstract boolean equals(Object o);
	
	/**
	 * Returns the element at the specified index
	 * 
	 * @param index - index of the array from which to get the data from
	 * @return the element at the specified index
	 * @throws {@link IndexOutOfBoundsException} if the given index is less than 0 or greater than size() - 1
	 */
	public abstract T get(int index);
	
	@Override
	public abstract int hashCode();
	
	/**
	 * @return the number of elements stored in the array
	 */
	public abstract int size();
	
	// ***************************************************************************
	// Single-value ZArray
	// ***************************************************************************
	private static final class SingleValueZArray<T> extends ZArray<T>
	{
		
		// Public constructors *************************************
		public SingleValueZArray(T o) throws IllegalArgumentException
		{
			DataChecker.checkDataNotNull(o, "Cannot create a ZArray from a null element");
			item = o;
		}
		
		// Public API methods **********************************************
		@SuppressWarnings("unchecked")
		@Override
		public boolean equals(Object o)
		{
			// quick check
			if (o == item)
			{
				return true;
			}
			
			// type check
			SingleValueZArray<T> other;
			if (o instanceof Iterable<?>)
			{
				try
				{
					other = (SingleValueZArray<T>) o;
					return item.equals(other.item);
				}
				catch (ClassCastException e)
				{
					return false;
				}
			}
			else
			{
				return false;
			}
		}
		
		@Override
		public T get(int index)
		{
			if (index != 0)
			{
				throw new IndexOutOfBoundsException();
			}
			return item;
		}
		
		@Override
		public int hashCode()
		{
			HashUtil hasher = HashUtil.createDefaultHashUtil();
			int curr = hasher.getStartingValue();
			return hasher.hash(item, curr);
		}
		
		@Override
		public Iterator<T> iterator()
		{
			return new SingleValueArrayIterator<T>(this);
		}
		
		@Override
		public int size()
		{
			return 1;
		}
		
		// TODO toString()
		
		// Private fields *************************************************
		private final T item;
		
		// Private Iterator Class *****************************************
		private static final class SingleValueArrayIterator<T> implements Iterator<T>
		{
			private final SingleValueZArray<T> arr;
			private boolean hasNext = true;
			
			public SingleValueArrayIterator(SingleValueZArray<T> inArr)
			{
				arr = inArr;
			}
			
			@Override
			public boolean hasNext()
			{
				return hasNext;
			}
			
			@Override
			public T next()
			{
				if (hasNext)
				{
					hasNext = false;
					return arr.item;
				}
				return null;
			}
			
			@Override
			public void remove()
			{
				throw new UnsupportedOperationException("ZArray is Immutable and does not allow removing elements");
			}
		}
	}
	
	// ***************************************************************************
	// Multi-value ZArray
	// ***************************************************************************
	private static final class MultiValueZArray<T> extends ZArray<T>
	{
		// Public constructors ********************************************
		public MultiValueZArray(Iterable<T> collectionToCopy) throws IllegalArgumentException
		{
			DataChecker.checkIterableNotEmptyOrNull(collectionToCopy, "Cannot create a ZArray from a null or empty iterable");
			
			int numOfElements = getNumberOfElementsIn(collectionToCopy);
			array = new Object[numOfElements];
			insertCollection(collectionToCopy);
		}
		
		@SafeVarargs
		public MultiValueZArray(T... collectionToCopy) throws IllegalArgumentException
		{
			DataChecker.checkArrayNotEmptyOrNull(collectionToCopy, "Cannot create a ZArray from a null or empty list of elements");
			
			array = collectionToCopy;
		}
		
		// Public API methods **********************************************
		@SuppressWarnings("unchecked")
		@Override
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
				try
				{
					other = (MultiValueZArray<T>) o;
				}
				
				catch (ClassCastException e)
				{
					return false;
				}
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
		@Override
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
		
		@Override
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
		
		@Override
		public final Iterator<T> iterator()
		{
			return new MultiValueArrayIterator<T>(this);
		}
		
		@Override
		public int size()
		{
			return array.length;
		}
		
		// TODO toString()
		
		// Private fields **************************************************
		private final Object[] array;// An Object array is much easier to
										// implement than
		
		// an array of type T. With all the public methods restricting
		// input to only objects of type T, this array is still typesafe
		
		// Private setters *************************************************
		// puts the given elements at the current location in the array
		private void insertCollection(Iterable<T> collection) throws IllegalArgumentException
		{
			int index = 0;
			for (T element : collection)
			{
				DataChecker.checkDataNotNull(element, "Cannot create a ZArray with a null element");
				array[index] = element;
				++index;
			}
		}
		
		// returns the number of elements in the given collection
		private int getNumberOfElementsIn(Iterable<T> collection)
		{
			int numOfElements = 0;
			// since we accept all Iterables, not just Collections (that would
			// remove
			// the ability to interact with this class), we must iterate through
			// the
			// given collection and count the number of elements
			for (@SuppressWarnings("unused")
			T o : collection)
			{
				++numOfElements;
			}
			return numOfElements;
		}
		
		// Private inner class: Iterator ************************************
		private static final class MultiValueArrayIterator<T> implements Iterator<T>
		{
			private final MultiValueZArray<T> coll;
			private int nextIndex = 0;
			
			protected MultiValueArrayIterator(MultiValueZArray<T> arr)
			{
				coll = arr;
			}
			
			@Override
			public boolean hasNext()
			{
				return nextIndex < coll.array.length;
			}
			
			@SuppressWarnings("unchecked")
			@Override
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
			
			@Override
			public void remove()
			{
				throw new UnsupportedOperationException("ZArray is Immutable and does not allow removing elements");
			}
		}
		
	}
}
