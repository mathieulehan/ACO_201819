package classes.piece;

import java.util.Set;

import exceptions.ParametreNullException;
import exceptions.ResultatNullException;

public interface VerifCompatibilite {

	boolean verificationIncompatibilite(Piece piece) throws ParametreNullException;

	Set<Piece> getIncompatibilites() throws ParametreNullException, ResultatNullException;
	Set<Piece> getNecessites() throws ResultatNullException;
}
