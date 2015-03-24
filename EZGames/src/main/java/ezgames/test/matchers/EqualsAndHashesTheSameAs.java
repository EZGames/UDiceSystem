package ezgames.test.matchers;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.TypeSafeMatcher;

/**
 * A matcher that verifies that {@code equals} and {@code hashCode} verify the
 * two objects to be the same.
 * 
 * @param <T> the type of object being tested
 */
public class EqualsAndHashesTheSameAs<T> extends TypeSafeMatcher<T>
{
	//***************************************************************************
	// Public static factory methods
	//***************************************************************************
	@Factory
	public static <T> EqualsAndHashesTheSameAs<T> equalsAndHashesTheSameAs(T other)
	{
		return null;
	}
	
	//***************************************************************************
	// Matcher methods
	//***************************************************************************
	@Override
	public void describeMismatchSafely(T item, Description mismatchDescription)
	{
		if(!areEqual)
		{
			mismatchDescription.appendText("objects are not equal");
		}
		if(!areEqual && !sameHashCode)
		{
			mismatchDescription.appendText("\nand\n");
		}
		if(!sameHashCode)
		{
			mismatchDescription.appendText("objects have different hash codes: ");
			mismatchDescription.appendText(Integer.toHexString(item.hashCode()));
			mismatchDescription.appendText(" vs ");
			mismatchDescription.appendText(Integer.toHexString(other.hashCode()));
		}
	}
	
	@Override
	public void describeTo(Description description)
	{
		description.appendText("objects are equal to each other and have the same hash code");
	}
	
	@Override
	protected boolean matchesSafely(T item)
	{
		areEqual = item.equals(other);
		sameHashCode = item.hashCode() == other.hashCode();
		return areEqual && sameHashCode; 
	}
	
	//***************************************************************************
	// Private fields
	//***************************************************************************
	private T other;
	private boolean areEqual;
	private boolean sameHashCode;
	
	//***************************************************************************
	// Private constructors
	//***************************************************************************
	private EqualsAndHashesTheSameAs(T other)
	{
		this.other = other;
	}
}
