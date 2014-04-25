package ezgames.test.matchers;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.TypeSafeMatcher;
import ezgames.utils.IterableUtil;

public class IsIn<E> extends TypeSafeMatcher<E>
{
	@Factory
	public static <T> IsIn<T> in(Iterable<T> iter)
	{
		return new IsIn<T>(iter);
	}
	
	//***************************************************************************
	// Public API methods
	//***************************************************************************
	@Override
	public void describeTo(Description description)
	{
		description.appendText("is in collection, " + collection + ".");
	}

	@Override
	protected boolean matchesSafely(E item)
	{
		return IterableUtil.contains(collection, item);
	}
	
	//***************************************************************************
	// Private fields
	//***************************************************************************
	private Iterable<E> collection;
	
	//***************************************************************************
	// Private constructor
	//***************************************************************************
	private IsIn(Iterable<E> iterable)
	{
		collection = iterable;
	}
}
