package ezgames.utils.tuples;

public final class Tuple3Impl<T1, T2, T3> implements Tuple3<T1, T2, T3>
{
	@Override
	public T1 one()
	{
		return one;
	}

	@Override
	public T2 two()
	{
		return two;
	}

	@Override
	public T3 three()
	{
		return three;
	}

	@Override
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
	public T2 two()
	{
		return original.two();
	}

	@Override
	public T3 three()
	{
		return original.one();
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
