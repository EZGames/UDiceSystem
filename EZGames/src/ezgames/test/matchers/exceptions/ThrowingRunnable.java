package ezgames.test.matchers.exceptions;

/**
 * For use with the {@link Throws} matcher, the {@code ThrowingRunnable}
 * interface is a functional interface similar to {@link Runnable}, except that
 * it can throw a {@link Throwable}.  No specific Throwable type is required,
 * since it's intended us is with the Throws matcher, which does not require it
 * to.
 */
public interface ThrowingRunnable
{
	void run() throws Throwable;
}
