package classes.config;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import java.util.Observable;
import java.util.Observer;
import java.util.Set;


import classes.categorie.Categorie;
import classes.piece.Piece;
import classes.piece.TypePiece;
import exceptions.ActionPieceInvalideException;
import exceptions.ParametreNullException;
import exceptions.ResultatNullException;

public class ConfigVoiture implements ConfigInterface, Observer {

	public Set<Piece> maConfig = new HashSet<Piece>();
	private Set<String> mesCategories = new HashSet<String>();
	private Set<Piece> mesIncompatibilites = new HashSet<Piece>();
	private Set<Piece> mesNecessites = new HashSet<Piece>();

	/**
	 * Si toutes les categories sont presentes dans la configuration, alors cette derniere est complete
	 */
	@Override
	public boolean estComplet() throws ResultatNullException, ParametreNullException {
		if(this.getMesCategories().size() == Categorie.getCategories().size()) {
			return true;
		}
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

	public Set<String> getMesCategories() {
		return mesCategories;
	}

	@Override
	public boolean supprimerPiece(String p) throws ActionPieceInvalideException, ResultatNullException, ParametreNullException {
		Piece piece = TypePiece.chercherPieceParNom(p);
		if(!maConfig.contains(piece) ) throw new ActionPieceInvalideException("Cette piece n'est pas dans votre configuration");
		
		this.mesIncompatibilites.removeAll(piece.getIncompatibilites()) ; // TODO NE MARCHE PAS
		this.getMesCategories().remove(rechercherCategorieParPiece(piece)) ;
		this.maConfig.remove(piece);
		return true;
	}

	@Override
	public boolean ajouterPiece(String p) throws ActionPieceInvalideException, ParametreNullException, ResultatNullException {
		Piece piece = TypePiece.chercherPieceParNom(p);
		if (mesIncompatibilites.contains(piece)) throw new ActionPieceInvalideException("Piece incompatible");
		if (!getPiecesPossibles().contains(piece)) throw new ActionPieceInvalideException("Cette piece n'est pas dans la liste des pieces possibles");

		this.mesIncompatibilites.addAll(piece.getIncompatibilites()) ; // TODO NE MARCHE PAS
		this.getMesCategories().add(rechercherCategorieParPiece(piece)) ;
		this.maConfig.add(piece);
		return true;


	}

	/**
	 * Renvoie toutes les categories presentes dans la configuration actuelle
	 * @throws ParametreNullException 
	 */
	//	@Override
	//	public Set<String> getConfigCategories() throws ResultatNullException, ParametreNullException {
	//		Map<String, List<Piece>> catalogue = Categorie.getCategorieCatalogue();
	//		Set<String> mesCategories = new HashSet<String>();
	//		Iterator<Entry<String, List<Piece>>> itCatalogue = catalogue.entrySet().iterator();
	//		while(itCatalogue.hasNext()) {
	//			Map.Entry<String, List<String>> entry = (Map.Entry) itCatalogue.next();
	//			String nomCategorie = entry.getKey();
	//			List<String> listPiece = entry.getValue();
	//			Iterator<Piece> itConfig = maConfig.iterator();
	//			while(itConfig.hasNext()) {
	//				Piece piece = itConfig.next();
	//				if(listPiece.contains(piece)) {
	//					mesCategories.add(nomCategorie);
	//				}
	//			}
	//		}
	//		return mesCategories;
	//	}

	/**
	 * Recherche de la categorie assiciee a une piece
	 * @param piece
	 * @return la categorie d'une piece si elle existe
	 * @throws ResultatNullException
	 */
	public String rechercherCategorieParPiece(Piece piece) throws ResultatNullException {
		Map<String, List<Piece>> catalogue = Categorie.getCategorieCatalogue();
		Iterator<Entry<String, List<Piece>>> itCatalogue = catalogue.entrySet().iterator();
		while(itCatalogue.hasNext()) {
			Map.Entry<String, List<String>> entry = (Map.Entry) itCatalogue.next();
			if(entry.getValue().contains(piece)) {
				return entry.getKey();
			}
		}
		throw new ResultatNullException("Pas de categorie pour cette piece");
	}

	/**
	 * Renvoie un set de categories non presentes dans la config
	 * @return Set<String>
	 * @throws ResultatNullException
	 * @throws ParametreNullException
	 */
	@Override
	public Set<String> getCategoriesRestantes() throws ResultatNullException, ParametreNullException{
		Set<String> categories = Categorie.getCategories();
		categories.removeAll(this.getMesCategories());
		return categories;
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
	 * et null si la piece n'a pas ete trouvee dans la configuration
	 * pré : String categorie
	 */
	@Override
	public Piece getPieceCategorie(String categorie) throws ParametreNullException, ResultatNullException {
		List<Piece> piecesCategorie = Categorie.getPiecesCategorie(categorie);
		Iterator<Piece> it = piecesCategorie.iterator();
		while(it.hasNext()) {
			Piece piece = it.next();
			if(maConfig.contains(piece)) {				
				return piece;
			}
		}
		throw new ResultatNullException("Aucune piece n'a été selectionné dans cette categorie");
	}

	/**
	 * Renvoie un set de toutes les pieces que l'on peut encore ajouter a la config
	 * @return
	 * @throws ParametreNullException
	 * @throws ResultatNullException
	 */
	@Override
	public Set<Piece> getPiecesPossibles() throws ParametreNullException, ResultatNullException{
		Set<Piece> piecesPossibles = new HashSet<Piece>();
		Set<String> categoriesRestantes = getCategoriesRestantes();
		Iterator<String> it = categoriesRestantes.iterator();
		while(it.hasNext()) {
			List<Piece> pieces = Categorie.getPiecesCategorie(it.next().toString());
			pieces.removeAll(mesIncompatibilites);
			piecesPossibles.addAll(pieces);
		}
		return piecesPossibles;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) throws Exception {
		ConfigVoiture cv = new ConfigVoiture();
		Categorie.initialiserCategories();
		cv.ajouterPiece("EG100");
		cv.ajouterPiece("XC");
		cv.ajouterPiece("IN");
		
		//System.out.println(TypePiece.chercherPieceParNom("EG100").getIncompatibilites());
		//System.out.println(cv.mesIncompatibilites);

	}
}
