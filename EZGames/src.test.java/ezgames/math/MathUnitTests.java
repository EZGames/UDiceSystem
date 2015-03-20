package ezgames.math;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import ezgames.math.random.EZRandomTest;
import ezgames.math.range.RangeBuilderTest;
import ezgames.math.range.RangeTest;

@RunWith(Suite.class)
@SuiteClasses({EZRandomTest.class, RangeBuilderTest.class, RangeTest.class})
public class MathUnitTests
{
	
}
