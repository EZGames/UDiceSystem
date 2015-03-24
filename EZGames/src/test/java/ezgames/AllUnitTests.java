package ezgames;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import ezgames.math.MathUnitTests;
import ezgames.udicesys.diceModels.DiceModelUnitTests;

@RunWith(Suite.class)
@SuiteClasses({MathUnitTests.class, DiceModelUnitTests.class})
public class AllUnitTests
{
	
}
