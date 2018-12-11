package classes.piece;

import exceptions.ParametreIncorrectException;

public class Interior extends Piece{
	public Interior(String nom, String description) throws ParametreIncorrectException{
		super(nom, description);
	}
	
	public double getPrix() {
		double d = super.getPrix();
		return d;
	}
}
