package ezgames.math.hashing;

/**
 * This {@code IHashingTechnique} is using the FNV hashing technique.
 * See the <a href="http://www.isthe.com/chongo/tech/comp/fnv/">FNV website</a> 
 * for more details.
 *
 */
public final class FnvHashAlgorithm implements HashAlgorithm
{
   @Override
   public int getStartingValue()
   {
      return START_VALUE;
   }

   @Override
   public int hashValue(int val, int currTotal)
   {
      return (currTotal * MULTIPLIER) ^ val;
   }
   
   private static final int START_VALUE = -2128831135;
   private static final int MULTIPLIER = 16777619;
}
