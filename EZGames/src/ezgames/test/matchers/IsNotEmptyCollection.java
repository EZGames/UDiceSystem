package ezgames.test.matchers;

import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.Matcher;
import org.hamcrest.Factory;
import org.hamcrest.Description;

import ezgames.utils.*;

public class IsNotEmptyCollection extends TypeSafeMatcher<Iterable<?>>
{
   @Override
   public boolean matchesSafely(Iterable<?> iter)
   {
	  return IterableUtil.sizeOf(iter) > 0;
   }
   
   public void describeTo(Description desc)
   {
	  desc.appendText("is not empty");
   }
   
   @Factory
   public static Matcher<Iterable<?>> notEmptyCollection()
   {
	  return new IsNotEmptyCollection();
   }
}
