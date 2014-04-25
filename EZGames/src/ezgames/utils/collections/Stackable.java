package ezgames.utils.collections;

import ezgames.utils.DataChecker;

//TODO Stackable's documentation
public interface Stackable<T extends Stackable<T>>
{
	@SuppressWarnings("unchecked")
	public default MlList<T> and(T another)
	{
		DataChecker.checkDataNotNull(another, "Cannot stack null items together");
		
		MlList<T> list = MlList.empty();
		list = list.add(another).add((T)this);
		
		return list;
	}
}