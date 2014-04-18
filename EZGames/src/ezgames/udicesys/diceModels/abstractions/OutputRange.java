package ezgames.udicesys.diceModels.abstractions;

import ezgames.annotations.Immutable;
import ezgames.math.range.Range;

@Immutable
public interface OutputRange extends Range
{
   /**
    * <p>Returns whether the calling {@code Range} is <i>mostly</i> less than or 
    * greater than given {@code Range} b, or if it overlaps in any way.</p>
    * <p>Being <i>mostly</i> less than or greater than means all values within
    * one {@code Range} are less than  of greater than all the values within the
    * other.</p>
    * <p><b>NOTE:</b> use the {@code compareRanges()} method if you want more
    * detail as to how the two {@code Range}s compare, such as whether one a 
    * sub-range of the other.</p>
    * @param b - the {@code Range} to compare against
    * @return -1 if a is fully less than b; 0 if a and b overlap or are equal; 1 if a is fully greater than b 
    */
   @Override
   public int compareTo(Range b);
   @Override
   public boolean equals(Object other);
   /**
    * Returns the formatted output
    * @param total - the amount that is applied to the formatted output, if applicable
    * @return the formatted output string
    */
   public String getOutput(int total);
   /**
    * Returns the given output string before formatting
    * @return the unformatted output string
    */
   public String getOutputExpression();
   public Range getRange();
   @Override
   public int hashCode();
}
