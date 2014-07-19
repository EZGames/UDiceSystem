package ezgames.utils.tuples;

import java.util.function.Consumer;

final class Tuple3Impl<T1, T2, T3> implements Tuple3<T1, T2, T3>
{
	public T1 one()
	{
		return one;
	}
	
	public Tuple3<T1, T2, T3> useOne(Consumer<? super T1> func)
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

	public Tuple3<T1, T2, T3> useTwo(Consumer<? super T2> func)
	{
		if(func != null)
		{
			func.accept(two);
		}
		return this;
	}

	public T3 three()
	{
		return three;
	}

	public Tuple3<T1, T2, T3> useThree(Consumer<? super T3> func)
	{
		if(func != null)
		{
			func.accept(three);
		}
		return this;
	}

	public Tuple3<T3, T2, T1> swap()
	{
		return new SwappedTuple3<>(this);
	}
	
	Tuple3Impl(T1 one, T2 two, T3 three)
	{
		this.one = one;
		this.two = two;
		this.three = three;
	}
	
	private final T1 one;
	private final T2 two;
	private final T3 three;
}

final class SwappedTuple3<T1, T2, T3> implements Tuple3<T1, T2, T3>
{
	@Override
	public T1 one()
	{
		return original.three();
	}

	@Override
	public Tuple3<T1, T2, T3> useOne(Consumer<? super T1> func)
	{
		original.useThree(func);
		return this;
	}

	@Override
	public T2 two()
	{
		return original.two();
	}

	@Override
	public Tuple3<T1, T2, T3> useTwo(Consumer<? super T2> func)
	{
		original.useTwo(func);
		return this;
	}

	@Override
	public T3 three()
	{
		return original.one();
	}

	@Override
	public Tuple3<T1, T2, T3> useThree(Consumer<? super T3> func)
	{
		original.useOne(func);
		return this;
	}

	@Override
	public Tuple3<T3, T2, T1> swap()
	{
		return original;
	}
	
	SwappedTuple3(Tuple3<T3, T2, T1> original)
	{
		this.original = original;
	}
	
	private final Tuple3<T3, T2, T1> original;
	
}
