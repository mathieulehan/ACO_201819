package classes.piece;

import java.util.Set;

import exceptions.PieceEstNullException;

public interface VerifCompatibilite {

	boolean verification(Piece piece) throws PieceEstNullException;

	Set<Piece> getIncompatibilites();
	Set<Piece> getNecessites();
}
