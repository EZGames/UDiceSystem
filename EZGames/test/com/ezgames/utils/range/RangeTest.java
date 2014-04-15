package com.ezgames.utils.range;

import static org.junit.Assert.*;
import static org.hamcrest.core.IsEqual.*;
import static org.hamcrest.core.Is.*;

import org.junit.Test;

import com.ezgames.utils.range.URange;

public class RangeTest 
{
	private Integer[] stdVals = { Integer.MIN_VALUE, -5000, -1, 0, 1, 5000, Integer.MAX_VALUE };

	// **************************************************************************
	// from()
	// **************************************************************************
	@SuppressWarnings("unchecked")
	@Test
	public void shouldCreateBuilderWithFromValue() 
	{
		URange.Builder builder = URange.from(0);

		assertThat((Class<URange.Builder>) builder.getClass(), is(equalTo(URange.Builder.class)));
		assertThat(builder.startValue(), is(equalTo(0)));
	}

	// **************************************************************************
	// valueOf()
	// **************************************************************************
	@Test
	public void shouldSetIntMinAndMax() 
	{
		Range range = URange.valueOf(Integer.MIN_VALUE, Integer.MAX_VALUE);
		// checks that it gets the values back
		assertEquals("Range's min value did not equal int min", Integer.MIN_VALUE, range.getMinimum());
		assertEquals("Range's max value did not equal int max", Integer.MAX_VALUE, range.getMaximum());
		// checks that values are in range
		for (int i = 0; i < stdVals.length; ++i) {
			assertTrue(stdVals[i] + " wasn't in Range's range",
					range.isInRange(stdVals[i]));
		}
	}

	@Test
	public void shouldReverseValuesOf() 
	{
		Range range = URange.valueOf(5, -5);
		// checks min and max to ensure they are in the correct order
		assertEquals("Range's min did not equal -5", -5, range.getMinimum());
		assertEquals("Range's max did not equal 5", 5, range.getMaximum());
	}

	@Test
	public void shouldGetCachedValueOf() 
	{
		Range range1 = URange.valueOf(0, 20);
		Range range2 = URange.valueOf(0, 20);
		// check that ranges are being cached and retrieved
		assertTrue("Ranges were not the same object", range1 == range2);
	}

	@Test
	public void shouldSetMiddlingValues() 
	{
		Range range = URange.valueOf(-5000, 5000);
		// checks that it gets the values back
		assertEquals("Range's min value did not equal -5000", -5000, range.getMinimum());
		assertEquals("Range's max value did not equal 5000", 5000, range.getMaximum());
		// checks that values are in range
		for (int i = 0; i < stdVals.length; ++i) 
		{
			// make sure that the int min and max are NOT included
			if (stdVals[i].equals(Integer.MIN_VALUE) || stdVals[i].equals(Integer.MAX_VALUE)) 
			{
				assertFalse(stdVals[i] + "was in Range's range", range.isInRange(stdVals[i]));
			}
			else
			{
				// , but the other values are
				assertTrue(stdVals[i] + "wasn't in Range's range", range.isInRange(stdVals[i]));
			}
		}
	}

	@Test
	public void shouldSetValueOf0() 
	{
		Range range = URange.valueOf(0, 0);
		// checks that it gets the values back
		assertEquals("Range's min value did not equal 0", 0, range.getMinimum());
		assertEquals("Range's max value did not equal 0", 0, range.getMaximum());
		// checks that values are in range
		for (int i = 0; i < stdVals.length; ++i) 
		{
			// make sure that non-zero values are NOT included
			if (!stdVals[i].equals(0)) 
			{
				assertFalse(stdVals[i] + "was in Range's range", range.isInRange(stdVals[i]));
			}
			else
			{
				// , but 0 is
				assertTrue(stdVals[i] + "wasn't in Range's range", range.isInRange(stdVals[i]));
			}
		}
	}

	// **************************************************************************
	// compareTo()
	// **************************************************************************
	@Test
	public void shouldCompareAsLessThan() 
	{
		Range a = URange.valueOf(0, 1);
		Range b = URange.valueOf(2, 3);
		// check that it's less than
		assertEquals("Range a was not less than Range b", -1, a.compareTo(b));
	}

	@Test
	public void shouldCompareAsZero() 
	{
		Range a = URange.valueOf(1, 2);
		Range b = URange.valueOf(0, 1);
		// check that it's 'equal'
		assertEquals("Range a was not 'equal' to Range b", 0, a.compareTo(b));

		a = URange.valueOf(1, 1);
		b = URange.valueOf(0, 1);
		// check that it's 'equal'
		assertEquals("Range a was not 'equal' to Range b", 0, a.compareTo(b));

		a = URange.valueOf(1, 3);
		b = URange.valueOf(0, 2);
		// check that it's 'equal'
		assertEquals("Range a was not 'equal' to Range b", 0, a.compareTo(b));

		a = URange.valueOf(1, 2);
		b = URange.valueOf(0, 2);
		// check that it's 'equal'
		assertEquals("Range a was not 'equal' to Range b", 0, a.compareTo(b));

		a = URange.valueOf(1, 2);
		b = URange.valueOf(0, 3);
		// check that it's 'equal'
		assertEquals("Range a was not 'equal' to Range b", 0, a.compareTo(b));

		a = URange.valueOf(0, 2);
		b = URange.valueOf(0, 1);
		// check that it's 'equal'
		assertEquals("Range a was not 'equal' to Range b", 0, a.compareTo(b));

		a = URange.valueOf(0, 1);
		b = URange.valueOf(0, 1);
		// check that it's 'equal'
		assertEquals("Range a was not 'equal' to Range b", 0, a.compareTo(b));

		a = URange.valueOf(0, 1);
		b = URange.valueOf(0, 2);
		// check that it's 'equal'
		assertEquals("Range a was not 'equal' to Range b", 0, a.compareTo(b));

		a = URange.valueOf(0, 0);
		b = URange.valueOf(0, 1);
		// check that it's 'equal'
		assertEquals("Range a was not 'equal' to Range b", 0, a.compareTo(b));

		a = URange.valueOf(0, 3);
		b = URange.valueOf(1, 2);
		// check that it's 'equal'
		assertEquals("Range a was not 'equal' to Range b", 0, a.compareTo(b));

		a = URange.valueOf(0, 2);
		b = URange.valueOf(1, 2);
		// check that it's 'equal'
		assertEquals("Range a was not 'equal' to Range b", 0, a.compareTo(b));

		a = URange.valueOf(0, 2);
		b = URange.valueOf(1, 3);
		// check that it's 'equal'
		assertEquals("Range a was not 'equal' to Range b", 0, a.compareTo(b));

		a = URange.valueOf(0, 1);
		b = URange.valueOf(1, 2);
		// check that it's 'equal'
		assertEquals("Range a was not 'equal' to Range b", 0, a.compareTo(b));
	}

	@Test
	public void shouldCompareAsGreaterThan() 
	{
		Range a = URange.valueOf(0, 1);
		Range b = URange.valueOf(2, 3);
		// check that it's less than
		assertEquals("Range b was not greater than Range a", 1, b.compareTo(a));
	}

	// **************************************************************************
	// equals()
	// **************************************************************************
	@Test
	public void aShouldEqualB() 
	{
		Range a = URange.valueOf(0, 1);
		Range b = URange.valueOf(0, 1);
		// check if equal
		assertTrue("Range a does not equal b", a.equals(b));
		assertTrue("Range b does not equal a", b.equals(a));
	}

	public void aShouldNotEqualB() 
	{
		Range a = URange.valueOf(0, 1);
		Range b = URange.valueOf(0, 2);
		// check not equal
		assertFalse("Range a equals b", a.equals(b));
		assertFalse("Range b equals a", b.equals(a));
	}

	// **************************************************************************
	// getMinimum() & getMaximum were ignored due to their trivial nature
	// TODO: if either are changed to be more complex, create a test
	// **************************************************************************

	// **************************************************************************
	// isInRange()
	// **************************************************************************
	@Test
	public void shouldBeInRange() 
	{
		Range range = URange.valueOf(-5000, 5000);
		// checks that values are in range
		for (int i = 1; i < stdVals.length - 1; ++i)// skips first and last
													// values
		{
			assertTrue(stdVals[i] + "wasn't in Range's range", range.isInRange(stdVals[i]));
		}
	}

	@Test
	public void shouldNotBeInRange() 
	{
		Range range = URange.valueOf(-5000, 5000);
		// checks that values are in range
		assertFalse(Integer.MIN_VALUE + "was in Range's range", range.isInRange(Integer.MIN_VALUE));
		assertFalse(Integer.MAX_VALUE + "was in Range's range", range.isInRange(Integer.MAX_VALUE));
		assertFalse("-5001 was in Range's range", range.isInRange(-5001));
		assertFalse("5001 was in Range's range", range.isInRange(5001));
	}

	// **************************************************************************
	// overlaps()
	// **************************************************************************
	@Test
	public void shouldOverlap() 
	{
		Range a = URange.valueOf(1, 2);
		Range b = URange.valueOf(0, 1);
		// check that it's 'equal'
		assertTrue("Range a did not overlap Range b", a.overlaps(b));

		a = URange.valueOf(1, 1);
		b = URange.valueOf(0, 1);
		// check that it's 'equal'
		assertTrue("Range a did not overlap Range b", a.overlaps(b));

		a = URange.valueOf(1, 3);
		b = URange.valueOf(0, 2);
		// check that it's 'equal'
		assertTrue("Range a did not overlap Range b", a.overlaps(b));

		a = URange.valueOf(1, 2);
		b = URange.valueOf(0, 2);
		// check that it's 'equal'
		assertTrue("Range a did not overlap Range b", a.overlaps(b));

		a = URange.valueOf(1, 2);
		b = URange.valueOf(0, 3);
		// check that it's 'equal'
		assertTrue("Range a did not overlap Range b", a.overlaps(b));

		a = URange.valueOf(0, 2);
		b = URange.valueOf(0, 1);
		// check that it's 'equal'
		assertTrue("Range a did not overlap Range b", a.overlaps(b));

		a = URange.valueOf(0, 1);
		b = URange.valueOf(0, 1);
		// check that it's 'equal'
		assertTrue("Range a did not overlap Range b", a.overlaps(b));

		a = URange.valueOf(0, 1);
		b = URange.valueOf(0, 2);
		// check that it's 'equal'
		assertTrue("Range a did not overlap Range b", a.overlaps(b));

		a = URange.valueOf(0, 0);
		b = URange.valueOf(0, 1);
		// check that it's 'equal'
		assertTrue("Range a did not overlap Range b", a.overlaps(b));

		a = URange.valueOf(0, 3);
		b = URange.valueOf(1, 2);
		// check that it's 'equal'
		assertTrue("Range a did not overlap Range b", a.overlaps(b));

		a = URange.valueOf(0, 2);
		b = URange.valueOf(1, 2);
		// check that it's 'equal'
		assertTrue("Range a did not overlap Range b", a.overlaps(b));

		a = URange.valueOf(0, 2);
		b = URange.valueOf(1, 3);
		// check that it's 'equal'
		assertTrue("Range a did not overlap Range b", a.overlaps(b));

		a = URange.valueOf(0, 1);
		b = URange.valueOf(1, 2);
		// check that it's 'equal'
		assertTrue("Range a did not overlap Range b", a.overlaps(b));
	}

	@Test
	public void shouldNotOverlap() 
	{
		Range a = URange.valueOf(2, 3);
		Range b = URange.valueOf(0, 1);
		// checks that it's fully less than
		assertFalse("Range a overlapped Range b", a.overlaps(b));
		assertFalse("Range a overlapped Range b", b.overlaps(a));
	}
}
