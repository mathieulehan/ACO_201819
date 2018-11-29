package exceptions;

public class ActionPieceInvalideException extends Exception {

	String message;
	
	public ActionPieceInvalideException(String message) {
		super();
		this.message = message;
	}
	
}
