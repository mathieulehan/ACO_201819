package classes.piece;

import java.util.Set;

import exceptions.ResultatNullException;

public interface VerifCompatibilite {

	public Set<Piece> getIncompatibilites() throws ResultatNullException;
	public Set<Piece> getNecessites() throws ResultatNullException;

	public boolean estIncompatible(Piece piece);
}
