package exceptions;

/**
 * Exception generee quant un parametre est detecte comme null alors que la precondition ne le permet pas
 * @author GR4
 *
 */
public class ParametreIncorrectException extends Exception{

	public ParametreIncorrectException(String message) {
		super(message);
	}
}
