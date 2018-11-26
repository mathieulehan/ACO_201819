package exceptions;

public class PieceEstNullException extends Exception{

	String message;

	public PieceEstNullException(String message) {
		super();
		this.message = message;
	}
}
