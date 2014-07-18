package ezgames.utils.tuples;

public interface Tuple3<T1, T2, T3>
{
	T1 one();
	
	T2 two();
	
	T3 three();
	
	Tuple3<T3, T2, T1> swap();
	
	//**************
	// Static factory method
	//***************
	public static <T1, T2, T3> Tuple3<T1, T2, T3> of(T1 one, T2 two, T3 three)
	{
		return new Tuple3Impl<>(one, two, three);
	}
}
