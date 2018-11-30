package classes.piece;

import java.util.Set;

import exceptions.ParametreNullException;
import exceptions.ResultatNullException;

public interface VerifCompatibilite {

	Set<Piece> getIncompatibilites() throws ParametreNullException, ResultatNullException;
	Set<Piece> getNecessites() throws ResultatNullException;

	boolean verificationIncompatibilite(Piece piece);
}
