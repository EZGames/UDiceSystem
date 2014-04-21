package ezgames.test.matchers;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.TypeSafeMatcher;

public class Throws<X extends Exception> extends TypeSafeMatcher<Runnable>
{
	//**************************************************************************
	// Public static factory methods
	//**************************************************************************
	@Factory
	public static <X extends Exception> Throws<X> throwsA(Class<X> ex)
	{
		return new Throws<X>(ex);
	}
	
	@Factory
	public static <X extends Exception> Throws<X> throwsAn(Class<X> ex)
	{
		return new Throws<X>(ex);
	}
	
	//**************************************************************************
	// Public API methods
	//**************************************************************************
	@Override
	public void describeTo(Description description)
	{
		description.appendText("throws " + exType.getSimpleName());
	}

	@Override
	protected boolean matchesSafely(Runnable item)
	{
		try
		{
			item.run();
		}
		catch(Exception ex)
		{
			if(exType.isInstance(ex))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		return false;
	}
	
	//**************************************************************************
	// Private fields
	//**************************************************************************
	private Class<X> exType;
	
	//**************************************************************************
	// Private constructor
	//**************************************************************************
	private Throws(Class<X> ex)
	{
		exType = ex;
	}	
}
