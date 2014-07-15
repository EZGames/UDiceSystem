package ezgames.test.matchers.collections;

import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.Matcher;
import org.hamcrest.Factory;
import org.hamcrest.Description;
import ezgames.utils.collections.simple.SimpleCollection;

/**
 * Similar to hamcrest's {@link org.hamcrests.collection.IsEmptyCollection IsEmptyCollection}
 * matcher, except that the base assumption is reversed and this matcher is set
 * to work with this library's {@link ezgames.utils.collections.simple.SimpleCollection 
 * SimpleCollection} interface, rather than java's {@link java.util.Collection 
 * Collection}
 * interface.
 *
 */
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
