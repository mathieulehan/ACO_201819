package classes.piece;

import java.util.List;
import java.util.Set;

import exceptions.PasDIncompatibilitesException;

public interface GestionCompatibilite {

	void setIncompatibilites(Set<String> newIncompatibilites);
	public void ajoutIncompatibilite(String incompatibilité);
	public void suppressionIncompatibilite(String incompatibilite) throws PasDIncompatibilitesException;

	void setNecessites(Set<String> newNecessites);
	public void ajoutNecessite(String necessite);
	public void suppressionNecessite(String necessite) throws PasDIncompatibilitesException;
}
