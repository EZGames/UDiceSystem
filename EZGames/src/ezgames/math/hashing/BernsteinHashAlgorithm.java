package ezgames.math.hashing;

/**
 * The Bernstein Hash Algorithm is best for small character keys, but
 * not very good for much else.
 */
public class BernsteinHashAlgorithm implements IHashAlgorithm
{
   @Override
   public int getStartingValue()
   {
      return 0;
   }

   @Override
   public int hashValue(int val, int currVal)
   {
      return 33 * currVal + val;
   }
}
