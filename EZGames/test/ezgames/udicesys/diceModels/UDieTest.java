package ezgames.udicesys.diceModels;


import static ezgames.test.matchers.IsNotEmptyCollection.*;
import static ezgames.test.matchers.Throws.*;
import static org.hamcrest.core.IsEqual.*;
import static org.hamcrest.core.IsNot.*;
import static org.hamcrest.core.IsNull.*;
import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;
import ezgames.udicesys.diceModels.UDie;
import ezgames.udicesys.diceModels.abstractions.Die;
import ezgames.udicesys.diceModels.abstractions.Face;
import ezgames.udicesys.diceModels.abstractions.FaceValue;
import ezgames.udicesys.diceModels.abstractions.OutputRange;
import ezgames.udicesys.diceModels.abstractions.Relationship;
import ezgames.udicesys.diceModels.abstractions.Roll;
import ezgames.utils.collections.MlList;
import ezgames.utils.collections.simple.SimpleCollection;
import ezgames.utils.exceptions.NullArgumentException;
import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Stream;
import org.junit.BeforeClass;
import org.junit.Test;

public class UDieTest
{	
	static Face onlyFace;
	static SimpleCollection<Face> faces;
	static String name;
	static Die die;
	
	@BeforeClass
	public static void beforeClass() throws NullArgumentException
	{
		onlyFace = defaultFace();
		faces = MlList.startWith(onlyFace);
		
		name = "testDie";
		
		die = UDie.with(name, faces);
	}
	
	public static Face defaultFace()
	{
		return new Face() 
			{
				public Iterator<FaceValue> iterator() { return null; }	
				public Stream<FaceValue> stream() {return null; }
				public SimpleCollection<Relationship> listRelationships() { return defaultRelationshipList(); }
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
	public void shouldThrowNullArgumentExceptionWithNullName()
	{
		assertThat(()->UDie.with(null, faces), throwsA(NullArgumentException.class));
	}
	
	@Test
	public void shouldThrowNullArgumentExceptionWithNullFaceList()
	{
		assertThat(()->UDie.with(name, null), throwsA(NullArgumentException.class));
	}
	
	@Test
	public void shouldThrowNullArgumentExceptionWithNullArguments()
	{
		assertThat(()->UDie.with(null, null), throwsA(NullArgumentException.class));
	}
	
	@Test
	public void shouldReturnProperUDie() throws NullArgumentException
	{
		assertNotNull(UDie.with(name, faces));
	}
	
	// public static with(*,*,simpleRandom) *************************************
	@Test
	public void shouldThrowNullArgumentExceptionWithNullRandom()
	{
		fail("hurry up and do it, fathead!");
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
	public void shouldBeEqual() throws NullArgumentException  
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
	public void shouldNotBeEqualWithDifferentNames() throws NullArgumentException
	{
		Die die2 = UDie.with("differentName", faces);
		
		assertThat(die, is(not(equalTo(die2))));
	}
	
	@Test
	public void shouldNotBeEqualWithDifferentFaces() throws NullArgumentException
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
	public void shouldHaveEqualHashCodes() throws NullArgumentException
	{
		int hash1 = die.hashCode();
		int hash2 = UDie.with(name, faces).hashCode();
		
		assertThat(hash1, is(equalTo(hash2)));
	}
	
	@Test
	public void shouldHaveDifferentHashCodesWithDifferentNames() throws NullArgumentException
	{
		int hash1 = die.hashCode();
		int hash2 = UDie.with("differentName", faces).hashCode();
		
		assertThat(hash1, is(not(equalTo(hash2))));
	}
	
	@Test
	public void shouldHaveDifferentHashCodesWithDifferentFaces() throws NullArgumentException
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
		Die rollDie = die.roll().die();
		
		assertThat(rollDie, is(equalTo(die)));
	}
	
	@Test
	public void shouldGetCorrectRoll()
	{
		fail("Not yet implemented");
	}
	
	@Test
	public void testRoll()
	{
		fail("Not yet implemented");
	}
}
