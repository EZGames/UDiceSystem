package ezgames.utils.tuples;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Tuple2<T, U>
{
	//***************************************************************************
	// Public static factories
	//***************************************************************************
	public static <T,U> Tuple2<T,U> of(T one, U two)
	{
		return new Tuple2<T,U>(one, two);
	}
	
	//***************************************************************************
	// Public fields
	//***************************************************************************
	public final T one;
	public final U two;
	
	//***************************************************************************
	// Public API methods
	//***************************************************************************
	public Tuple2<T,U> doWithOne(Consumer<T> oneFunc)
	{
		if(oneFunc != null)
		{
			oneFunc.accept(one);
		}
		return this;
	}
	
	public Tuple2<T,U> doWithOneIfNotNull(Consumer<T> oneFunc)
	{
		if(one != null && oneFunc != null)
		{
			oneFunc.accept(one);
		}
		return this;
	}
	
	public <R> R transformOne(Function<T,R> oneFunc)
	{
		if(oneFunc != null)
			return oneFunc.apply(one);
		else
			return null;
	}
	
	public T operateOnOne(UnaryOperator<T> oneFunc)
	{
		if(oneFunc != null)
			return oneFunc.apply(one);
		else
			return null;
	}
	
	public Tuple2<T,U> doWithTwo(Consumer<U> twoFunc)
	{
		if(twoFunc != null)
		{
			twoFunc.accept(two);
		}
		return this;
	}
	
	public Tuple2<T,U> doWithTwoIfNotNull(Consumer<U> twoFunc)
	{
		if(two != null && twoFunc != null)
		{
			twoFunc.accept(two);
		}
		return this;
	}
	
	public <R> R transformTwo(Function<U,R> twoFunc)
	{
		if(twoFunc != null)
			return twoFunc.apply(two);
		else
			return null;
	}
	
	public U operateOnTwo(UnaryOperator<U> twoFunc)
	{
		if(twoFunc != null)
			return twoFunc.apply(two);
		else
			return null;
	}
	
	//***************************************************************************
	// Private constructors
	//***************************************************************************
	private Tuple2(T first, U second)
	{
		one = first;
		two = second;
	}
	
	
}
