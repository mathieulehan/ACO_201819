package classes.piece;

import exceptions.ParametreIncorrectException;

public class TM6 extends Transmission{
	public TM6(String nom, String description) throws ParametreIncorrectException{
		super(nom, description);
	}
	
	@Override
	public double getPrix() {
		double d = super.getPrix();
		return d + 350.00;
	}
}