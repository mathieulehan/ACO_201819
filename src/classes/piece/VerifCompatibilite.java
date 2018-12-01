package classes.piece;

import java.util.Set;

import exceptions.ResultatNullException;

public interface VerifCompatibilite {

	Set<Piece> getIncompatibilites() throws ResultatNullException;
	Set<Piece> getNecessites() throws ResultatNullException;

	//boolean estIncompatible();
}
