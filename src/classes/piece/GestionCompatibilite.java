package classes.piece;

import java.util.Set;

import exceptions.PasDIncompatibilitesException;

public interface GestionCompatibilite {

	void setIncompatibilites(Set<Piece> incompatibilites);
	void ajoutIncompatibilite(Piece incompatibilite);
	public void suppressionIncompatibilite(Piece incompatibilite) throws PasDIncompatibilitesException;

	void setNecessites(Set<Piece> necessites);
	public void ajoutNecessite(Piece necessite);
	public void suppressionNecessite(Piece necessite) throws PasDIncompatibilitesException;
}
