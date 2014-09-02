package ezgames.math.range;

/**
 * {@code Range} represents a range of integers inclusively.
 */
public interface Range extends Comparable<Range>
{
	/**
	 * <p> Returns whether the calling {@code Range} is <i>fully</i> less than
	 * or greater than given {@code Range} b, or whether it overlaps in any way.
	 * </p>
	 * <p> Being <i>fully</i> less than or greater than means all values
	 * within one {@code Range} are less than of greater than all the values
	 * within the other. </p>
	 * @param b - the {@code Range} to compare against
	 * @return -1 if a is fully less than b; 0 if a and b overlap at all;
	 * 1 if a is fully greater than b
	 */
	@Override
	default int compareTo(Range b)
	{
		if (this.getMaximum() < b.getMinimum()) return -1;
		if (this.getMinimum() > b.getMaximum()) return 1;
		return 0;
	}
	
	/**
	 * @return the inclusive lower bound of the Range
	 */
	int getMinimum();
	
	/**
	 * @return the inclusive upper bound of the Range
	 */
	int getMaximum();
	
	/**
	 * Checks whether the given value is within this range
	 * @param value - the value to be checked
	 * @return {@code true} if {@code value} is within this range, otherwise 
	 * {@code false} 
	 */
	boolean isInRange(int value);
	
	/**
	 * Checks whether the given {@code Range} shares any values with this
	 * {@code Range}
	 * @param b - the {@code Range} to compare against
	 * @return {@code true} if the two {@code Range}s share any values, otherwise
	 * {@code false}
	 */
	boolean overlaps(Range b);
	
	/**
	 * The lowest value that can be in a {@code Range}
	 */
	final int LOWER_BOUND = Integer.MIN_VALUE;
	/**
	 * The highest value that can be in a {@code Range}
	 */
	final int UPPER_BOUND = Integer.MAX_VALUE;
}
