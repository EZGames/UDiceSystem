package ezgames.udicesys.diceModels;


import static ezgames.test.matchers.IsNotEmptyCollection.*;
import static ezgames.test.matchers.Throws.*;
import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;
import ezgames.udicesys.diceModels.UDie;
import ezgames.udicesys.diceModels.abstractions.Die;
import ezgames.udicesys.diceModels.abstractions.Face;
import ezgames.udicesys.diceModels.abstractions.FaceValue;
import ezgames.udicesys.diceModels.abstractions.OutputRange;
import ezgames.udicesys.diceModels.abstractions.Relationship;
import ezgames.utils.collections.MlList;
import java.util.ArrayList;
import java.util.Iterator;
import org.junit.BeforeClass;
import org.junit.Test;

public class UDieTest
{	
	static ArrayList<Face> faces;
	static String name;
	
	@BeforeClass
	public static void beforeClass()
	{
		Face onlyFace = defaultFace();
		faces = new ArrayList<>();
		faces.add(onlyFace);
		
		name = "testDie";		
	}
	
	public static Face defaultFace()
	{
		return new Face() 
			{
				public Iterator<FaceValue> iterator() { return null; }				
				public Iterable<Relationship> listRelationships() { return defaultRelationshipList(); }
			};
	}
	
	public static Iterable<Relationship> defaultRelationshipList()
	{
		Relationship rel = new Relationship() 
			{
				public Iterator<OutputRange> iterator() { return null; }	
			};
		return MlList.<Relationship>empty().add(rel);
	}
	
	// public static with() ****************************************************
	@Test
	public void shouldThrowIllegalArgumentExceptionWithNullName()
	{
		assertThat(()->UDie.with(null, faces), throwsAn(IllegalArgumentException.class));
	}
	
	@Test
	public void shouldThrowIllegalArgumentExceptionWithNullFaceList()
	{
		assertThat(()->UDie.with(name, null), throwsAn(IllegalArgumentException.class));
	}
	
	@Test
	public void shouldThrowIllegalArgumentExceptionWithNullArguments()
	{
		assertThat(()->UDie.with(null, null), throwsAn(IllegalArgumentException.class));
	}
	
	@Test
	public void shouldReturnProperUDie()
	{
		assertNotNull(UDie.with(name, faces));
	}
	
	// Iterator<Face> iterator() ***********************************************
	@Test
	public void shouldGetNonNullIterator()
	{
		Die die = UDie.with(name, faces);
		
		Iterator<Face> iter = die.iterator();
		
		assertNotNull(iter);
	}
	
	// public Iterable<Relationship> listRelationships() ***********************
	@Test
	public void shouldGetNonNullRelationshipsList()
	{
		Die die = UDie.with(name, faces);
		
		Iterable<Relationship> rels = die.listRelationships();
		
		assertNotNull(rels);
	}
	
	@Test
	public void shouldGetNonEmptyRelationshipsList()
	{
	   Die die = UDie.with(name, faces);
	   
	   Iterable<Relationship> rels = die.listRelationships();
	   
	   assertThat(rels, is(notEmptyCollection()));
	}

	//**************************************************************************
	
	//**************************************************************************	
	@Test
	public void testRoll()
	{
		fail("Not yet implemented");
	}
	
	@Test
	public void testName()
	{
		fail("Not yet implemented");
	}
}
