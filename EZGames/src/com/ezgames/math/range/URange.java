package com.ezgames.math.range;

import java.util.ArrayList;
import java.util.List;
import com.ezgames.annotations.Immutable;

/**
 * <p>The {@code Range} class is a simple data class that represents a range of
 * numbers from the minimum to the maximum (both are inclusive).</p>
 * <p><b>NOTE:</b> This class does not follow the general standard set by java
 * of making the maximum number exclusive.</p>
 * @author Jacob ZD Zimmerman
 */
@Immutable
public final class URange implements Range
{
   
   // Public factory constructors *********************************************
   /**
    * <p>Returns a {@code Range} object with the given bounds </p>
    * <p><b>NOTE:</b> This class does not follow the general standard set by 
    * java of making the maximum number exclusive.</p>
    * @param min - the lower bound of the new {@code Range} (inclusive)
    * @param max - the upper bound of the new {@code Range} (inclusive)
    * @return a Range object with the given bounds
    */
   public static Range valueOf(int min, int max)
   {
      //check to see if the values need to be swapped
      if(min > max)
      {
    	  int temp = max;
    	  max = min;
    	  min = temp;
      }
      
      //check the cache
      Range out = checkCacheForRange(min, max);
      if(out == null) //Range isn't in the cache
      {
    	  out = buildRange(min, max);
      }
      
      return out;
   }
   
   public static Range fullRange()
   {
	   return valueOf(Integer.MIN_VALUE, Integer.MAX_VALUE);
   }
   
   public static URange.Builder from(int val)
   {
	   return new Builder(val);
   }

   // Public API methods ******************************************************
   @Override
   public int compareTo(Range b)
   {
	  if(this.lessThan(b))
		  return -1;
	  if(this.greaterThan(b))
		  return 1;
	  return 0;
   }
   
   @Override
   public boolean equals(Object o)
   {
      if(this == o) { return true; }
      
      if(!(o instanceof URange)) { return false; }
      
      URange that = (URange) o;
      return (this.minimum == that.minimum && this.maximum == that.maximum);
   }
   
   public int getMinimum() { return minimum; }
   
   public int getMaximum() { return maximum; }
   
   @Override
   public int hashCode()
   {
      if(hashCache == null)
      {
         int result = 31;
         result += 17 * minimum;
         result += 17 + maximum;
         
         hashCache = result;
      }
      return hashCache;
   }
   
   public boolean isInRange(int val)
   {
      return (val >= minimum && val <= maximum);
   }
   
   public boolean overlaps(Range B)
   {
      if(B == null) { return false; }
      return compareTo(B) == 0;
   }

   // Private fields ***********************************************************
   private final int maximum;
   private final int minimum;
   private static final List<Range> cache = new ArrayList<>();
   private Integer hashCache = null;
   
   // Private constructors *****************************************************
   private URange(int min, int max)
   {
      minimum = min;
      maximum = max;
   }
   
   // Private helper(s) ********************************************************
   private boolean lessThan(Range b) { return maximum < b.getMinimum(); }
   
   private boolean greaterThan(Range b) { return minimum > b.getMaximum(); }
   
   private static Range checkCacheForRange(int min, int max)
   {
      for(Range range : cache)
      {
    	  if(haveEqualBounds(range, min, max)) { return range; }
      }      
      return null;
   }
   
   private static boolean haveEqualBounds(Range comparer, int min, int max)
   {
      return (comparer.getMinimum() == min && comparer.getMaximum() == max);
   }
   
   private static Range buildRange(int min, int max)
   {
	   Range out;
	   
	   //if the range is the entire Integer spectrum, then make a FullRange
       if(min == Integer.MIN_VALUE && max == Integer.MAX_VALUE)
       {
     	 out = new FullRange();
       }
       else
       {
     	  out = new URange(min, max);
       }
       cache.add(out);
       return out;
   }
   
   //**************************************************************************
   // Private special implementation classes
   //**************************************************************************
   private static class FullRange implements Range
   {
	   public int compareTo(Range b) { return 0; }
	   public int getMinimum() { return Integer.MIN_VALUE; }
	   public int getMaximum() { return Integer.MAX_VALUE; }
	   public boolean isInRange(int value) { return true; }
	   public boolean overlaps(Range b) { return true;}
	   public boolean equals(Object o) { return o instanceof FullRange; }
	   public int hashCode() { return Integer.MAX_VALUE; }
   }
   
   //***************************************************************************
   // Private Builder
   //***************************************************************************
   static class Builder
   {
	   public Builder(int val){start = val;}
	   
	   public int startValue()
	   {
		   return start;
	   }
	   
	   public Range to(int val)
	   {
		   int min = start < val ? start : val;
		   int max = start > val ? start : val;
		   
		   return URange.valueOf(min, max);
	   }
	   
	   private int start;
   }
}
