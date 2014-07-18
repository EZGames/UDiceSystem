package ezgames.utils.tuples;

public final class Tuple2Impl<T, U> implements Tuple2<T, U>
{
	//***************************************************************************
	// Public API methods
	//***************************************************************************
	public T one()
	{
		return one;
	}
	
	public U two()
	{
		return two;
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

	@Override
	public T one()
	{
		return original.two();
	}

	@Override
	public U two()
	{
		return original.one();
	}

	@Override
	public Tuple2<U, T> swap()
	{
		return original;
	}
	
	private final Tuple2<U, T> original;
	
}
