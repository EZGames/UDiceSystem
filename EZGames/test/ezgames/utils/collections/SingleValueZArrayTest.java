package ezgames.utils.collections;

import static ezgames.test.matchers.IsIteratingNumTimes.*;
import static ezgames.test.matchers.IsThrowing.*;
import static org.hamcrest.core.IsEqual.*;
import static org.hamcrest.core.IsNot.*;
import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Iterator;
import org.junit.BeforeClass;
import org.junit.Test;

public class SingleValueZArrayTest
{
	@BeforeClass
	public static void beforeClass()
	{
		default1 = ZArray.createWithSingleValue(1);
		default2 = ZArray.createWithSingleValue(2);
	}
	
	// public static ZArray CreateWithSingleValue(E) ***************************
	@Test
	public void shouldCreateZArrayWithSingleValue()
	{
		ZArray<Integer> arr = ZArray.createWithSingleValue(1);
		Iterator<Integer> iter = arr.iterator();
		
		assertThat(iter, iteratingNumTimes(1));
	}
	
	@Test
	public void shouldRejectNullSingleValue()
	{
		assertThat(()->ZArray.createWithSingleValue(null), is(throwing(IllegalArgumentException.class)));
	}
	
	// public static ZArray createWithMultipleValues(Iterable<E>)***************
	@Test
	public void shouldCreateZArrayWithSingleValueDespiteMultipleValueCall()
	{
		ArrayList<Integer> oneValueIterable = new ArrayList<>();
		oneValueIterable.add(1);
		
		ZArray<Integer> arr = ZArray.createWithMultipleValues(oneValueIterable);
		
		assertThat(arr, is(equalTo(default1)));
	}
	
	// public int hashCode() ***************************************************
	@Test
	public void shouldHaveDifferentHashCodes()
	{
		assertThat(default1.hashCode(), is(not(equalTo(default2.hashCode()))));
	}
	
	@Test
	public void shouldHaveSameHashCodes()
	{
		ZArray<Integer> other1 = ZArray.createWithSingleValue(1);
		
		assertThat(default1.hashCode(), is(equalTo(other1.hashCode())));
	}
	
	// public boolean equals(obj) **********************************************
	@Test
	public void shouldNotBeEqual()
	{
		assertThat(default1, is(not(equalTo(default2))));
	}
	
	@Test
	public void shouldBeEqualToItself()
	{
		assertThat(default1, is(equalTo(default1)));
	}
	
	@Test
	public void shouldBeEqual()
	{
		ZArray<Integer> also1 = ZArray.createWithSingleValue(1);
		
		assertThat(default1, is(equalTo(also1)));
	}
	
	// public E get(int) *******************************************************
	@Test
	public void shouldThrowIndexOutOfBoundsExceptionWithNonZeroIndex()
	{
		assertThat(()->default1.get(-1), is(throwing(IndexOutOfBoundsException.class)));
		assertThat(()->default1.get(1), is(throwing(IndexOutOfBoundsException.class)));
	}
	
	@Test
	public void shouldReturnOnlyValue()
	{
		assertThat(default1.get(0), is(equalTo(1)));
	}
	
	// public int size() *******************************************************
	@Test
	public void shouldReturnOne()
	{
		assertThat(default1.size(), is(1));
		assertThat(default2.size(), is(1));
	}
	
	// public Iterator<E> iterator() *******************************************
	@Test
	public void shouldIterateOnce()
	{
		assertThat(default1.iterator(), is(iteratingNumTimes(1)));
	}
	
	//******************************
	// private fields
	//******************************
	private static ZArray<Integer> default1;
	private static ZArray<Integer> default2;
}
