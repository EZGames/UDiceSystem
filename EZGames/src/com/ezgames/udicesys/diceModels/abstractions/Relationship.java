package com.ezgames.udicesys.diceModels.abstractions;

import com.ezgames.utils.interfaces.TImmutable;

public interface Relationship extends TImmutable, Iterable<OutputRange>
{
   public String getName();
   /**
    * @param total - value to use to find correct Range and get the appropriate value
    * @return string of output appropriate for the given value
    */
   public String getOutputFor(int total);
   /**
    * @param rangeValue - value that the returned range encompasses
    * @return Range whose range contains the given value
    */
   public OutputRange getRangeForValue(int rangeValue);
}
