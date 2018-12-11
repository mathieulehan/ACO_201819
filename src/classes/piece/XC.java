package classes.piece;

import exceptions.ParametreIncorrectException;

public class XC extends Exterior{
	public XC(String nom, String description) throws ParametreIncorrectException{
		super(nom, description);
		setCouleur(Couleur.ROUGE.name());
	}
	
	@Override
	public double getPrix() {
		double d = super.getPrix();
		return d + 600.00;
	}
}