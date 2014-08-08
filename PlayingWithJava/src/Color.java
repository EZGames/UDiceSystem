import func.java.tuples.Quadruplet;
import func.java.tuples.Triplet;


public class Color
{
	public static Color rgb(Triplet<Integer, Integer, Integer> rgb)
	{
		Triplet<Integer, Integer, Integer> hsl = rgb2hsl(rgb.one(), rgb.two(), rgb.three());
		return new Color(rgb.one(), rgb.two(), rgb.three(), 255, hsl.one(), hsl.two(), hsl.three());
	}
	
	public static Color rgba(Quadruplet<Integer, Integer, Integer, Integer> rgba)
	{
		Triplet<Integer, Integer, Integer> hsl = rgb2hsl(rgba.one(), rgba.two(), rgba.three());
		return new Color(rgba.one(), rgba.two(), rgba.three(), rgba.four(), hsl.one(), hsl.two(), hsl.three());
	}
	
	public static Color hsl(Triplet<Integer, Integer, Integer> hsl)
	{
		Triplet<Integer, Integer, Integer> rgb = hsl2rgb(hsl.one(), hsl.two(), hsl.three());
		return new Color(rgb.one(), rgb.two(), rgb.three(), 255, hsl.one(), hsl.two(), hsl.three());
	}
	
	public static Color hsla(Quadruplet<Integer, Integer, Integer, Integer> hsla)
	{
		Triplet<Integer, Integer, Integer> rgb = hsl2rgb(hsla.one(), hsla.two(), hsla.three());
		return new Color(rgb.one(), rgb.two(), rgb.three(), hsla.four(), hsla.one(), hsla.two(), hsla.three());
	}
	
	public Integer r()
	{
		return r;
	}
	
	public Integer red()
	{
		return r();
	}
	
	public Integer g()
	{
		return g;
	}
	
	public Integer green()
	{
		return g();
	}
	
	public Integer b()
	{
		return b;
	}
	
	public Integer blue()
	{
		return b();
	}
	
	public Integer a()
	{
		return a;
	}
	
	public Integer alpha()
	{
		return a();
	}
	
	public Integer h()
	{
		return h;
	}
	
	public Integer hue()
	{
		return h();
	}
	
	public Integer s()
	{
		return s;
	}
	
	public Integer saturation()
	{
		return s();
	}
	
	public Integer l()
	{
		return l;
	}
	
	public Integer luminosity()
	{
		return l();
	}
	
	public Triplet<Integer, Integer, Integer> asHsl()
	{
		return Triplet.of(h, s, l);
	}
	
	public Triplet<Integer, Integer, Integer> asRgb()
	{
		return Triplet.of(r, g, b);
	}
	
	public Quadruplet<Integer, Integer, Integer, Integer> asHsla()
	{
		return Quadruplet.of(h, s, l, a);
	}
	
	public Quadruplet<Integer, Integer, Integer, Integer> asRgba()
	{
		return Quadruplet.of(r, g, b, a);
	}
	
	
	public String asHex()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("#")
			.append(Integer.toHexString(r))
			.append(Integer.toHexString(g))
			.append(Integer.toHexString(b));
		return sb.toString();
	}
	
	private static Triplet<Integer, Integer, Integer> rgb2hsl(int r, int g, int b)
	{
		//TODO
		return Triplet.of(0, 0, 0);
	}
	
	private static Triplet<Integer, Integer, Integer> hsl2rgb(int h, int s, int l)
	{
		return Triplet.of(0, 0, 0);
	}
	
	private Color(int r, int g, int b, int a, int h, int s, int l)
	{
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
		this.h = h;
		this.s = s;
		this.l = l;
	}
	
	private final int r;
	private final int g;
	private final int b;
	private final int a;
	private final int h;
	private final int s;
	private final int l;
}
