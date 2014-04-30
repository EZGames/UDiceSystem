package speedTest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SpeedTestingUtility
{
	public static void run(Runnable action, String nameOfAction, int numTimes)
	{
		long startTime = Calendar.getInstance().getTimeInMillis();
		
		for(int i = 0; i < numTimes; i++)
		{
			action.run();
		}
		
		long timeTaken = Calendar.getInstance().getTimeInMillis() - startTime;
		System.out.println(nameOfAction + ": " + timeTaken + "ms");
	}
	
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
