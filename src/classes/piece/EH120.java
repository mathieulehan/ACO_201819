package classes.piece;

import exceptions.ParametreIncorrectException;

public class EH120 extends Engine{
	public EH120(String nom, String description) throws ParametreIncorrectException{
		super(nom, description);
	}
	
	@Override
	public double getPrix() {
		double d = super.getPrix();
		return d + 1800.00;
	}
}