package ezgames.utils.collections;

import static ezgames.test.matchers.IsThrowing.*;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsEqual.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.BeforeClass;
import org.junit.Test;

public class StackableTest
{
	@BeforeClass
	public static void beforeClass()
	{
		siIterable = MlList.<StackableInt>empty().add(val(1)).add(val(2)).add(val(3));		
		siArray = new StackableInt[] {val(1), val(2), val(3) };
	}
	
	private static MlList<StackableInt> siIterable;
	private static StackableInt[] siArray;
	private static final MlList<StackableInt> control = MlList.<StackableInt>empty().add(val(0)).and(val(1)).and(val(2)).and(val(3));
	
	// public MlList<T> and(T) *************************************************
	@Test
	public void shouldAddAdditionalIndividuals()
	{
		MlList<StackableInt> trial = val(0).and(val(1).and(val(2).and(val(3))));
		
		assertEqualToControl(trial);
		assertEquivalentToControl(trial);
	}
	
	@Test
	public void shouldAddAdditionalIterable()
	{
		MlList<StackableInt> trial = val(0).and(siIterable);
		
		assertEqualToControl(trial);
		assertEquivalentToControl(trial);
	}
	
	@Test
	public void shouldAddAdditionalArray()
	{
		MlList<StackableInt> trial = val(0).and(siArray);
		
		assertEqualToControl(trial);
		assertEquivalentToControl(trial);
	}
	
	@Test
	public void shouldAddAdditionalVarArgs()
	{
		MlList<StackableInt> trial = val(0).and(val(1), val(2), val(3));
		
		assertEqualToControl(trial);
		assertEquivalentToControl(trial);
	}
	
	@Test
	public void shouldRejectNullIndividualsAtEnd()
	{
		StackableInt end = null;
		
		assertThat(()->val(0).and(end), is(throwing(IllegalArgumentException.class)));
	}
	
	@Test
	public void shouldRejectNullIterable()
	{
		Iterable<StackableInt> nullIter = null;
		
		assertThat(()->val(0).and(nullIter), is(throwing(IllegalArgumentException.class)));
	}
	
	@Test
	public void shouldRejectNullItemsInIterable()
	{
		ArrayList<StackableInt> badIter = new ArrayList<>();
		badIter.add(val(1));
		badIter.add(null);
		badIter.add(val(3));
		
		assertThat(()->val(0).and(badIter), is(throwing(IllegalArgumentException.class)));		
	}
	
	@Test
	public void shouldRejectNullArray()
	{
		StackableInt[] nullArr = null;
		
		assertThat(()->val(0).and(nullArr), is(throwing(IllegalArgumentException.class)));
	}
	
	@Test
	public void shouldRejectNullItemsInArray()
	{
		StackableInt[] badArr = {val(1), null, val(3)};
		
		assertThat(()->val(0).and(badArr), is(throwing(IllegalArgumentException.class)));
	}
	
	@Test
	public void shouldRejectNullItemsInVarArgs()
	{
		assertThat(()->val(0).and(val(1), null, val(3)), is(throwing(IllegalArgumentException.class)));
	}
	
	//**************************************************************************
	// private test helpers
	//**************************************************************************
	private static void assertEqualToControl(MlList<StackableInt> trial)
	{
		assertThat(trial, is(equalTo(control)));
	}
	
	private static void assertEquivalentToControl(MlList<StackableInt> trial)
	{
		assertTrue(trial.contains(val(0)));
		assertTrue(trial.contains(val(1)));
		assertTrue(trial.contains(val(2)));
		assertTrue(trial.contains(val(3)));
		assertThat(trial.size(), is(4));
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
