package ezgames.udicesys.diceModels;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import java.util.Iterator;
import java.util.stream.Stream;
import org.junit.Test;
import ezgames.udicesys.diceModels.abstractions.FaceValue;
import ezgames.udicesys.diceModels.abstractions.OutputRange;
import ezgames.udicesys.diceModels.abstractions.Relationship;
import ezgames.utils.collections.MlList;
import ezgames.utils.collections.simple.SimpleCollection;

public class UFaceTest
{
	private static final String name = "name";
	private static final Relationship rel1 = generateRelationship();
	private static final Relationship rel2 = generateRelationship();
	private static final FaceValue value1 = generateFaceValue(rel1);
	private static final FaceValue value2 = generateFaceValue(rel1);
	private static final FaceValue value3 = generateFaceValue(rel2);
	private static final int weight = 2;
	private static final MlList<FaceValue> faceValues = MlList.startWith(value1).add(value2).add(value3); 
	private UFace face;
	
	private static FaceValue generateFaceValue(Relationship rel)
	{
		return new FaceValue(){
			public int getValue() { return 0; }
			public Relationship getRelationship() { return rel; }			
		};
	}
	
	private static Relationship generateRelationship()
	{
		return new Relationship() {
			public Iterator<OutputRange> iterator() { return null; }
			public Stream<OutputRange> stream() { return null; }
		};
	}
	
	@Test
	// the "name" constant is the name that "face" is set with
	public void name1()
	{
		face = UFace.with(name, faceValues);
		
		String result = face.name();
		
		assertThat(result, is(equalTo(name)));
	}
	
	@Test
	// an alternate name to test that UFace isn't returning a constant
	public void name2()
	{
		String otherName = "a different name";
		face = UFace.with(otherName, faceValues);
		
		String result = face.name();
		
		assertThat(result, is(equalTo(otherName)));
	}
	
	@Test
	// the default weight is 1
	public void weight1()
	{
		face = UFace.with(name, faceValues);
		
		int result = face.weight();
		
		assertThat(result, is(equalTo(1)));
	}
	
	@Test
	// weight() returns the given weight
	public void weight2()
	{
		face = UFace.with(name, faceValues, weight);
		
		int result = face.weight();
		
		assertThat(result, is(equalTo(weight)));
	}
	
	@Test
	// listRelationships returns the only relationship in the only facevalue given
	public void listRelationships1()
	{
		face = UFace.with(name, MlList.startWith(value1));
		
		SimpleCollection<Relationship> result = face.listRelationships();
		
		//just rel1
		assertTrue(result.contains(rel1));
		assertThat(result.size(), is(equalTo(1)));
	}
	
	@Test
	// listRelationships returns the only two relationships in the two facevalues given
	public void listRelationships2()
	{
		face = UFace.with(name, MlList.startWith(value1).add(value3)); //value1 has rel1 and value3 has rel2
		
		SimpleCollection<Relationship> result = face.listRelationships();
		
		//just rel1 and rel2
		assertTrue(result.contains(rel1));
		assertTrue(result.contains(rel2));
		assertThat(result.size(), is(equalTo(2)));
	}
	
	@Test
	// listRelationships returns a collection containing only rel1 and rel2
	public void listRelationships3()
	{
		face = UFace.with(name, faceValues); //value1 and value2 both have rel1, and value3 has rel2
		
		SimpleCollection<Relationship> result = face.listRelationships();
		
		//only rel1 and rel2
		assertTrue(result.contains(rel1));
		assertTrue(result.contains(rel2));
		assertThat(result.size(), is(equalTo(2)));
	}
	
	@Test
	// iterator returns only values in facevalues
	public void iterator1()
	{
		face = UFace.with(name, faceValues);
		
		for(FaceValue fv : face)
		{
			assertTrue(faceValues.contains(fv));
		}
	}
	
	@Test
	// iterator only provides 3 facevalues
	public void iterator2()
	{
		face = UFace.with(name, faceValues);
		
		int count = 0;
		for(FaceValue fv: face)
		{
			count++;
		}
		
		assertThat(count, is(equalTo(3))); //There are 3 FaceValues in faceValues
	}
	
	@Test
	// listFaceValues returns pretty much exactly faceValues
	public void listFaceValues()
	{
		face = UFace.with(name, faceValues);
		
		SimpleCollection<FaceValue> result = face.listFaceValues();
		
		assertThat(result, is(equalTo(faceValues)));
	}
	
	//TODO TEST static factories
	//TODO move some tests out to a FaceTest 
}
