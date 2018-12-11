package classes.piece;

import exceptions.ParametreIncorrectException;

public class EG133 extends Engine{
	public EG133(String nom, String description) throws ParametreIncorrectException{
		super(nom, description);
	}
	
	@Override
	public double getPrix() {
		double d = super.getPrix();
		return d + 1200.00;
	}
}