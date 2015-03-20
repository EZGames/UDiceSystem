package ezgames.udicesys.diceModels.abstractions;

import static ezgames.testing.matchers.exceptions.ThrowsA.throwsAn;
import static org.junit.Assert.assertThat;
import org.junit.Test;

abstract public class AbstractDieTest
{
	//DOC
	abstract protected Die createTestDieFrom(String name);
	
	@Test
	// Die names cannot be blank
	public void blankName()
	{
		assertThat(() -> createTestDieFrom(""), throwsAn(IllegalArgumentException.class));
	}
}
