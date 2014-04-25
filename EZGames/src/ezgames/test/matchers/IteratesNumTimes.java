package ezgames.test.matchers;

import static org.hamcrest.core.IsNot.*;
import java.util.Iterator;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class IteratesNumTimes extends TypeSafeMatcher<Iterator<?>>
{
	//**************************************************************************
	// Public static factory methods
	//**************************************************************************
	@Factory
	public static IteratesNumTimes iteratesNumTimes(int times)
	{
		return new IteratesNumTimes(times);
	}
	
	@Factory
	public static Matcher<Iterator<?>> doesNotIterateNumTimes(int times)
	{
		return not(iteratesNumTimes(times));
	}

	//**************************************************************************
	// Public API methods
	//**************************************************************************
	@Override
	public void describeTo(Description description)
	{
		description.appendText("iterates " + numTimes + " times");
	}

	@Override
	protected boolean matchesSafely(Iterator<?> item)
	{
		while(item.hasNext())
		{
			count++;
			item.next();
		}
		return count == numTimes;
	}
	
	//***************************************************************************
	// Protected methods
	//***************************************************************************
	@Override
	protected void describeMismatchSafely(Iterator<?> item, Description mismatchDescription) 
	{
		mismatchDescription.appendText("iterated " + count + " times");
   }
	
	//**************************************************************************
	// Private fields
	//**************************************************************************
	int numTimes;
	int count = 0;
	
	//**************************************************************************
	// Private constructor
	//**************************************************************************
	private IteratesNumTimes(int times)
	{
		numTimes = times;
	}	
}
