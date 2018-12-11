package classes.piece;

import exceptions.ParametreIncorrectException;

public class TM5 extends Transmission{
	public TM5(String nom, String description) throws ParametreIncorrectException{
		super(nom, description);
	}
	
	@Override
	public double getPrix() {
		double d = super.getPrix();
		return d + 300.00;
	}
}