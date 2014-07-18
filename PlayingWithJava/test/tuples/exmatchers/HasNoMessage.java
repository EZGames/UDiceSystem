package tuples.exmatchers;

import org.hamcrest.Description;

public class HasNoMessage<X extends Throwable> extends ThrowsMatcher<X>
{
	public HasNoMessage(ThrowsMatcher<X> toBeDecorated)
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
			matches = decoratedMatcher.matchesSafely(item) && getException().getMessage() == null;
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
		
		description.appendText("\nmatch: had no message");		
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
			mismatchDescription.appendText("\nmismatch: had a message when it shouldn't have");
		}
	}
	
	//***************************************************************************
	// Private fields
	//***************************************************************************
	private ThrowsMatcher<X> decoratedMatcher;
	private boolean matches;
}
