package ezgames.utils.exceptions;

import ezgames.math.range.Range;

public class MissingRangeException extends Exception
{
	//***************************************************************************
	// Public constructors
	//***************************************************************************
	public MissingRangeException(Range gap)
	{
		super("Range from " + gap.getMinimum() + " to " + gap.getMaximum() + "missing from group of ranges");
		this.gap = gap;
	}
	
	public MissingRangeException(Range gap, String additionalMessage)
	{
		super("Range from " + gap.getMinimum() + " to " + gap.getMaximum() + "missing from group of ranges.  " + additionalMessage);
		this.gap = gap;
	}
	
	public MissingRangeException(Range gap, Throwable cause)
	{
		super("Range from " + gap.getMinimum() + " to " + gap.getMaximum() + "missing from group of ranges", cause);
		this.gap = gap;
	}
	
	public MissingRangeException(Range gap, String additionalMessage, Throwable cause)
	{
		super("Range from " + gap.getMinimum() + " to " + gap.getMaximum() + "missing from group of ranges.  " + additionalMessage, cause);
		this.gap = gap;
	}
	
	//***************************************************************************
	// Public API Methods
	//***************************************************************************
	public Range getRange()
	{
		return gap;
	}
	
	public int getMinimum()
	{
		return gap.getMinimum();
	}
	
	public int getMaximum()
	{
		return gap.getMaximum();
	}

	//***************************************************************************
	//Private fields
	//***************************************************************************
	private static final long serialVersionUID = 1L;
	private Range gap;
	
}
