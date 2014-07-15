package ezgames.test.matchers;

import org.hamcrest.Matcher;
import org.hamcrest.core.IsNot;
import org.hamcrest.object.IsCompatibleType;


/**
 * Simple extension of hamcrest's {@link org.hamcreast.object.IsCompatibleType}
 * so that it uses my preferred phrasing of "is assignable from" instead. 
 */
public class IsAssignableFrom<T> extends IsCompatibleType<T>
{
	
	public static <T> IsAssignableFrom<T> assignableFrom(Class<T> type)
	{
		return new IsAssignableFrom<>(type);
	}
	
	public static <T> Matcher<Class<?>> isNotAssignableFrom(Class<T> type)
	{
		return new IsNot<Class<?>>(new IsAssignableFrom<T>(type));
	}

	private IsAssignableFrom(Class<T> type)
	{
		super(type);
	}
	
}
