package exceptions;

/**
 * Exception generee quant un resultat de methode est detecte comme null alors que la postcondition ne le permet pas
 * @author mathi
 *
 */
public class ResultatNullException extends Exception{

	public ResultatNullException(String message) {
		super(message);
	}
}
