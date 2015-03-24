package ezgames.test.mocks;

public class ValidationDescription
{
	public ValidationDescription(String descriptionOfInvalid, boolean isValid)
	{
		this.descriptionOfInvalid = descriptionOfInvalid;
		this.isValid = isValid;
	}
	
	String describeInvalidation() { return descriptionOfInvalid; }
	boolean isValid() { return isValid; }
	
	private final String descriptionOfInvalid;
	private final boolean isValid;
}