package ie.nuigalway.cs.ct326;

/**
 * Customers can only be registered on a loyalty program before the current date. This class represents
 * an exception to be thrown in the case that a future date is used.
 * @author Adrian Clear
 *
 */
public class InvalidRegisterDateException extends Exception {
	
	/**
	 * The constructor for instantiating invalid register date exceptions.
	 * @param message A string representing the message to be associated with this exception.
	 */
	public InvalidRegisterDateException(String message) {
		super(message);
	}

}
