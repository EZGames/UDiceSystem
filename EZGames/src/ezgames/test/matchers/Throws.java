package ezgames.test.matchers;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * {@code Throws} is a matcher that checks whether the given {@link Throwable}
 * type is thrown within the block of code given in the first part of the
 * {@code assertThat()} method.
 * @param <X> the Throwable type being checked for
 */
public class Throws<X extends Throwable> extends TypeSafeMatcher<ThrowingRunnable>
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
	public static <X extends Throwable> Matcher<ThrowingRunnable> doesNotThrowA(Class<X> ex)
	{
		return org.hamcrest.core.IsNot.not(throwsA(ex));
	}
	
	@Factory
	public static <X extends Throwable> Matcher<ThrowingRunnable> doesNotThrowAn(Class<X> ex)
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
	protected boolean matchesSafely(ThrowingRunnable item)
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
	protected void describeMismatchSafely(ThrowingRunnable item, Description mismatchDescription) 
	{
		mismatchDescription.appendText("threw a ")
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
