package classes.piece;

import exceptions.ParametreIncorrectException;

public class ED180 extends Engine{
	public ED180(String nom, String description) throws ParametreIncorrectException{
		super(nom, description);
	}
	
	@Override
	public double getPrix() {
		double d = super.getPrix();
		return d + 1400.00;
	}
}