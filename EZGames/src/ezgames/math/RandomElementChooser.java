package ezgames.math;

import java.util.Iterator;
import ezgames.utils.DataChecker;
import ezgames.utils.IterableUtil;
import ezgames.utils.Weighted;

public abstract class RandomElementChooser<E>
{
	//**************************************************************************
	// Static factory methods
	//**************************************************************************
	public static <T> RandomElementChooser<T> fromCollection(Iterable<T> collection)
	{
		return fromCollection(collection, new EZRandom());
	}
	
	public static <T> RandomElementChooser<T> fromCollection(Iterable<T> collection, SimpleRandom randomizer)
	{
		checkInput(collection, randomizer);
		
		return new SimpleRandomElementChooser<>(collection, randomizer);
	}
	
	public static <T> RandomElementChooser<T> fromWeightedCollection(Iterable<Weighted<T>> weightedCollection)
	{
		return fromWeightedCollection(weightedCollection, new EZRandom());
	}
	
	public static <T> RandomElementChooser<T> fromWeightedCollection(Iterable<Weighted<T>> weightedCollection, SimpleRandom randomizer)
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
	private static void checkInput(Iterable<?> coll, SimpleRandom rand)
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
			int randIndex = randomizer.randBetween(0, IterableUtil.sizeOf(collection) - 1);
			
			return IterableUtil.elementAt(collection, randIndex);
		}
		
		public SimpleRandomElementChooser(Iterable<E> collection, SimpleRandom randomizer)
		{
			
			this.collection = collection;
			this.randomizer = randomizer;
		}
		
		private final Iterable<E> collection;
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
		
		public WeightedRandomElementChooser(Iterable<Weighted<E>> collection, SimpleRandom randomizer)
		{
			this.collection = collection;
			this.randomizer = randomizer;
		}
		
		private Iterable<Weighted<E>> collection;
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
