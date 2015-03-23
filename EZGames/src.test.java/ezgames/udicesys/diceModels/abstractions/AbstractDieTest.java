package ezgames.udicesys.diceModels.abstractions;

import static ezgames.testing.matchers.exceptions.ThrowsA.throwsAn;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import org.junit.Test;
import ezgames.utils.collections.simple.SimpleCollection;

/**
 * A base class for testing implementations of {@code Die}. All test classes 
 * for {@code Die} implementations should inherit from this test to make 
 * certain that they are following certain invariants.
 */
abstract public class AbstractDieTest
{
	/**
	 * Factory method to create a new {@code Die} of the implementation under 
	 * test with the given name.
	 * <p>
	 * Used by DieTest to test that certain invariants are upheld in all 
	 * implementations</p>
	 * @param name
	 * @return
	 */
	abstract protected Die createTestDie(String name, SimpleCollection<Face> faces);
	
	/** Die names cannot be blank */
	@Test
	public void blankName()
	{
		assertThat(() -> createTestDie("", null), throwsAn(IllegalArgumentException.class));
	}
	
	/** 
	 * Creating a Die combines Faces with the same set of FaceValues into a 
	 * single Face with the same set of FaceValues and a combined weight of the
	 * two original Faces
	 */
	@Test
	public void creation1()
	{
		fail("test not implemented");
	}
	
	/**
	 * Creating a Die does not combine Faces that have different sets of 
	 * FaceValues into a single Face
	 */
	@Test
	public void creation2()
	{
		fail("test not implemented");
	}
}
