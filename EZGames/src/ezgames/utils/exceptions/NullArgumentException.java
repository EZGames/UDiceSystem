package ezgames.utils.exceptions;

public class NullArgumentException extends RuntimeException
{
	private static final long serialVersionUID = 4326326056370996967L;

	public NullArgumentException()
	{
		super();
	}
	
	public NullArgumentException(String s)
	{
		super(s);
	}
	
	public NullArgumentException(Throwable cause)
	{
		super(cause);
	}
	
	public NullArgumentException(String message, Throwable cause)
	{
		super(message, cause);
	}	
}
