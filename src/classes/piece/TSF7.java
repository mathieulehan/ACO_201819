package classes.piece;

import exceptions.ParametreIncorrectException;

public class TSF7 extends Transmission{
	public TSF7(String nom, String description) throws ParametreIncorrectException{
		super(nom, description);
	}
	
	@Override
	public double getPrix() {
		double d = super.getPrix();
		return d + 900.00;
	}
}