package ezgames.udicesys.diceModels.abstractions;

import static org.junit.Assert.*;
import static ezgames.testing.matchers.exceptions.ThrowsA.*;
import org.junit.Test;
import ezgames.test.mocks.BasicMockRelationship;

abstract public class FaceValueTest
{
	/**
	 * Creates a new {@code FaceValue} with the given {@code Relationship}.
	 * <p>
	 * Used by tests</p>
	 * <p>
	 * @param rel - a {@code Relationship} to build a {@code FaceValue} from
	 * @return a new {@code FaceValue}, built from the given {@code Relationship}
	 */
	abstract protected FaceValue createTestFaceValueFor(Relationship rel, int val);
	
	@Test
	// cannot create a FaceValue with a value of 0
	public void construct1()
	{
		assertThat(() -> createTestFaceValueFor(new BasicMockRelationship(1), 0), throwsAn(IllegalArgumentException.class));
	}
	
	@Test
	// hasSameRelationshipAs() detects when both FaceValues have the same Relationship
	public void hasSameRelationships()
	{
		Relationship sameRelationship = new BasicMockRelationship(1);
		FaceValue faceVal1 = createTestFaceValueFor(sameRelationship, 1);
		FaceValue faceVal2 = createTestFaceValueFor(sameRelationship, 1);
		
		assertTrue(faceVal1.hasSameRelationshipAs(faceVal2));
		assertTrue(faceVal2.hasSameRelationshipAs(faceVal1));		
	}
	
	@Test
	// hasSameRelationshipAs() detects when two FaceValues have different Relationships
	public void hasDifferentRelationships()
	{
		Relationship firstRelationship = new BasicMockRelationship(1);
		Relationship secondRelationship = new BasicMockRelationship(2);
		FaceValue faceVal1 = createTestFaceValueFor(firstRelationship, 1);
		FaceValue faceVal2 = createTestFaceValueFor(secondRelationship, 1);
		
		assertFalse(faceVal1.hasSameRelationshipAs(faceVal2));
		assertFalse(faceVal2.hasSameRelationshipAs(faceVal1));	
	}
}
