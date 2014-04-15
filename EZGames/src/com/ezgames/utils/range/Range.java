package com.ezgames.utils.range;

public interface Range extends Comparable<Range>
{
   /**
    * <p>Returns whether the calling {@code Range} is <i>fully</i> less than or 
    * greater than given {@code Range} b, or whether it overlaps in any way.</p>
    * <p>Being <i>fully</i> less than or greater than means all values within
    * one {@code Range} are less than  of greater than all the values within the
    * other.</p>
    * @param b - the {@code Range} to compare against
    * @return -1 if a is fully less than b; 0 if a and b overlap or are equal; 1 if a is fully greater than b 
    */
   @Override
	public default int compareTo(Range b)
	{
	   if(this.getMaximum() < b.getMinimum())
		   return -1;
	   if(this.getMinimum() > b.getMaximum())
		   return 1;
	   return 0;
	}
   @Override
   public boolean equals(Object o);   
   /**
    * @return the lower bound of the Range
    */
   public int getMinimum();   
   /**
    * @return the upper bound of the Range
    */
   public int getMaximum();   
   @Override
   public int hashCode();
   public boolean isInRange(int value);   
   public boolean overlaps(Range b);
   public static final int LOWER_BOUND = Integer.MIN_VALUE;
   public static final int UPPER_BOUND = Integer.MAX_VALUE;
}
