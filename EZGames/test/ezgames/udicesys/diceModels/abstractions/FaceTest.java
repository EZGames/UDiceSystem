package ezgames.udicesys.diceModels.abstractions;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsEqual.*;
import static ezgames.testing.matchers.exceptions.ThrowsA.throwsAn;
import org.junit.Test;
import ezgames.test.mocks.BasicMockRelationship;
import ezgames.udicesys.diceModels.UFaceValue;
import ezgames.utils.collections.MlList;
import ezgames.utils.collections.simple.SimpleCollection;

/**
 * A base class for testing implementations of {@code Face}. All test classes 
 * for {@code Face} implementations should inherit from this test to make 
 * certain that they are following certain invariants.
 */
abstract public class FaceTest
{
	/**
	 * Factory method to create a new {@code Face} of the implementation under 
	 * test with the given name and {@code FaceValue}s.
	 * <p>
	 * Used by FaceTest to test that certain invariants are upheld in all 
	 * implementations</p>
	 * <p>
	 * @param name
	 * @param faceValues
	 * @return
	 */
	abstract protected Face createTestFace(String name, SimpleCollection<FaceValue> faceValues);
	
	/** Faces shouldn't have blank names */
	@Test
	public void blankName()
	{
		assertThat(() -> createTestFace("", MlList.empty()), throwsAn(IllegalArgumentException.class));
	}
	
	/** If multiple FaceValues have the same Relationship, they are combined into
	 * a single FaceValue */
	@Test
	public void combineMultipleRelationships1()
	{
		Relationship sameRelationship = new BasicMockRelationship(1);
		FaceValue fv1 = UFaceValue.from(sameRelationship, 1);
		FaceValue fv2 = UFaceValue.from(sameRelationship, 1);
		
		Face face = createTestFace("face", fv1.and(fv2));
		
		int result = face.listFaceValues().size();
		assertThat(result, is(equalTo(1)));
	}
	
	/** If multiple FaceValues have the same Relationship, they're values are 
	 * combined into a single FaceValue */
	@Test
	public void combineMultipleRelationships2()
	{
		Relationship sameRelationship = new BasicMockRelationship(1);
		int val1 = 1;
		int val2 = 2;
		int resultVal = val1 + val2;
		FaceValue fv1 = UFaceValue.from(sameRelationship, val1);
		FaceValue fv2 = UFaceValue.from(sameRelationship, val2);
		
		Face face = createTestFace("face", fv1.and(fv2));
		
		int result = face.listFaceValues().get(0).getValue();
		assertThat(result, is(equalTo(resultVal)));
	}
	
	/** While multiple FaceValues with the same Relationship should be combined, 
	 * we should be sure that it does not combine FaceValues with different 
	 * Relationships */
	@Test
	public void combineMultipleRelationships3()
	{
		Relationship rel1 = new BasicMockRelationship(1);
		Relationship rel2 = new BasicMockRelationship(2);
		FaceValue fv1 = UFaceValue.from(rel1, 1);
		FaceValue fv2 = UFaceValue.from(rel2, 1);
		
		Face face = createTestFace("face", fv1.and(fv2));
		
		int result = face.listFaceValues().size();
		assertThat(result, is(equalTo(2)));
	}
}
