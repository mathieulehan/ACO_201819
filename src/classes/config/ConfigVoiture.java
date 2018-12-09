package classes.config;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import java.util.Set;
import java.util.Objects;

import classes.categorie.Categorie;
import classes.piece.Piece;
import classes.piece.TypePiece;
import exceptions.ActionPieceInvalideException;
import exceptions.ParametreIncorrectException;
import exceptions.ResultatIncorrectException;

/**
 * La classe ConfigVoiture effectue toutes les changements d'etat de la configuration actuelle :
 * - Ajout et suppression de pieces
 * - Gestion des incompatibilites et necessites, categories
 * 
 * @author YMCA
 *
 */
public class ConfigVoiture implements ConfigInterface {

	private Set<Piece> maConfig;
	private Set<String> mesCategories;
	private Set<Piece> mesIncompatibilites;

	public ConfigVoiture() {
		this.maConfig = new HashSet<>();
		this.mesCategories = new HashSet<>();
		this.mesIncompatibilites = new HashSet<>();
	}

	/**
	 * Si toutes les categories sont presentes dans la configuration, alors cette derniere est complete
	 */
	@Override
	public boolean estComplet() {
		return (this.mesCategories.size() == Categorie.getCategories().size());
	}

	/**
	 * Ajouter une piece dans ma configuration
	 * @param la piece a ajoute
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
		return true;
	}

	/**
	 * Ajouter les pieces necessaires a une autre automatiquement
	 * @param la piece
	 * @throws ResultatIncorrectException
	 * @throws ParametreIncorrectException
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
	 * Supprimer une piece de ma configuration
	 * @param la piece a supprime
	 * @return true si la piece est supprimee de ma configuration, false sinon
	 * @throws ActionPieceInvalideException si la piece n'est pas dans ma configuration
	 * @throws ResultatIncorrectException 
	 * @throws ActionPieceInvalideException si la piece est deja dans ma configuration
	 */
	@Override
	public boolean supprimerPiece(String p) throws ActionPieceInvalideException, ResultatIncorrectException {
		String pieceNonNull = Objects.requireNonNull(p);
		Piece piece = TypePiece.chercherPieceParNom(pieceNonNull);
		if(!maConfig.contains(piece) ) throw new ActionPieceInvalideException("Cette piece n'est pas dans votre configuration");

		this.mesIncompatibilites.removeAll(piece.getIncompatibilites()) ;
		this.mesCategories.remove(rechercherCategorieParPiece(piece)) ;
		this.maConfig.remove(piece);
		supprimerPieceNecessaire(piece);
		return true;
	}

	/**
	 * Supprime les pieces necessaires a une autre automatiquement
	 * @param la piece
	 * @throws ResultatIncorrectException
	 * @throws ActionPieceInvalideException si la piece n'est pas dans ma configuration
	 */
	private void supprimerPieceNecessaire(Piece piece) throws ResultatIncorrectException, ActionPieceInvalideException {
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
	 * Recherche de la categorie associee a une piece
	 * @param la piece
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
	 * @param la categorie
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
	 */
	@Override
	public Set<Piece> getPiecesPossibles() throws ParametreIncorrectException {
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
	
	public Set<Piece> getMesIncompatibilites(){
		return this.mesIncompatibilites;
	}

	public Set<Piece> getConfiguration() {
		return maConfig;
	}

	public Set<String> getMesCategories() {
		return this.mesCategories;
	}
}
