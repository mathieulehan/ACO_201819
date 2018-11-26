package exceptions;

/**
 * 
 * Classe creant une Exception dans le cas ou l'incompatibilite est null
 * @author mathi
 *
 */
public class IncompatibiliteEstNullException extends Exception{

	String message;

	public IncompatibiliteEstNullException(String message) {
		super();
		this.message = message;
	}
}
