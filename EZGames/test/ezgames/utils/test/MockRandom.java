package ezgames.utils.test;

import ezgames.math.SimpleRandom;

public class MockRandom implements SimpleRandom 
{
	public MockRandom(int numToProduce)
	{
		this.numToRoll = numToProduce;
	}

	@Override
	public int randBetween(int min, int max) 
	{
		if(numToRoll < min || numToRoll > max)
		{
			throw new IllegalArgumentException("number to be returned, " + numToRoll + ", is out of " + min + "-" + max + " range.");
		}
		return numToRoll;
	}

	@Override
	public void setSeed(int seed) 
	{
		numToRoll = seed;
	}
	
	private int numToRoll;
}
