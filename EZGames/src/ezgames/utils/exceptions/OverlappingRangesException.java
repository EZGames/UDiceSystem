package ezgames.utils.exceptions;

import ezgames.math.range.Range;

// DOC TEST FINISH
public class OverlappingRangesException extends Exception
{
	private static final long serialVersionUID = 2951645266893162012L;

	//***************************************************************************
	// Public constructors
	//***************************************************************************
	public OverlappingRangesException(Range range1, Range range2)
	{
		super();
	}
	
	public OverlappingRangesException(Range range1, Range range2, String message)
	{
		super(message);
	}
	
	public OverlappingRangesException(Range range1, Range range2, Throwable cause)
	{
		super(cause);
	}
	
	public OverlappingRangesException(Range range1, Range range2, String message, Throwable cause)
	{
		super(message, cause);
	}	
}
