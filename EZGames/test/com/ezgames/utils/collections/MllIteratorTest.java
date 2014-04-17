package com.ezgames.utils.collections;

import static com.ezgames.utils.test.IteratesNumTimes.*;
import static com.ezgames.utils.test.TestHelper.*;
import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Test;

public class MllIteratorTest
{
	MlList<Integer> emptyList = MlList.<Integer>empty();
	MlList<Integer> listOf1 = MlList.<Integer>empty().add(1);
	MlList<Integer> listOf2 = MlList.<Integer>empty().add(1).add(2);
	MlList<Integer> listOf3 = MlList.<Integer>empty().add(1).add(2).add(3);
	
	@Test
	public void shouldIterateOnce()
	{
		Iterator<Integer> inter = listOf1.iterator();
		
		assertThat(inter, iteratesNumTimes(1));
	}
	
	@Test
	public void shouldNotIterate()
	{
		Iterator<Integer> inter = emptyList.iterator();
		
		assertThat(inter, iteratesNumTimes(0));
	}
	
	@Test
	public void shouldIterateTwice()
	{
		Iterator<Integer> inter = listOf2.iterator();
		
		assertThat(inter, iteratesNumTimes(2));
	}
	
	@Test
	public void shouldIterateThrice()
	{
		Iterator<Integer> inter = listOf3.iterator();
		
		assertThat(inter, iteratesNumTimes(3));
	}
	
	@Test
	public void shouldThrowUnsupportedOperationException()
	{
		Iterator<Integer> inter = listOf1.iterator();
		
		while(inter.hasNext())
		{
			assertThrows(UnsupportedOperationException.class, ()->inter.remove());
		}
	}
	
}
