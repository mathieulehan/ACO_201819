package classes.piece;

import java.util.Set;

public interface VerifCompatibilite {

	boolean verification(Piece piece);

	Set<Piece> getIncompatibilites();
	Set<Piece> getNecessites();
}
