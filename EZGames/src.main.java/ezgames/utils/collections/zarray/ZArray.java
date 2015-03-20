package ezgames.utils.collections.zarray;

import java.util.Iterator;
import java.util.stream.Stream;
import ezgames.utils.collections.simple.SimpleCollection;

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
	 * @param inList - a {@code SimpleCollection} containing items of type T to be
	 *            inserted into the returned {@code ZRollerArray} instance
	 */
	public static <E> ZArray<E> createFromSimpleCollection(SimpleCollection<E> inList)
	{
		if (inList instanceof ZArray<?>)
		{
			return (ZArray<E>)inList;
		}
		
		if (inList.size() == 1)
		{
			Iterator<E> iter = inList.iterator();
			return new SingleValueZArray<E>(iter.next());
		}
		return new MultiValueZArray<E>(inList);
	}
	
	/**
	 * Creates an instance of a {@code ZArray} containing the items given
	 * in the varargs parameter.
	 * @param inList - a varargs list of values to put into the new ZArray
	 */
	@SafeVarargs
	public static <E> ZArray<E> createFromValues(E... inList)
	{
		if(inList.length == 1)
		{
			return new SingleValueZArray<E>(inList[0]);
		}
		
		return new MultiValueZArray<E>(inList);
	}
	
	//TODO: make a Stream Collector for ZArray
}
