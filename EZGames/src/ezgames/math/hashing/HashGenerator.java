package ezgames.math.hashing;

/**
 * {@code HashUtil} is a class to help with creating hash codes. <p>
 * {@code HashUtil} is capable of using many different hashing techniques by
 * accepting any technique that can be implemented as an {@code IHashAlgorithm}.
 * This utility's main job is to convert any given type to an integer before
 * passing it to its {@code IHashAlgorithm}, which only accepts integers for
 * hashing.</p> <p>Example usage:</p> public int hashCode() {<br> &emsp;
 * HashUtil hasher = HashUtil.createDefaultHashUtil(); <br> &emsp; int code =
 * hasher.getStartingValue();<br> &emsp; code = hasher.hash(member1, out);<br>
 * &emsp; code = hasher.hash(member2, out);<br> &emsp; return code;<br> }
 */
public class HashGenerator
{
	// **************************************************************************
	// Built-in HashAlgorithm Getters
	// **************************************************************************
	/**
	 * Returns an instance of the {@code FnvHashAlgorithm} class
	 * @return an instance of the {@code FnvHashAlgorithm} class
	 */
	public static FnvHashAlgorithm getFnvHashAlgorithm()
	{
		return new FnvHashAlgorithm();
	}
	
	/**
	 * Returns an instance of the {@code RotatingHashAlgorithm} class
	 * @return an instance of the {@code RotatingHashAlgorithm} class
	 */
	public static RotatingHashAlgorithm getRotatingHashAlgorithm()
	{
		return new RotatingHashAlgorithm();
	}
	
	/**
	 * Returns an instance of the {@code BernsteinHashAlgorithm} class
	 * @return an instance of the {@code BernsteinHashAlgorithm} class
	 */
	public static BernsteinHashAlgorithm getBernsteinHashAlgorithm()
	{
		return new BernsteinHashAlgorithm();
	}
	
	// **************************************************************************
	// Public Static Factories
	// **************************************************************************
	/**
	 * Constructs a new {@code HashUtil} with a default {@code IHashAlgorithm} of
	 * {@code FnvHashAlgorithm}.
	 */
	public static HashGenerator createWithDefaultHashAlgorithm()
	{
		return new HashGenerator(new FnvHashAlgorithm());
	}
	
	/**
	 * Constructs a new {@code HashUtil} object with the given
	 * {@code IHashAlgorithm}. <p>In most cases, the user of this method only
	 * needs to create the {@code IHashAlgorithm} object in the constructor.</p>
	 * <p>i.e. {@code new HashUtil(new SpecificHashAlgorithm())}</p> <p>As an
	 * alternative, you can use some of {@code HashUtil}'s built-in
	 * {@code IHashAlgorithm}s.</p> <p>i.e.
	 * {@code new HashUtil(HashUtil.getFnvHashAlgorithm())}</p> <p>See
	 * {@code HashUtil}'s static methods for all its built-in hashing techniques.
	 * @param strategy - the {@code IHashAlgorithm} to apply
	 */
	public static HashGenerator createHashGeneratorWith(HashAlgorithm strategy)
	{
		return new HashGenerator(strategy);
	}
	
	// **************************************************************************
	// Public API Methods
	// **************************************************************************
	/**
	 * Returns the starting total for the hashing technique given in the
	 * constructor.
	 * @return the starting total for the given hashing technique.
	 */
	public int getStartingValue()
	{
		return strategy.getStartingValue();
	}
	
	/**
	 * Creates a hash value from a boolean value. <p>The boolean is transformed
	 * into an int value of alternating 0s and 1s using
	 * {@code (bool ? 1431655765 : -1431655766)}. Then it is hashed as a int.</p>
	 * @param bool - the boolean to be hashed
	 * @param currTotal - the current total used in the calculation
	 * @return the hash code of the boolean and the current total
	 */
	public int hash(boolean bool, int currTotal)
	{
		int l = (bool ? 1431655765 : -1431655766);
		return strategy.hashValue(l, currTotal);
	}
	
	/**
	 * Creates a hash value from a character. <p>The character is transformed
	 * with a simple cast to a int. Then it is hashed as a int.</p>
	 * @param aChar - the character to be hashed
	 * @param currTotal - the current total used in the calculation
	 * @return the hash code of the character and the current total
	 */
	public int hash(char aChar, int currTotal)
	{
		int l = (int) aChar;
		return strategy.hashValue(l, currTotal);
	}
	
	/**
	 * Creates a hash value from a long. <p>The long is transformed with a simple
	 * cast to a int. Then it is hashed as a int.</p>
	 * @param num - the integer to be hashed
	 * @param currTotal - the current total used in the calculation
	 * @return the hash code of the long and the current total
	 */
	public int hash(long num, int currTotal)
	{
		int l = ((int) (num >> 32)) ^ (int) num; // does an XOR of the top 32 bits
																// with the bottom 32 bits. Thus
																// we don't actually lose any
																// information.
		return strategy.hashValue(l, currTotal);
	}
	
	/**
	 * Creates a hash value from an integer.
	 * @param num - the int to be hashed
	 * @param currTotal - the current total used in the calculation
	 * @return the hash code of the int and the current total
	 */
	public int hash(int num, int currTotal)
	{
		return strategy.hashValue(num, currTotal);
	}
	
	/**
	 * Creates a hash value from a float. <p>The float is transformed with
	 * {@code Float.floatToIntBits()}, which is then cast to a int. Then it is
	 * hashed as a int.</p>
	 * @param num - the float to be hashed
	 * @param currTotal - the current total used in the calculation
	 * @return the hash code of the float and the current total
	 */
	public int hash(float num, int currTotal)
	{
		int l = Float.floatToIntBits(num);
		return strategy.hashValue(l, currTotal);
	}
	
	/**
	 * Creates a hash value from a double. <p>The double is transformed with
	 * {@code Double.doubleTointBits()}, which is then cast to a int. Then it is
	 * hashed as a int.</p>
	 * @param num - the double to be hashed
	 * @param currTotal - the current total used in the calculation
	 * @return the hash code of the double and the current total
	 */
	public int hash(double num, int currTotal)
	{
		long l = Double.doubleToLongBits(num);
		return hash(l, currTotal);
	}
	
	/**
	 * Creates a hash value from an Object. <p>The Object is transformed by
	 * calling its {@code hashCode()} method, then casting it to a int. Then it
	 * is hashed as a int.</p>
	 * @param obj - the Object to be hashed
	 * @param currTotal - the current total used in the calculation
	 * @return the hash code of the Object and the current total
	 */
	public int hash(Object obj, int currTotal)
	{
		int l = obj.hashCode();
		return strategy.hashValue(l, currTotal);
	}
	
	/**
	 * Creates a hash value from an {@code Iterable} collection. <p>Iterates
	 * through the collection, getting the object's hashCode() value and runs it
	 * through the hashing technique.</p>
	 * @param iter - the {@code Iterable} to iterate through to hash
	 * @param currTotal - the current total used in the collection
	 * @return the hash code of the of {@code Iterable} and the current total
	 */
	public <T> int hash(Iterable<T> iter, int currTotal)
	{
		for (T obj : iter)
		{
			currTotal = hash(obj, currTotal);
		}
		return currTotal;
	}
	
	// **************************************************************************
	// Private Members
	// **************************************************************************
	private HashAlgorithm strategy;
	
	// **************************************************************************
	// Private constructors
	// **************************************************************************
	private HashGenerator(HashAlgorithm strategy)
	{
		this.strategy = strategy;
	}
}
