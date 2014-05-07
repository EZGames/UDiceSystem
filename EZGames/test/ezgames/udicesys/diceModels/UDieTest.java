package ezgames.udicesys.diceModels;


import static ezgames.test.matchers.IsNotEmptyCollection.*;
import static ezgames.test.matchers.Throws.*;
import static org.hamcrest.core.IsEqual.*;
import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;
import ezgames.udicesys.diceModels.UDie;
import ezgames.udicesys.diceModels.abstractions.Die;
import ezgames.udicesys.diceModels.abstractions.Face;
import ezgames.udicesys.diceModels.abstractions.FaceValue;
import ezgames.udicesys.diceModels.abstractions.OutputRange;
import ezgames.udicesys.diceModels.abstractions.Relationship;
import ezgames.utils.collections.MlList;
import ezgames.utils.collections.simple.SimpleCollection;
import ezgames.utils.exceptions.NullArgumentException;
import java.util.Iterator;
import java.util.stream.Stream;
import org.junit.BeforeClass;
import org.junit.Test;

public class UDieTest
{	
	static SimpleCollection<Face> faces;
	static String name;
	
	@BeforeClass
	public static void beforeClass()
	{
		Face onlyFace = defaultFace();
		faces = MlList.startWith(onlyFace);
		
		name = "testDie";		
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
	
	// Iterator<Face> iterator() ***********************************************
	@Test
	public void shouldGetNonNullIterator() throws NullArgumentException
	{
		Die die = UDie.with(name, faces);
		
		Iterator<Face> iter = die.iterator();
		
		assertNotNull(iter);
	}
	
	// public Iterable<Relationship> listRelationships() ***********************
	@Test
	public void shouldGetNonNullRelationshipsList() throws NullArgumentException
	{
		Die die = UDie.with(name, faces);
		
		Iterable<Relationship> rels = die.listRelationships();
		
		assertNotNull(rels);
	}
	
	@Test
	public void shouldGetNonEmptyRelationshipsList() throws NullArgumentException
	{
	   Die die = UDie.with(name, faces);
	   
	   SimpleCollection<Relationship> rels = die.listRelationships();
	   
	   assertThat(rels, isNotAnEmptyCollection());
	}

	//**************************************************************************
	
	// public void name() ******************************************************
	@Test
	public void shouldReturnGivenName() throws NullArgumentException
	{
		Die die = UDie.with(name, faces);
		
		assertThat(die.name(), is(equalTo(name)));
	}
	
	@Test
	public void testRoll()
	{
		fail("Not yet implemented");
	}
	
	@Test
	public void testEquals()
	{
		fail("Not yet implemented");
	}
	
	@Test
	public void testHashCode()
	{
		fail("Not yet implemented");
	}
	
	@Test
	public void testStream()
	{
		fail("Not yet implemented");
	}
}
