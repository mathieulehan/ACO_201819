package exceptions;

public class PasDeCategorieException extends Exception{
	
	String message;
	
	public PasDeCategorieException(String message) {
		super();
		this.message = message;
	}
}
