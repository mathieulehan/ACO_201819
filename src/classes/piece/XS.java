package classes.piece;

import exceptions.ParametreIncorrectException;

public class XS extends Exterior{
	public XS(String nom, String description) throws ParametreIncorrectException{
		super(nom, description);
		setCouleur(Couleur.ROUGE.name());
	}
	
	@Override
	public double getPrix() {
		double d = super.getPrix();
		return d + 1000.00;
	}
}
