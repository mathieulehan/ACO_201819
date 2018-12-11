package classes.piece;

import exceptions.ParametreIncorrectException;

public class IN extends Interior{
	public IN(String nom, String description) throws ParametreIncorrectException{
		super(nom, description);
	}
	
	@Override
	public double getPrix() {
		double d = super.getPrix();
		return d + 400.00;
	}
}