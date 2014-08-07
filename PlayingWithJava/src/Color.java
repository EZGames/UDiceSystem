import func.java.tuples.Quadruplet;
import func.java.tuples.Triplet;


public class Color
{
	public static Color rgb(Triplet<Integer, Integer, Integer> rgb)
	{
		Triplet<Integer, Integer, Integer> hsl = rgb2hsl(rgb);
		return new Color(Quadruplet.of(rgb.one(), rgb.two(), rgb.three(), 255), hsl);
	}
	
	public static Color rgba(Quadruplet<Integer, Integer, Integer, Integer> rgba)
	{
		Triplet<Integer, Integer, Integer> hsl = rgb2hsl(Triplet.of(rgba.one(), rgba.two(), rgba.three()));
		return new Color(rgba, hsl);
	}
	
	public static Color hsl(Triplet<Integer, Integer, Integer> hsl)
	{
		return null;
	}
	
	public static Color hsla(Quadruplet<Integer, Integer, Integer, Integer> hsla)
	{
		return null;
	}
	
	public Integer r()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	public Integer red()
	{
		return r();
	}
	
	public Integer g()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	public Integer green()
	{
		return g();
	}
	
	public Integer b()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	public Integer blue()
	{
		return b();
	}
	
	public Integer a()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	public Integer alpha()
	{
		return a();
	}
	
	public Integer h()
	{
		return null;
	}
	
	public Integer hue()
	{
		return h();
	}
	
	public Integer s()
	{
		return null;
	}
	
	public Integer saturation()
	{
		return s();
	}
	
	public Integer l()
	{
		return null;
	}
	
	public Integer luminosity()
	{
		return l();
	}
	
	private static Triplet<Integer, Integer, Integer> rgb2hsl(Triplet<Integer, Integer, Integer> rgb)
	{
		return null;
	}
	
	private static Triplet<Integer, Integer, Integer> hsl2rgb(Triplet<Integer, Integer, Integer> hsl)
	{
		return null;
	}
	
	private Color(Quadruplet<Integer, Integer, Integer, Integer> rgba, Triplet<Integer, Integer, Integer> hsl)
	{
		this.rgba = rgba;
		this.hsl = hsl;
	}
	
	private final Quadruplet<Integer, Integer, Integer, Integer> rgba;
	private final Triplet<Integer, Integer, Integer> hsl;
}
