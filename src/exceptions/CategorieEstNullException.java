package exceptions;

public class CategorieEstNullException extends Exception{

	String message;
	
	public CategorieEstNullException(String message) {
		super();
		this.message = message;
	}
}
