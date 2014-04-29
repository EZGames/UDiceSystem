package ezgames.utils.collections;

import ezgames.utils.DataChecker;
import ezgames.utils.exceptions.NullArgumentException;

//TODO Stackable's documentation
public interface Stackable<T extends Stackable<T>>
{
	@SuppressWarnings("unchecked")
	default MlList<T> and(T another) throws NullArgumentException
	{
		DataChecker.checkDataNotNull(another, "Cannot stack null items together");
		
		MlList<T> list = MlList.empty();
		list = list.add(another).add((T)this);
		
		return list;
	}
}