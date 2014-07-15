package ezgames.test.matchers;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.TypeSafeMatcher;

/**
 * A matcher that verifies that {@code equals} and {@code hashCode} verify the
 * two objects to be different.
 * 
 * @param <T> the type of object being tested
 */
public class DoesNotEqualAndHashesDifferentlyFrom<T> extends TypeSafeMatcher<T>
{
	//***************************************************************************
	// Public static factory methods
	//***************************************************************************
	@Factory
	public static <T> DoesNotEqualAndHashesDifferentlyFrom<T> doesNotEqualAndHashesDifferentlyFrom(T other)
	{
		return null;
	}
	
	//***************************************************************************
	// Matcher methods
	//***************************************************************************
	@Override
	public void describeMismatchSafely(T item, Description mismatchDescription)
	{
		if(!areUnequal)
		{
			mismatchDescription.appendText("objects are equal");
		}
		if(!areUnequal && !differentHashCode)
		{
			mismatchDescription.appendText("\nand\n");
		}
		if(!differentHashCode)
		{
			mismatchDescription.appendText("objects have the same hash code");
		}
	}
	
	@Override
	public void describeTo(Description description)
	{
		description.appendText("objects are not equal to each other and have different hash codes");
	}
	
	@Override
	protected boolean matchesSafely(T item)
	{
		areUnequal = !item.equals(other);
		differentHashCode = item.hashCode() != other.hashCode();
		return areUnequal && differentHashCode; 
	}
	
	//***************************************************************************
	// Private fields
	//***************************************************************************
	private T other;
	private boolean areUnequal;
	private boolean differentHashCode;
	
	//***************************************************************************
	// Private constructors
	//***************************************************************************
	private DoesNotEqualAndHashesDifferentlyFrom(T other)
	{
		this.other = other;
	}
}
