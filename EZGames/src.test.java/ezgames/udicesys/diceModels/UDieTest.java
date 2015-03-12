package ezgames.udicesys.diceModels;


import static ezgames.test.matchers.collections.IsNotEmptyCollection.isNotAnEmptyCollection;
import static ezgames.testing.mocking.Validates.validates;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import java.util.Iterator;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import ezgames.test.mocks.BasicMockRelationship;
import ezgames.test.mocks.MockSimpleRandom;
import ezgames.test.mocks.ValidationDescription;
import ezgames.udicesys.diceModels.abstractions.Die;
import ezgames.udicesys.diceModels.abstractions.AbstractDieTest;
import ezgames.udicesys.diceModels.abstractions.Face;
import ezgames.udicesys.diceModels.abstractions.FaceValue;
import ezgames.udicesys.diceModels.abstractions.Relationship;
import ezgames.udicesys.diceModels.abstractions.Roll;
import ezgames.utils.collections.MlList;
import ezgames.utils.collections.simple.SimpleCollection;

public class UDieTest extends AbstractDieTest
{	
	final Face onlyFace = makeFace("default");
	final MlList<Face> faces = MlList.startWith(onlyFace);
	final String name = "testDie";
	Die die;
	MockSimpleRandom rand;
	
	@Before
	public void before()
	{
		//reset the randomNumberGenerator for validation purposes
		rand = new MockSimpleRandom(
				(randCalled, seedCalled, min, max) -> new ValidationDescription("randBetween() was never called", randCalled),
				0);
	}
	
	// Initialization helpers ***************************************************
	public Face makeFace(String name)
	{
		return new Face() 
			{
				public Iterator<FaceValue> iterator() { return null; }	
				public SimpleCollection<Relationship> listRelationships() { return defaultRelationshipList(); }
				public String name() { return name; }
				//public SimpleCollection<Effect> listEffects() { return null; }
				public SimpleCollection<FaceValue> listFaceValues() { return null; }
			};
	}
	
	public static SimpleCollection<Relationship> defaultRelationshipList()
	{
		Relationship rel = new BasicMockRelationship(1);
		return MlList.startWith(rel);
	}

	// parent test helper//******************************************************
	protected Die createTestDie(String testName)
	{
		return UDie.with(testName, faces);
	}
	
	// public static with() ****************************************************
	@Test
	public void shouldReturnProperUDieWithNameAndFaces()
	{
		assertNotNull(UDie.with(name, faces));
	}
	
	// public static with(*,*,simpleRandom) *************************************
	@Test
	public void shouldReturnProperUDieWithNameFacesAndRandom()
	{
		assertNotNull(UDie.with(name, faces, rand));
	}
	
	// Iterator<Face> iterator() ***********************************************
	@Test
	public void shouldGetNonNullIterator()
	{
		die = UDie.with(name, faces, rand);
		
		Iterator<Face> iter = die.iterator();
		
		assertNotNull(iter);
	}
	
	// public Iterable<Relationship> listRelationships() ***********************
	@Test
	public void shouldGetNonNullRelationshipsList()  
	{
		die = UDie.with(name, faces, rand);
		
		Iterable<Relationship> rels = die.listRelationships();
		
		assertNotNull(rels);
	}
	
	@Test
	public void shouldGetNonEmptyRelationshipsList()  
	{
		die = UDie.with(name, faces, rand);
		
	   SimpleCollection<Relationship> rels = die.listRelationships();
	   
	   assertThat(rels, isNotAnEmptyCollection());
	}

	//***************************************************************************
	
	// public void name() *******************************************************
	@Test
	public void shouldReturnGivenName()  
	{
		die = UDie.with(name, faces, rand);
		
		assertThat(die.name(), is(equalTo(name)));
	}
	
	//public boolean equals(obj) ************************************************
	@Test
	public void shouldBeEqual()
	{
		fail("test and class not implemented; waiting on Apache Commons helper");
		
		die = UDie.with(name, faces, rand);
		Die die2 = UDie.with(name, faces);
		
		assertThat(die, is(equalTo(die2)));
	}
	
	@Test
	public void shouldEqualSelf()  
	{
		fail("test and class not implemented; waiting on Apache Commons helper");
		
		die = UDie.with(name, faces, rand);
		
		assertThat(die, is(equalTo(die)));
	}
	
	@Test
	public void shouldNotBeEqualWithDifferentNames()
	{
		fail("test and class not implemented; waiting on Apache Commons helper");
		
		die = UDie.with(name, faces, rand);
		Die die2 = UDie.with("differentName", faces);
		
		assertThat(die, is(not(equalTo(die2))));
	}
	
	@Test
	public void shouldNotBeEqualWithDifferentFaces()
	{
		fail("test and class not implemented; waiting on Apache Commons helper");
		
		die = UDie.with(name, faces, rand);
		Die die2 = UDie.with(name, differentFaces());
		
		assertThat(die, is(not(equalTo(die2))));
	}
		// only needed in two methods: shouldNotBeEqualWithDifferentFaces(), and shouldHaveDifferentHashCodeWithDifferentFaces()
		private SimpleCollection<Face> differentFaces()
		{
			return MlList.startWith(onlyFace).add(onlyFace);
		}
	
	// public int hashCode() ****************************************************
	@Test
	public void shouldHaveEqualHashCodes()
	{
		fail("test and class not implemented; waiting on Apache Commons helper");
		
		die = UDie.with(name, faces, rand);
		Die die2 = UDie.with(name, faces);
		
		int hash1 = die.hashCode();
		int hash2 = die2.hashCode();
		
		assertThat(hash1, is(equalTo(hash2)));
	}
	
	@Test
	public void shouldHaveDifferentHashCodesWithDifferentNames()
	{
		fail("test and class not implemented; waiting on Apache Commons helper");
		
		die = UDie.with(name, faces, rand);
		Die die2 = UDie.with("differentName", faces);
		
		int hash1 = die.hashCode();
		int hash2 = die2.hashCode();
		
		assertThat(hash1, is(not(equalTo(hash2))));
	}
	
	@Test
	public void shouldHaveDifferentHashCodesWithDifferentFaces()
	{
		fail("test and class not implemented; waiting on Apache Commons helper");
		
		die = UDie.with(name, faces, rand);
		Die die2 = UDie.with(name, differentFaces());
				
		int hash1 = die.hashCode();
		int hash2 = die2.hashCode();
		
		assertThat(hash1, is(not(equalTo(hash2))));
	}
	
	// public Stream<Face> stream() *********************************************
	@Test
	public void shouldGetAStream()  
	{
		die = UDie.with(name, faces, rand);
		
		assertThat(die.stream(), is(notNullValue()));
	}
	
	@Test
	public void shouldGetCountOfOneFromStream()  
	{
		die = UDie.with(name, faces, rand);
		
		int count = (int) die.stream().count();
		
		assertThat(count, is(1));
	}
	
	@Test
	public void shouldGetCorrectFace()
	{
		die = UDie.with(name, faces, rand);
		
		Optional<Face> optFace = die.stream().findFirst();
		
		assertThat(onlyFace, is(equalTo(optFace.get())));
	}
	
	// public Roll roll() *******************************************************
	@Test
	public void shouldGetNonNullRoll()
	{
		die = UDie.with(name, faces, rand);
		
		Roll roll = die.roll();
		
		assertThat(roll, is(notNullValue()));
	}
	
	@Test
	public void shouldGetRollWithThisDie()
	{
		die = UDie.with(name, faces, rand);
		
		Die rolledDie = die.roll().die();
		
		assertThat(rolledDie, is(equalTo(die)));
	}
	
	@Test
	public void shouldRollFirstFace()
	{
		die = UDie.with(name, faces, rand);
		
		Face rolledFace = die.roll().rolledFace();
		
		assertThat(rolledFace.name(), is("default"));
		assertThat(rand, validates());
	}
	
	@Test
	public void shouldRollSecondFace()
	{
		//Create a Die with 2 faces and uses a "random number generator" to return the second face
		Face secondFace = makeFace("second");
		MockSimpleRandom generatesAOne = new MockSimpleRandom(
				(randCalled, seedCalled, min, max) -> new ValidationDescription("randBetween() wasn't called and/or setSeed() was called", randCalled && ! seedCalled), 
				1);//always generates a 1 (0-indexed), not okay to set the seed
		Die die = UDie.with(name, onlyFace.and(secondFace), generatesAOne); //MlList puts new objects in the front, so secondFace needs to be placed "before" onlyFace in order to be the second object in the collection
		
		Face rolledFace = die.roll().rolledFace();
		
		assertThat(rolledFace.name(), is("second"));
		assertThat(generatesAOne, validates());
	}
}
