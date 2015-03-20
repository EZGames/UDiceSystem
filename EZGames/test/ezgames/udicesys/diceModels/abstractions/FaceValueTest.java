package ezgames.udicesys.diceModels.abstractions;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsEqual.*;
import static org.hamcrest.core.IsNull.*;
import static ezgames.testing.matchers.exceptions.ThrowsA.*;
import static ezgames.testing.matchers.exceptions.DoesNotThrow.doesNotThrowA;
import org.junit.Test;
import ezgames.test.mocks.BasicMockRelationship;
import ezgames.udicesys.ConflictingRelationshipException;

/**
 * A base class for testing implementations of {@code FaceValue}. All test 
 * classes for {@code FaceValue} implementations should inherit from this test 
 * to make certain that they are following certain invariants.
 * <p>
 * Tests are present for {@link FaceValue#hasSameRelationshipAs()} because it has
 * a default implementation and because all implementations should have the exact
 * same result</p>
 */
abstract public class FaceValueTest
{
	private final Relationship A_RELATIONSHIP = new BasicMockRelationship(1);
	private final Relationship DIFFERENT_RELATIONSHIP = new BasicMockRelationship(2);
	private final int VAL = 1;
	private FaceValue faceVal1;
	private FaceValue faceVal2;
	
	/**
	 * Factory method to create a new {@code FaceValue} of the implementation
	 * under test with the given {@code Relationship} and value.
	 * <p>
	 * Used by FaceValueTest to test that certain invariants are upheld in all 
	 * implementations</p>
	 * <p>
	 * @param rel - a {@code Relationship} to build a {@code FaceValue} from
	 * @return a new {@code FaceValue}, built from the given {@code Relationship}
	 */
	abstract protected FaceValue createTestFaceValue(Relationship rel, int val);
	
	/** Cannot create a FaceValue with a value of 0, since that would be pointless */
	@Test
	public void construct1()
	{
		assertThat(() -> createTestFaceValue(A_RELATIONSHIP, 0), throwsAn(IllegalArgumentException.class));
	}
	
	/** hasSameRelationshipAs() detects when both FaceValues have the same Relationship */
	@Test
	public void hasSameRelationships()
	{
		faceVal1 = createTestFaceValue(A_RELATIONSHIP, VAL);
		faceVal2 = createTestFaceValue(A_RELATIONSHIP, VAL);
		
		assertTrue(faceVal1.hasSameRelationshipAs(faceVal2));
		assertTrue(faceVal2.hasSameRelationshipAs(faceVal1));		
	}
	
	/** hasSameRelationshipAs() detects when two FaceValues have different Relationships */
	@Test
	public void hasDifferentRelationships()
	{
		faceVal1 = createTestFaceValue(A_RELATIONSHIP, VAL);
		faceVal2 = createTestFaceValue(DIFFERENT_RELATIONSHIP, VAL);
		
		assertFalse(faceVal1.hasSameRelationshipAs(faceVal2));
		assertFalse(faceVal2.hasSameRelationshipAs(faceVal1));	
	}
	
	/** combinedWith() throws a ConflictingRelationshipException when this
	 * FaceValue and the given FaceValue have different Relationships*/
	@Test
	public void combiningDifferentRelationships()
	{
		faceVal1 = createTestFaceValue(A_RELATIONSHIP, VAL);
		faceVal2 = createTestFaceValue(DIFFERENT_RELATIONSHIP, VAL);
		
		assertThat(() -> faceVal1.combinedWith(faceVal2), throwsA(ConflictingRelationshipException.class));
		assertThat(() -> faceVal2.combinedWith(faceVal1), throwsA(ConflictingRelationshipException.class));
	}
	
	/** combinedWith() does not throw a ConflictinRelationshipException when both
	 * FaceValues have the same Relationship */
	@Test
	public void combiningSameDoesNotThrow()
	{
		faceVal1 = createTestFaceValue(A_RELATIONSHIP, VAL);
		faceVal2 = createTestFaceValue(A_RELATIONSHIP, VAL);
		
		assertThat(() -> faceVal1.combinedWith(faceVal2), doesNotThrowA(ConflictingRelationshipException.class));
		assertThat(() -> faceVal2.combinedWith(faceVal1), doesNotThrowA(ConflictingRelationshipException.class));
	}
	
	/** combinedWith() returns a non-null FaceValue when given non-conflicting */
	@Test
	public void combiningReturnsNonNull()
	{
		faceVal1 = createTestFaceValue(A_RELATIONSHIP, VAL);
		faceVal2 = createTestFaceValue(A_RELATIONSHIP, VAL);
		
		FaceValue result1 = faceVal1.combinedWith(faceVal2);
		FaceValue result2 = faceVal2.combinedWith(faceVal1);
		
		assertThat(result1, is(notNullValue()));
		assertThat(result2, is(notNullValue()));
	}
	
	/** combinedWith() returns a FaceValue with the same Relationship */
	@Test
	public void combinedHasSameRelationship()
	{
		faceVal1 = createTestFaceValue(A_RELATIONSHIP, VAL);
		faceVal2 = createTestFaceValue(A_RELATIONSHIP, VAL);
		
		Relationship result1 = faceVal1.combinedWith(faceVal2).getRelationship();
		Relationship result2 = faceVal2.combinedWith(faceVal1).getRelationship();
		
		assertThat(result1, is(equalTo(A_RELATIONSHIP)));
		assertThat(result2, is(equalTo(A_RELATIONSHIP)));
	}
	
	/** combinedWith() returns a FaceValue with a sum of the two FaceValues' values */
	@Test
	public void combinedHasSummedValues()
	{
		faceVal1 = createTestFaceValue(A_RELATIONSHIP, VAL);
		faceVal2 = createTestFaceValue(A_RELATIONSHIP, VAL);
		int summedValues = VAL + VAL;
		
		int result1 = faceVal1.combinedWith(faceVal2).getValue();
		int result2 = faceVal2.combinedWith(faceVal1).getValue();
		
		assertThat(result1, is(equalTo(summedValues)));
		assertThat(result2, is(equalTo(summedValues)));
	}
}
