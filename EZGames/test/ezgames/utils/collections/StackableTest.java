package ezgames.utils.collections;

import static ezgames.test.matchers.collections.IsIn.*;
import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class StackableTest
{
	// public MlList<T> and(T) *************************************************
	@Test
	public void shouldAddAdditionalIndividuals()
	{
		MlList<StackableInt> trial = val(0).and(val(1));
		
		assertThat(val(0), isIn(trial));
		assertThat(val(1), isIn(trial));
		assertThat(trial.size(), is(2));
	}
	
	//**************************************************************************
	// private test class and helper static factory
	//**************************************************************************
	private static StackableInt val(final int val)
	{
		return new StackableInt(val);
	}
	
	private static class StackableInt implements Stackable<StackableInt>
	{
		private int val;
		
		StackableInt(final int val)
		{
			this.val = val;
		}
		
		@Override
		public boolean equals(Object o)
		{
			StackableInt si = (StackableInt)o;
			
			return val == si.val;
		}
	}
}
