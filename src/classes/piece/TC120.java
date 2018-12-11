package classes.piece;

import exceptions.ParametreIncorrectException;

public class TC120 extends Transmission{
	public TC120(String nom, String description) throws ParametreIncorrectException{
		super(nom, description);
	}
	
	@Override
	public double getPrix() {
		double d = super.getPrix();
		return d + 800.00;
	}
}