package ezgames.utils;

import java.util.Collection;
import java.util.List;

//TODO document & test
public class IterableUtil
{
	//***************************************************************************
	// Public utility methods
	//***************************************************************************
	public static int sizeOf(Iterable<?> iterable)
	{
		if (iterable instanceof Collection<?>) { return ((Collection<?>) iterable).size(); }
		
		int size = 0;
		for (@SuppressWarnings("unused")
		Object o : iterable)
		{
			++size;
		}
		return size;
	}
	
	//TODO: clean code up
	@SuppressWarnings("unchecked")
	public static <T> T getFrom(Iterable<T> iterable, int index) throws IndexOutOfBoundsException
	{
		//could throw IndexOutOfBoundsException
		if (iterable instanceof List<?>) { return (T) ((List<?>) iterable).get(index); }
		
		return elementAt(iterable, index);
	}
	
	public static <T> int indexOf(Iterable<T> iterable, T obj)
	{
		int index = 0;
		for (T t : iterable)
		{
			if (t.equals(obj)) { return index; }
		}
		return -1;
	}
	
	public static <T> boolean contains(Iterable<T> iterable, T obj)
	{
		for (T t : iterable)
		{
			if (t.equals(obj)) { return true; }
		}
		return false;
	}
	
	//***************************************************************************
	// Private helper methods
	//***************************************************************************
	public static <T> void checkIndex(Iterable<T> iterable, int index)
	{
		if (index > sizeOf(iterable) - 1 || index < 0) 
		{ 
			throw new IndexOutOfBoundsException("Index " + index + " is out of the bounds of " + iterable);
		}
	}
	
	public static <T> T elementAt(Iterable<T> iterable, int index)
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
}
