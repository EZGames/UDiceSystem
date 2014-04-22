package ezgames.test.matchers;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.TypeSafeMatcher;

public class IsThrowing<X extends Exception> extends TypeSafeMatcher<Runnable>
{
	//**************************************************************************
	// Public static factory methods
	//**************************************************************************
	@Factory
	public static <X extends Exception> IsThrowing<X> throwing(Class<X> ex)
	{
		return new IsThrowing<X>(ex);
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
	private IsThrowing(Class<X> ex)
	{
		exType = ex;
	}	
}
