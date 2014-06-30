package ezgames.utils.collections;

import ezgames.utils.DataChecker;
import ezgames.utils.exceptions.NullArgumentException;

/**
 * <code>Stackable</code> works alongside {@link MlList} for quick, easy-to-read
 * creation of collections.  Many of the UDice System's domain objects use
 * this interface in order to build up collections of them simply.
 * TODO: finish
 * <p>See {@link ezgames.utils.collections} for a better idea of the entire collections
 * package and <code>Stackable</code>'s place within it.</p>
 * 
 * @param <T>
 */
public interface Stackable<T extends Stackable<T>>
{
	/**
	 * TODO:
	 * @param another
	 * @return
	 * @throws NullArgumentException
	 */
	@SuppressWarnings("unchecked")
	default MlList<T> and(T another) throws NullArgumentException
	{
		DataChecker.checkDataNotNull(another, "Cannot stack null items together");
		
		MlList<T> list = MlList.startWith(another).add((T)this);
		
		return list;
	}
}