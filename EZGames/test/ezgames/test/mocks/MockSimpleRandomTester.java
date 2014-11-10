package ezgames.test.mocks;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;
import static ezgames.testing.mocking.Validates.*;
import org.junit.Test;
import ezgames.test.mocks.MockSimpleRandom.UsageAllowance;

public class MockSimpleRandomTester
{
	@Test
	public void shouldFailWithTooLowOfRange()
	{
		MockSimpleRandom testMSR = new MockSimpleRandom(12, false);
		
		testMSR.randBetween(1, 3);
		
		assertThat(testMSR, doesNotValidate());
	}
	
	@Test
	public void shouldFailWithTooHighOfRange()
	{
		MockSimpleRandom testMSR = new MockSimpleRandom(12, false);
		
		testMSR.randBetween(13, 33);
		
		assertThat(testMSR, doesNotValidate());
	}
	
	@Test
	public void shouldSucceedWithGoodRange()
	{
		MockSimpleRandom testMSR = new MockSimpleRandom(12, false);
		
		testMSR.randBetween(11, 13);
		
		assertThat(testMSR, validates());
	}
	
	@Test
	public void shouldSucceedWithSettingSeed()
	{
		MockSimpleRandom testMSR = new MockSimpleRandom(12, false);
		
		testMSR.setSeed(12);
		
		assertThat(testMSR, validates());
	}
	
	@Test
	public void shouldFailWithSettingSeed()
	{
		MockSimpleRandom testMSR = new MockSimpleRandom(12, true);
		
		testMSR.setSeed(12);
		
		assertThat(testMSR, doesNotValidate());
	}
	
	@Test
	public void shouldReturnGivenValue()
	{
		MockSimpleRandom testMSR = new MockSimpleRandom(12, false);
		
		int val = testMSR.randBetween(11, 13);
		
		assertThat(testMSR, validates());
		assertThat(val, is(12));
	}
	
	@Test
	public void shouldFailButStillReturnGivenValue()
	{
		MockSimpleRandom testMSR = new MockSimpleRandom(12, false);
		
		int val = testMSR.randBetween(13, 14);
		
		assertThat(testMSR, doesNotValidate());
		assertThat(val, is(12));
	}
	
	@Test
	public void shouldFailFromNotBeingUsedWhenItShouldBe()
	{
		MockSimpleRandom testMSR = new MockSimpleRandom(1, false, UsageAllowance.SHOULD_BE_USED);
		
		assertThat(testMSR, doesNotValidate());
	}
	
	@Test
	public void shouldPassWithoutBeingUsedWhenItCanBe()
	{
		MockSimpleRandom testMSR = new MockSimpleRandom(1, false, UsageAllowance.CAN_BE_USED);
		
		assertThat(testMSR, validates());
	}
	
	@Test
	public void shouldPassWithoutBeingUsedWhenItCantBe()
	{
		MockSimpleRandom testMSR = new MockSimpleRandom(1, false, UsageAllowance.CANNOT_BE_USED);
		
		assertThat(testMSR, validates());
	}
	
	@Test
	public void shouldPassWithBeingUsedWhenItShouldBe()
	{
		MockSimpleRandom testMSR = new MockSimpleRandom(1, false, UsageAllowance.SHOULD_BE_USED);
		
		testMSR.randBetween(1, 1);
		testMSR.setSeed(0);
		
		assertThat(testMSR, validates());
	}
	
	@Test
	public void shouldPassWithBeingUsedWhenItCanBe()
	{
		MockSimpleRandom testMSR = new MockSimpleRandom(1, false, UsageAllowance.CAN_BE_USED);
		
		testMSR.randBetween(1, 1);
		testMSR.setSeed(0);
		
		assertThat(testMSR, validates());
	}
	
	@Test
	public void shouldFailWithBeingUsedWhenItCantBe()
	{
		MockSimpleRandom testMSR = new MockSimpleRandom(1, false, UsageAllowance.CANNOT_BE_USED);
		
		testMSR.randBetween(1, 1);
		testMSR.setSeed(0);
		
		assertThat(testMSR, doesNotValidate());
	}
	
	@Test
	public void shouldFailFromSettingSeedDespiteBeingUsedWhenItCanBe()
	{
		MockSimpleRandom testMSR = new MockSimpleRandom(1, true, UsageAllowance.CAN_BE_USED);
		
		testMSR.setSeed(0);
		
		assertThat(testMSR, doesNotValidate());
	}
	
	@Test
	public void shouldFailFromSettingSeedDespiteBeingUsedWhenItShouldBe()
	{
		MockSimpleRandom testMSR = new MockSimpleRandom(1, true, UsageAllowance.SHOULD_BE_USED);
		
		testMSR.setSeed(1);
		
		assertThat(testMSR, doesNotValidate());
	}
	
	@Test
	public void shouldFailFromSettingSeedAndFromBeingUsedWhenItCantBe()
	{
		MockSimpleRandom testMSR = new MockSimpleRandom(1, true, UsageAllowance.CANNOT_BE_USED);
		
		testMSR.setSeed(1);
		
		assertThat(testMSR, doesNotValidate());
	}
}