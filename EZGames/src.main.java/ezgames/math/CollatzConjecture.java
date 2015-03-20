package ezgames.math;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CollatzConjecture implements Iterator<Integer>
{
	
	public static Stream<Integer> stream(int startingNumber)
	{
		Spliterator<Integer> spliter = Spliterators.spliteratorUnknownSize(new CollatzConjecture(startingNumber), Spliterator.NONNULL);
		Stream<Integer> stream = StreamSupport.stream(spliter, false);
		return stream;
	}
	
	//***************************************************************************
	// Public constructor
	//***************************************************************************
	public CollatzConjecture(int startingNumber)
	{
		if(startingNumber <= 0)
		{
			throw new IllegalArgumentException("Cannot create a Collatz Conjecture Iterator starting at 0 or less.");
		}
		
		current = startingNumber;
	}
	
	//***************************************************************************
	// Public API methods
	//***************************************************************************
	@Override
	public boolean hasNext()
	{
		return current != 0;
	}

	@Override
	public Integer next()
	{
		//prepare current number to be the one returned
		int out = current;
		
		//prepare the next number:
		if(current % 2 == 0)
		{ //if the number is even, divide by 2
			current = current / 2;
		}
		else if(current == 1)
		{ //if it's 1, we're done
			current = 0;
		}
		else
		{ //otherwise, if it's odd, multiply by 3 and add 1
			current = (current * 3) + 1;
		}
		
		//return "next" number
		return out;
	}
	
	//***************************************************************************
	// Private field(s)
	//***************************************************************************
	private int current;
	
}
