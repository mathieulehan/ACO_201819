package classes.piece;

import java.util.Set;

import exceptions.ParametreIncorrectException;

/**
 * 
 * @author YMCA
 *
 */
public interface GestionCompatibilite {

	/**
	 * @param les incompatibilites a associer a la piece
	 * @throws ParametreIncorrectException
	 * 
	 */
	public void setIncompatibilites(Set<Piece> incompatibilites) throws ParametreIncorrectException;
	
	/**
	 * Ajout d'une incompatibilite
	 * @param une incompatibilite (non null) a ajouter a la piece
	 * @throws ParametreIncorrectException 
	 */
	public void ajoutIncompatibilite(Piece incompatibilite) throws ParametreIncorrectException;
	
	/**
	 * Suppression d'une incompatibilite
	 * @param une incompatibilite que l'on souhaite supprimer pour la piece
	 * @throws ParametreIncorrectException si la piece ne possede pas cette incompatibilite
	 */
	public void suppressionIncompatibilite(Piece incompatibilite) throws ParametreIncorrectException;

	/**
	 * @param le Set de necessites a associer a la piece
	 */
	public void setNecessites(Set<Piece> necessites) throws ParametreIncorrectException;

	/**
	 * Ajout d'une necessite
	 * @param la necessite a ajouter a la piece
	 * @throws ParametreIncorrectException 
	 */
	public void ajoutNecessite(Piece necessite) throws ParametreIncorrectException;

	/**
	 * Suppression d'une necessite
	 * @param la necessite a supprimer de la piece
	 * @throws ParametreIncorrectException si la piece n'a pas la necessite en parametre
	 */
	public void suppressionNecessite(Piece necessite) throws ParametreIncorrectException;
}
