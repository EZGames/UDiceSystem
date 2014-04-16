package com.ezgames.udicesys.diceModels;

import static org.junit.Assert.*;
import static com.ezgames.utils.TestHelper.*;
import java.util.ArrayList;
import java.util.Iterator;
import org.junit.BeforeClass;
import org.junit.Test;
import com.ezgames.udicesys.diceModels.abstractions.Die;
import com.ezgames.udicesys.diceModels.abstractions.Face;
import com.ezgames.udicesys.diceModels.abstractions.FaceValue;
import com.ezgames.udicesys.diceModels.abstractions.OutputRange;
import com.ezgames.udicesys.diceModels.abstractions.Relationship;
import com.ezgames.utils.collections.MlList;

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
		assertThrows(IllegalArgumentException.class, ()->UDie.with(null, faces));
	}
	
	@Test
	public void shouldThrowIllegalArgumentExceptionWithNullFaceList()
	{
		assertThrows(IllegalArgumentException.class, ()->UDie.with(name, null));
	}
	
	@Test
	public void shouldThrowIllegalArgumentExceptionWithNullArguments()
	{
		assertThrows(IllegalArgumentException.class, ()->UDie.with(null, null));
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
