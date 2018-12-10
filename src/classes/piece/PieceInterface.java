package classes.piece;

import java.util.Optional;
import java.util.Set;

/**
 * 
 * @author YMCA
 *
 */
public interface PieceInterface {

	Set<String> getNomsPropriete();

	void setPropriete(String nomPropriete, String valeurPropriete);

	Optional<String> getPropriete(String nomPropriete);

	Set<String> getValeursProprietePossibles(String nomPropriete);
}
