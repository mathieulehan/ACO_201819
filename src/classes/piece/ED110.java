package classes.piece;

import exceptions.ParametreIncorrectException;

public class ED110 extends Engine{
	public ED110(String nom, String description) throws ParametreIncorrectException{
		super(nom, description);
	}
	
	@Override
	public double getPrix() {
		double d = super.getPrix();
		return d + 1100.00;
	}
}