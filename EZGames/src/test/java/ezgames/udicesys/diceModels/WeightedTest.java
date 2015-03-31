package ezgames.udicesys.diceModels;

import static org.junit.Assert.*;
import static org.hamcrest.core.IsEqual.*;
import static org.hamcrest.core.Is.*;
import org.junit.Test;

public class WeightedTest
{
	private final Integer AN_OBJECT = 2;
	private final Integer ANOTHER_OBJECT = 4;
	private final int A_WEIGHT = 1;
	private final int ANOTHER_WEIGHT = 5;
	private Weighted<Integer> weightedObject;
	private Weighted<Integer> anotherWeightedObject;
	
	@Test
	public void create1()
	{
		weightedObject = new Weighted<>(AN_OBJECT, A_WEIGHT);
		
		assertThat(weightedObject.object, is(equalTo(AN_OBJECT)));
	}
	
	@Test
	public void create2()
	{
		weightedObject = new Weighted<>(AN_OBJECT, A_WEIGHT);
		
		assertThat(weightedObject.weight, is(equalTo(A_WEIGHT)));
	}
	
	@Test
	public void equalObjects1()
	{
		weightedObject = new Weighted<>(AN_OBJECT, A_WEIGHT);
		anotherWeightedObject = new Weighted<>(AN_OBJECT, ANOTHER_WEIGHT);
		
		assertTrue(weightedObject.hasSameObjectAs(anotherWeightedObject));
	}
	
	@Test
	public void equalObjects2()
	{
		weightedObject = new Weighted<>(AN_OBJECT, A_WEIGHT);
		anotherWeightedObject = new Weighted<>(AN_OBJECT, A_WEIGHT);
		
		assertTrue(weightedObject.hasSameObjectAs(anotherWeightedObject));
	}
	
	@Test
	public void equalObjects3()
	{
		weightedObject = new Weighted<>(AN_OBJECT, A_WEIGHT);
		anotherWeightedObject = new Weighted<>(ANOTHER_OBJECT, ANOTHER_WEIGHT);
		
		assertFalse(weightedObject.hasSameObjectAs(anotherWeightedObject));
	}
	
	@Test
	public void combine1()
	{
		fail("not yet implemented");
	}
}
