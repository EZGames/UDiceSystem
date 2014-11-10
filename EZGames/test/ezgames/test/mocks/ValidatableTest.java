package ezgames.test.mocks;

import static org.junit.Assert.*;
import static ezgames.testing.mocking.Validates.*;
import org.junit.Test;
import ezgames.testing.mocking.Validatable;

public class ValidatableTest
{
	
	@Test
	public void shouldFail()
	{
		Validatable obj = new Validatable() { public boolean validate() { return false; } };
		
		assertThat(obj, doesNotValidate());
	}
	
	@Test
	public void shouldPass()
	{
		Validatable obj = new Validatable() { public boolean validate() { return true; } };
		
		assertThat(obj, validates());
	}
}
