package ezgames.udicesys.diceModels.abstractions;

import ezgames.utils.collections.Stackable;
import ezgames.utils.collections.simple.SimpleCollection;

public interface Effect extends Stackable<Effect>
{	
	SimpleCollection<Roll> trigger(Roll original);
}
