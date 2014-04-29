package ezgames.utils.collections;

import static ezgames.test.matchers.IsIn.*;
import static ezgames.test.matchers.IteratesNumTimes.*;
import static ezgames.test.matchers.Throws.*;
import static org.hamcrest.core.IsEqual.*;
import static org.hamcrest.core.IsNot.*;
import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Iterator;
import org.junit.BeforeClass;
import org.junit.Test;
import ezgames.test.matchers.ThrowingRunnable;
import ezgames.utils.exceptions.NullArgumentException;

public class MultiValueZArrayTest
{
	@BeforeClass
	public static void beforeClass() throws IllegalArgumentException, NullArgumentException
	{
		MlList<Integer> list1 = MlList.<Integer>empty().add(0).and(1).and(2);
		
		MlList<Integer> list2 = MlList.<Integer>empty().add(3).and(4);
		
		default1 = ZArrayFactory.createWithMultipleValues(list1);
		default2 = ZArrayFactory.createWithMultipleValues(list2);
	}
	
	// public static createWithMultipleValues(SimpleCollection<E>) **********************************
	@Test
	public void shouldCreateZArrayWithThreeValuesFromIterable()
	{
		//default 1 was already created with createdWithMultipleValues() in @BeforeClass
		Iterator<Integer> iter = default1.iterator();
		
		assertThat(iter, iteratesNumTimes(3));
	}
	
	@Test
	public void shouldCreateZArrayWithTwoValuesFromIterable()
	{
		//default2 was already created with createWithMultipleValues() in @BeforeClass
		Iterator<Integer> iter = default2.iterator();
		
		assertThat(iter, iteratesNumTimes(2));
	}
	
	@Test
	public void shouldRejectNullValue()
	{
		SimpleCollection<Integer> nullIter = null;
		assertThat(()->ZArrayFactory.createWithMultipleValues(nullIter), throwsAn(NullArgumentException.class));
	}
	
	@Test
	public void shouldRejectEmptyIterable()
	{
		ArrayList<Integer> emptyArrayList = new ArrayList<>();
		SimpleCollection<Integer> emptyIterable = SimpleCollection.fromList(emptyArrayList);
		
		assertThat(seeNextMethod(emptyIterable), throwsAn(IllegalArgumentException.class));
	}
	
	private ThrowingRunnable<IllegalArgumentException> seeNextMethod(SimpleCollection<?> coll)
	{
		return new ThrowingRunnable<IllegalArgumentException>()
				{
					public void run() throws IllegalArgumentException
					{
						try
						{ ZArrayFactory.createWithMultipleValues(coll); }
						catch(NullArgumentException x) {}
					}
				};
	}
	
	@Test
	public void shouldRejectIterableWithANullElement()
	{
		ArrayList<Integer> badArrayList = new ArrayList<>();
		badArrayList.add(null);
		badArrayList.add(3);
		SimpleCollection<Integer> badIterable = SimpleCollection.fromList(badArrayList);
		
		assertThat(()->ZArrayFactory.createWithMultipleValues(badIterable), throwsAn(NullArgumentException.class));
	}
	
	// public static createWithMultipleValues(E...)*****************************
	@Test
	public void shouldCreateZArrayWithThreeValuesFromSeparateValues() throws NullArgumentException
	{
		SimpleCollection<Integer> arr = ZArrayFactory.createWithMultipleValues(1, 2, 3);
		
		Iterator<Integer> iter = arr.iterator();
		assertThat(iter, iteratesNumTimes(3));
	}
	
	@Test
	public void shouldCreateZArrayWithTwoValuesFromSeparateValues() throws NullArgumentException
	{
		SimpleCollection<Integer> arr = ZArrayFactory.createWithMultipleValues(3, 4);
		
		Iterator<Integer> iter = arr.iterator();
		assertThat(iter, iteratesNumTimes(2));
	}
	
	@Test
	public void shouldCreateZArrayWithThreeValuesFromArray() throws NullArgumentException
	{
		Integer[] arr = {1, 2, 3};
		SimpleCollection<Integer> zarr = ZArrayFactory.createWithMultipleValues(arr);
		
		Iterator<Integer> iter = zarr.iterator();
		assertThat(iter, iteratesNumTimes(3));
	}
	
	@Test
	public void shouldCreateZArrayWithTwoValuesFromArray() throws NullArgumentException
	{
		Integer[] arr = {3, 4};
		SimpleCollection<Integer> zarr = ZArrayFactory.createWithMultipleValues(arr);
		
		Iterator<Integer> iter = zarr.iterator();
		assertThat(iter, iteratesNumTimes(2));
	}
	
	@Test
	public void shouldRejectNullArray()
	{
		Integer[] arr = null;
		
		assertThat(()->ZArrayFactory.createWithMultipleValues(arr), throwsAn(NullArgumentException.class));
	}
	
	@Test
	public void shouldRejectAnyNullValuesInArray()
	{
		Integer[] arr = {1, null, 3};
		
		assertThat(()->ZArrayFactory.createWithMultipleValues(arr), throwsAn(NullArgumentException.class));
	}
	
	@Test
	public void shouldRejectAnyEmptyValuesInArray()
	{
		Integer[] arr = new Integer[3];
		arr[0] = 1;
		arr[2] = 3;
		
		assertThat(()->ZArrayFactory.createWithMultipleValues(arr), throwsAn(NullArgumentException.class));
	}
	
	@Test
	public void shouldRejectAnyNullSeparateValues()
	{
		assertThat(()->ZArrayFactory.createWithMultipleValues(1, null, 3), throwsAn(NullArgumentException.class));
	}
	
	// public int hashCode() ***************************************************
	@Test
	public void shouldHaveDifferentHashCodes()
	{
		assertThat(default1.hashCode(), is(not(equalTo(default2.hashCode()))));
	}
	
	@Test
	public void shouldHaveSameHashCodes() throws IllegalArgumentException, NullArgumentException
	{
		MlList<Integer> otherList = MlList.<Integer>empty().add(0).and(1).and(2);
		
		SimpleCollection<Integer> other1 = ZArrayFactory.createWithMultipleValues(otherList);
		
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
	public void shouldBeEqual() throws IllegalArgumentException, NullArgumentException
	{
		MlList<Integer> alsoList1 = MlList.<Integer>empty().add(0).and(1).and(2);
		
		SimpleCollection<Integer> also1 = ZArrayFactory.createWithMultipleValues(alsoList1);
		
		assertThat(default1, is(equalTo(also1)));
	}
	
	// public E get(int) *******************************************************
	@Test
	public void shouldThrowIndexOutOfBoundsExceptionWithBadIndex()
	{
		assertThat(()->default1.get(-1), throwsAn(IndexOutOfBoundsException.class));
		assertThat(()->default1.get(3), throwsAn(IndexOutOfBoundsException.class));
	}
	
	@Test
	public void shouldReturnValuesProperly()
	{
		assertThat(0, isIn(default1));
		assertThat(1, isIn(default1));
		assertThat(2, isIn(default1));
	}
	
	// public int size() *******************************************************
	@Test
	public void shouldGetCorrectSizes()
	{
		assertThat(default1.size(), is(3));
		assertThat(default2.size(), is(2));
	}
	
	// public Iterator<E> iterator() *******************************************
	@Test
	public void shouldIterateThrice()
	{
		assertThat(default1.iterator(), iteratesNumTimes(3));
	}
	
	@Test
	public void shouldIterateTwice()
	{
		assertThat(default2.iterator(), iteratesNumTimes(2));
	}
	
	//******************************
	// private fields
	//******************************
	private static SimpleCollection<Integer> default1;
	private static SimpleCollection<Integer> default2;
}
