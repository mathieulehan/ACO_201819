package classes.piece;

import java.util.HashSet;
import java.util.Set;

import exceptions.ParametreNullException;
import exceptions.PasDIncompatibilitesException;
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
	 * @param description la description de la piece
	 */
	public Piece(String nom, String description) {
		this.nom = nom;
		this.description = description;
		this.incompatibilites = new HashSet<>();
		this.necessites = new HashSet<>();
	}
	
	public String getNom() throws ResultatNullException {
		if(this.nom == null) throw new ResultatNullException("Le nom de la piece est null");
		return this.nom;
	}
	
	/**
	 * @param nom le nom de la piece
	 * @throws ParametreNullException si le nom donne en parametre est null
	 */
	public void setNom(String nom) throws ParametreNullException {
		if (nom == null) throw new ParametreNullException("Le nom entre en parametre est null");
		this.nom = nom;
	}

	/**
	 * @return la description de la piece
	 * @throws ResultatNullException si la description de la piece est null
	 */
	public String getDescription() throws ResultatNullException {
		if(this.description == null) throw new ResultatNullException("La description de la piece est null");
		return this.description;
	}

	/**
	 * @param description la description souhaitee pour la piece
	 * @throws ParametreNullException si la description en parametre est null
	 */
	public void setDescription(String description) throws ParametreNullException {
		if (description == null) throw new ParametreNullException("La description en parametre est nulle");
		this.description = description;
	}
	
	/**
	 * @param piecesIncomp les incompatibilites a ajouter a la piece
	 * @throws ParametreNullException si le Set d'incompatibilites en parametre est null
	 */
	@Override
	public void setIncompatibilites(Set<Piece> piecesIncomp) throws ParametreNullException {
		// TODO : DESIGN PATTERN this.incompatibilites = Object.requireNonNull(piecesIncomp);
		if (piecesIncomp == null) throw new ParametreNullException("Le Set d'incompatibilites en parametre est null");
		this.incompatibilites = piecesIncomp;
	}
	
	/**
	 * @throws ParametreNullException si la piece a un set d'incompatibilites null
	 * @throws ResultatNullException si le Set d'incompatibilite de la piece est null
	 */
	@Override
	public Set<Piece> getIncompatibilites() throws ParametreNullException, ResultatNullException {
		if(this.incompatibilites.getClass() != HashSet.class) throw new ParametreNullException("Les incompatibilites de la piece ne sont stockees sous forme de Set");
		return this.incompatibilites;
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
		else if (!this.incompatibilites.contains(incompatibilite)) throw new PasDIncompatibilitesException("L'incompatibilite en parametre est nulle");
		this.incompatibilites.remove(incompatibilite);
	}

	/**
	 * @param necessites le Set de necessites a ajouter a la piece
	 * @throws ParametreNullException si le Set de necessites en parametre est null
	 */
	@Override
	public void setNecessites(Set<Piece> necessites) throws ParametreNullException {
		if (this.necessites == null) throw new ParametreNullException("La necessite en parametre est nulle");
		this.necessites = necessites;
	}
	
	/**
	 * @throws ResultatNullException si le Set de necessite de la piece est null
	 * @return le Set de necessites de la piece
	 * @throws ParametreNullException 
	 */
	@Override
	public Set<Piece> getNecessites() throws ResultatNullException, ParametreNullException {
		if(this.necessites.getClass() != HashSet.class) throw new ParametreNullException("Les necessites de la piece sont stockees sous forme de Set");
		return this.necessites;
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
	 * On verifie que la piece n'est pas presente dans les incompatibilites de notre configVoiture
	 * Precondition : Piece non nulle
	 * @return true si la piece n'est pas presente dans les incompatibilites, false sinon
	 * @throws ParametreNullException
	 */
//	@Override
//	public boolean estIncompatible (Piece piece) {
//		return this.incompatibilites.contains(piece);
//	}
}
