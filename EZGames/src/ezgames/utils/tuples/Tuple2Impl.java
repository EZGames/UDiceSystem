package ezgames.utils.tuples;

import java.util.function.Consumer;

final class Tuple2Impl<T1, T2> implements Tuple2<T1, T2>
{
	//***************************************************************************
	// Public API methods
	//***************************************************************************
	public T1 one()
	{
		return one;
	}
	
	public Tuple2<T1, T2> useOne(Consumer<? super T1> func)
	{
		if(func != null)
		{
			func.accept(one);
		}
		return this;
	}
	
	public T2 two()
	{
		return two;
	}

	public Tuple2<T1, T2> useTwo(Consumer<? super T2> func)
	{
		if(func != null)
		{
			func.accept(two);
		}
		return this;
	}
	
	public Tuple2<T2, T1> swap()
	{
		return new SwappedTuple2<>(this);
	}
	
	//***************************************************************************
	// Package-private constructors
	//***************************************************************************
	Tuple2Impl(T1 first, T2 second)
	{
		one = first;
		two = second;
	}
	
	//***************************************************************************
	// Private fields
	//***************************************************************************
	private final T1 one;
	private final T2 two;	
}

final class SwappedTuple2<T1, T2> implements Tuple2<T1, T2>
{
	public SwappedTuple2(Tuple2<T2, T1> original)
	{
		this.original = original;
	}

	public T1 one()
	{
		return original.two();
	}

	public Tuple2<T1, T2> useOne(Consumer<? super T1> func)
	{
		original.useTwo(func);
		return this;
	}

	public T2 two()
	{
		return original.one();
	}

	public Tuple2<T1, T2> useTwo(Consumer<? super T2> func)
	{
		original.useOne(func);
		return this;
	}

	public Tuple2<T2, T1> swap()
	{
		return original;
	}
	
	private final Tuple2<T2, T1> original;	
}
