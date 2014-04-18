package ezgames.utils.test;

import static org.junit.Assert.*;

public class TestHelper
{
	public static <X extends Throwable> void assertThrows(final Class<X> exClass, final Runnable block)
	{
		try
		{
			block.run();
		}
		catch(Throwable ex)
		{
			assertTrue(exClass.isInstance(ex));
			return;
		}
		fail("Failed to throw expected exception, " + exClass.toString());
	}
	
	public static <X extends Throwable> void assertThrows(final Class<X> exClass, final Runnable block, String message)
	{
		try
		{
			block.run();
		}
		catch(Throwable ex)
		{
			assertTrue(exClass.isInstance(ex));
			return;
		}
		fail(message);
	}
}
