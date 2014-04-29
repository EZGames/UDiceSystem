package ezgames.utils.collections;

import java.util.Iterator;
import ezgames.random.EZRandom;
import ezgames.random.SimpleRandom;
import ezgames.utils.DataChecker;
import ezgames.utils.Weighted;
import ezgames.utils.exceptions.NullArgumentException;

//TODO: document and test
public abstract class RandomElementChooser<E>
{
	//**************************************************************************
	// Static factory methods
	//**************************************************************************
	public static <T> RandomElementChooser<T> fromCollection(SimpleCollection<T> collection) throws NullArgumentException
	{
		return fromCollection(collection, new EZRandom());
	}
	
	public static <T> RandomElementChooser<T> fromCollection(SimpleCollection<T> collection, SimpleRandom randomizer) throws NullArgumentException
	{
		checkInput(collection, randomizer);
		
		return new SimpleRandomElementChooser<>(collection, randomizer);
	}
	
	public static <T> RandomElementChooser<T> fromWeightedCollection(SimpleCollection<Weighted<T>> weightedCollection) throws NullArgumentException
	{
		return fromWeightedCollection(weightedCollection, new EZRandom());
	}
	
	public static <T> RandomElementChooser<T> fromWeightedCollection(SimpleCollection<Weighted<T>> weightedCollection, SimpleRandom randomizer) throws NullArgumentException
	{
		checkInput(weightedCollection, randomizer);
		DataChecker.checkIterableDataNotNull(weightedCollection, "Cannot create new RandomeElementChooser from a weighted collection containing null elements");
				
		return new WeightedRandomElementChooser<>(weightedCollection, randomizer);
	}
	
	//**************************************************************************
	// Public API methods
	//**************************************************************************
	public abstract E choose();
	
	//**************************************************************************
	// Private static helper methods
	//**************************************************************************
	private static void checkInput(SimpleCollection<?> coll, SimpleRandom rand) throws NullArgumentException
	{
		DataChecker.checkIterableNotEmptyOrNull(coll, "Cannot create new RandomElementChooser from an empty or null collection");
		DataChecker.checkDataNotNull(rand, "Cannot create new RandomElementChooser with a null random number generator");
	}
	
	//**************************************************************************
	// Private Strategy: simple random chooser
	//**************************************************************************
	private static final class SimpleRandomElementChooser<E> extends RandomElementChooser<E>
	{		
		public E choose()
		{
			int randIndex = randomizer.randBetween(0, collection.size() - 1);
			
			return collection.get(randIndex);
		}
		
		public SimpleRandomElementChooser(SimpleCollection<E> collection, SimpleRandom randomizer)
		{
			
			this.collection = collection;
			this.randomizer = randomizer;
		}
		
		private final SimpleCollection<E> collection;
		private final SimpleRandom randomizer;
	}
	
	//**************************************************************************
	// Private Strategy: weighted random chooser
	//**************************************************************************
	private static final class WeightedRandomElementChooser<E> extends RandomElementChooser<E>
	{
		public E choose() 
		{
			int randIndex = randomizer.randBetween(1, totalWeight(collection.iterator(), 0));
			
			return getFromWeightedIndex(collection.iterator(), randIndex, 0);
		}
		
		public WeightedRandomElementChooser(SimpleCollection<Weighted<E>> collection, SimpleRandom randomizer)
		{
			this.collection = collection;
			this.randomizer = randomizer;
		}
		
		private SimpleCollection<Weighted<E>> collection;
		private SimpleRandom randomizer;
		
		private int totalWeight(Iterator<Weighted<E>> iter, int runningTotal)
		{
			if(iter.hasNext())
			{
				Weighted<E> element = iter.next();
				return totalWeight(iter, runningTotal + element.weight());
			}
			else
			{
				return runningTotal;
			}
		}
		
		private E getFromWeightedIndex(Iterator<Weighted<E>> iter, int weightedIndex, int elementMaxIndex)
		{
			Weighted<E> element = iter.next();
			if(found(weightedIndex, elementMaxIndex, element.weight()))
			{
				return element.object();
			}
			else
			{
				return getFromWeightedIndex(iter, weightedIndex, updatedMaxIndex(elementMaxIndex, element.weight()));
			}
		}
		
		private int updatedMaxIndex(int currentMaxIndex, int elementWeight)
		{
			return currentMaxIndex + elementWeight;
		}
		
		private boolean found(int weightedIndex, int elementMaxIndex, int elementWeight)
		{
			return weightedIndex <= (elementMaxIndex + elementWeight);
		}
	}
}
