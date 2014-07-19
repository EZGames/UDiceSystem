package ezgames.utils.collections.simple;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import ezgames.utils.DataChecker;
import ezgames.utils.exceptions.NullArgumentException;

class SimpleList<T> implements SimpleCollection<T>
{
	//************************************************************************
	// Public constructors
	//************************************************************************
	public SimpleList(List<T> list) throws NullArgumentException
	{
		DataChecker.checkDataNotNull(list, "Cannot create SimpleList from null List");
		this.list = list;
	}
	
	//************************************************************************
	// Public API methods
	//************************************************************************
	public int size()
	{
		return list.size();
	}
	
	public T get(int index)
	{
		return list.get(index);
	}
	
	public List<T> getOriginalList()
	{
		return list;
	}
	
	public Optional<Integer> indexOf(T obj)
	{
		int index = list.indexOf(obj);
		
		return (index != -1) ? Optional.of(index) : Optional.empty(); 
	}
	
	public boolean contains(T obj)
	{
		return list.contains(obj);
	}
	
	public Iterator<T> iterator()
	{
		return list.iterator();
	}
	
	public Stream<T> stream()
	{
		return list.stream();
	}
	
	//************************************************************************
	// Private fields
	//************************************************************************
	private final List<T> list;
}
