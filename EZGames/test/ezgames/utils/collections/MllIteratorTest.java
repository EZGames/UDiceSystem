package ezgames.utils.collections;

import static ezgames.test.matchers.IsThrowing.*;
import static ezgames.test.matchers.IsIteratingNumTimes.*;
import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Test;
import ezgames.utils.collections.MlList;

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
		
		assertThat(inter, is(iteratingNumTimes(1)));
	}
	
	@Test
	public void shouldNotIterate()
	{
		Iterator<Integer> inter = emptyList.iterator();
		
		assertThat(inter, is(iteratingNumTimes(0)));
	}
	
	@Test
	public void shouldIterateTwice()
	{
		Iterator<Integer> inter = listOf2.iterator();
		
		assertThat(inter, is(iteratingNumTimes(2)));
	}
	
	@Test
	public void shouldIterateThrice()
	{
		Iterator<Integer> inter = listOf3.iterator();
		
		assertThat(inter, is(iteratingNumTimes(3)));
	}
	
	@Test
	public void shouldThrowUnsupportedOperationException()
	{
		Iterator<Integer> inter = listOf2.iterator();
		
		inter.next();
		
		assertThat(()->inter.remove(), is(throwing(UnsupportedOperationException.class)));
	}
	
}
