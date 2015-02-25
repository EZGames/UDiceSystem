package ezgames.udicesys.diceModels.abstractions;

import ezgames.utils.collections.Stackable;
import ezgames.utils.collections.simple.SimpleCollection;

/**
 * Effect is a planned feature. It will not be released in version 1.<br>
 * Effect is an additional property of Face, that, if the Face is rolled, you
 * supply the Roll to Effect's trigger method, which returns a SimpleCollection
 * of new Rolls. It is meant for reroll and "roll again and add to this" actions.
 * There hasn't been a whole lot of thought put into other possible effects, this
 * is a prototypical idea and isn't planned to be used for a while. 
 */
@Deprecated
public interface Effect extends Stackable<Effect>
{	
	SimpleCollection<Roll> trigger(Roll original);
}
