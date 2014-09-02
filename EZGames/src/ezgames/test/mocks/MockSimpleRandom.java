package ezgames.test.mocks;

import ezgames.math.random.SimpleRandom;

//TODO: update to work as noted in Evernot
/**
 * <p>A mock class for the {@link ezgames.math.random.SimpleRandom SimpleRandom}
 * interface.  It allows the tester to provide a value for the {@code SimpleRandom}
 * to "generate", as well as having some simple validation capabilities.</p>
 * <p>There are two things that validation goes over:</p>
 * <ol>
 * 	<li>if {@code isSettingSeedBad} is set to true, a call to {@link #setSeed}
 * will cause validation to return false</li>
 * 	<li>if the range provided in {@link #randBetween} does not include the 
 * value given to produce in the constructor, the validation will return false</li>
 * </ol>
 * @see {@link ezgames.test.mocks.Validatable} for more on EZGames' mock 
 * validation.
 */
public class MockSimpleRandom implements SimpleRandom, Validatable
{
	/**
	 * Creates a new {@link MockSimpleRandom}
	 * @param numToProduce - the number that should be returned when {@link #randBetween}
	 * is called
	 * @param isSettingSeedBad - if true, calling {@link #setSeed} before validating
	 * will cause the validation to fail.
	 */
	public MockSimpleRandom(int numToProduce, boolean isSettingSeedBad, UsageAllowance allowance)
	{
		numToReturn = numToProduce;
		this.isSettingSeedBad = isSettingSeedBad;
		this.allowance = allowance;
	}
	
	public MockSimpleRandom(int numToProduce, boolean isSettingSeedBad)
	{
		this(numToProduce, isSettingSeedBad, UsageAllowance.CAN_BE_USED);
	}

	/**
	 * If the given min and max do not include {@code numToProduce} from the
	 * constructor, running validation after calling this will cause validation
	 * to fail.
	 * @return {@code numToProduce} given in the constructor 
	 */
	@Override
	public int randBetween(int min, int max) 
	{
		beingUsed();
		if(numToReturn < min || numToReturn > max)
		{
			isValid = false;
		}
		return numToReturn;
	}
	
	/**
	 * If {@code isSettingSeedBad} is set to true in the constructor, calling
	 * this method will cause validation to return false.
	 */
	@Override
	public void setSeed(int seed)
	{
		beingUsed();
		if(isSettingSeedBad)
		{
			isValid = false;
		}
	}

	/**
	 * @return true if all calls to {@link #randBetween} did not cause validation
	 * to fail and if {@link #setSeed} was not called unless {@code isSettingSeedBad}
	 * was false.  Otherwise, it returns false.
	 */
	@Override
	public boolean validate()
	{
		return isValid && isNotUsedWhenItCantBe();
	}
	
	private void beingUsed()
	{
		isUsed = true;
		validateUsage();
	}
	
	private void validateUsage()
	{
		if(isUsed && !allowance.isSafeToUse)
		{
			isValid = false;
		}
	}
	
	private boolean isNotUsedWhenItCantBe()
	{
		return isUsed || allowance.isSafeNotToUse;
	}
	
	private int numToReturn;
	private boolean isSettingSeedBad;
	private boolean isValid = true;
	private UsageAllowance allowance;
	private boolean isUsed = false;
	
	public enum UsageAllowance
	{
		CANNOT_BE_USED(false, true),
		CAN_BE_USED(true, true),
		SHOULD_BE_USED(true, false);
		
		private UsageAllowance(boolean safeToUse, boolean safeNotToUse)
		{
			isSafeToUse = safeToUse;
			isSafeNotToUse = safeNotToUse;
		}
		
		public boolean isSafeToUse;
		public boolean isSafeNotToUse;
	}
}
