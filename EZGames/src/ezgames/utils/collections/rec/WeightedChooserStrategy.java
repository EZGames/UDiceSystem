package ezgames.utils.collections.rec;

import java.util.Iterator;
import ezgames.math.random.SimpleRandom;
import ezgames.utils.Weighted;
import ezgames.utils.collections.simple.SimpleCollection;

// DOC TEST
public class WeightedChooserStrategy<E> implements ChooserStrategy<Weighted<E>>
{	
	@Override
	public Weighted<E> choose(SimpleCollection<Weighted<E>> coll, SimpleRandom rand)
	{
		int randIndex = rand.randBetween(1, getTotalWeightOf(coll.iterator(), 0));
		
		return getFromWeightedIndex(coll.iterator(), randIndex, 0);
	}
	
	private int getTotalWeightOf(Iterator<Weighted<E>> iter, int runningTotal)
	{
		if(iter.hasNext())
		{
			Weighted<E> element = iter.next();
			return getTotalWeightOf(iter, runningTotal + element.weight());
		}
		else
		{
			return runningTotal;
		}
	}
	
	private Weighted<E> getFromWeightedIndex(Iterator<Weighted<E>> iter, int weightedIndex, int elementMaxIndex)
	{
		Weighted<E> element = iter.next();
		if(isFound(weightedIndex, elementMaxIndex, element.weight()))
		{
			return element;
		}
		else
		{
			return getFromWeightedIndex(iter, weightedIndex, updateMaxIndex(elementMaxIndex, element.weight()));
		}
	}
	
	private int updateMaxIndex(int currentMaxIndex, int elementWeight)
	{
		return currentMaxIndex + elementWeight;
	}
	
	private boolean isFound(int weightedIndex, int elementMaxIndex, int elementWeight)
	{
		return weightedIndex <= (elementMaxIndex + elementWeight);
	}
	
}
