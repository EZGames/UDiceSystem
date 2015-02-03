package ezgames.utils.collections.zarray;

import java.util.EnumSet;
import java.util.Set;
import java.util.Vector;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import ezgames.utils.collections.simple.SimpleCollection;

final class ZArrayCollector<T> implements Collector<T, Vector<T>, ZArray<T>>
{
	final Set<Collector.Characteristics> characteristics;
	
	ZArrayCollector()
	{
		characteristics = EnumSet.of(Collector.Characteristics.CONCURRENT, Collector.Characteristics.UNORDERED);
	}
	
	@Override
	public Supplier<Vector<T>> supplier()
	{
		return () -> new Vector<T>();
	}

	@Override
	public BiConsumer<Vector<T>, T> accumulator()
	{
		return (acc, el) -> acc.add(el);
	}

	@Override
	public BinaryOperator<Vector<T>> combiner()
	{
		return (acc1, acc2) -> {
			Vector<T> result = new Vector<>();
			result.addAll(acc1);
			result.addAll(acc2);
			return result; 
		};
	}

	@Override
	public Function<Vector<T>, ZArray<T>> finisher()
	{
		return acc -> ZArray.createFromSimpleCollection(SimpleCollection.from(acc));
	}

	@Override
	public Set<Collector.Characteristics> characteristics()
	{
		return characteristics;
	}
	
}