package ezgames.utils.tuples;

import java.util.function.Consumer;

final class Tuple2Impl<T, U> implements Tuple2<T, U>
{
	//***************************************************************************
	// Public API methods
	//***************************************************************************
	public T one()
	{
		return one;
	}
	
	public Tuple2<T, U> useOne(Consumer<? super T> func)
	{
		if(func != null)
		{
			func.accept(one);
		}
		return this;
	}
	
	public U two()
	{
		return two;
	}

	public Tuple2<T, U> useTwo(Consumer<? super U> func)
	{
		if(func != null)
		{
			func.accept(two);
		}
		return this;
	}
	
	public Tuple2<U, T> swap()
	{
		return new SwappedTuple2<>(this);
	}
	
	//***************************************************************************
	// Package-private constructors
	//***************************************************************************
	Tuple2Impl(T first, U second)
	{
		one = first;
		two = second;
	}
	
	//***************************************************************************
	// Private fields
	//***************************************************************************
	private final T one;
	private final U two;	
}

final class SwappedTuple2<T, U> implements Tuple2<T, U>
{
	public SwappedTuple2(Tuple2<U, T> original)
	{
		this.original = original;
	}

	public T one()
	{
		return original.two();
	}

	public Tuple2<T, U> useOne(Consumer<? super T> func)
	{
		original.useTwo(func);
		return this;
	}

	public U two()
	{
		return original.one();
	}

	public Tuple2<T, U> useTwo(Consumer<? super U> func)
	{
		original.useOne(func);
		return this;
	}

	public Tuple2<U, T> swap()
	{
		return original;
	}
	
	private final Tuple2<U, T> original;	
}
