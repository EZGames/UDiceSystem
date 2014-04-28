package ezgames.test.matchers;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import ezgames.utils.collections.SimpleCollection;

public final class IsIn<E> extends TypeSafeMatcher<E>
{
	//**************************************************************************
	// Public static factory methods
	//**************************************************************************
	@Factory
	public static <T> Matcher<T> isIn(Iterable<T> iter)
	{
		return new IsIn<T>(iter);
	}
	
	@Factory
	public static <T> Matcher<T> isNotIn(Iterable<T> iter)
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
	private IsIn(Iterable<E> iterable)
	{
		collection = SimpleCollection.from(iterable);
	}
}
