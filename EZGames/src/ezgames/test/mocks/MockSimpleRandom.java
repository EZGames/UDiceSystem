package ezgames.test.mocks;

import ezgames.random.SimpleRandom;

public class MockSimpleRandom implements SimpleRandom, Validatable
{
	public MockSimpleRandom(int numToProduce, boolean isSettingSeedBad)
	{
		numToRoll = numToProduce;
		this.isSettingSeedBad = isSettingSeedBad;
	}

	@Override
	public int randBetween(int min, int max) 
	{
		if(numToRoll < min || numToRoll > max)
		{
			isValid = false;
		}
		return numToRoll;
	}
	
	/**
	 * If <code>isSettingSeedBad</code> is set to true in the constructor, calling
	 * this method will cause a bad validation.
	 */
	@Override
	public void setSeed(int seed)
	{
		if(isSettingSeedBad)
		{
			isValid = false;
		}
	}
	
	@Override
	public boolean validate()
	{
		return isValid;
	}
	
	private int numToRoll;
	private boolean isSettingSeedBad;
	private boolean isValid = true;
}
