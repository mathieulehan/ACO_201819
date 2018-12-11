package classes.piece;

import exceptions.ParametreIncorrectException;

public class EG100 extends Engine{
	public EG100(String nom, String description) throws ParametreIncorrectException{
		super(nom, description);
	}
	
	@Override
	public double getPrix() {
		double d = super.getPrix();
		return d + 1000.00;
	}
}