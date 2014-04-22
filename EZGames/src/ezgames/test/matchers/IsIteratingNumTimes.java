package ezgames.test.matchers;

import java.util.Iterator;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.TypeSafeMatcher;

public class IsIteratingNumTimes extends TypeSafeMatcher<Iterator<?>>
{
	int numTimes;
	
	private IsIteratingNumTimes(int times)
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
	public static IsIteratingNumTimes iteratingNumTimes(int times)
	{
		return new IsIteratingNumTimes(times);
	}
	
}
