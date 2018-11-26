package exceptions;

public class DescriptionEstNullException extends Exception {

	String message;

	public DescriptionEstNullException(String message) {
		super();
		this.message = message;
	}
}
