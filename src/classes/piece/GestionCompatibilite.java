package classes.piece;

import java.util.Set;

import exceptions.ParametreNullException;

public interface GestionCompatibilite {

	void setIncompatibilites(Set<Piece> incompatibilites);
	void ajoutIncompatibilite(Piece incompatibilite) throws ParametreNullException;
	public void suppressionIncompatibilite(Piece incompatibilite) throws ParametreNullException;

	void setNecessites(Set<Piece> necessites);
	public void ajoutNecessite(Piece necessite) throws ParametreNullException;
	public void suppressionNecessite(Piece necessite) throws ParametreNullException;
}
