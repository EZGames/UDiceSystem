import func.java.tuples.*;

public class ColorHexStringTest
{
	public static void main(String[] args)
	{
		Color color = Color.rgb(Triplet.of(64, 32, 16));
		System.out.println(color.asHex());
	}
}
