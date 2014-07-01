package ezgames.test.mocks;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;
import static ezgames.test.matchers.Validates.*;
import org.junit.Test;

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
}
