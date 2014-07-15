package ezgames.utils.collections;

import ezgames.utils.DataChecker;
import ezgames.utils.exceptions.NullArgumentException;

/**
 * <code>Stackable</code> works alongside {@link MlList} for quick, easy-to-read
 * creation of collections.  Many of the UDice System's domain objects use
 * this interface in order to build up collections of them simply.
 * <p>For example, you can make a new list of objects like this: {@code 
 * obj1.and(obj2).and(obj3).and(obj4)}</p>
 * <p>See {@link ezgames.utils.collections} for a better idea of the entire collections
 * package and <code>Stackable</code>'s place within it.</p>
 * 
 * @param <T> - the type implementing this interface
 */
public interface Stackable<T extends Stackable<T>>
{
	/**
	 * <p>Creates an {@link MlList} from two objects
	 * of the same type, {@code T}.</p>
	 * <p> This, coupled with {@code MlList}'s {@link #and} method, allows users 
	 * to create collections of {@code Stackable} objects very simply.</p> 
	 * <p>ex. {@code obj1.and(obj2).and(obj3).and(obj4)}</p>
	 * @param another - the object to add to the list after this one 
	 * @return an {@code MlList} containing this object and {@code another}
	 * @throws NullArgumentException if {@code another} is null 
	 */
	@SuppressWarnings("unchecked")
	default MlList<T> and(T another) throws NullArgumentException
	{
		DataChecker.checkDataNotNull(another, "Cannot stack null items together");
		
		MlList<T> list = MlList.startWith(another).add((T)this);
		
		return list;
	}
}