package ezgames.test.matchers.collections;

import java.util.Iterator;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import ezgames.utils.collections.simple.SimpleCollection;

public class ContainsAllFrom<T> extends TypeSafeMatcher<SimpleCollection<T>>
{
	//***************************************************************************
	// Public static facory methods
	//***************************************************************************
	@Factory
	public static <T> Matcher<SimpleCollection<T>> containsAllFrom(SimpleCollection<T> values)
	{
		return new ContainsAllFrom<>(values);
	}
	
	//***************************************************************************
	// Implementation methods
	//***************************************************************************
	@Override
	public void describeTo(Description description)
	{
		description.appendText("contains all values in given SimpleCollection of values");
	}

	@Override
	protected boolean matchesSafely(SimpleCollection<T> collectionUnderTest)
	{
		return matchesSafely(collectionUnderTest, valuesCollection.iterator());
	}
	
	@Override
	protected void describeMismatchSafely(SimpleCollection<T> item, Description mismatchDescription) {
      mismatchDescription.appendText("not all values were in the tested SimpleCollection");
	}
	
	//***************************************************************************
	// Private constructor
	//***************************************************************************
	private ContainsAllFrom(SimpleCollection<T> valuesCollection)
	{
		this.valuesCollection = valuesCollection;
	}
	
	//***************************************************************************
	// Private helper method
	//***************************************************************************
	//TODO: use tail recursion
	private boolean matchesSafely(SimpleCollection<T> collectionUnderTest, Iterator<T> values)
	{
		if(values.hasNext())
			if(collectionUnderTest.contains(values.next()))
				return matchesSafely(collectionUnderTest, values);
			else
				return false;
		else
			return true;
	}
	
	//***************************************************************************
	// Private field
	//***************************************************************************
	private final SimpleCollection<T> valuesCollection;
}