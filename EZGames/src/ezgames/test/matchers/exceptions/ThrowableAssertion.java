package ezgames.test.matchers.exceptions;
//package com.github.kolorobot.exceptions.java8;

//import static org.hamcrest.core.Is.*;
import static org.hamcrest.Matchers.*;
import org.junit.Assert;
import ezgames.utils.exceptions.ExceptionNotThrownAssertionError;

/**
 * This was copied over from http://www.javacodegeeks.com/2014/07/junit-testing-exception-with-java-8-and-lambda-expressions.html
 * as a way to optionally have additional functionality beyond my {@code Throws}
 * Matcher class.
 * @deprecated All the functionality has been added to the {@link Throws} Matcher instead
 */
@Deprecated 
public class ThrowableAssertion {

    public static ThrowableAssertion assertThrown(ThrowingRunnable exceptionThrower) {
        try {
            exceptionThrower.run();
        } catch (Throwable caught) {
            return new ThrowableAssertion(caught);
        }
        throw new ExceptionNotThrownAssertionError();
    }

    private final Throwable caught;

    public ThrowableAssertion(Throwable caught) {
        this.caught = caught;
    }

    @SuppressWarnings("unchecked")
	public ThrowableAssertion isInstanceOf(Class<? extends Throwable> exceptionClass) {
        Assert.assertThat(caught, isA((Class<Throwable>) exceptionClass));
        return this;
    }

    public ThrowableAssertion hasMessage(String expectedMessage) {
        Assert.assertThat(caught.getMessage(), is(equalTo(expectedMessage)));
        return this;
    }

    public ThrowableAssertion hasNoCause() {
        Assert.assertThat(caught.getCause(), nullValue());
        return this;
    }

    @SuppressWarnings("unchecked")
	public ThrowableAssertion hasCauseInstanceOf(Class<? extends Throwable> exceptionClass) {
        Assert.assertThat(caught.getCause(), notNullValue());
        Assert.assertThat(caught.getCause(), isA((Class<Throwable>) exceptionClass));
        return this;
    }
}