package ezgames.math.random;

public interface SimpleRandom 
{
	/**
     * Generates a random number between the given min and max, inclusively.
     * @param min - the lowest number possible to generate
     * @param max - the highest number possible to generate
     * @return random number between the given min and max, inclusively.
     */
	int randBetween(int min, int max);
	/**
     * Changes the current seed to the given value
     * @param seed - the new seed value
     */
	void setSeed(int seed);
}
