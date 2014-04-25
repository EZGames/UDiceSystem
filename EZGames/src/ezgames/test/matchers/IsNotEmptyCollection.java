package ezgames.test.matchers;

import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.Matcher;
import org.hamcrest.Factory;
import org.hamcrest.Description;
import ezgames.utils.*;

public final class IsNotEmptyCollection extends TypeSafeMatcher<Iterable<?>>
{
	// **************************************************************************
	// Public static factory methods
	// **************************************************************************
	@Factory
	public static Matcher<Iterable<?>> isNotAnEmptyCollection()
	{
		return new IsNotEmptyCollection();
	}
	
	@Factory
	public static Matcher<Iterable<?>> isAnEmptyCollection()
	{
		return org.hamcrest.core.IsNot.not(isNotAnEmptyCollection());
	}
	
	// **************************************************************************
	// Public API methods
	// **************************************************************************
	@Override
	public boolean matchesSafely(Iterable<?> iter)
	{
		return IterableUtil.sizeOf(iter) > 0;
	}
	
	public void describeTo(Description desc)
	{
		desc.appendText("not an empty collection");
	}
	
	//***************************************************************************
	// Protected methods
	//***************************************************************************
	@Override
	protected void describeMismatchSafely(Iterable<?> item, Description mismatchDescription) 
	{
		mismatchDescription.appendText("was an empty collection");
   }
	
	//**************************************************************************
	// Private constructor
	//**************************************************************************
	private IsNotEmptyCollection(){}
}
