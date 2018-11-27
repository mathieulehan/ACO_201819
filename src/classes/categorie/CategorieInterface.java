package classes.categorie;

import java.util.List;
import java.util.Map;

import exceptions.ParametreNullException;
import exceptions.ResultatNullException;

public interface CategorieInterface {

	/**
	 * Initialisation des pieces associees aux categories
	 */
	public void initialiserCategorie();
	
	/**
	 * Recupere les pieces d'une categorie
	 * @param categorie
	 * @return la liste des pieces dans la categorie
	 * @throws ParametreNullException 
	 * @throws ResultatNullException 
	 */
	public List<String> getCategorie(String categorie) throws ParametreNullException, ResultatNullException;

	/**
	 * Recupere toutes les pieces et toutes les categories
	 * @return l'ensemble des categories et des pieces existantes
	 * @throws ResultatNullException 
	 */
	public Map<String, List<String>> getCategorieCatalogue() throws ResultatNullException ;
}
