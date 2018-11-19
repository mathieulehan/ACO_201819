package classes.config;

import java.util.Set;

import classes.categorie.Categorie;
import classes.piece.Piece;

public class ConfigVoiture implements ConfigInterface {

	public String[] maConfig;
	public static Set<Piece> mesIncompatibilites;
	public static Set<Piece> mesNecessites;

	@Override
	public boolean estComplet() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean estValide() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean estObservable() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void supprimerPiece(Piece piece) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void ajouterPiece(Piece piece) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void getPieces(Categorie categorie) {
		// TODO Auto-generated method stub
		
	}
}
