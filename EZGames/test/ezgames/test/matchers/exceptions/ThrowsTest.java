package ezgames.test.matchers.exceptions;

import org.junit.Test;
import static ezgames.test.matchers.exceptions.Throws.*;
import static org.junit.Assert.*;
import static org.hamcrest.core.IsNot.*;

public class ThrowsTest
{
	private Class<? extends Throwable> expectedCause = NullPointerException.class;
	private Class<? extends Throwable> expectedException = IllegalArgumentException.class;
	private String message = "default message";
	
	private ThrowingRunnable nonThrowingRunnable()
	{
		return new ThrowingRunnable() {
			public void run() throws Throwable { }
		};
	}
	
	private ThrowingRunnable simpleThrowingRunnable()
	{
		return new ThrowingRunnable() {
			public void run() throws Throwable { throw new IllegalArgumentException(); }
		};
	}
	
	private ThrowingRunnable throwingRunnableWithMessage()
	{
		return new ThrowingRunnable() {
			public void run() throws Throwable { throw new IllegalArgumentException(message); }
		};
	}
	
	private ThrowingRunnable throwingRunnableWithCause()
	{
		return new ThrowingRunnable() {
			public void run() throws Throwable { throw new IllegalArgumentException(new NullPointerException()); }
		};
	}
	
	private ThrowingRunnable throwingRunnableWithBoth()
	{
		return new ThrowingRunnable() {
			public void run() throws Throwable { throw new IllegalArgumentException(message, new NullPointerException()); }
		};
	}
	
	@Test
	public void allFailingTestsForNonThrowingRunnable()
	{
		assertThat(nonThrowingRunnable(), doesNotThrowAn(expectedException));
		assertThat(nonThrowingRunnable(), not(throwsAn(expectedException).andHasACause()));
		assertThat(nonThrowingRunnable(), not(throwsAn(expectedException).andHasAMessage()));
		assertThat(nonThrowingRunnable(), not(throwsAn(expectedException).andHasCause(expectedCause)));
		assertThat(nonThrowingRunnable(), not(throwsAn(expectedException).andHasMessage(message)));
		assertThat(nonThrowingRunnable(), not(throwsAn(expectedException).andHasNoCause()));
		assertThat(nonThrowingRunnable(), not(throwsAn(expectedException).andHasNoMessage()));
		assertThat(nonThrowingRunnable(), not(throwsAn(expectedException).andHasACause().andHasAMessage()));
		assertThat(nonThrowingRunnable(), not(throwsAn(expectedException).andHasACause().andHasMessage(message)));
		assertThat(nonThrowingRunnable(), not(throwsAn(expectedException).andHasACause().andHasNoMessage()));
		assertThat(nonThrowingRunnable(), not(throwsAn(expectedException).andHasCause(expectedCause).andHasAMessage()));
		assertThat(nonThrowingRunnable(), not(throwsAn(expectedException).andHasCause(expectedCause).andHasMessage(message)));
		assertThat(nonThrowingRunnable(), not(throwsAn(expectedException).andHasCause(expectedCause).andHasNoMessage()));
		assertThat(nonThrowingRunnable(), not(throwsAn(expectedException).andHasNoCause().andHasAMessage()));
		assertThat(nonThrowingRunnable(), not(throwsAn(expectedException).andHasNoCause().andHasMessage(message)));
		assertThat(nonThrowingRunnable(), not(throwsAn(expectedException).andHasNoCause().andHasNoMessage()));
	}
	
	@Test
	public void allPassingTestsForSimpleThrowingRunnable()
	{
		assertThat(simpleThrowingRunnable(), throwsAn(expectedException));
		assertThat(simpleThrowingRunnable(), throwsAn(expectedException).andHasNoCause());
		assertThat(simpleThrowingRunnable(), throwsAn(expectedException).andHasNoMessage());
		assertThat(simpleThrowingRunnable(), throwsAn(expectedException).andHasNoCause().andHasNoMessage());
	}
	
	@Test
	public void allFailingTestsForSimpleThrowingRunnable()
	{
		assertThat(simpleThrowingRunnable(), not(throwsAn(expectedException).andHasACause()));
		assertThat(simpleThrowingRunnable(), not(throwsAn(expectedException).andHasAMessage()));
		assertThat(simpleThrowingRunnable(), not(throwsAn(expectedException).andHasCause(expectedCause)));
		assertThat(simpleThrowingRunnable(), not(throwsAn(expectedException).andHasMessage(message)));
		assertThat(simpleThrowingRunnable(), not(throwsAn(expectedException).andHasACause().andHasAMessage()));
		assertThat(simpleThrowingRunnable(), not(throwsAn(expectedException).andHasACause().andHasMessage(message)));
		assertThat(simpleThrowingRunnable(), not(throwsAn(expectedException).andHasACause().andHasNoMessage()));
		assertThat(simpleThrowingRunnable(), not(throwsAn(expectedException).andHasCause(expectedCause).andHasAMessage()));
		assertThat(simpleThrowingRunnable(), not(throwsAn(expectedException).andHasCause(expectedCause).andHasMessage(message)));
		assertThat(simpleThrowingRunnable(), not(throwsAn(expectedException).andHasCause(expectedCause).andHasNoMessage()));
		assertThat(simpleThrowingRunnable(), not(throwsAn(expectedException).andHasNoCause().andHasAMessage()));
		assertThat(simpleThrowingRunnable(), not(throwsAn(expectedException).andHasNoCause().andHasMessage(message)));
	}
	
	@Test
	public void allPassingTestsForThrowingRunnableWithMessage()
	{
		assertThat(throwingRunnableWithMessage(), throwsAn(expectedException));
		assertThat(throwingRunnableWithMessage(), throwsAn(expectedException).andHasAMessage());
		assertThat(throwingRunnableWithMessage(), throwsAn(expectedException).andHasMessage(message));
		assertThat(throwingRunnableWithMessage(), throwsAn(expectedException).andHasNoCause());
		assertThat(throwingRunnableWithMessage(), throwsAn(expectedException).andHasAMessage().andHasNoCause());
		assertThat(throwingRunnableWithMessage(), throwsAn(expectedException).andHasMessage(message).andHasNoCause());
		
	}
	
	@Test
	public void allFailingTestsForThrowingRunnableWithMessage()
	{
		assertThat(throwingRunnableWithMessage(), not(throwsAn(expectedException).andHasACause()));
		assertThat(throwingRunnableWithMessage(), not(throwsAn(expectedException).andHasCause(expectedCause)));
		assertThat(throwingRunnableWithMessage(), not(throwsAn(expectedException).andHasNoMessage()));
		assertThat(throwingRunnableWithMessage(), not(throwsAn(expectedException).andHasACause().andHasAMessage()));
		assertThat(throwingRunnableWithMessage(), not(throwsAn(expectedException).andHasACause().andHasMessage(message)));
		assertThat(throwingRunnableWithMessage(), not(throwsAn(expectedException).andHasACause().andHasNoMessage()));
		assertThat(throwingRunnableWithMessage(), not(throwsAn(expectedException).andHasCause(expectedCause).andHasAMessage()));
		assertThat(throwingRunnableWithMessage(), not(throwsAn(expectedException).andHasCause(expectedCause).andHasMessage(message)));
		assertThat(throwingRunnableWithMessage(), not(throwsAn(expectedException).andHasCause(expectedCause).andHasNoMessage()));
		assertThat(throwingRunnableWithMessage(), not(throwsAn(expectedException).andHasNoCause().andHasNoMessage()));
	}
	
	@Test
	public void allPassingTestsForThrowingRunnableWithCause()
	{
		assertThat(throwingRunnableWithCause(), throwsAn(expectedException));
		assertThat(throwingRunnableWithCause(), throwsAn(expectedException).andHasACause());
		assertThat(throwingRunnableWithCause(), throwsAn(expectedException).andHasCause(expectedCause));
		assertThat(throwingRunnableWithCause(), throwsAn(expectedException).andHasAMessage());
		assertThat(throwingRunnableWithCause(), throwsAn(expectedException).andHasACause().andHasAMessage());
		assertThat(throwingRunnableWithCause(), throwsAn(expectedException).andHasCause(expectedCause).andHasAMessage());
	}
	
	@Test
	public void allFailingTestsForThrowingRunnableWithCause()
	{
		assertThat(throwingRunnableWithCause(), not(throwsAn(expectedException).andHasMessage(message)));
		assertThat(throwingRunnableWithCause(), not(throwsAn(expectedException).andHasNoCause()));
		assertThat(throwingRunnableWithCause(), not(throwsAn(expectedException).andHasNoMessage()));
		assertThat(throwingRunnableWithCause(), not(throwsAn(expectedException).andHasACause().andHasMessage(message)));
		assertThat(throwingRunnableWithCause(), not(throwsAn(expectedException).andHasACause().andHasNoMessage()));
		assertThat(throwingRunnableWithCause(), not(throwsAn(expectedException).andHasCause(expectedCause).andHasMessage(message)));
		assertThat(throwingRunnableWithCause(), not(throwsAn(expectedException).andHasCause(expectedCause).andHasNoMessage()));
		assertThat(throwingRunnableWithCause(), not(throwsAn(expectedException).andHasNoCause().andHasAMessage()));
		assertThat(throwingRunnableWithCause(), not(throwsAn(expectedException).andHasNoCause().andHasMessage(message)));
		assertThat(throwingRunnableWithCause(), not(throwsAn(expectedException).andHasNoCause().andHasNoMessage()));
	}
	
	@Test
	public void allPassingTestsForThrowingRunnableWithBoth()
	{
		assertThat(throwingRunnableWithBoth(), throwsAn(expectedException));
		assertThat(throwingRunnableWithBoth(), throwsAn(expectedException).andHasACause());
		assertThat(throwingRunnableWithBoth(), throwsAn(expectedException).andHasAMessage());
		assertThat(throwingRunnableWithBoth(), throwsAn(expectedException).andHasCause(expectedCause));
		assertThat(throwingRunnableWithBoth(), throwsAn(expectedException).andHasMessage(message));
		assertThat(throwingRunnableWithBoth(), throwsAn(expectedException).andHasACause().andHasAMessage());
		assertThat(throwingRunnableWithBoth(), throwsAn(expectedException).andHasACause().andHasMessage(message));
		assertThat(throwingRunnableWithBoth(), throwsAn(expectedException).andHasCause(expectedCause).andHasAMessage());
		assertThat(throwingRunnableWithBoth(), throwsAn(expectedException).andHasCause(expectedCause).andHasMessage(message));
	}
	
	@Test
	public void allFailingTestsForThrowingRunnableWithBoth()
	{
		assertThat(throwingRunnableWithBoth(), not(throwsAn(expectedException).andHasNoCause()));
		assertThat(throwingRunnableWithBoth(), not(throwsAn(expectedException).andHasNoMessage()));
		assertThat(throwingRunnableWithBoth(), not(throwsAn(expectedException).andHasACause().andHasNoMessage()));
		assertThat(throwingRunnableWithBoth(), not(throwsAn(expectedException).andHasCause(expectedCause).andHasNoMessage()));
		assertThat(throwingRunnableWithBoth(), not(throwsAn(expectedException).andHasNoCause().andHasAMessage()));
		assertThat(throwingRunnableWithBoth(), not(throwsAn(expectedException).andHasNoCause().andHasMessage(message)));
		assertThat(throwingRunnableWithBoth(), not(throwsAn(expectedException).andHasNoCause().andHasNoMessage()));
	}
	
	@Test
	public void somePassingTestsWithOrderChanged()
	{
		assertThat(throwingRunnableWithBoth(), throwsAn(expectedException).andHasAMessage().andHasACause());
		assertThat(throwingRunnableWithMessage(), throwsAn(expectedException).andHasNoCause().andHasMessage(message));	
	}
	
	@Test
	public void somePassingTestsWithRedundantChecks()
	{
		assertThat(throwingRunnableWithMessage(), throwsAn(expectedException).andHasNoCause().andHasAMessage().andHasMessage(message));
		assertThat(throwingRunnableWithCause(), throwsAn(expectedException).andHasACause().andHasAMessage().andHasCause(expectedCause));
	}
}
