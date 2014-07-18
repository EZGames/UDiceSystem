package ezgames.utils.tuples;

public interface Tuple2<T, U>
{
	T one();
	
	U two();
	
	Tuple2<U, T> swap();
	
	//***************************************************************************
	// Public static factories
	//***************************************************************************
	public static <T,U> Tuple2<T,U> of(T one, U two)
	{
		return new Tuple2Impl<T,U>(one, two);
	}
}
