package ezgames.utils.collections.zarray;

import static ezgames.test.matchers.collections.ContainsAllFrom.containsAllFrom;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.collection.IsIn.isIn;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertTrue;
import java.util.Vector;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import org.junit.Test;
import ezgames.utils.collections.MlList;

public class ZArrayCollectorTest
{
	private final ZArrayCollector<Integer> collector = new ZArrayCollector<>();
	private final int VEC1_VAL1 = 0;
	private final int VEC1_VAL2 = 2;
	private final int VEC1_VAL3 = 4;
	private final int VEC2_VAL1 = 1;
	private final int VEC2_VAL2 = 3;
	private final int VEC2_VAL3 = 5;
	
	private Vector<Integer> makeVec1()
	{
		Vector<Integer> vec = new Vector<>();
		vec.add(VEC1_VAL1);
		vec.add(VEC1_VAL2);
		vec.add(VEC1_VAL3);
		return vec;
	}
	
	private Vector<Integer> makeVec2()
	{
		Vector<Integer> vec = new Vector<>();
		vec.add(VEC2_VAL1);
		vec.add(VEC2_VAL2);
		vec.add(VEC2_VAL3);
		return vec;
	}
	
	@Test
	// supplier() returns a non-null Supplier
	public void supplier1()
	{
		Supplier<Vector<Integer>> result = collector.supplier();
		
		assertThat(result, is(notNullValue()));
	}
	
	@Test
	//supplier() returns a Supplier that returns an empty Vector<Integer>
	public void supplier2()
	{
		Supplier<Vector<Integer>> supplier = collector.supplier();
		
		Vector<Integer> result = supplier.get();
		
		assertThat(result, is(empty()));
	}
	
	@Test
	//accumulator() returns a non-null BiConsumer<Vector<Integer>, Integer>
	public void accumulator1()
	{
		BiConsumer<Vector<Integer>, Integer> result = collector.accumulator();
		
		assertThat(result, is(notNullValue()));
	}
	
	@Test
	//accumulator adds a given element to the Vector
	public void accumulator2()
	{
		int A_NUMBER = 1;
		Vector<Integer> vector = collector.supplier().get();
		BiConsumer<Vector<Integer>, Integer> accumulator = collector.accumulator();
		
		assertThat(vector, is(empty()));
		accumulator.accept(vector, A_NUMBER);
		
		assertThat(A_NUMBER, isIn(vector));
	}
	
	@Test
	//combiner() returns a non-null BinaryOperator<Vector<Integer>>
	public void combiner1()
	{
		BinaryOperator<Vector<Integer>> result = collector.combiner();
		
		assertThat(result, is(notNullValue()));
	}
	
	@Test
	//combiner takes empty 2 vectors and returns a non-null empty vector
	public void combiner2()
	{
		Vector<Integer> vec1 = new Vector<>();
		Vector<Integer> vec2 = new Vector<>();
		
		Vector<Integer> vec3 = collector.combiner().apply(vec1, vec2);
		
		assertThat(vec3, is(notNullValue()));
		assertThat(vec3, is(empty()));
	}
	
	@Test
	//combiner takes 2 vectors and returns a third with all the elements of the originals
	public void combiner3()
	{
		Vector<Integer> vec1 = makeVec1();
		Vector<Integer> vec2 = makeVec2();
		
		Vector<Integer> vec3 = collector.combiner().apply(vec1, vec2);
		
		assertThat(vec3, containsInAnyOrder(VEC1_VAL1, VEC1_VAL2, VEC1_VAL3, VEC2_VAL1, VEC2_VAL2, VEC2_VAL3));
		assertThat(vec3, hasSize(vec1.size() + vec2.size()));
	}
	
	@Test
	//finisher() returns a non-null Function<Vector<Integer>, ZArray<Integer>>
	public void finisher1()
	{
		Function<Vector<Integer>, ZArray<Integer>> result = collector.finisher();
		
		assertThat(result, is(notNullValue()));
	}
	
	@Test
	//finisher turns a vector into a non-null ZArray
	public void finisher2()
	{
		Function<Vector<Integer>, ZArray<Integer>> finisher = collector.finisher();
		Vector<Integer> vector = new Vector<>();
		
		ZArray<Integer> result = finisher.apply(vector);
		
		assertThat(result, is(notNullValue()));
		assertTrue(result.size() == 0);
	}
	
	@Test
	//finisher creates a ZArray with all the same elements that the Vector has
	public void finisher3()
	{
		Function<Vector<Integer>, ZArray<Integer>> finisher = collector.finisher();
		Vector<Integer> vector = makeVec1();
		
		ZArray<Integer> result = finisher.apply(vector);
		
		assertThat(result, containsInAnyOrder(VEC1_VAL1, VEC1_VAL2, VEC1_VAL3));
	}
	
	@Test
	//must have the right characteristics
	public void characteristics()
	{
		assertThat(collector.characteristics(), containsInAnyOrder(Collector.Characteristics.CONCURRENT, Collector.Characteristics.UNORDERED));
	}
	
	@Test
	//works with a Stream  !!INTEGRATION TeST!!
	public void testWithStream()
	{
		MlList<Integer> startingCollection = MlList.fromValues(0, 1, 2, 3, 4, 5);
		
		ZArray<Integer> result = startingCollection.stream().collect(collector);
		
		assertTrue(result.size() == startingCollection.size());
		assertThat(result, containsAllFrom(startingCollection));
	}
	
	@Test
	//works with Parallel Stream !!INTEGRATION TeST!!
	public void testWithParallelStream()
	{
		MlList<Integer> startingCollection = MlList.fromValues(0, 1, 2, 3, 4, 5);
		
		ZArray<Integer> result = startingCollection.stream().parallel().collect(collector);
		
		assertTrue(result.size() == startingCollection.size());
		assertThat(result, containsAllFrom(startingCollection));
	}
}