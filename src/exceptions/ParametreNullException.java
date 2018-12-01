package exceptions;

/**
 * Exception generee quant un parametre est detecte comme null alors que la precondition ne le permet pas
 * @author mathi
 *
 */
public class ParametreNullException extends Exception{

	public ParametreNullException(String message) {
		super(message);
	}
}
