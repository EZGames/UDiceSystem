package ezgames.utils;

import ezgames.utils.collections.SimpleCollection;
import ezgames.utils.exceptions.NullArgumentException;

//TODO: finish documentation, then test
public class DataChecker
{
	/**
	 * Throws an {@link NullArgumentException} with the given message if o is
	 * null 
	 * @param o - the Object being checked for null
	 * @param errMessage - the message included in the exception
	 * @throws NullArgumentException if the given Object is null
	 */
	public static void checkDataNotNull(Object o, String errMessage) throws NullArgumentException
	{
		if (o == null)
		{
			throw new NullArgumentException(errMessage, new NullPointerException());
		}
	}
	
	/**
	 * Throws an {@link NullArgumentException} with the given message if o is
	 * null or empty. If you only want it to throw an exception if the
	 * {@code String} is null, but not when it's empty, use
	 * {@code checkDataNotNull()} instead.	  
	 * @param o - the Object being checked for null
	 * @param errMessage - the message included in the exception
	 * @throws NullArgumentException if the given String is null or empty
	 */
	public static void checkStringDataNotNull(String o, String errMessage) throws NullArgumentException
	{
		if (o == null)
		{
			throw new NullArgumentException(errMessage, new NullPointerException());
		}
		if (o.isEmpty())
		{
			throw new NullArgumentException(errMessage, new IllegalArgumentException("Empty String"));
		}
	}
	
	/**
	 * Throws an {@link NullArgumentException} with the given message if i or
	 * any of its elements are null.	  
	 * @param i - the {@code Iterable} object being checked
	 * @param errMessage - the message included in the exception
	 * @throws NullArgumentException if the given {@code Iterable} or any of
	 *             its elements are null
	 */
	public static void checkIterableDataNotNull(SimpleCollection<?> i, String errMessage) throws NullArgumentException
	{
		checkDataNotNull(i, errMessage);
		
		for (Object o : i)
		{
			checkDataNotNull(o, errMessage);
		}
	}
	
	/**
	 * Throws an {@link NullArgumentException} with the given message if i is
	 * null or any of its elements are null or empty. If you don't want it to
	 * throw an exception for empty {@code Strings}, use
	 * {@code checkIterableDataNotNull()} instead.	 
	 * @param i - the {@code Iterable} object being checked
	 * @param errMessage - the message included in the exception
	 * @throws NullArgumentException if the given {@code Iterable} is null or
	 *             any of its elements are null or empty
	 */
	public static void checkStringIterableNotNull(SimpleCollection<String> i, String errMessage) throws NullArgumentException
	{
		checkDataNotNull(i, errMessage);
		
		for (String o : i)
		{
			checkStringDataNotNull(o, errMessage);
		}
	}
	
	/**
	 * Throws an {@link NullArgumentException} with the given message if i is
	 * null or contains no elements. If you want to throw an exception for null
	 * elements, you can use {@code checkIterableDateNotNull()} in conjunction
	 * with or instead of this method.
	 * @param i - the {@code Iterable} object being checked
	 * @param errMessage - the message included in the exception
	 * @throws NullArgumentException if the given {@code Iterable} is null or
	 *             empty
	 */
	public static void checkIterableNotEmptyOrNull(SimpleCollection<?> i, String errMessage) throws NullArgumentException
	{
		checkDataNotNull(i, errMessage);
		if (i.size() == 0)
		{
			throw new NullArgumentException(errMessage);
		}
	}
	
	/**
	 * Throws an {@link NullArgumentException} with the given message if arr or
	 * any of its elements are null.	  
	 * @param i - the array being checked
	 * @param errMessage - the message included in the exception
	 * @throws NullArgumentException if the given array or any of
	 *             its elements are null
	 */
	public static void checkArrayDataNotNull(Object[] arr, String errMessage) throws NullArgumentException
	{
		checkDataNotNull(arr, errMessage);
		for (Object o : arr)
		{
			checkDataNotNull(o, errMessage);
		}
	}
	
	/**
	 * Throws an {@link NullArgumentException} with the given message if arr is
	 * null or any of its elements are null or empty. If you don't want it to
	 * throw an exception for empty {@link String}s, use
	 * {@link checkArrayDataNotNull()} instead.	 
	 * @param i - the array being checked
	 * @param errMessage - the message included in the exception
	 * @throws NullArgumentException if the given array is null or
	 *             any of its elements are null or empty
	 */
	public static void checkStringArrayNotNull(String[] arr, String errMessage) throws NullArgumentException
	{
		checkDataNotNull(arr, errMessage);
		for(String s : arr)
		{
			checkStringDataNotNull(s, errMessage);
		}
	}
	
	/**
	 * Throws an {@link NullArgumentException} with the given message if arr is
	 * null or contains no elements. If you want to throw an exception for null
	 * elements, you can use {@link checkArrayDataNotNull()} in conjunction
	 * with or instead of this method.
	 * @param arr - the array being checked
	 * @param errMessage - the message included in the exception
	 * @throws NullArgumentException if the given array is null or empty
	 */
	public static void checkArrayNotEmptyOrNull(Object[] arr, String errMessage) throws NullArgumentException
	{
		checkDataNotNull(arr, errMessage);
		if(arr.length == 0)
		{
			throw new NullArgumentException("empty array: " + errMessage);
		}
	}
}
