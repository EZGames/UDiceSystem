package ezgames.udicesys.diceModels.abstractions;

import ezgames.annotations.Immutable;
import ezgames.math.range.Range;

@Immutable
public interface OutputRange extends Range
{
   /**
    * Returns the formatted output
    * @param total - the amount that is applied to the formatted output, if applicable
    * @return the formatted output string
    */
   String getOutput(int total);
   /**
    * Returns the given output string before formatting
    * @return the unformatted output string
    */
   String getOutputExpression();
   Range getRange();
}
