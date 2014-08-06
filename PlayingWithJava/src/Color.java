import java.util.function.Consumer;
import func.java.tuples.Tuple3;
import func.java.tuples.Tuple4;


public class Color implements Tuple4<Integer, Integer, Integer, Integer>
{
	public static Color rgb(Tuple3<Integer, Integer, Integer> rgb)
	{
		return new Color(Tuple4.of(rgb.one(), rgb.two(), rgb.three(), 255));
	}
	
	public static Color rgba(Tuple4<Integer, Integer, Integer, Integer> rgba)
	{
		return new Color(rgba);
	}
	
	@Override
	public Integer one()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Tuple4<Integer, Integer, Integer, Integer> useOne(Consumer<? super Integer> func)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Integer two()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Tuple4<Integer, Integer, Integer, Integer> useTwo(Consumer<? super Integer> func)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Integer three()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Tuple4<Integer, Integer, Integer, Integer> useThree(Consumer<? super Integer> func)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Integer four()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Tuple4<Integer, Integer, Integer, Integer> useFour(Consumer<? super Integer> func)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Tuple4<Integer, Integer, Integer, Integer> swap()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	
	Color(Tuple4<Integer, Integer, Integer, Integer> rgba)
	{
		this.rgba = rgba;
	}
	
	private final Tuple4<Integer, Integer, Integer, Integer> rgba;
}
