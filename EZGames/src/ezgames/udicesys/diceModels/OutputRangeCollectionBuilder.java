package ezgames.udicesys.diceModels;

import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Stream;
import ezgames.math.range.Range;
import ezgames.udicesys.diceModels.abstractions.OutputRange;
import ezgames.utils.collections.simple.SimpleCollection;

// DOC TEST FINISH
/**
 * The RangeCollectionBuilder class is used to create collections of 
 * {@link Range}s that don't overlap and cover every value within a given 
 * spectrum.
 *
 */
public class OutputRangeCollectionBuilder implements SimpleCollection<OutputRange>
{

	public Iterator<OutputRange> iterator()
	{
		return null;
	}

	public boolean contains(OutputRange obj)
	{
		return false;
	}

	public OutputRange get(int index) throws IndexOutOfBoundsException
	{
		return null;
	}

	public Optional<Integer> indexOf(OutputRange obj)
	{
		return Optional.empty();
	}

	public int size()
	{
		return 0;
	}

	public Stream<OutputRange> stream()
	{
		return null;
	}
	
	
	/*
	 * void addNextLowestUpTo(int max, String output) throws something when max is below min or causes collision or goes above the collection's max 
	 * void fillGapsWith(String output)
	 * void add(OutputRange range) throws OverlappingRangesException
	 * SimpleCollection<OutputRange> build() throws MissingRangeException (actually returns ZArray)
	 */
	
}
