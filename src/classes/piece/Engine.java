package classes.piece;

import exceptions.ParametreIncorrectException;

public class Engine extends Piece{
	public Engine(String nom, String description) throws ParametreIncorrectException{
		super(nom, description);
	}
	
	public double getPrix() {
		double prix = super.getPrix();
		return prix;
	}
}
