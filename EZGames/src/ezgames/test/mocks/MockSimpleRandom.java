package ezgames.test.mocks;

import ezgames.math.random.SimpleRandom;
import ezgames.testing.mocking.Validatable;

public final class MockSimpleRandom implements SimpleRandom, Validatable
{
	public MockSimpleRandom(Validator validator, int numToGenerate)
	{
		this.validator = validator;
		this.numToGenerate = numToGenerate;
		this.randBetweenCalled = false;
		this.setSeedCalled = false;
	}
	
	@Override
	public int randBetween(int min, int max)
	{
		minArg = min;
		maxArg = max;
		randBetweenCalled = true;
		return numToGenerate;
	}
	
	public void setNewNumToGenerate(int numToGenerate)
	{
		this.numToGenerate = numToGenerate;
	}

	@Override
	public void setSeed(int seed)
	{
		setSeedCalled = true;
	}

	@Override
	public boolean validate()
	{
		validation = validator.validate(randBetweenCalled, setSeedCalled, minArg, maxArg);
		return validation.isValid();
	}

	@Override
	public String errorMessage()
	{
		return validation.describeInvalidation();
	}
	
	private final Validator validator;
	private int numToGenerate;
	private boolean randBetweenCalled;
	private boolean setSeedCalled;
	private int minArg;
	private int maxArg;
	private ValidationDescription validation;
	
	@FunctionalInterface
	public interface Validator
	{
		public ValidationDescription validate(boolean randBetweenCalled, boolean setSeedCalled, int minArg, int maxArg);
	}
}
