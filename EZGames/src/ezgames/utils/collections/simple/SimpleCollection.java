package ezgames.utils.collections.simple;

import java.util.Collection;
import java.util.List;
import ezgames.utils.collections.Streamable;
import ezgames.utils.exceptions.NullArgumentException;

public interface SimpleCollection<E> extends Iterable<E>, Streamable<E>
{
	boolean contains(E obj);
	E get(int index) throws IndexOutOfBoundsException;
	int indexOf(E obj);
	int size();
	
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
