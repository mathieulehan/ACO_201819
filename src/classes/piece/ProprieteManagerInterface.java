package classes.piece;

import java.util.Optional;
import java.util.Set;

/**
 * 
 * @author YMCA
 *
 */
public interface ProprieteManagerInterface {
	/**
	 * Retourne un set des noms de proprietes gerees par le manager
	 */
	public Set<String> getNomsProprietes();
	
	/**
	 * Retourne un set des valeurs de la propriete en parametre
	 * Pour les proprietes qui n'ont pas de valeurs possibles ou pour un
	 * nom de propriete inexistant, retourne un set vide
	 * 
	 * @param nomPropriete
	 * @return immutable set
	 */
	public Set<String> getValeursProprietePossibles(String nomPropriete);
	
	/**
	 * Retourne la valeur de la propriete
	 * Si l'objet ne gere pas cette propriete, return vide
	 * @param nomPropriete
	 * @return
	 */
	public Optional<String> getPropriete(String nomPropriete);
	
	/**
	 * Met en place la valeur de la propriete passee en parametre
	 * Si la propriete n'existe pas (ou non modifiable), ou si la valeur est invalide,
	 * return une exception IllegalArgumentException
	 * @param nomPropriete
	 * @param valeurPropriete
	 * @throws IllegalArgumentException
	 */
	void setPropriete(String nomPropriete, String valeurPropriete);
	
}
