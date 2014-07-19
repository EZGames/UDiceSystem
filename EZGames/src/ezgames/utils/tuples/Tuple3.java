package ezgames.utils.tuples;

import java.util.function.Consumer;

public interface Tuple3<T1, T2, T3>
{
	/**
	 * @return the first object in the tuple
	 */
	T1 one();
	/**
	 * Provide a function that uses the first object in the tuple. Returns the
	 * same tuple, allowing you to chain commands.
	 *	@see {@link Tuple2#useOne} for a fuller description of the purpose of this method
	 * @param func - the function to "consume" the first object
	 * @return <code>this</code>, for method chaining.
	 */
	Tuple3<T1, T2, T3> useOne(Consumer<? super T1> func);
	/**
	 * @return the second object in the tuple
	 */
	T2 two();
	/**
	 * Provide a function that uses the second object in the tuple. Returns the
	 * same tuple, allowing you to chain commands.
	 *	@see {@link Tuple2#useOne} for a fuller description of the purpose of this method
	 * @param func - the function to "consume" the second object
	 * @return <code>this</code>, for method chaining.
	 */
	Tuple3<T1, T2, T3> useTwo(Consumer<? super T2> func);
	/**
	 * @return the third object in the tuple
	 */
	T3 three();
	/**
	 * Provide a function that uses the third object in the tuple. Returns the
	 * same tuple, allowing you to chain commands.
	 *	@see {@link Tuple2#useOne} for a fuller description of the purpose of this method
	 * @param func - the function to "consume" the third object
	 * @return <code>this</code>, for method chaining.
	 */
	Tuple3<T1, T2, T3> useThree(Consumer<? super T3> func);
	/**
	 * @return the same tuple in reverse order
	 */
	Tuple3<T3, T2, T1> swap();
	
	//***************************************************************************
	// Static factory method
	//***************************************************************************
	//hides the existance of Tuple3Impl
	/**
	 * Creates and returns a new Tuple3 containing the given objects
	 * @param one - the first object of the tuple
	 * @param two - the second object of the tuple
	 * @param three - the third object of the tuple
	 * @return the created Tuple3 containing the given objects
	 */
	public static <T1, T2, T3> Tuple3<T1, T2, T3> of(T1 one, T2 two, T3 three)
	{
		return new Tuple3Impl<>(one, two, three);
	}
}
