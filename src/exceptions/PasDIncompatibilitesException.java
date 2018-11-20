package exceptions;

/**
 * 
 * Classe creant une Exception lors de la tentative de suppression d'une incompatibilite ou d'une necessite
 * 
 * @author mathi
 *
 */
public class PasDIncompatibilitesException extends Exception{

	String message;
	
	public PasDIncompatibilitesException(String message) {
		super();
		this.message = message;
	}
}

