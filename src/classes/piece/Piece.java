package classes.piece;

import java.util.HashSet;
import java.util.Set;

import classes.categorie.Categorie;
import classes.config.ConfigVoiture;
import exceptions.CategorieEstNullException;
import exceptions.DescriptionEstNullException;
import exceptions.IncompatibiliteEstNullException;
import exceptions.NecessiteEstNullException;
import exceptions.PasDIncompatibilitesException;
import exceptions.PasDeNecessitesException;
import exceptions.PieceEstNullException;

/**
 * Classe possedant des methodes permettant de gerer les pieces
 * @author mathi & chach44 & yann
 *
 */
public class Piece implements PieceInterface, GestionCompatibilite, VerifCompatibilite {

	private String nom;
	private String description;
	private Set<Piece> incompatibilites;
	private Set<Piece> necessites;

	/**
	 * Constructeur
	 * @param nom le nom de la piece
	 * @param description la descriptiond de la piece
	 */
	public Piece(String nom, String description) {
		this.nom = nom;
		this.description = description;
		this.incompatibilites = new HashSet<Piece>();
		this.necessites = new HashSet<Piece>();
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	/**
	 * @param description la description souhaitee pour la piece
	 * @throws DescriptionEstNullException si la description en parametre est nulle	
	 */
	public void setDescription(String description) throws DescriptionEstNullException {
		if (description == null) {
			throw new DescriptionEstNullException("La description en parametre est nulle");
		}
		this.description = description;
	}

	/**
	 * @return la categorie de la piece
	 * @throws CategorieEstNullException si la categorie de la piece est nulle
	 */
	public String getCategorie() throws CategorieEstNullException {
		Categorie cat = new Categorie();
		cat.initialiserCategorie();
		for (String categorie : cat.getCategories()) {
			if(cat.getCategorie(categorie).contains(this.nom)) {
				return categorie;
			}
		}
		return null;
	}
	
	/**
	 * @param incompatibilites les incompatibilites a ajouter a la piece
	 * @throws IncompatibiliteEstNullException si le Set d'incompatibilites en parametre est null
	 */
	@Override
	public void setIncompatibilites(Set<Piece> incompatibilites) throws IncompatibiliteEstNullException {
		if (incompatibilites == null) {
			throw new IncompatibiliteEstNullException("Le Set d'incompatibilites en parametre est null");
		}
		this.incompatibilites = incompatibilites;
	}
	
	@Override
	public Set<Piece> getIncompatibilites() {
		return incompatibilites;
	}
	
	/**
	 * Ajout d'une incompatibilite
	 * @param incompatibilite l'incompatibilite a ajouter a la piece
	 * @throws IncompatibiliteEstNullException si l'incompatibilite est nulle
	 */
	@Override
	public void ajoutIncompatibilite(Piece incompatibilite) throws IncompatibiliteEstNullException {
		if (incompatibilite == null) {
			throw new IncompatibiliteEstNullException("L'incompatibilite en parametre est nulle");
		}
		this.incompatibilites.add(incompatibilite);
	}

	/**
	 * Suppression d'une incompatibilite
	 * @param incompatibilite l'incompatibilite que l'on souhaite supprimer pour la piece
	 * @throws PasDIncompatibilitesException si la piece ne possede pas cette incompatibilite
	 * @throws IncompatibiliteEstNullException si l'incompatibilite en parametre est nulle
	 */
	@Override
	public void suppressionIncompatibilite(Piece incompatibilite) throws PasDIncompatibilitesException, IncompatibiliteEstNullException {
		if (incompatibilite == null) {
			throw new IncompatibiliteEstNullException("L'incompatibilite en parametre est nulle");
		}
		this.incompatibilites.remove(incompatibilite);
	}

	/**
	 * @param necessites le Set de necessites a ajouter a la piece
	 * @throws NecessiteEstNullException si le Set de necessites en parametre est null
	 */
	@Override
	public void setNecessites(Set<Piece> necessites) throws NecessiteEstNullException {
		if (necessites == null) {
			throw new NecessiteEstNullException("La necessite en parametre est nulle");
		}
		this.necessites = necessites;
	}
	
	@Override
	public Set<Piece> getNecessites() {
		return necessites;
	}
	
	/**
	 * Ajout d'une necessite
	 * @param necessite la necessite a ajouter a la piece
	 * @throws NecessiteEstNullException si la necessite en parametre est nulle
	 */
	@Override
	public void ajoutNecessite(Piece necessite) throws NecessiteEstNullException {
		if (necessite == null) {
			throw new NecessiteEstNullException("La necessite est parametre est nulle");
		}
		this.necessites.add(necessite);
	}

	/**
	 * Suppression d'une necessite
	 * @param necessite la necessite a supprimer de la piece
	 * @throws NecessiteEstNullException si la necessite en parametre est nulle
	 * @throws PasDeNecessitesException si la piece n'a pas la necessite en parametre
	 */
	@Override
	public void suppressionNecessite(Piece necessite) throws PasDeNecessitesException, NecessiteEstNullException {
		if (necessite == null) {
			throw new NecessiteEstNullException("La necessite en parametre est nulle");
		}
		this.necessites.remove(necessite);
	}

	/**
	 * On verifie que la piece 
	 * @param nomPiece la piece a verifier
	 * @return
	 * @throws PieceEstNullException si la piece en parametre est nulle
	 */
	@Override
	public boolean verification (Piece piece) throws PieceEstNullException {
		if (piece == null) {
			throw new PieceEstNullException("La piece en parametre est nulle");
		}
		return ConfigVoiture.mesIncompatibilites.contains(piece);
	}
}
