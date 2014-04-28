package ezgames.utils.collections;

import java.util.List;

public interface SimpleCollection<T> extends Iterable<T>
{
	boolean contains(T obj);
	T get(int index) throws IndexOutOfBoundsException;
	int indexOf(T obj);
	int size();
	
	static <T> SimpleCollection<T> fromList(List<T> list)
	{
		return new SimpleList<T>(list);
	}
	
	static <T> SimpleCollection<T> from(Iterable<T> coll)
	{
		if(coll instanceof List<?>)
		{
			return new SimpleList<T>((List<T>)coll);
		}
		if(coll instanceof SimpleCollection<?>)
		{
			return (SimpleCollection<T>)coll;
		}
		else
		{
			return new SimpleCollectionImpl<T>(coll);
		}
	}
	
}
