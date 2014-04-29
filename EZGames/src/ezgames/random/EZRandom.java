package ezgames.random;

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
	
   public EZRandom() 
   { 
	   current = (int)Calendar.getInstance().getTimeInMillis(); 
   }
   
   public EZRandom(int startingSeed) 
   { 
	   current = startingSeed; 
   }
   
   public int randBetween(int min, int max)
   {
	   //A new, local seed variable is produced in an attempt to make the use of
	   // current more thread-safe.  The final calculation will call on seed, 
	   // so that, even if current is set to something else between the seed
	   // calculation and the return value calculation, it won't affect the
	   // return value calculation.
	   int seed = current = (current * MULTIPLIER) ^ (min ^ max);
	   return Math.abs(seed % (max - min + 1)) + min;
   }
   
   public void setSeed(int seed)
   {
      current = seed;
   }
   
   private static final int MULTIPLIER = 16777619;
   private int current;   
}
