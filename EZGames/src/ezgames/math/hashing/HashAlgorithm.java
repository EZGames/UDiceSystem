package ezgames.math.hashing;

/**
 * The {@code IHashingTechnique} interface is a Strategy designed to be used
 * with the Context, {@code HashUtil}. 
 * <p>It supports a majority (if not all) hashing algorithms for generating
 * hashes for java's {@code hashCode()} method.<p>   
 */
public interface HashAlgorithm
{
   /**
    * Returns the algorithm's starting value.
    * <p> Many hashing algorithms don't have and official starting value. If 
    * this is the case, the algorithm should return 0. 
    * @return the starting value
    */
   int getStartingValue();
   
   /**
    * Runs the hashing routine on the given value and returns the new value.
    * @param val - value to be hashed
    * @param currVal - the current total to be used in the calculation
    * @return the hash of val and currVal
    */
   int hashValue(int val, int currVal);
}
