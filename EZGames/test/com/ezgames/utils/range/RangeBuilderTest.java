package com.ezgames.utils.range;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsEqual.*;

import org.junit.Test;

import com.ezgames.utils.range.URange.Builder;

public class RangeBuilderTest 
{
	// constructor *************************************************************
	@Test
	public void shouldConstructBuilder() 
	{
		//tests entire range from -500 to 500 (arbitrary)
		for(int i = -500; i <= 500; i ++)
		{
			Range.Builder test = new Range.Builder(i);
			
			assertEquals(i, test.getValue());
		}
	}
	
	// to() ********************************************************************
	@Test
	public void shouldCreateRangeWithSingleValue() 
	{
		Range.Builder test = new Range.Builder(0);
		
		IRange range = test.to(0);
		
		assertEquals(0, range.getMaximum());
		assertEquals(0, range.getMinimum());
	}
	
	@Test
	public void shouldCreateCorrectRangeFromBackwardsValues()
	{
		Range.Builder test = new Range.Builder(0);
		
		IRange range = test.to(-2);
		
		assertEquals(0, range.getMaximum());
		assertEquals(-2, range.getMinimum());
	}
	
	@Test
	public void shouldCreateRangeFromNormalValues()
	{
		Range.Builder test = new Range.Builder(0);
		
		IRange range = test.to(2);
		
		assertEquals(2, range.getMaximum());
		assertEquals(0, range.getMinimum());
	}
	
	// getMinimum() ************************************************************
	@Test
	public void shouldGetMinimum() 
	{
		//tests entire range from -500 to 500 (arbitrary)
		for(int i = -500; i <= 500; i ++)
		{
			Range.Builder test = new Range.Builder(i);
			
			assertEquals(i, test.getValue());
		}
	}
}
