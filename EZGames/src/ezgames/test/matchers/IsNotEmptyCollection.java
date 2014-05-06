package ezgames.test.matchers;

import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.Matcher;
import org.hamcrest.Factory;
import org.hamcrest.Description;
import ezgames.utils.collections.simple.SimpleCollection;

public final class IsNotEmptyCollection extends TypeSafeMatcher<SimpleCollection<?>>
{
	// **************************************************************************
	// Public static factory methods
	// **************************************************************************
	@Factory
	public static Matcher<SimpleCollection<?>> isNotAnEmptyCollection()
	{
		return new IsNotEmptyCollection();
	}
	
	@Factory
	public static Matcher<SimpleCollection<?>> isAnEmptyCollection()
	{
		return org.hamcrest.core.IsNot.not(isNotAnEmptyCollection());
	}
	
	// **************************************************************************
	// Public API methods
	// **************************************************************************
	@Override
	public boolean matchesSafely(SimpleCollection<?> coll)
	{
		return coll.size() > 0;
	}
	
	public void describeTo(Description desc)
	{
		desc.appendText("not an empty collection");
	}
	
	//***************************************************************************
	// Protected methods
	//***************************************************************************
	@Override
	protected void describeMismatchSafely(SimpleCollection<?> item, Description mismatchDescription) 
	{
		mismatchDescription.appendText("was an empty collection");
   }
	
	//**************************************************************************
	// Private constructor
	//**************************************************************************
	private IsNotEmptyCollection(){}
}
