package ezgames.udicesys.diceModels;

import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Stream;
import ezgames.math.range.Range;
import ezgames.udicesys.diceModels.abstractions.OutputRange;
import ezgames.utils.collections.simple.SimpleCollection;

/**
 * The RangeCollectionBuilder class is used to create collections of 
 * {@link Range}s that don't overlap and cover every value within a given 
 * spectrum.
 *
 */
public class OutputRangeCollectionBuilder implements SimpleCollection<OutputRange>
{

	@Override
	public Iterator<OutputRange> iterator()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean contains(OutputRange obj)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public OutputRange get(int index) throws IndexOutOfBoundsException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Integer> indexOf(OutputRange obj)
	{
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public int size()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Stream<OutputRange> stream()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/*
	 * void addNextLowestUpTo(int max, String output) throws something when max is below min or causes collision or goes above the collection's max 
	 * void fillGapsWith(String output)
	 * void add(OutputRange range) throws OverlappingRangesException
	 * SimpleCollection<OutputRange> build() throws MissingRangeException (actually returns ZArray)
	 */
	
}
