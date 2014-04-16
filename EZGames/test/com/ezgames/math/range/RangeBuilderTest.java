package com.ezgames.math.range;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsEqual.*;
import org.junit.Test;
import com.ezgames.math.range.Range;
import com.ezgames.math.range.URange;

public class RangeBuilderTest 
{
	// constructor *************************************************************
	@Test
	public void shouldConstructBuilder() 
	{
		//tests entire range from -500 to 500 (arbitrary)
		for(int i = -500; i <= 500; i ++)
		{
			URange.Builder test = new URange.Builder(i);
			
			assertThat(i, is(equalTo(test.startValue())));
		}
	}
	
	// to() ********************************************************************
	@Test
	public void shouldCreateRangeWithSingleValue() 
	{
		URange.Builder test = new URange.Builder(0);
		
		Range range = test.to(0);
		
		assertThat(0, is(equalTo(range.getMaximum())));
		assertThat(0, is(equalTo(range.getMinimum())));
	}
	
	@Test
	public void shouldCreateCorrectRangeFromBackwardsValues()
	{
		URange.Builder test = new URange.Builder(0);
		
		Range range = test.to(-2);
		
		assertThat(0, is(equalTo(range.getMaximum())));
		assertThat(-2, is(equalTo(range.getMinimum())));
	}
	
	@Test
	public void shouldCreateRangeFromNormalValues()
	{
		URange.Builder test = new URange.Builder(0);
		
		Range range = test.to(2);
		
		assertThat(2, is(equalTo(range.getMaximum())));
		assertThat(0, is(equalTo(range.getMinimum())));
	}
	
	// getMinimum() ************************************************************
	@Test
	public void shouldGetMinimum() 
	{
		//tests entire range from -500 to 500 (arbitrary)
		for(int i = -500; i <= 500; i ++)
		{
			URange.Builder test = new URange.Builder(i);
			
			assertThat(i, is(equalTo(test.startValue())));
		}
	}
}
