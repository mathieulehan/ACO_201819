package classes.piece;

import java.util.HashSet;
import java.util.Set;

import exceptions.ParametreIncorrectException;

public class Exterior extends Piece{
	private String couleur = Couleur.BLANC.name();

	public String getCouleur() {
		return couleur;
	}
	public void setCouleur(String nouvelleCouleur) {
		this.couleur = nouvelleCouleur;
	}

	@Override
	public double getPrix() {
		double prix = super.getPrix();
		double monPrix;
		switch(couleur) {
		case "BLEU": 
			monPrix = 500;
			break;
		case "ROUGE": 
			monPrix=  400;
			break;
		case "BLANC": 
			monPrix = 0;
			break;
		default: 
			monPrix = 0.0;
			break;
		}
		return prix + monPrix;
	}


	Set<String> couleursPossibles = new HashSet<>();
	public Exterior(String nom, String description) throws ParametreIncorrectException{
		super(nom, description);
		couleursPossibles.add(Couleur.BLEU.name());
		couleursPossibles.add(Couleur.BLANC.name());
		couleursPossibles.add(Couleur.ROUGE.name());
		ajoutPropriete("couleur", () -> getCouleur(), (c) -> setCouleur(c), couleursPossibles);
	}
}
