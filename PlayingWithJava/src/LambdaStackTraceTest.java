
public class LambdaStackTraceTest
{
	@SuppressWarnings("null")
	public static void main(String[] args)
	{
		try
		{
			referenceRunner(LambdaStackTraceTest::referencedMethod);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		try
		{
			String s = null;
			referenceRunner(() -> s.notify());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void referenceRunner(Runnable runnable)
	{
		runnable.run();
	}
	
	public static void referencedMethod()
	{
		throw new NullPointerException();
	}
}
