package ezgames.udicesys.diceModels.abstractions;

import ezgames.utils.collections.simple.SimpleCollection;

public interface Effect
{	
	SimpleCollection<Roll> trigger(Roll original);
}
