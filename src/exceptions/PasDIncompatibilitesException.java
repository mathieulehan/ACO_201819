package exceptions;

/**
 * 
 * Classe creant une Exception dans le cas ou la piece n'aurait pas l'incompatibilite que l'on souhaite supprimer
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

