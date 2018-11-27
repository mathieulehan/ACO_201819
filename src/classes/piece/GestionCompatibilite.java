package classes.piece;

import java.util.Set;

import exceptions.ParametreNullException;
import exceptions.PasDIncompatibilitesException;
import exceptions.PasDeNecessitesException;

public interface GestionCompatibilite {

	void setIncompatibilites(Set<Piece> incompatibilites) throws ParametreNullException;
	void ajoutIncompatibilite(Piece incompatibilite) throws ParametreNullException;
	public void suppressionIncompatibilite(Piece incompatibilite) throws PasDIncompatibilitesException, ParametreNullException;

	void setNecessites(Set<Piece> necessites) throws ParametreNullException;
	public void ajoutNecessite(Piece necessite) throws ParametreNullException;
	public void suppressionNecessite(Piece necessite) throws PasDeNecessitesException, ParametreNullException;
}
