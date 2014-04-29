package ezgames.test.matchers;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class Throws<X extends Throwable> extends TypeSafeMatcher<ThrowingRunnable<X>>
{
	//**************************************************************************
	// Public static factory methods
	//**************************************************************************
	@Factory
	public static <X extends Throwable> Throws<X> throwsA(Class<X> ex)
	{
		return new Throws<X>(ex);
	}
	
	@Factory
	public static <X extends Throwable> Throws<X> throwsAn(Class<X> ex)
	{
		return throwsA(ex);
	}
	
	@Factory
	public static <X extends Throwable> Matcher<ThrowingRunnable<X>> doesNotThrowA(Class<X> ex)
	{
		return org.hamcrest.core.IsNot.not(throwsA(ex));
	}
	
	@Factory
	public static <X extends Throwable> Matcher<ThrowingRunnable<X>> doesNotThrowAn(Class<X> ex)
	{
		return doesNotThrowA(ex);
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
	protected boolean matchesSafely(ThrowingRunnable<X> item)
	{
		try
		{
			item.run();
		}
		catch(Throwable ex)
		{
			if(exType.isInstance(ex))
			{
				return true;
			}
			else
			{
				actualExType = ex.getClass();
				return false;
			}
		}
		return false;
	}
	
	//***************************************************************************
	// Protected methods
	//***************************************************************************
	@Override
	protected void describeMismatchSafely(ThrowingRunnable<X> item, Description mismatchDescription) 
	{
		mismatchDescription.appendText("was a ")
		   .appendText(actualExType.getName());
   }
	
	//**************************************************************************
	// Private fields
	//**************************************************************************
	private Class<X> exType;
	private Class<? extends Throwable> actualExType;
	
	//**************************************************************************
	// Private constructor
	//**************************************************************************
	private Throws(Class<X> ex)
	{
		exType = ex;
	}	
}
