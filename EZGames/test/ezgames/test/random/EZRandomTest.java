package ezgames.test.random;

import static ezgames.test.matchers.IsBetween.*;
import static org.hamcrest.core.IsEqual.*;
import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;
import org.junit.Test;
import ezgames.random.EZRandom;

public class EZRandomTest
{
	private static final int LOOP_NUM_TIMES = 2000;
	
	@Test
	public void shouldGetSameNumberBothTimes()
	{
		EZRandom rand = new EZRandom(1);
		
		int one = rand.randBetween(0, 100);
		rand.setSeed(1);
		int two = rand.randBetween(0, 100);
		
		assertThat(one, is(equalTo(two)));
	}
	
	@Test
	public void shouldNeverGetOutOfNormalRange()
	{
		EZRandom rand = new EZRandom();
		
		int num = 0;
		for(int i = 0; i < LOOP_NUM_TIMES; i++)
		{
			num = rand.randBetween(0, 100);
			
			assertThat(num, is(between(0, and(100))));
		}
	}
	
	@Test
	public void shouldNeverGetOutOfHighRange()
	{
		EZRandom rand = new EZRandom();
		
		int num = 0;
		for(int i = 0; i < LOOP_NUM_TIMES; i++)
		{
			num = rand.randBetween(100, 1000);
			
			assertThat(num, is(between(100, and(1000))));
		}
	}
	
	@Test
	public void shouldNeverGetOutOfOverZeroRange()
	{
		EZRandom rand = new EZRandom();
		
		int num = 0;
		for(int i = 0; i < LOOP_NUM_TIMES; i++)
		{
			num = rand.randBetween(-10, 100);
			
			assertThat(num, is(between(-10, and(100))));
		}
	}
	
	@Test
	public void shouldNeverGetOutOfNegativeRange()
	{
		EZRandom rand = new EZRandom();
		
		int num = 0;
		for(int i = 0; i < LOOP_NUM_TIMES; i++)
		{
			num = rand.randBetween(-1000, -100);
			
			assertThat(num, is(between(-1000, and(-100))));
		}
	}
	
}
