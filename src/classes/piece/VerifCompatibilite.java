package classes.piece;

import java.util.Set;

import exceptions.ResultatIncorrectException;

/**
 * 
 * @author GR4
 *
 */
public interface VerifCompatibilite {

	public Set<Piece> getIncompatibilites() throws ResultatIncorrectException;
	public Set<Piece> getNecessites() throws ResultatIncorrectException;

	public boolean estIncompatible(Piece piece);
}
