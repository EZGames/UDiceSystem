package ezgames.math.random;

import java.util.Calendar;

/**
 * TODO: redo this documentation! also: testing
 * The {@code EZRandom} class started as a way to test whether a hashing function
 * would make for a decent random number generator.  It turns out that it is.
 * <p>EZRandom's benefits: <br>
 * + provides a comparably even distribution of numbers as java's built-in methods
 * for generating random numbers <br>
 * + runs faster than both of java's built-in random functions (util.Random was close
 * behind, though) <br>
 * + is all static, lacking the need for instantiation (so it Math.rand()) <br>
 * + does not require you to do the calculations to bring the number into
 * your desired range <br>
 * + has a tiny footprint - stores 2 ints and has 2 short functions
 * <p>EZRandom's pitfalls: <br>
 * - due to being static, testing can be more difficult due to the same seed being
 * used for every use of EZRandom throughout the program. This doesn't affect 
 * unit testing very much, but larger scale tests will have a difficult time with
 * repeatability.  Not a very big problem, but a problem none-the-less.<br>
 * - that also means it's not technically thread-safe, assuming you want individualized
 * seeds for different threads. Steps have been taken to reduce this problem a 
 * little, though.<br>
 * - only generates integers.
 */
public class EZRandom implements SimpleRandom
{
	//***************************************************************************
	// Public Static Methods for Quick Use
	//***************************************************************************
   /**
    * A quick, one-shot way to generate a random number with EZRandom, where it 
    * creates the instance for you, produces the result and then releases the 
    * instance.  Best for when you only need a few random numbers
    * @param min - the lowest number possible to generate
    * @param max - the highest number possible to generate
    * @return random number between the given min and max, inclusively.
    */
   public static int quickRandBetween(int min, int max)
   {
	   return new EZRandom().randBetween(min, max);
   }
   
   /**
    * A quick, one-shot way to generate a seeded random number with EZRandom, 
    * where it creates the instance for you, produces the result and then 
    * releases the instance.  Best for when you only need a few random numbers
    * @param min - the lowest number possible to generate
    * @param max - the highest number possible to generate
    * @param seed - the seed used to produce the number
    * @return random number between the given min and max, inclusively.
    */
   public static int quickSeededRandBetween(int min, int max, int seed)
   {
	   return new EZRandom(seed).randBetween(min, max);
   }
	
   //***************************************************************************
   // Public Constructors
   //***************************************************************************
   public EZRandom() 
   { 
	   currSeed = (int)Calendar.getInstance().getTimeInMillis(); 
   }
   
   public EZRandom(int startingSeed) 
   { 
	   currSeed = startingSeed; 
   }
   
   //***************************************************************************
   // Public API methods
   //***************************************************************************
   /**
    * Generates a pseudo-random number between min and max, inclusively.
    */
   public int randBetween(int min, int max)
   {
	   setSeed((currSeed * MULTIPLIER) ^ (min ^ max));
	   return Math.abs(currSeed % (max - min + 1)) + min;
   }
   
   /**
    * sets a seed so you can dictate 
    */
   public void setSeed(int seed) throws IllegalStateException
   {
   	currSeed = seed;
   }
   
   //***************************************************************************
   // Private Member Fields
   //***************************************************************************
   private static final int MULTIPLIER = 16777619; //A good, randomized set of 1s and 0s
   //alternate multiplier possibility = -1802644563  //1001 0100 1000 1101 1101 0011 1010 1101
   private int currSeed;
}
