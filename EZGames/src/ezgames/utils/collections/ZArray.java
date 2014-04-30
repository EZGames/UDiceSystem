package ezgames.utils.collections;

import java.util.Iterator;
import java.util.stream.Stream;
import ezgames.utils.DataChecker;
import ezgames.utils.exceptions.NullArgumentException;

public interface ZArray<E> extends SimpleCollection<E>
{
	E[] asArray();
	
	default Stream<E> stream()
	{
		return Stream.of(this.asArray());
	}
	
	/**
	 * Creates an instance of a {@code ZArray} containing the items
	 * contained within the {@code SimpleCollection} specified 
	 * @param inList - an {@code SimpleCollection} containing items of type T to be
	 *            inserted into the returned {@code ZRollerArray} instance
	 * @throws IllegalArgumentException if the given collection is empty
	 * @throws NullArgumentException if an element in the specified
	 *             {@code SimpleCollection} is null
	 */
	public static <E> ZArray<E> createFromSimpleCollection(SimpleCollection<E> inList) throws IllegalArgumentException, NullArgumentException
	{
		DataChecker.checkDataNotNull(inList, "Cannot create a ZArray from a null Iterable");
		
		if (inList instanceof ZArray<?>)
		{
			return (ZArray<E>)inList;
		}
		
		int numElements = inList.size();
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
	
	/**
	 * Creates an instance of a {@code ZArray} containing the items given
	 * in the varargs parameter.
	 * @param inList
	 * @throws NullArgumentException if an item specified is null
	 */
	@SafeVarargs
	public static <E> ZArray<E> createFromValues(E... inList) throws NullArgumentException
	{
		DataChecker.checkArrayNotEmptyOrNull(inList, "Cannot create a ZArray from a null collection of elements");
		DataChecker.checkArrayDataNotNull(inList, "Cannot create a ZArray from a collection with a null element");
		
		if(inList.length == 1)
		{
			return new SingleValueZArray<E>(inList[0]);
		}
		
		return new MultiValueZArray<E>(inList);
	}
}
