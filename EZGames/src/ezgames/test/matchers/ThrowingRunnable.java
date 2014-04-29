package ezgames.test.matchers;

public interface ThrowingRunnable<X extends Throwable>
{
	void run() throws X;
}
