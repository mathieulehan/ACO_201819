package classes.piece;

import java.util.Set;

import exceptions.IncompatibiliteEstNullException;
import exceptions.NecessiteEstNullException;
import exceptions.PasDIncompatibilitesException;
import exceptions.PasDeNecessitesException;

public interface GestionCompatibilite {

	void setIncompatibilites(Set<Piece> incompatibilites) throws IncompatibiliteEstNullException;
	void ajoutIncompatibilite(Piece incompatibilite) throws IncompatibiliteEstNullException;
	public void suppressionIncompatibilite(Piece incompatibilite) throws PasDIncompatibilitesException, IncompatibiliteEstNullException;

	void setNecessites(Set<Piece> necessites) throws NecessiteEstNullException;
	public void ajoutNecessite(Piece necessite) throws NecessiteEstNullException;
	public void suppressionNecessite(Piece necessite) throws NecessiteEstNullException, PasDeNecessitesException;
}
