package classes.piece;

import exceptions.ParametreIncorrectException;

public class TA5 extends Transmission{
	public TA5(String nom, String description) throws ParametreIncorrectException{
		super(nom, description);
	}
	
	@Override
	public double getPrix() {
		double d = super.getPrix();
		return d + 600.00;
	}
}