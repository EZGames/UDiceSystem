package com.ezgames.udicesys.diceModels.abstractions;

public interface Effect
{	
	public abstract Iterable<Roll> trigger(Roll original);
}
