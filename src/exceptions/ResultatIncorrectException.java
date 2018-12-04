package exceptions;

/**
 * Exception generee quant un resultat de methode est detecte comme null alors que la postcondition ne le permet pas
 * @author mathi
 *
 */
public class ResultatIncorrectException extends Exception{

	public ResultatIncorrectException(String message) {
		super(message);
	}
}
