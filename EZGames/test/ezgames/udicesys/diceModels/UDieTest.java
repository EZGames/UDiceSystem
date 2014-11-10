package ezgames.udicesys.diceModels;


import static ezgames.test.matchers.collections.IsNotEmptyCollection.*;
import static ezgames.testing.mocking.Validates.*;
import static org.hamcrest.core.IsEqual.*;
import static org.hamcrest.core.IsNot.*;
import static org.hamcrest.core.IsNull.*;
import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;
import ezgames.test.mocks.MockSimpleRandom;
import ezgames.test.mocks.MockSimpleRandom.UsageAllowance;
import ezgames.udicesys.diceModels.UDie;
import ezgames.udicesys.diceModels.abstractions.Die;
import ezgames.udicesys.diceModels.abstractions.Effect;
import ezgames.udicesys.diceModels.abstractions.Face;
import ezgames.udicesys.diceModels.abstractions.FaceValue;
import ezgames.udicesys.diceModels.abstractions.OutputRange;
import ezgames.udicesys.diceModels.abstractions.Relationship;
import ezgames.udicesys.diceModels.abstractions.Roll;
import ezgames.utils.collections.MlList;
import ezgames.utils.collections.simple.SimpleCollection;
import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Stream;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UDieTest
{	
	static Face onlyFace;
	static MlList<Face> faces;
	static String name;
	static Die die;
	static MockSimpleRandom rand;
	
	@BeforeClass
	public static void beforeClass()
	{
		onlyFace = makeFace("default");
		faces = MlList.startWith(onlyFace);		
		name = "testDie";
	}
	
	@Before
	public void before()
	{
		//reset the randomNumberGenerator for validation purposes
		rand = new MockSimpleRandom(0, true, UsageAllowance.SHOULD_BE_USED);
		//and therefore reset rand in the die
		die = UDie.with(name, faces, rand);
	}
	
	public static Face makeFace(String name)
	{
		return new Face() 
			{
				public Iterator<FaceValue> iterator() { return null; }	
				public SimpleCollection<Relationship> listRelationships() { return defaultRelationshipList(); }
				public String name() { return name; }
				public SimpleCollection<Effect> listEffects() { return null; }
				public SimpleCollection<FaceValue> listFaceValues() { return null; }
			};
	}
	
	public static SimpleCollection<Relationship> defaultRelationshipList()
	{
		Relationship rel = new Relationship() 
			{
				public Iterator<OutputRange> iterator() { return null; }	
				public Stream<OutputRange> stream() {return null; }
			};
		return MlList.startWith(rel);
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
		Iterator<Face> iter = die.iterator();
		
		assertNotNull(iter);
	}
	
	// public Iterable<Relationship> listRelationships() ***********************
	@Test
	public void shouldGetNonNullRelationshipsList()  
	{
		Iterable<Relationship> rels = die.listRelationships();
		
		assertNotNull(rels);
	}
	
	@Test
	public void shouldGetNonEmptyRelationshipsList()  
	{
	   SimpleCollection<Relationship> rels = die.listRelationships();
	   
	   assertThat(rels, isNotAnEmptyCollection());
	}

	//***************************************************************************
	
	// public void name() *******************************************************
	@Test
	public void shouldReturnGivenName()  
	{
		assertThat(die.name(), is(equalTo(name)));
	}
	
	//public boolean equals(obj) ************************************************
	@Test
	public void shouldBeEqual()
	{
		Die die2 = UDie.with(name, faces);
		
		assertThat(die, is(equalTo(die2)));
	}
	
	@Test
	public void shouldEqualSelf()  
	{
		assertThat(die, is(equalTo(die)));
	}
	
	@Test
	public void shouldNotBeEqualWithDifferentNames()
	{
		Die die2 = UDie.with("differentName", faces);
		
		assertThat(die, is(not(equalTo(die2))));
	}
	
	@Test
	public void shouldNotBeEqualWithDifferentFaces()
	{
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
		int hash1 = die.hashCode();
		int hash2 = UDie.with(name, faces).hashCode();
		
		assertThat(hash1, is(equalTo(hash2)));
	}
	
	@Test
	public void shouldHaveDifferentHashCodesWithDifferentNames()
	{
		int hash1 = die.hashCode();
		int hash2 = UDie.with("differentName", faces).hashCode();
		
		assertThat(hash1, is(not(equalTo(hash2))));
	}
	
	@Test
	public void shouldHaveDifferentHashCodesWithDifferentFaces()
	{
		int hash1 = die.hashCode();
		int hash2 = UDie.with(name, differentFaces()).hashCode();
		
		assertThat(hash1, is(not(equalTo(hash2))));
	}
	
	// public Stream<Face> stream() *********************************************
	@Test
	public void shouldGetAStream()  
	{
		assertThat(die.stream(), is(notNullValue()));
	}
	
	@Test
	public void shouldGetCountOfOneFromStream()  
	{
		int count = (int) die.stream().count();
		
		assertThat(count, is(1));
	}
	
	@Test
	public void shouldGetCorrectFace()
	{
		Optional<Face> optFace = die.stream().findFirst();
		
		assertThat(onlyFace, is(equalTo(optFace.get())));
	}
	
	// public Roll roll() *******************************************************
	@Test
	public void shouldGetNonNullRoll()
	{
		Roll roll = die.roll();
		
		assertThat(roll, is(notNullValue()));
	}
	
	@Test
	public void shouldGetRollWithThisDie()
	{
		Die rolledDie = die.roll().die();
		
		assertThat(rolledDie, is(equalTo(die)));
	}
	
	@Test
	public void shouldRollFirstFace()
	{
		Face rolledFace = die.roll().rolledFace();
		
		assertThat(rolledFace.name(), is("default"));
		assertThat(rand, validates());
	}
	
	@Test
	public void shouldRollSecondFace()
	{
		//Create a Die with 2 faces and uses a "random number generator" to return the second face
		Face secondFace = makeFace("second");
		MockSimpleRandom generatesAOne = new MockSimpleRandom(1, true, UsageAllowance.SHOULD_BE_USED);//always generates a 1 (0-indexed), not okay to set the seed
		Die die = UDie.with(name, onlyFace.and(secondFace), generatesAOne); //MlList puts new objects in the front, so secondFace needs to be placed "before" onlyFace in order to be the second object in the collection
		
		Face rolledFace = die.roll().rolledFace();
		
		assertThat(rolledFace.name(), is("second"));
		assertThat(generatesAOne, validates());
	}
}
