package classes.piece;

import exceptions.ParametreIncorrectException;

public class XM extends Exterior{
	public XM(String nom, String description) throws ParametreIncorrectException{
		super(nom, description);
		setCouleur(Couleur.ROUGE.name());
	}
	
	@Override
	public double getPrix() {
		double d = super.getPrix();
		return d + 800.00;
	}
}