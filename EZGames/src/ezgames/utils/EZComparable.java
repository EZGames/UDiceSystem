package ezgames.utils;

public interface EZComparable<T> extends Comparable<T>
{
   default boolean lessThan(T other)
   {
	  return compareTo(other) < 0;
   }
   
   default boolean lessThanOrEqualTo(T other)
   {
	  return compareTo(other) <= 0;
   }
   
   default boolean equalTo(T other)
   {
	  return compareTo(other) == 0;
   }
   
   default boolean greaterThanOrEqualTo(T other)
   {
	  return compareTo(other) >= 0;
   }
   
   default boolean greaterThan(T other)
   {
	  return compareTo(other) > 0;
   }
}
