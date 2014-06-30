package ezgames.utils.collections.simple;

import java.util.Collection;
import java.util.List;
import ezgames.utils.collections.Streamable;
import ezgames.utils.exceptions.NullArgumentException;

/**
 * TODO: finish; NOTE: talk about how the original objects can be returned the 
 * SimpleCollection versions, but are only returned as List, Collection, or Iterable.
 * <p>See {@link ezgames.utils.collections} for a better idea of the entire collections
 * package and <code>SimpleCollection</code>'s place within it.</p>
 * 
 * @param <E> - the type of the elements stored within the collection
 */
public interface SimpleCollection<E> extends Iterable<E>, Streamable<E>
{
	/**
	 * Checks to see whether the given object is an element of this collection
	 * @param obj - object to check for
	 * @return <code>true</code> if the object is an element of this collection.
	 * Otherwise <code>false</code>.
	 */
	boolean contains(E obj);
	/**
	 * Returns the element at the given index within this collection
	 * @param index - index to get the element from
	 * @return the element at the given index
	 * @throws IndexOutOfBoundsException if the given index is less than 0 or
	 * greater than this collection's size - 1.
	 */
	E get(int index) throws IndexOutOfBoundsException;
	/**
	 * @param obj - the object to get the index of
	 * @return the index of the given object within this collection, or -1 if the
	 * object is not within this collection 
	 */
	int indexOf(E obj);
	/**
	 * @return the number of elements stored within this collection
	 */
	int size();
	
	/**
	 * Creates and returns a <code>SimpleCollection</code> object from the given
	 * <code>Iterable</code> object.
	 * @param coll - an <code>Iterable</code> collection from which to create a new 
	 * <code>SimpleCollection</code> object from.
	 * @return the new <code>SimpleCollection</code> object
	 * @throws NullArgumentException if a null or empty <code>Iterable</code> is
	 * given.
	 */
	static <E> SimpleCollection<E> from(Iterable<E> coll) throws NullArgumentException
	{
		if(coll instanceof List<?>)
		{
			return new SimpleList<E>((List<E>)coll);
		}
		if(coll instanceof Collection<?>)
		{
			return new SimpleCollectionImpl<E>((Collection<E>)coll);
		}
		if(coll instanceof SimpleCollection<?>)
		{
			return (SimpleCollection<E>)coll;
		}
		else
		{
			return new SimpleIterableCollection<E>(coll);
		}
	}
	
}
