package ezgames.utils.collections;

import java.util.Iterator;

import ezgames.math.EZRandom;
import ezgames.utils.Weighted;
import ezgames.utils.exceptions.ImpossibleException;

public class WeightedZArray<T> implements RceCollection<T> 
{
	//**************************************************************************
	// Static factory methods
	//**************************************************************************
	public static <T> WeightedZArray<T> createFromWeights(Iterable<T> objs, Iterable<Integer> weights)
	{
		//TODO
		return null;
	}
	
	public static <T> WeightedZArray<T> createFromRanges(Iterable<Weighted<T>> weightedObjs)
	{
		//TODO
		return null;
	}
	
	//**************************************************************************
	// Public API methods
	//**************************************************************************
	@Override
	public Iterator<T> iterator() 
	{
		return new WeightedIterator<T>(elements.iterator());
	}

	@Override
	public boolean contains(T obj) 
	{
		return indexOf(obj) != -1;
	}

	@Override
	public T get(int index) 
	{
		return elements.get(index).object();
	}

	@Override
	public int indexOf(T obj) 
	{
		//TODO: if I ever get Streams from ZArrays, use Stream.map(..)?
		int index = 0;
		
		for(Weighted<T> el : elements)
		{
			if(objectsEqual(el, obj))
			{
				return index;
			}
			++index;
		}
		return -1;
	}

	@Override
	public int size() 
	{
		return elements.size();
	}

	@Override
	public T random() 
	{
		int randIndex = EZRandom.randomNumBetween(1, totalWeight());
		return getFromWeightedIndex(randIndex);
	}
	
	//**************************************************************************
	// Private fields
	//**************************************************************************
	private ZArray<Weighted<T>> elements;
	
	//**************************************************************************
	// Private helper methods
	//**************************************************************************
	private boolean objectsEqual(Weighted<T> wt, T o)
	{
		return wt.object().equals(o);
	}
	
	private int totalWeight()
	{
		int total = 0;
		for(Weighted<T> el : elements)
		{
			total += el.weight();
		}
		return total;
	}
	
	private T getFromWeightedIndex(int weightedIndex)
	{
		check(weightedIndex); //should never throw an exception, since we shouldn't have incorrect values being generated TODO: remove after testing
		
		int elementMaxIndex = 0;
		
		for(Weighted<T> el : elements)
		{
			elementMaxIndex = updateMaxIndex(elementMaxIndex, el.weight());
			if(found(weightedIndex, elementMaxIndex))
			{
				return el.object();
			}
		}
		return null; //satisfy the compiler
	}
	
	private void check(int weightedIndex)
	{		
		if(weightedIndex < 1)
		{
			throw new ImpossibleException("internally-generated value failed invariants", new IndexOutOfBoundsException("weightedIndex of " + weightedIndex + " is below the minimum weighted index value of 1"));
		}
		
		int totalWeight = totalWeight();
		if(weightedIndex > totalWeight)
		{
			throw new ImpossibleException("internally-generated value failed invariants", new IndexOutOfBoundsException("weightedIndex of " + weightedIndex + " exceeds total weight of " + totalWeight));
		}
	}
	
	private int updateMaxIndex(int currentMaxIndex, int elementWeight)
	{
		return currentMaxIndex + elementWeight;
	}
	
	private boolean found(int weightedIndex, int elementMaxIndex)
	{
		return weightedIndex <= elementMaxIndex;
	}
}
