package classes.piece;

import exceptions.ParametreIncorrectException;

public class EG210 extends Engine{
	public EG210(String nom, String description) throws ParametreIncorrectException{
		super(nom, description);
	}
	
	@Override
	public double getPrix() {
		double d = super.getPrix();
		return d + 1400.00;
	}
}