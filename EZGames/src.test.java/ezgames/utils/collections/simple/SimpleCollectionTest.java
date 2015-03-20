package ezgames.utils.collections.simple;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import org.junit.BeforeClass;
import org.junit.Test;
import ezgames.utils.collections.simple.SimpleCollection;

public class SimpleCollectionTest
{
	@BeforeClass
	public static void beforeClass()
	{
		intList1 = SimpleCollection.from(getList(1));
		intList2 = SimpleCollection.from(getList(2));
		intList3 = SimpleCollection.from(getList(3));
		
		intIter1 = SimpleCollection.from(new TestIterable<Integer>(1));
		intIter2 = SimpleCollection.from(new TestIterable<Integer>(1, 2));
		intIter3 = SimpleCollection.from(new TestIterable<Integer>(1, 2, 3));
		
		intColl1 = SimpleCollection.from(getStack(1));
		intColl2 = SimpleCollection.from(getStack(2));
		intColl3 = SimpleCollection.from(getStack(3));
	}
	
	private static Stack<Integer> getStack(int num)
	{
		Stack<Integer> stack = new Stack<>();
		for(int i = 1; i <= num; i++)
		{
			stack.push(i);
		}
		return stack;
	}
	
	private static List<Integer> getList(int num)
	{
		List<Integer> list = new ArrayList<>();
		for(int i = 1; i <= num; i++)
		{
			list.add(i);
		}
		return list;
	}
	
	private static SimpleCollection<Integer> intList1;
	private static SimpleCollection<Integer> intList2;
	private static SimpleCollection<Integer> intList3;
	private static SimpleCollection<Integer> intIter1;
	private static SimpleCollection<Integer> intIter2;
	private static SimpleCollection<Integer> intIter3;
	private static SimpleCollection<Integer> intColl1;
	private static SimpleCollection<Integer> intColl2;
	private static SimpleCollection<Integer> intColl3;
	
	@Test
	public void shouldGetSameInstanceBackFromList()
	{
		SimpleCollection<Integer> sl = SimpleCollection.from(intList1);
		
		assertThat(sl, is(intList1));
	}
	
	@Test
	public void shouldGetSameInstanceBackFromIter()
	{
		SimpleCollection<Integer> sl = SimpleCollection.from(intIter1);
		
		assertThat(sl, is(intIter1));
	}
	
	@Test
	public void shouldGetSameInstanceBackFromColl()
	{
		SimpleCollection<Integer> sl = SimpleCollection.from(intColl1);
		
		assertThat(sl, is(intColl1));
	}
	
	@Test
	public void shouldBeCorrectSize()
	{
		assertThat(intList1.size(), is(1));
		assertThat(intList2.size(), is(2));
		assertThat(intList3.size(), is(3));
		
		assertThat(intIter1.size(), is(1));
		assertThat(intIter2.size(), is(2));
		assertThat(intIter3.size(), is(3));
		
		assertThat(intColl1.size(), is(1));
		assertThat(intColl2.size(), is(2));
		assertThat(intColl3.size(), is(3));
	}
	
	@Test
	public void shouldContainAllTheCorrectElements()
	{
		assertThat(intList1.contains(1), is(true));
		assertThat(intList2.contains(1), is(true));
		assertThat(intList2.contains(2), is(true));
		assertThat(intList3.contains(1), is(true));
		assertThat(intList3.contains(2), is(true));
		assertThat(intList3.contains(3), is(true));
		
		assertThat(intIter1.contains(1), is(true));
		assertThat(intIter2.contains(1), is(true));
		assertThat(intIter2.contains(2), is(true));
		assertThat(intIter3.contains(1), is(true));
		assertThat(intIter3.contains(2), is(true));
		assertThat(intIter3.contains(3), is(true));
		
		assertThat(intColl1.contains(1), is(true));
		assertThat(intColl2.contains(1), is(true));
		assertThat(intColl2.contains(2), is(true));
		assertThat(intColl3.contains(1), is(true));
		assertThat(intColl3.contains(2), is(true));
		assertThat(intColl3.contains(3), is(true));
	}
	
	@Test
	public void shouldGetElementsFromCorrectIndexes()
	{
		assertThat(intList1.get(0), is(1));
		assertThat(intList2.get(0), is(1));
		assertThat(intList3.get(0), is(1));
		assertThat(intList2.get(1), is(2));
		assertThat(intList3.get(1), is(2));
		assertThat(intList3.get(2), is(3));
		
		assertThat(intIter1.get(0), is(1));
		assertThat(intIter2.get(0), is(1));
		assertThat(intIter3.get(0), is(1));
		assertThat(intIter2.get(1), is(2));
		assertThat(intIter3.get(1), is(2));
		assertThat(intIter3.get(2), is(3));
		
		assertThat(intColl1.get(0), is(1));
		assertThat(intColl2.get(0), is(1));
		assertThat(intColl3.get(0), is(1));
		assertThat(intColl2.get(1), is(2));
		assertThat(intColl3.get(1), is(2));
		assertThat(intColl3.get(2), is(3));
	}
	
	@Test
	public void shouldGiveCorrectIndexesOfAllValues()
	{
		assertThat(intList1.indexOf(1), is(0));
		assertThat(intList2.indexOf(1), is(0));
		assertThat(intList2.indexOf(2), is(1));
		assertThat(intList3.indexOf(1), is(0));
		assertThat(intList3.indexOf(2), is(1));
		assertThat(intList3.indexOf(3), is(2));
		
		assertThat(intIter1.indexOf(1), is(0));
		assertThat(intIter2.indexOf(1), is(0));
		assertThat(intIter2.indexOf(2), is(1));
		assertThat(intIter3.indexOf(1), is(0));
		assertThat(intIter3.indexOf(2), is(1));
		assertThat(intIter3.indexOf(3), is(2));
		
		assertThat(intColl1.indexOf(1), is(0));
		assertThat(intColl2.indexOf(1), is(0));
		assertThat(intColl2.indexOf(2), is(1));
		assertThat(intColl3.indexOf(1), is(0));
		assertThat(intColl3.indexOf(2), is(1));
		assertThat(intColl3.indexOf(3), is(2));
	}
	
	@Test
	public void shouldGiveNonNullStream()
	{
		assertNotNull(intList1.stream());
		assertNotNull(intList2.stream());
		assertNotNull(intList3.stream());
		
		assertNotNull(intIter1.stream());
		assertNotNull(intIter2.stream());
		assertNotNull(intIter3.stream());
		
		assertNotNull(intColl1.stream());
		assertNotNull(intColl2.stream());
		assertNotNull(intColl3.stream());
	}

	//**************************************************************************
	// Private test iterable object
	//**************************************************************************
	private static class TestIterable<T> implements Iterable<T>
	{
		@SafeVarargs
		public TestIterable(T... ts)
		{
			for(T t : ts)
			{
				arr.add(t);
			}
		}
		
		@Override
		public Iterator<T> iterator()
		{
			return arr.iterator();
		}
		
		private ArrayList<T> arr = new ArrayList<>();		
	}
}
