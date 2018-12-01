package classes.piece;

import java.util.Set;

import exceptions.ParametreNullException;

public interface GestionCompatibilite {

	public void setIncompatibilites(Set<Piece> incompatibilites) throws ParametreNullException;
	public void ajoutIncompatibilite(Piece incompatibilite) throws ParametreNullException;
	public void suppressionIncompatibilite(Piece incompatibilite) throws ParametreNullException;

	public void setNecessites(Set<Piece> necessites) throws ParametreNullException;
	public void ajoutNecessite(Piece necessite) throws ParametreNullException;
	public void suppressionNecessite(Piece necessite) throws ParametreNullException;
}
