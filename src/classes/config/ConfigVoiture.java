package classes.config;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import java.util.Observable;
import java.util.Set;
import java.util.Objects;

import classes.categorie.Categorie;
import classes.piece.Piece;
import classes.piece.TypePiece;
import exceptions.ActionPieceInvalideException;
import exceptions.ParametreIncorrectException;
import exceptions.ResultatIncorrectException;

/**
 * Classe ConfigVoiture, effectue tous les changements sur la configuration actuelle :
 * Ajout & suppression de pieces, incompatibilites, necessites, categories
 * @author GR4
 *
 */
public class ConfigVoiture extends Observable implements ConfigInterface {

	private Set<Piece> maConfig;
	private Set<String> mesCategories;
	private Set<Piece> mesIncompatibilites;
	
	public ConfigVoiture(){
		this.maConfig = new HashSet<>();
		this.mesCategories = new HashSet<>();
		this.mesIncompatibilites = new HashSet<>();
	}
	
	/**
	 * Si toutes les categories sont presentes dans la configuration, alors cette derniere est complete
	 * @throws ResultatIncorrectException 
	 */
	@Override
	public boolean estComplet() throws ResultatIncorrectException {
		return (this.mesCategories.size() == Categorie.getCategories().size());
	}
	
	public Set<Piece> getConfiguration() {
		return maConfig;
	}

	public Set<String> getMesCategories() {
		return this.mesCategories;
	}

	/**
	 * Supprimer une piece de ma configuration
	 * @param piece
	 * @return true si la piece est supprimee de ma configuration, false sinon
	 * @throws ActionPieceInvalideException 
	 * @throws ResultatIncorrectException 
	 * @throws ParametreIncorrectException si la piece n'est pas dans ma configuration
	 */
	@Override
	public boolean supprimerPiece(String p) throws ActionPieceInvalideException, ResultatIncorrectException, ParametreIncorrectException {
		String pieceNonNull = Objects.requireNonNull(p);
		Piece piece = TypePiece.chercherPieceParNom(pieceNonNull);
		if(!maConfig.contains(piece) ) throw new ActionPieceInvalideException("Cette piece n'est pas dans votre configuration");

		this.mesIncompatibilites.removeAll(piece.getIncompatibilites()) ;
		this.mesCategories.remove(rechercherCategorieParPiece(piece)) ;
		this.maConfig.remove(piece);
		supprimerPieceNecessaire(piece);
		notifierObserver();
		return true;
	}

	/**
	 * Supprime les pieces necessaires a une autre automatiquement
	 * @param piece
	 * @throws ResultatIncorrectException
	 * @throws ParametreIncorrectException si piece trouvable
	 * @throws ActionPieceInvalideException si la piece n'est pas dans ma configuration
	 */
	private void supprimerPieceNecessaire(Piece piece) throws ResultatIncorrectException, ParametreIncorrectException, ActionPieceInvalideException {
		if(!piece.getNecessites().isEmpty()) {
			Iterator<Piece> it = piece.getNecessites().iterator();
			while(it.hasNext()) {
				Piece pieceNecessaire = it.next();
				if(maConfig.contains(pieceNecessaire)) {
					supprimerPiece(pieceNecessaire.getNom());
				}
			}
		}
	}

	/**
	 * Ajouter une piece dans ma configuration
	 * @param piece
	 * @return true si la piece est ajoutee a ma configuration, false sinon
	 * @throws ResultatIncorrectException 
	 * @throws ParametreIncorrectException
	 * @throws ActionPieceInvalideException si la piece est deja dans ma configuration
	 */
	@Override
	public boolean ajouterPiece(String p) throws ActionPieceInvalideException, ResultatIncorrectException, ParametreIncorrectException {
		if(estComplet()) throw new ActionPieceInvalideException("La configuration de la voiture est terminee");
		String pieceNonNull = Objects.requireNonNull(p);
		Piece piece = TypePiece.chercherPieceParNom(pieceNonNull);
		if (!getPiecesPossibles().contains(piece)) throw new ActionPieceInvalideException("Cette piece n'est pas dans la liste des pieces possibles");
		
		this.mesIncompatibilites.addAll(piece.getIncompatibilites()) ;
		this.mesCategories.add(rechercherCategorieParPiece(piece)) ;
		this.maConfig.add(piece);
		ajouterPiecesNecessaires(piece);
		notifierObserver();
		return true;
	}

	/**
	 * Ajouter les pieces necessaires a une autre automatiquement
	 * @param piece
	 * @throws ResultatIncorrectException
	 * @throws ParametreIncorrectException si piece introuvable
	 * @throws ActionPieceInvalideException si la piece est deja dans ma configuration 
	 */
	private void ajouterPiecesNecessaires(Piece piece) throws ResultatIncorrectException, ActionPieceInvalideException, ParametreIncorrectException {
		if(!piece.getNecessites().isEmpty()) {
			Iterator<Piece> it = piece.getNecessites().iterator();
			while(it.hasNext()) {
				Piece pieceNecessaire = it.next();
				if(!maConfig.contains(pieceNecessaire)) {
					ajouterPiece(pieceNecessaire.getNom());
				}
			}
		}
	}

	/**
	 * Recherche de la categorie associee a une piece
	 * @param piece
	 * @return la categorie d'une piece si elle existe
	 * @throws ResultatIncorrectException si la piece n'appartient a aucune categorie
	 */
	private String rechercherCategorieParPiece(Piece piece) throws ResultatIncorrectException {
		Map<String, List<Piece>> categories = Categorie.getCategorieCatalogue();
		Iterator<Entry<String, List<Piece>>> it = categories.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry<String, List<Piece>> catalogueCategories = it.next();
			if(catalogueCategories.getValue().contains(piece)) {
				String res = "";
				res += catalogueCategories.getKey();
				return res;
			}
		}
		throw new ResultatIncorrectException("Pas de categorie pour cette piece");
	}

	/**
	 * Renvoie un set de categories non presentes dans ma configuration
	 * @return un set de categories
	 */
	@Override
	public Set<String> getCategoriesRestantes() {
		Set<String> lesCategories = new HashSet<>();
		lesCategories.addAll(Categorie.getCategories());
		lesCategories.removeAll(this.mesCategories);
		return lesCategories;
	}

	/**
	 * Renvoie une piece par categorie 
	 * @throws ResultatIncorrectException si aucune piece n'a ete choisi dans cette categorie
	 * @throws ParametreIncorrectException si categorie inexistante
	 */
	@Override
	public Piece getPieceParCategorie(String categorie) throws ParametreIncorrectException, ResultatIncorrectException  {
		List<Piece> piecesCategorie = Categorie.getPiecesParCategorie(categorie);
		Iterator<Piece> it = piecesCategorie.iterator();
		while(it.hasNext()) {
			Piece piece = it.next();
			if(maConfig.contains(piece)) {		
				return piece;
			}
		}
		throw new ResultatIncorrectException("Aucune piece n'a ete selectionnee dans cette categorie");
	}

	/**
	 * Recupere un set de toutes les pieces que l'on peut encore ajouter a ma configuration
	 * @return un set de pieces
	 * @throws ParametreIncorrectException
	 * @throws ResultatIncorrectException
	 */
	@Override
	public Set<Piece> getPiecesPossibles() throws ResultatIncorrectException, ParametreIncorrectException {
		Set<Piece> piecesPossibles = new HashSet<>();
		Set<String> categoriesRestantes = getCategoriesRestantes();
		Iterator<String> it = categoriesRestantes.iterator();
		while(it.hasNext()) {
			String categorie = it.next();
			List<Piece> pieces = new LinkedList<>();
			pieces.addAll(Categorie.getPiecesParCategorie(categorie));
			pieces.removeAll(mesIncompatibilites);
			piecesPossibles.addAll(pieces);
		}
		return piecesPossibles;
	}
	
	/**
	 *  Methode permettant de notifier tous les observateurs lors d'un changement d'etat de la configuration de la voiture
	 */
	public void notifierObserver() {
		setChanged();
		notifyObservers();
	}
	
	public Set<Piece> getMesIncompatibilites(){
		return this.mesIncompatibilites;
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
}
