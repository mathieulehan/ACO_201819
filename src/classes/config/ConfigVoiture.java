package classes.config;

import java.io.PrintStream;
import java.util.Collections;
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
import classes.piece.Piece.Couleur;
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
	 * Renvoie le prix de la configuration actuelle en euros apres possibles reductions
	 * @return
	 * @throws ResultatIncorrectException 
	 */
	@Override
	public String getPrix() throws ResultatIncorrectException {
		double prix = 0.0;
		for (Piece piece : maConfig) {
			prix += piece.getPrix();
		}
		if(maConfig.contains(TypePiece.chercherPieceParNom("ED180")) && maConfig.contains(TypePiece.chercherPieceParNom("XS")) 
				&& maConfig.contains(TypePiece.chercherPieceParNom("IS")) && maConfig.contains(TypePiece.chercherPieceParNom("TA5"))) {
			prix *= 0.90;
		}
		if(maConfig.contains(TypePiece.chercherPieceParNom("XC")) && maConfig.contains(TypePiece.chercherPieceParNom("IN"))) {
			prix -= 100.00;
		}
		String monPrix = "Cette configuration coute actuellement " + prix + "€.";
		return monPrix;
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
	 * Supprimer une piece de ma configuration
	 * @param la piece a supprime
	 * @return true si la piece est supprimee de ma configuration, false sinon
	 * @throws ActionPieceInvalideException si la piece n'est pas dans ma configuration
	 * @throws ResultatIncorrectException 
	 * @throws ParametreIncorrectException
	 * @throws ActionPieceInvalideException si la piece est deja dans ma configuration
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
		return true;
	}

	/**
	 * Supprime les pieces necessaires a une autre automatiquement
	 * @param la piece
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

	public Set<Couleur> getCouleursPossibles(String p) throws ResultatIncorrectException, ParametreIncorrectException{
		String pieceNonNull = Objects.requireNonNull(p);
		Piece piece = TypePiece.chercherPieceParNom(pieceNonNull);
		if(Categorie.getPiecesParCategorie("EXTERIOR").contains(piece)) {
			Set<Couleur> mesCouleurs = new HashSet<>();
			mesCouleurs.add(Piece.Couleur.BLANC);
			mesCouleurs.add(Piece.Couleur.BLEU);
			mesCouleurs.add(Piece.Couleur.ROUGE);
			String maCouleur = piece.getPropriete("couleur").get();
			switch(maCouleur) {
			case "BLANC": mesCouleurs.remove(Piece.Couleur.BLANC);
			break;
			case "BLEU": mesCouleurs.remove(Piece.Couleur.BLEU);
			break;
			case "ROUGE": mesCouleurs.remove(Piece.Couleur.ROUGE);
			break;
			}
			return mesCouleurs;
		}
		return Collections.emptySet();
	}

	/**
	 * Modification de la couleur d'une peinture, peu importe si la piece est dans ma configuration ou non
	 * Si la peinture a deja la couleur demandee, renvoie false
	 * @param p
	 * @param c
	 * @throws ResultatIncorrectException
	 * @throws ParametreIncorrectException
	 */
	public boolean setCouleur(String p, Couleur c) throws ResultatIncorrectException, ParametreIncorrectException {
		String pieceNonNull = Objects.requireNonNull(p);
		Couleur couleurNonNull = Objects.requireNonNull(c);
		Piece piece = TypePiece.chercherPieceParNom(pieceNonNull);
		// S'il s'agit bien d'une peinture
		if(Categorie.getPiecesParCategorie("EXTERIOR").contains(piece)) {
			// Autre possibilite de check s'il s'agit bien d'une peinture : piece doit heriter de Exterior :
			//if(piece.getClass().getSuperclass().getSimpleName() == TypePiece.chercherPieceParNom("XS").getClass().getSuperclass().getSimpleName()) {
			if(piece.getValeursProprietePossibles("couleur").contains(c.name()) && piece.getPropriete("couleur").get() != c.name()) {
				piece.setPropriete("couleur", couleurNonNull.name());
				return true;
			}
		}
		return false;
	}
	
	/**
	 * L'utilisateur peut recuperer la description de sa configuration si elle est complete (pieces + prix)
	 * @param ps
	 * @throws ResultatIncorrectException
	 */
	public void getDescription(PrintStream ps) throws ResultatIncorrectException {
		if(estComplet()) {
			// TODO utiliser un PrintStream
			System.out.println("Votre configuration est composee de ces quatre pieces : ");
			this.getConfiguration();
			System.out.println("Son prix s'eleve a " + this.getPrix());
		}
	}
}
