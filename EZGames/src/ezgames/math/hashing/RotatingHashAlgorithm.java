package ezgames.math.hashing;

/**
 * The Rotating Hash algorithm is considered the most minimal acceptable
 * algorithm.
 */
public class RotatingHashAlgorithm implements HashAlgorithm
{
   @Override
   public int getStartingValue()
   {
      return 0;
   }

   @Override
   public int hashValue(int val, int currVal)
   {
      return val ^ ((currVal << 4) ^ (currVal >> 28));
   }
}
