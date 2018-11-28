package classes.config;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;


import classes.categorie.Categorie;
import classes.piece.Piece;
import exceptions.ParametreNullException;
import exceptions.ResultatNullException;

public class ConfigVoiture implements ConfigInterface, Observer {

	public Set<Piece> maConfig;
	public static Set<String> mesIncompatibilites;
	public static Set<String> mesNecessites;


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

	/**
	 * Renvoie toutes les categories presentes dans la configuration actuelle
	 * @throws ParametreNullException 
	 */
	@Override
	public Set<String> getCategories() throws ResultatNullException, ParametreNullException {
		Categorie.initialiserCategories();
		Map<String, List<Piece>> catalogue = new HashMap();
		catalogue = Categorie.getCategorieCatalogue();
		Set<String> mesCategories = new HashSet<>();
		Iterator<Piece> itConfig = maConfig.iterator();
		Set s = catalogue.entrySet();
		Iterator itMap = s.iterator();
		while(itMap.hasNext()) {
			Map.Entry<String, List<String>> entry = (Map.Entry)itMap.next();
			while(itConfig.hasNext()) {
				Piece piece = itConfig.next();
				if(!entry.getValue().contains(piece)) {
					mesCategories.add(entry.getKey());
				}
			}
		}
		return mesCategories;
	}

	
//	// anciennement dans Piece, pour garder une trace
//	public String getPieceCategorie() throws PasDeCategorieException, ParametreNullException, ResultatNullException {
//		TypePiece tp = new TypePiece();
//		tp.initialiserPieces();
//		for (String categorie : getCategories()) {
//			if(maConfig.contains(this.nom)) {
//				return categorie;
//			}
//		}
//		throw new PasDeCategorieException("La piece ne possede pas de categorie");
//	}

	/**
	 * Renvoie pour l'instant une pièce par categorie (une pièce max par categorie ou deux possible ?)
	 * et null si la piece n'a pas ete trouvee
	 * pré : String categorie
	 */
	@Override
	public Piece getPieceCategorie(String categorie) throws ParametreNullException, ResultatNullException {
		Categorie.initialiserCategories();
		Piece maPiece = null;
		List<Piece> piecesCategorie = Categorie.getPiecesCategorie(categorie);
		Iterator<Piece> it = piecesCategorie.iterator();
		while(it.hasNext()) {
			if(maConfig.contains(it.next())) {
				maPiece = it.next();
			}
		}
		return maPiece;
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}
