package speedTest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * This Speed-testing utility class is, as the name implies, for testing speeds
 * of actions.  Usually this is done to compare if one way of doing something is
 * faster than another.
 * <p>When using <code>SpeedTestingUtility</code>, make sure the first thing you do
 * is run {@code getReady()}.  This will cause the JIT compiler to cache the
 * {@code run()} method so that the first thing you use it for isn't marked as
 * slower than it actually is.</p>
 * <p>Then you just need to use {@code run()} with a Runnable that contains the
 * code you're testing, A String to use as a label for the output of what's
 * being run, and the number of times you want to run it, since it almost always
 * needs to be run many times in order to have a decent idea of how long it takes.</p>
 * <p>Since there are quite a few instances where speed testing needs to be done
 * on a decent-sized collection of data, {@code getListOfInts()} is available for
 * an easy way to make that collection.  </p>
 */
public class SpeedTestingUtility
{	
	//***************************************************************************
	// Prep methods
	//***************************************************************************
	/**
	 * Runs the {@code run} method enough times for the JIT compiler to cache the
	 * code so that the actual tests don't get messed up by it not being cached
	 * starting off.
	 * <p>This should be the first method you call right before doing the tests.
	 */
	public static void getReady()
	{
		run(SpeedTestingUtility::doNothing, "Readying the SpeedTestUtility", 1000);
	}
	
	private static void doNothing(){}
	
	//***************************************************************************
	// Test methods
	//***************************************************************************
	/**
	 * Runs an action a number of times equal to {@code numTimes} and outputs 
	 * {@code nameOfAction} with how long it took.  Most things must be run many
	 * times in order to get a somewhat accurate amount of time.
	 * @param action - the code you want to test the speed of
	 * @param nameOfAction - a label for what is being run in {@code action}. Is
	 * output as the label for how long it took to run.
	 * @param numTimes - the number of times the action needs to be run.
	 * @throws RuntimeException if the action throws an exception
	 */
	public static void run(Runnable action, String nameOfAction, int numTimes) throws RuntimeException
	{
		cacheAction(action);
		try
		{
			long startTime = Calendar.getInstance().getTimeInMillis();
			
			runNTimes(action, numTimes);
			
			long timeTaken = Calendar.getInstance().getTimeInMillis() - startTime;
			System.out.println(nameOfAction + ": " + timeTaken + "ms");
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	private static void cacheAction(Runnable action)
	{
		runNTimes(action, 100);
	}
	
	private static void runNTimes(Runnable action, int numTimes)
	{
		for(int i = 0; i < numTimes; i++)
		{
			action.run();
		}
	}
	
	//***************************************************************************
	// Helper methods
	//***************************************************************************
	/**
	 * Returns a List of Integers from 0 to {@code howMany - 1}.  Useful for
	 * quickly building collections for tests needing some.
	 * @param howMany - the number of elements in the List of Integers
	 * @return the created list
	 */
	public static List<Integer> getListOfInts(int howMany)
	{
		List<Integer> list = new ArrayList<>();
		
		for(int i = 0; i < howMany; i++)
		{
			list.add(i);
		}
		
		return list;
	}
}
