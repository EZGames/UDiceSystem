package ezgames.utils.collections.zarray;

import static ezgames.test.matchers.collections.IsIn.*;
import static ezgames.test.matchers.collections.IteratesNumTimes.*;
import static ezgames.testing.matchers.exceptions.ThrowsA.*;
import static org.hamcrest.core.IsEqual.*;
import static org.hamcrest.core.IsNot.*;
import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Iterator;
import org.junit.BeforeClass;
import org.junit.Test;
import ezgames.utils.collections.MlList;
import ezgames.utils.collections.simple.SimpleCollection;
import ezgames.utils.collections.zarray.ZArray;

public class MultiValueZArrayTest
{
	@BeforeClass
	public static void beforeClass()
	{
		MlList<Integer> list1 = MlList.startWith(0).and(1).and(2);
		
		MlList<Integer> list2 = MlList.startWith(3).and(4);
		
		default1 = ZArray.createFromSimpleCollection(list1);
		default2 = ZArray.createFromSimpleCollection(list2);
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
	public void shouldRejectEmptyIterable()
	{
		ArrayList<Integer> emptyArrayList = new ArrayList<>();
		SimpleCollection<Integer> emptyIterable = SimpleCollection.from(emptyArrayList);
		
		assertThat(()->ZArray.createFromSimpleCollection(emptyIterable), throwsAn(IllegalArgumentException.class));
	}
	
	// public static createWithMultipleValues(E...)*****************************
	@Test
	public void shouldCreateZArrayWithThreeValuesFromSeparateValues()
	{
		SimpleCollection<Integer> arr = ZArray.createFromValues(1, 2, 3);
		
		Iterator<Integer> iter = arr.iterator();
		assertThat(iter, iteratesNumTimes(3));
	}
	
	@Test
	public void shouldCreateZArrayWithTwoValuesFromSeparateValues()
	{
		SimpleCollection<Integer> arr = ZArray.createFromValues(3, 4);
		
		Iterator<Integer> iter = arr.iterator();
		assertThat(iter, iteratesNumTimes(2));
	}
	
	@Test
	public void shouldCreateZArrayWithThreeValuesFromArray()
	{
		Integer[] arr = {1, 2, 3};
		SimpleCollection<Integer> zarr = ZArray.createFromValues(arr);
		
		Iterator<Integer> iter = zarr.iterator();
		assertThat(iter, iteratesNumTimes(3));
	}
	
	@Test
	public void shouldCreateZArrayWithTwoValuesFromArray()
	{
		Integer[] arr = {3, 4};
		SimpleCollection<Integer> zarr = ZArray.createFromValues(arr);
		
		Iterator<Integer> iter = zarr.iterator();
		assertThat(iter, iteratesNumTimes(2));
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
		MlList<Integer> otherList = MlList.startWith(0).and(1).and(2);
		
		SimpleCollection<Integer> other1 = ZArray.createFromSimpleCollection(otherList);
		
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
		MlList<Integer> alsoList1 = MlList.startWith(0).and(1).and(2);
		
		SimpleCollection<Integer> also1 = ZArray.createFromSimpleCollection(alsoList1);
		
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