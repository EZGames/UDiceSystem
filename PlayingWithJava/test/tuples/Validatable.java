package tuples;

/**
 * <code>Validatable</code> is an interface for home-brewed mocks to be run
 * through the {@link ezgames.test.matchers.Validates} Matcher.
 */
public interface Validatable
{
	/**
	 * @return whether the object was used correctly
	 */
	boolean validate();
}
