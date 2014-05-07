package ezgames.udicesys.diceModels.abstractions;

import ezgames.annotations.Immutable;
import ezgames.utils.collections.Streamable;

@Immutable
public interface Relationship extends Iterable<OutputRange>, Streamable<OutputRange>
{
//   String getName();
//   /**
//    * @param total - value to use to find correct Range and get the appropriate value
//    * @return string of output appropriate for the given value
//    */
//   String getOutputFor(int total);
//   /**
//    * @param rangeValue - value that the returned range encompasses
//    * @return Range whose range contains the given value
//    */
//   OutputRange getRangeForValue(int rangeValue);
}
