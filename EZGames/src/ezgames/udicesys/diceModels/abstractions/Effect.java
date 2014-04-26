package ezgames.udicesys.diceModels.abstractions;

public interface Effect
{	
	Iterable<Roll> trigger(Roll original);
}
