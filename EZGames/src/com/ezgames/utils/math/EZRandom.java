package com.ezgames.utils.math;

import java.util.Calendar;

/**
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
public class EZRandom
{
   private static final int MULTIPLIER = 16777619;
   private static int current = (int)Calendar.getInstance().getTimeInMillis();
   
   /**
    * Generates a random number between the given min and max, inclusively.
    * @param min - the lowest number possible to generate
    * @param max - the highest number possible to generate
    * @return random number between the given min and max, inclusively.
    */
   public static int randomNumBetween(int min, int max)
   {
	   //A new, local seed variable is produced in an attempt to make the use of
	   // current more thread-safe.  The final calculation will call on seed, 
	   // so that, even if current is set to something else between the seed
	   // calculation and the return value calculation, it won't affect the
	   // return value calculation.
	   int seed = current = (current * MULTIPLIER) ^ (min ^ max);
	   return Math.abs(seed % (max - min + 1)) + min;
   }
   
   /**
    * Changes the current seed to the given value
    * @param seed - the new seed value
    */
   public static void setSeed(int seed)
   {
      current = seed;
   }
   
   private EZRandom(){} //cannot create instance of this class
}
