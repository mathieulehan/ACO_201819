package classes.piece;

import exceptions.ParametreIncorrectException;

public class IH extends Interior{
	public IH(String nom, String description) throws ParametreIncorrectException{
		super(nom, description);
	}
	
	@Override
	public double getPrix() {
		double d = super.getPrix();
		return d + 700.00;
	}
}