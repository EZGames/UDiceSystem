package ezgames.test.matchers.exceptions;

import org.hamcrest.Description;

public class HasNoCause<X extends Throwable> extends ThrowsMatcher<X>
{
	public HasNoCause(ThrowsMatcher<X> toBeDecorated)
	{
		decoratedMatcher = toBeDecorated;
	}
	
	//***************************************************************************
	// Matcher Methods
	//***************************************************************************
	@Override
	protected Throwable getException()
	{
		return decoratedMatcher.getException();
	}
	
	@Override
	protected boolean matchesSafely(ThrowingRunnable item)
	{
		try
		{
			matches = decoratedMatcher.matchesSafely(item) && getException().getCause() == null;
		}
		catch(NullPointerException ex)
		{
			matches = false;
		}

		return matches;
	}
	
	@Override
	public void describeTo(Description description)
	{
		decoratedMatcher.describeTo(description);
		
		description.appendText("\nmatch: had no cause");		
	}
	
	@Override
	protected void describeMismatchSafely(ThrowingRunnable item, Description mismatchDescription)
	{
		if(matches)
		{
			decoratedMatcher.describeMismatchSafely(item, mismatchDescription);
		}
		else
		{
			decoratedMatcher.describeTo(mismatchDescription);
			mismatchDescription.appendText("\nmismatch: had a cause when it shouldn't have");
		}
	}
	
	//***************************************************************************
	// Private fields
	//***************************************************************************
	private ThrowsMatcher<X> decoratedMatcher;
	private boolean matches;
}
