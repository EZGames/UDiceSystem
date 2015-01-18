package ezgames.utils;

/**
 * The {@code Weighted} class is used to add a weight attribute to any object.
 * It's main purpose was for the UDice System to make weighted dice where 
 * faces would have a higher or lower chance of being rolled, but it can 
 * technically be used to add an integer weight field to any object.
 */
public interface Weighted<T>
{
	/**
	 * Returns the weight
	 */
	public int weight();
}
