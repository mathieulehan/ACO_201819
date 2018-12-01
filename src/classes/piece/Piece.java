package classes.piece;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import exceptions.ParametreNullException;
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
	 */
	public void setNom(String nom) {
		String nouveauNom = Objects.requireNonNull(nom);
		this.nom = nouveauNom;
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
	 */
	public void setDescription(String description) {
		String nouvelleDescription = Objects.requireNonNull(description);
		this.description = nouvelleDescription;
	}
	
	/**
	 * @param piecesIncomp les incompatibilites a ajouter a la piece
	 */
	@Override
	public void setIncompatibilites(Set<Piece> piecesIncomp) {
		Set<Piece> nouvellesIncompatibilites = Objects.requireNonNull(piecesIncomp);
		// TODO verifier si il s'agit d'un Set<piece>
		this.incompatibilites = nouvellesIncompatibilites;
	}
	
	/**
	 * @throws ResultatNullException si le Set d'incompatibilite de la piece est vide
	 */
	@Override
	public Set<Piece> getIncompatibilites() throws ResultatNullException {
		//if(this.incompatibilites.isEmpty()) throw new ResultatNullException("Le set d'incompatibilite est vide");
		return this.incompatibilites;
	}
	
	/**
	 * Ajout d'une incompatibilite
	 * @param une incompatibilite (non null) a ajouter a la piece
	 * @throws ParametreNullException 
	 */
	@Override
	public void ajoutIncompatibilite(Piece incompatibilite) throws ParametreNullException {
		Piece nouvelleIncompatilibite = Objects.requireNonNull(incompatibilite);
		// TODO verifier si il s'agit d'une piece
		if (this.necessites.contains(nouvelleIncompatilibite)) throw new ParametreNullException("L'incompatibilite en parametre existe deja pour cette piece");
		this.incompatibilites.add(nouvelleIncompatilibite);
	}

	/**
	 * Suppression d'une incompatibilite
	 * @param une incompatibilite que l'on souhaite supprimer pour la piece
	 * @throws ParametreNullException si la piece ne possede pas cette incompatibilite
	 */
	@Override
	public void suppressionIncompatibilite(Piece incompatibilite) throws ParametreNullException {
		Piece ancienneIncompatilibite = Objects.requireNonNull(incompatibilite);
		// TODO verifier si il s'agit d'une piece
		if (!this.incompatibilites.contains(ancienneIncompatilibite)) throw new ParametreNullException("L'incompatibilite en parametre n'existe pas pour cette piece");
		this.incompatibilites.remove(ancienneIncompatilibite);
	}

	/**
	 * @param le Set de necessites a ajouter a la piece
	 * @throws ParametreNullException 
	 */
	@Override
	public void setNecessites(Set<Piece> necessites) {
		Set<Piece> nouvellesNecessites = Objects.requireNonNull(necessites);
		// TODO verifier si il s'agit d'un Set<piece>
		this.necessites = nouvellesNecessites;
	}
	
	/**
	 * @throws ResultatNullException si le Set de necessite de la piece est vide
	 * @return le Set de necessites de la piece
	 */
	@Override
	public Set<Piece> getNecessites() throws ResultatNullException {
		//if(this.necessites.isEmpty()) throw new ResultatNullException("Le set de necessites est vide");
		return this.necessites;
	}
	
	/**
	 * Ajout d'une necessite
	 * @param necessite la necessite a ajouter a la piece
	 * @throws ParametreNullException 
	 */
	@Override
	public void ajoutNecessite(Piece necessite) throws ParametreNullException  {
		Piece nouvelleNecessite = Objects.requireNonNull(necessite);
		// TODO verifier si il s'agit d'une piece
		if (this.necessites.contains(nouvelleNecessite)) throw new ParametreNullException("La necessite en parametre existe deja pour cette piece");
		this.necessites.add(nouvelleNecessite);
	}

	/**
	 * Suppression d'une necessite
	 * @param necessite la necessite a supprimer de la piece
	 * @throws ParametreNullException si la piece n'a pas la necessite en parametre
	 */
	
	@Override
	public void suppressionNecessite(Piece necessite) throws ParametreNullException {
		Piece ancienneNecessite = Objects.requireNonNull(necessite);
		// TODO verifier si il s'agit d'une piece
		if (!this.necessites.contains(ancienneNecessite)) throw new ParametreNullException("La necessite en parametre n'existe pas pour cette piece");
		this.necessites.remove(ancienneNecessite);
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
