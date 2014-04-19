package ezgames.utils;

//TODO: finish documentation, then test
public class DataChecker
{
   /**
    * Throws an {@code IllegalArgumentException} with the given message if o is null
    * @param o - the Object being checked for null
    * @param errMessage - the message included in the exception
    * @throws IllegalArgumentException if the given Object is null 
    */
   public static void checkDataNotNull(Object o, String errMessage)
   {
      if(o == null)
      {
	 throw new IllegalArgumentException(errMessage, new NullPointerException());
      }
   }
   
   /**
    * Throws an {@code IllegalArgumentException} with the given message if o is null or empty.
    * If you only want it to throw an exception if the {@code String} is null, but not when it's empty, use {@code checkDataNotNull()} instead.
    * @param o - the Object being checked for null
    * @param errMessage - the message included in the exception
    * @throws IllegalArgumentException if the given String is null or empty
    */
   public static void checkStringDataNotNull(String o, String errMessage)
   {
      if(o == null)
      {
	 throw new IllegalArgumentException(errMessage, new NullPointerException());
      }
      if(o.isEmpty())
      {
	 throw new IllegalArgumentException(errMessage, new IllegalArgumentException("Empty String"));
      }
   }
   
   /**
    * Throws an {@code IllegalArgumentException} with the given message if i or any of its elements are null.
    * @param i - the {@code Iterable} object being checked
    * @param errMessage - the message included in the exception
    * @throws IllegalArgumentException if the given {@code Iterable} or any of its elements are null
    */
   public static void checkIterableDataNotNull(Iterable<?> i, String errMessage)
   {
      checkDataNotNull(i, errMessage);
      
      for(Object o : i)
      {
	 checkDataNotNull(o, errMessage);
      }
   }
   
   /**
    * Throws an {@code IllegalArgumentException} with the given message if i is null or any of its elements are null or empty.
    * If you don't want it to throw an exception for empty {@code Strings}, use {@code checkIterableDataNotNull()} instead.
    * @param i - the {@code Iterable} object being checked
    * @param errMessage - the message included in the exception
    * @throws IllegalArgumentException if the given {@code Iterable} is null or any of its elements are null or empty
    */
   public static void checkStringIterableNotNull(Iterable<String> i, String errMessage)
   {
      checkDataNotNull(i, errMessage);
      
      for(String o : i)
      {
	 checkStringDataNotNull(o, errMessage);
      }
   }
   
   /**
    * Throws an {@code IllegalArgumentException} with the given message if i is null or contains no elements.
    * If you want to throw an exception for null elements, you can use {@code checkIterableDateNotNull()} in conjunction with or instead of this method.
    * @param i - the {@code Iterable} object being checked
    * @param errMessage - the message included in the exception
    * @throws IllegalArgumentException if the given {@code Iterable} is null or empty
    */
   public static void checkIterableNotEmptyOrNull(Iterable<?> i, String errMessage)
   {
      checkDataNotNull(i, errMessage);
      if(IterableUtil.sizeOf(i) == 0)
      {
	 throw new IllegalArgumentException(errMessage);
      }
   }
}
