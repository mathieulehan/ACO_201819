package classes.piece;

import exceptions.ParametreIncorrectException;

public class TS6 extends Transmission{
	public TS6(String nom, String description) throws ParametreIncorrectException{
		super(nom, description);
	}
	
	@Override
	public double getPrix() {
		double d = super.getPrix();
		return d + 650.00;
	}
}