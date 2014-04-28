package ezgames.utils.collections;

import java.util.Iterator;
import java.util.List;

class SimpleList<T> implements SimpleCollection<T>
{
	//************************************************************************
	// Public constructors
	//************************************************************************
	public SimpleList(List<T> list)
	{
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
	
	public int indexOf(T obj)
	{
		return list.indexOf(obj);
	}
	
	public boolean contains(T obj)
	{
		return list.contains(obj);
	}
	
	public Iterator<T> iterator()
	{
		return list.iterator();
	}
	
	//************************************************************************
	// Private fields
	//************************************************************************
	private final List<T> list;
}
