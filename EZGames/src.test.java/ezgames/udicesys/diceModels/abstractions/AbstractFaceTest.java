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

abstract public class AbstractFaceTest
{
	//DOC
	abstract protected Face createTestFaceWith(String name, SimpleCollection<FaceValue> faceValues);
	
	@Test
	// Faces shouldn't have blank names
	public void blankName()
	{
		assertThat(() -> createTestFaceWith("", MlList.empty()), throwsAn(IllegalArgumentException.class));
	}
	
	@Test
	// If multiple FaceValues have the same Relationship, they are combined into a single FaceValue
	public void combineMultipleRelationships1()
	{
		Relationship sameRelationship = new BasicMockRelationship(1);
		FaceValue fv1 = UFaceValue.from(sameRelationship, 1);
		FaceValue fv2 = UFaceValue.from(sameRelationship, 1);
		
		Face face = createTestFaceWith("face", fv1.and(fv2));
		
		int result = face.listFaceValues().size();
		assertThat(result, is(equalTo(1)));
	}
	
	@Test
	// If multiple FaceValues have the same Relationship, they're values are combined into a single FaceValue
	public void combineMultipleRelationships2()
	{
		Relationship sameRelationship = new BasicMockRelationship(1);
		int val1 = 1;
		int val2 = 2;
		int resultVal = val1 + val2;
		FaceValue fv1 = UFaceValue.from(sameRelationship, val1);
		FaceValue fv2 = UFaceValue.from(sameRelationship, val2);
		
		Face face = createTestFaceWith("face", fv1.and(fv2));
		
		int result = face.listFaceValues().get(0).getValue();
		assertThat(result, is(equalTo(resultVal)));
	}
	
	@Test
	// does not combine FaceValues with different Relationships
	public void combineMultipleRelationships3()
	{
		Relationship rel1 = new BasicMockRelationship(1);
		Relationship rel2 = new BasicMockRelationship(2);
		FaceValue fv1 = UFaceValue.from(rel1, 1);
		FaceValue fv2 = UFaceValue.from(rel2, 1);
		
		Face face = createTestFaceWith("face", fv1.and(fv2));
		
		int result = face.listFaceValues().size();
		assertThat(result, is(equalTo(2)));
	}
}
