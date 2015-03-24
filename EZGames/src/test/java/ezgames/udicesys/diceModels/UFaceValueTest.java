package ezgames.udicesys.diceModels;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;
import org.junit.Test;
import ezgames.test.mocks.BasicMockRelationship;
import ezgames.udicesys.diceModels.abstractions.FaceValue;
import ezgames.udicesys.diceModels.abstractions.AbstractFaceValueTest;
import ezgames.udicesys.diceModels.abstractions.Relationship;

public class UFaceValueTest extends AbstractFaceValueTest
{
	UFaceValue faceVal;
	final Relationship A_RELATIONSHIP = new BasicMockRelationship(1);
	final Relationship ANOTHER_RELATIONSHIP = new BasicMockRelationship(2);
	final int A_VALUE = 1;
	final int ANOTHER_VALUE = 500;
	
	//Inherited Factory for parent test(s)
	@Override
	protected FaceValue createTestFaceValue(Relationship rel, int val)
	{
		return UFaceValue.from(rel, val);
	}
	
	@Test
	// when constructed with GIVEN_VALUE1 in the constructor, returns same value
	public void value1()
	{
		faceVal = UFaceValue.from(A_RELATIONSHIP, A_VALUE);
		
		int result = faceVal.getValue();
		
		assertThat(result, is(equalTo(A_VALUE)));
	}
	
	@Test
	// when constructed with GIVEN_VALUE2 in the constructor, returns same value
	public void value2()
	{
		faceVal = UFaceValue.from(A_RELATIONSHIP, ANOTHER_VALUE);
		
		int result = faceVal.getValue();
		
		assertThat(result, is(equalTo(ANOTHER_VALUE)));
	}
	
	@Test
	// doesn't return a null Relationship when given one
	public void relationship1()
	{
		faceVal = UFaceValue.from(A_RELATIONSHIP, A_VALUE);
		
		Relationship result = faceVal.getRelationship();
		
		assertThat(result, is(notNullValue()));
	}
	
	@Test
	// returns the given Relationship
	public void relationship2()
	{
		faceVal = UFaceValue.from(A_RELATIONSHIP, A_VALUE);
		
		Relationship result = faceVal.getRelationship();
		
		assertThat(result, is(equalTo(A_RELATIONSHIP)));
	}
	
	@Test
	// returns the other given Relationship - redundancy
	public void relationship3()
	{
		faceVal = UFaceValue.from(ANOTHER_RELATIONSHIP, A_VALUE);
		
		Relationship result = faceVal.getRelationship();
		
		assertThat(result, is(equalTo(ANOTHER_RELATIONSHIP)));
	}
	
	@Test
	public void equals()
	{
		fail("test and class not implemented; waiting on Apache Commons helper");
	}
	
	@Test
	public void hashCode1()
	{
		fail("test and class not implemented; waiting on Apache Commons helper");
	}
}
