package classes.piece;

import exceptions.ParametreIncorrectException;

public class Transmission extends Piece{
	public Transmission(String nom, String description) throws ParametreIncorrectException{
		super(nom, description);
	}
	
	public double getPrix() {
		double d = super.getPrix();
		return d;
	}
}
