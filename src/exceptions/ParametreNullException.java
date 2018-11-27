package exceptions;

/**
 * Exception generee quant un parametre est detecte comme null alors que la precondition ne le permet pas
 * @author mathi
 *
 */
public class ParametreNullException extends Exception{

	String message;
	
	public ParametreNullException(String message) {
		super();
		this.message = message;
	}
}
