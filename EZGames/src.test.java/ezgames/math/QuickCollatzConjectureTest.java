package ezgames.math;

public class QuickCollatzConjectureTest
{
	public static void main(String[] args)
	{
		CollatzConjecture.stream(365).forEach(i -> System.out.println(i));		
	}
}
