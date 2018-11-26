package classes.categorie;

import java.util.List;
import java.util.Map;

import exceptions.CategorieEstNullException;

public interface CategorieInterface {

	/**
	 * Initialisation des pieces associees aux categories
	 */
	public void initialiserCategorie();
	
	/**
	 * Recupere les pieces d'une categorie
	 * @param categorie
	 * @return la liste des pieces dans la categorie
	 * @throws CategorieEstNullException 
	 */
	public List<String> getCategorie(String categorie) throws CategorieEstNullException;

	/**
	 * Recupere toutes les pieces et toutes les categories
	 * @return l'ensemble des categories et des pieces existantes
	 */
	public Map<String, List<String>> getCategorieCatalogue() ;
}
