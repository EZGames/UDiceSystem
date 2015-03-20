package ezgames.utils.exceptions;

import ezgames.math.range.Range;

// DOC TEST
public class OverlappingRangesException extends Exception
{
	private static final long serialVersionUID = 2951645266893162012L;

	//***************************************************************************
	// Public constructors
	//***************************************************************************
	public OverlappingRangesException(Range range1, Range range2)
	{
		super(baseMessage(range1, range2));
	}
	
	public OverlappingRangesException(Range range1, Range range2, String message)
	{
		super(baseMessage(range1, range2) + ":\n\t" + message);
	}
	
	public OverlappingRangesException(Range range1, Range range2, Throwable cause)
	{
		super(baseMessage(range1, range2), cause);
	}
	
	public OverlappingRangesException(Range range1, Range range2, String message, Throwable cause)
	{
		super(baseMessage(range1, range2) + ":\n\t" + message, cause);
	}
	
	private static String baseMessage(Range range1, Range range2)
	{
		return String.format(
				"%s overlaps %s in a case that cannot have overlapping ranges", 
				range1.toString(), 
				range2.toString());
	}
}
