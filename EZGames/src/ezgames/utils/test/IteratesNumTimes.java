package ezgames.utils.test;

import java.util.Iterator;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.TypeSafeMatcher;

public class IteratesNumTimes extends TypeSafeMatcher<Iterator<?>>
{
	int numTimes;
	
	private IteratesNumTimes(int times)
	{
		numTimes = times;
	}

	@Override
	public void describeTo(Description description)
	{
		description.appendText("iterates " + numTimes + " times");
	}

	@Override
	protected boolean matchesSafely(Iterator<?> item)
	{
		int count = 0;
		while(item.hasNext())
		{
			count++;
			item.next();
		}
		return count == numTimes;
	}
	
	@Factory
	public static IteratesNumTimes iteratesNumTimes(int times)
	{
		return new IteratesNumTimes(times);
	}
	
}
