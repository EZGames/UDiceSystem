package ezgames.utils.collections;

//TODO Stackable's testing and documentation
//TODO update Stackable to Java 8 interface
public interface Stackable<T extends Stackable<T>>
{
	@SuppressWarnings("unchecked")
	public default MlList<T> and(T another)
	{
		MlList<T> list = MlList.empty();
		list = list.add(another).add((T)this);
		return list;
	}

	@SuppressWarnings("unchecked")
	public default MlList<T> and(Iterable<T> others)
	{
		if(others instanceof MlList<?>)
		{
			return ((MlList<T>)others).add((T)this);
		}
		else
		{
			MlList<T> list = MlList.fromIterable(others);
			return list.add((T)this);
		}
	}
	
	public default MlList<T> and(T... others)
	{
		
	}
}