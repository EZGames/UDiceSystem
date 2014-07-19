package ezgames.utils.tuples;

import java.util.function.Consumer;

/**
 * Represents a 2-part tuple, as seen in other programming languages, essentially
 * allowing for multiple values to be passed around together (usually in a return
 * statement) without being intrinsically related.
 * <p>
 * It's main purpose is to serve as a return object, allowing methods to, in effect,
 * return more than one object.</p>
 * <p>
 * If one of the objects being returned is only sometimes returned, it is
 * suggested that you return an {@link java.util.Optional Optional} of that type,
 * forcing those that use your returned tuple to think about the possibility of
 * the object containing no value.</p> 
 * @param <T> the type of the first object in the tuple
 * @param <U> the type of the second object in the tuple
 */
public interface Tuple2<T, U>
{
	/**
	 * @return the first object in the tuple
	 */
	T one();	
	/**
	 * Provide a function that uses the first object in the tuple. Returns the
	 * same tuple, allowing you to chain commands.
	 * <p>
	 * The primary purpose of this is to allow in-line assignment.</p>
	 * <p>
	 * For example:<br>
	 * <code>
	 * String foo;<br>
	 * String bar;<br>
	 * //the method, baz(), returns a Tuple2&lt;String, String&gt;<br>
	 * baz().useOne((s) -> foo = s).useTwo((s) -> bar = s);
	 * </code>
	 * @param func - the function to "consume" the first object
	 * @return <code>this</code>, for method chaining.
	 */
	Tuple2<T, U> useOne(Consumer<?  super T> func);	
	/**
	 * @return the second object in the tuple
	 */
	U two();	
	/**
	 * Provide a function that uses the second object in the tuple. Returns the
	 * same tuple, allowing you to chain commands.
	 *	@see {@link #useOne} for a fuller description of the purpose of this method
	 * @param func - the function to "consume" the second object
	 * @return <code>this</code>, for method chaining.
	 */
	Tuple2<T, U> useTwo(Consumer<? super U> func);	
	/**
	 * @return the same tuple in reverse order
	 */
	Tuple2<U, T> swap();
	
	//***************************************************************************
	// Public static factories
	//***************************************************************************
	/**
	 * Creates and returns a Tuple2 containing the given objects
	 * @param one - the first object of the tuple
	 * @param two - the second object of the tuple
	 * @return the created Tuple2 containing the given objects
	 */
	public static <T,U> Tuple2<T, U> of(T one, U two)
	{
		return new Tuple2Impl<T,U>(one, two);
	}
}
