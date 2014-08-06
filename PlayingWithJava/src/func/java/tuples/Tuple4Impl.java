package func.java.tuples;

import java.util.function.Consumer;

class Tuple4Impl<T1, T2, T3, T4> implements Tuple4<T1, T2, T3, T4>
{
	
	@Override
	public T1 one()
	{
		return one;
	}
	
	@Override
	public Tuple4<T1, T2, T3, T4> useOne(Consumer<? super T1> func)
	{
		if(func != null)
		{
			func.accept(one);
		}
		return this;
	}
	
	@Override
	public T2 two()
	{
		return two;
	}
	
	@Override
	public Tuple4<T1, T2, T3, T4> useTwo(Consumer<? super T2> func)
	{
		if(func != null)
		{
			func.accept(two);
		}
		return this;
	}
	
	@Override
	public T3 three()
	{
		return three;
	}
	
	@Override
	public Tuple4<T1, T2, T3, T4> useThree(Consumer<? super T3> func)
	{
		if(func != null)
		{
			func.accept(three);
		}
		return this;
	}
	
	@Override
	public T4 four()
	{
		return four;
	}
	
	@Override
	public Tuple4<T1, T2, T3, T4> useFour(Consumer<? super T4> func)
	{
		if(func != null)
		{
			func.accept(four);
		}
		return this;
	}
	
	@Override
	public Tuple4<T4, T3, T2, T1> swap()
	{
		return new SwappedTuple4<>(this);
	}
	
	Tuple4Impl(T1 one, T2 two, T3 three, T4 four)
	{
		this.one = one;
		this.two = two;
		this.three = three;
		this.four = four;
	}
	
	private final T1 one;
	private final T2 two;
	private final T3 three;
	private final T4 four;
	
}

class SwappedTuple4<T1, T2, T3, T4> implements Tuple4<T1, T2, T3, T4>
{

	@Override
	public T1 one()
	{
		return normal.four();
	}

	@Override
	public Tuple4<T1, T2, T3, T4> useOne(Consumer<? super T1> func)
	{
		normal.useFour(func);
		return this;
	}

	@Override
	public T2 two()
	{
		return normal.three();
	}

	@Override
	public Tuple4<T1, T2, T3, T4> useTwo(Consumer<? super T2> func)
	{
		normal.useThree(func);
		return this;
	}

	@Override
	public T3 three()
	{
		return normal.two();
	}

	@Override
	public Tuple4<T1, T2, T3, T4> useThree(Consumer<? super T3> func)
	{
		normal.useTwo(func);
		return this;
	}

	@Override
	public T4 four()
	{
		return normal.one();
	}

	@Override
	public Tuple4<T1, T2, T3, T4> useFour(Consumer<? super T4> func)
	{
		normal.useOne(func);
		return this;
	}

	@Override
	public Tuple4<T4, T3, T2, T1> swap()
	{
		return normal;
	}
	
	SwappedTuple4(Tuple4<T4, T3, T2, T1> normal)
	{
		this.normal = normal;
	}
	
	private final Tuple4<T4, T3, T2, T1> normal;
}
