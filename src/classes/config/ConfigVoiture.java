package classes.config;

import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import classes.categorie.Categorie;
import classes.piece.Piece;

public class ConfigVoiture implements ConfigInterface, Observer {

	public Set<Piece> maConfig;
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

	// Bien penser a mettre setChanged() et notifyObservers() a chaque modification pour notifier a configVoiture qu'il y a un changement
	@Override
	public boolean estObservable() {
		// TODO Auto-generated method stub
		return false;
	}

	// Bien penser a mettre setChanged() et notifyObservers() a chaque modification pour notifier a configVoiture qu'il y a un changement
	@Override
	public void supprimerPiece(Piece piece) {
		this.maConfig.remove(piece);
	}

	@Override
	public void ajouterPiece(Piece piece) {
		this.maConfig.add(piece);
	}

	@Override
	public Categorie[] getCategories() {
		return
	}


	@Override
	public Piece[] getPiecesCategorie(Categorie categorie) {
		Map<String, List<String>> mesPiece = Categorie.getCategorieCatalogue();
		return mesPieces;
	}


	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
