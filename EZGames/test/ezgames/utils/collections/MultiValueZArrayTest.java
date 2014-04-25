package ezgames.utils.collections;

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

public class MultiValueZArrayTest
{
	@BeforeClass
	public static void beforeClass()
	{
		ArrayList<Integer> list1 = new ArrayList<>();
		list1.add(1);
		list1.add(2);
		list1.add(3);
		
		ArrayList<Integer> list2 = new ArrayList<>();
		list2.add(3);
		list2.add(4);
		
		default1 = ZArray.createWithMultipleValues(list1);
		default2 = ZArray.createWithMultipleValues(list2);
	}
	
	// public static createWithMultipleValues(Iterable<E>) **********************************
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
		Iterable<Integer> nullIter = null;
		assertThat(()->ZArray.createWithMultipleValues(nullIter), throwsAn(IllegalArgumentException.class));
	}
	
	@Test
	public void shouldRejectEmptyIterable()
	{
		ArrayList<Integer> emptyIterable = new ArrayList<>();
		
		assertThat(()->ZArray.createWithMultipleValues(emptyIterable), throwsAn(IllegalArgumentException.class));
	}
	
	@Test
	public void shouldRejectIterableWithANullElement()
	{
		ArrayList<Integer> badIterable = new ArrayList<>();
		badIterable.add(null);
		badIterable.add(3);
		
		assertThat(()->ZArray.createWithMultipleValues(badIterable), throwsAn(IllegalArgumentException.class));
	}
	
	// public static createWithMultipleValues(E...)*****************************
	@Test
	public void shouldCreateZArrayWithThreeValuesFromSeparateValues()
	{
		ZArray<Integer> arr = ZArray.createWithMultipleValues(1, 2, 3);
		
		Iterator<Integer> iter = arr.iterator();
		assertThat(iter, iteratesNumTimes(3));
	}
	
	@Test
	public void shouldCreateZArrayWithTwoValuesFromSeparateValues()
	{
		ZArray<Integer> arr = ZArray.createWithMultipleValues(3, 4);
		
		Iterator<Integer> iter = arr.iterator();
		assertThat(iter, iteratesNumTimes(2));
	}
	
	@Test
	public void shouldCreateZArrayWithThreeValuesFromArray()
	{
		Integer[] arr = {1, 2, 3};
		ZArray<Integer> zarr = ZArray.createWithMultipleValues(arr);
		
		Iterator<Integer> iter = zarr.iterator();
		assertThat(iter, iteratesNumTimes(3));
	}
	
	@Test
	public void shouldCreateZArrayWithTwoValuesFromArray()
	{
		Integer[] arr = {3, 4};
		ZArray<Integer> zarr = ZArray.createWithMultipleValues(arr);
		
		Iterator<Integer> iter = zarr.iterator();
		assertThat(iter, iteratesNumTimes(2));
	}
	
	@Test
	public void shouldRejectNullArray()
	{
		Integer[] arr = null;
		
		assertThat(()->ZArray.createWithMultipleValues(arr), throwsAn(IllegalArgumentException.class));
	}
	
	@Test
	public void shouldRejectAnyNullValuesInArray()
	{
		Integer[] arr = {1, null, 3};
		
		assertThat(()->ZArray.createWithMultipleValues(arr), throwsAn(IllegalArgumentException.class));
	}
	
	@Test
	public void shouldRejectAnyEmptyValuesInArray()
	{
		Integer[] arr = new Integer[3];
		arr[0] = 1;
		arr[2] = 3;
		
		assertThat(()->ZArray.createWithMultipleValues(arr), throwsAn(IllegalArgumentException.class));
	}
	
	@Test
	public void shouldRejectAnyNullSeparateValues()
	{
		assertThat(()->ZArray.createWithMultipleValues(1, null, 3), throwsAn(IllegalArgumentException.class));
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
		ArrayList<Integer> otherList = new ArrayList<>();
		otherList.add(1);
		otherList.add(2);
		otherList.add(3);		
		ZArray<Integer> other1 = ZArray.createWithMultipleValues(otherList);
		
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
		ArrayList<Integer> alsoList1 = new ArrayList<>();
		alsoList1.add(1);
		alsoList1.add(2);
		alsoList1.add(3);
		
		ZArray<Integer> also1 = ZArray.createWithMultipleValues(alsoList1);
		
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
		assertThat(default1.get(0), is(equalTo(1)));
		assertThat(default1.get(1), is(equalTo(2)));
		assertThat(default1.get(2), is(equalTo(3)));
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
	private static ZArray<Integer> default1;
	private static ZArray<Integer> default2;
}
