package classes.piece;

import java.util.HashSet;
import java.util.Set;

import classes.categorie.Categorie;
import classes.config.ConfigVoiture;
import exceptions.ParametreNullException;
import exceptions.PasDIncompatibilitesException;
import exceptions.PasDeCategorieException;
import exceptions.PasDeNecessitesException;
import exceptions.ResultatNullException;

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
	
	public void setNom(String nom) throws ParametreNullException {
		if (nom == null) throw new ParametreNullException("Le nom entre en parametre est null");
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	/**
	 * @param description la description souhaitee pour la piece
	 * @throws ParametreNullException si la description en parametre est nulle	
	 */
	public void setDescription(String description) throws ParametreNullException {
		if (description == null) throw new ParametreNullException("La description en parametre est nulle");
		this.description = description;
	}

	/**
	 * @return la categorie de la piece
	 * @throws ParametreNullException si une categorie renvoyee par cat.getCategories() est null
	 * @throws PasDeCategorieException si la piece n'a pas de categorie
	 * @throws ResultatNullException 
	 */
	public String getCategorie() throws PasDeCategorieException, ParametreNullException, ResultatNullException {
		Categorie cat = new Categorie();
		cat.initialiserCategorie();
		for (String categorie : cat.getCategories()) {
			if(cat.getCategorie(categorie).contains(this.nom)) {
				return categorie;
			}
		}
		throw new PasDeCategorieException("La piece ne possede pas de categorie");
	}
	
	/**
	 * @param incompatibilites les incompatibilites a ajouter a la piece
	 * @throws ParametreNullException si le Set d'incompatibilites en parametre est null
	 */
	@Override
	public void setIncompatibilites(Set<Piece> incompatibilites) throws ParametreNullException {
		if (incompatibilites == null) throw new ParametreNullException("Le Set d'incompatibilites en parametre est null");
		this.incompatibilites = incompatibilites;
	}
	
	/**
	 * @throws ParametreNullException si la piece a un set d'incompatibilites null
	 * @throws ResultatNullException 
	 */
	@Override
	public Set<Piece> getIncompatibilites() throws ParametreNullException, ResultatNullException {
		if(incompatibilites.getClass() != Set.class) throw new ParametreNullException("Les incompatibilites de la piece ne sont stockees sous forme de Set");
		else if (incompatibilites == null) throw new ResultatNullException("Le Set d'incompatibilite de la piece est null");
		return incompatibilites;
	}
	
	/**
	 * Ajout d'une incompatibilite
	 * @param incompatibilite l'incompatibilite a ajouter a la piece
	 * @throws ParametreNullException si l'incompatibilite est nulle
	 */
	@Override
	public void ajoutIncompatibilite(Piece incompatibilite) throws ParametreNullException {
		if (incompatibilite == null) throw new ParametreNullException("L'incompatibilite en parametre est nulle");
		this.incompatibilites.add(incompatibilite);
	}

	/**
	 * Suppression d'une incompatibilite
	 * @param incompatibilite l'incompatibilite que l'on souhaite supprimer pour la piece
	 * @throws PasDIncompatibilitesException si la piece ne possede pas cette incompatibilite
	 * @throws ParametreNullException si l'incompatibilite en parametre est nulle
	 */
	@Override
	public void suppressionIncompatibilite(Piece incompatibilite) throws PasDIncompatibilitesException, ParametreNullException {
		if (incompatibilite == null) throw new ParametreNullException("L'incompatibilite en parametre est nulle");
		this.incompatibilites.remove(incompatibilite);
	}

	/**
	 * @param necessites le Set de necessites a ajouter a la piece
	 * @throws ParametreNullException si le Set de necessites en parametre est null
	 */
	@Override
	public void setNecessites(Set<Piece> necessites) throws ParametreNullException {
		if (necessites == null) throw new ParametreNullException("La necessite en parametre est nulle");
		this.necessites = necessites;
	}
	
	/**
	 * @throws ResultatNullException si le Set de necessite de la piece est null
	 * @return le Set de necessites de la piece
	 */
	@Override
	public Set<Piece> getNecessites() throws ResultatNullException {
		if (necessites == null) {
			throw new ResultatNullException("Le Set de necessite de la piece est null");
		}
		return necessites;
	}
	
	/**
	 * Ajout d'une necessite
	 * @param necessite la necessite a ajouter a la piece
	 * @throws ParametreNullException si la necessite en parametre est nulle
	 */
	@Override
	public void ajoutNecessite(Piece necessite) throws ParametreNullException {
		if (necessite == null) throw new ParametreNullException("La necessite est parametre est nulle");
		this.necessites.add(necessite);
	}

	/**
	 * Suppression d'une necessite
	 * @param necessite la necessite a supprimer de la piece
	 * @throws ParametreNullException si la necessite en parametre est nulle
	 * @throws PasDeNecessitesException si la piece n'a pas la necessite en parametre
	 */
	@Override
	public void suppressionNecessite(Piece necessite) throws PasDeNecessitesException, ParametreNullException {
		if (necessite == null) throw new ParametreNullException("La necessite en parametre est nulle");
		else if (!this.necessites.contains(necessite)) throw new PasDeNecessitesException("La necessite en parametre est nulle");
		this.necessites.remove(necessite);
	}

	/**
	 * On verifie que la piece 
	 * @param nomPiece la piece a verifier
	 * @return
	 * @throws ParametreNullException si la piece en parametre est nulle
	 */
	@Override
	public boolean verification (Piece piece) throws ParametreNullException {
		if (piece == null) throw new ParametreNullException("La piece en parametre est nulle");
		return ConfigVoiture.mesIncompatibilites.contains(piece);
	}
}
