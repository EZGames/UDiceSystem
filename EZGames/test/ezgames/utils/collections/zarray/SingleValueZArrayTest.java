package ezgames.utils.collections.zarray;

import static ezgames.test.matchers.IteratesNumTimes.*;
import static ezgames.test.matchers.exceptions.Throws.*;
import static org.hamcrest.core.IsEqual.*;
import static org.hamcrest.core.IsNot.*;
import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.BeforeClass;
import org.junit.Test;
import ezgames.utils.collections.MlList;
import ezgames.utils.collections.simple.SimpleCollection;
import ezgames.utils.collections.zarray.ZArray;
import ezgames.utils.exceptions.NullArgumentException;

public class SingleValueZArrayTest
{
	@BeforeClass
	public static void beforeClass() throws NullArgumentException
	{
		default1 = ZArray.createFromValues(1);
		default2 = ZArray.createFromValues(2);
	}
	
	// public static ZArray CreateWithSingleValue(E) ***************************
	@Test
	public void shouldCreateZArrayWithSingleValue() throws NullArgumentException
	{
		SimpleCollection<Integer> arr = ZArray.createFromValues(1);
		Iterator<Integer> iter = arr.iterator();
		
		assertThat(iter, iteratesNumTimes(1));
	}
	
	@Test
	public void shouldRejectNullSingleValue()
	{
		Object nullObj = null;
		assertThat(()->ZArray.createFromValues(nullObj), throwsA(NullArgumentException.class));
	}
	
	// public static ZArray createWithMultipleValues(Iterable<E>)***************
	@Test
	public void shouldCreateZArrayWithSingleValueDespiteMultipleValueCall() throws IllegalArgumentException, NullArgumentException
	{
		MlList<Integer> oneValueIterable = MlList.startWith(1);
		
		SimpleCollection<Integer> arr = ZArray.createFromSimpleCollection(oneValueIterable);
		
		assertThat(arr, is(equalTo(default1)));
	}
	
	// public int hashCode() ***************************************************
	@Test
	public void shouldHaveDifferentHashCodes()
	{
		assertThat(default1.hashCode(), is(not(equalTo(default2.hashCode()))));
	}
	
	@Test
	public void shouldHaveSameHashCodes() throws NullArgumentException
	{
		SimpleCollection<Integer> other1 = ZArray.createFromValues(1);
		
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
	public void shouldBeEqual() throws NullArgumentException
	{
		SimpleCollection<Integer> also1 = ZArray.createFromValues(1);
		
		assertThat(default1, is(equalTo(also1)));
	}
	
	// public E get(int) *******************************************************
	@Test
	public void shouldThrowIndexOutOfBoundsExceptionWithNonZeroIndex()
	{
		assertThat(()->default1.get(-1), throwsAn(IndexOutOfBoundsException.class));
		assertThat(()->default1.get(1), throwsAn(IndexOutOfBoundsException.class));
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
		assertThat(default1.iterator(), iteratesNumTimes(1));
	}
	
	//******************************
	// private fields
	//******************************
	private static SimpleCollection<Integer> default1;
	private static SimpleCollection<Integer> default2;
}
