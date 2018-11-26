package exceptions;

/**
 * 
 * Classe creant une Exception dans le cas ou la piece n'aurait pas la necessite que l'on souhaite supprimer
 * @author mathi
 *
 */
public class PasDeNecessitesException extends Exception{

	String message;

	public PasDeNecessitesException(String message) {
		super();
		this.message = message;
	}	
}
