package classes.piece;

import exceptions.ParametreIncorrectException;

public class IS extends Interior{
	public IS(String nom, String description) throws ParametreIncorrectException{
		super(nom, description);
	}
	
	@Override
	public double getPrix() {
		double d = super.getPrix();
		return d + 800.00;
	}
}