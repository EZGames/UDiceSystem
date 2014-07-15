package ezgames.test.matchers.collections;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import ezgames.utils.collections.simple.SimpleCollection;


/**
 * Probably works exactly the same as hamcrest's {@link org.hamcrest.collection.IsIn}
 * matcher, except that, when I wrote this, I only had access to hamcrest's core
 * matchers. Also, this {@code IsIn} is designed to work with the library's
 * {@link ezgames.utils.collections.simple.SimpleCollection SimpleCollection}
 * interface.
 *  @param <E> the type of element stored within the collection being checked
 */
public final class IsIn<E> extends TypeSafeMatcher<E>
{
	//**************************************************************************
	// Public static factory methods
	//**************************************************************************
	@Factory
	public static <T> Matcher<T> isIn(SimpleCollection<T> iter)
	{
		return new IsIn<T>(iter);
	}
	
	@Factory
	public static <T> Matcher<T> isNotIn(SimpleCollection<T> iter)
	{
		return org.hamcrest.core.IsNot.not(new IsIn<T>(iter));
	}
	
	//**************************************************************************
	// Public API methods
	//**************************************************************************
	@Override
	public void describeTo(Description description)
	{
		description.appendText("is in collection");
	}
	
	@Override
	protected boolean matchesSafely(E item)
	{
		
		return collection.contains(item);
	}
	
	//***************************************************************************
	// Protected methods
	//***************************************************************************
	@Override
	protected void describeMismatchSafely(E item, Description mismatchDescription) 
	{
		mismatchDescription.appendText(item.getClass().getSimpleName() + " was not in collection");
   }
	
	//***************************************************************************
	// Private fields
	//***************************************************************************
	private final SimpleCollection<E> collection;
	
	//***************************************************************************
	// Private constructor
	//***************************************************************************
	private IsIn(SimpleCollection<E> iterable)
	{
		collection = iterable;
	}
}
