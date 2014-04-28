package ezgames.udicesys.diceModels.abstractions;

import ezgames.utils.collections.SimpleCollection;

public interface Effect
{	
	SimpleCollection<Roll> trigger(Roll original);
}
