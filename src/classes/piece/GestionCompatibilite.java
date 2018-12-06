package classes.piece;

import java.util.Set;

import exceptions.ParametreIncorrectException;

/**
 * 
 * @author GR4
 *
 */
public interface GestionCompatibilite {

	public void setIncompatibilites(Set<Piece> incompatibilites) throws ParametreIncorrectException;
	public void ajoutIncompatibilite(Piece incompatibilite) throws ParametreIncorrectException;
	public void suppressionIncompatibilite(Piece incompatibilite) throws ParametreIncorrectException;

	public void setNecessites(Set<Piece> necessites) throws ParametreIncorrectException;
	public void ajoutNecessite(Piece necessite) throws ParametreIncorrectException;
	public void suppressionNecessite(Piece necessite) throws ParametreIncorrectException;
}
