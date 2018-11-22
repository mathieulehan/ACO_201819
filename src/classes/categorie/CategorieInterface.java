package classes.categorie;

import java.util.List;
import java.util.Map;

public interface CategorieInterface {

	/**
	 * Initialisation des pieces associees aux categories
	 */
	public void initialiserCategorie();
	
	/**
	 * Recupere les pieces d'une categorie
	 * @param categorie
	 * @return la liste des pieces dans la categorie
	 */
	public List<String> getCategorie(String categorie);

	/**
	 * Recupere toutes les pieces et toutes les categories
	 * @return l'ensemble des categories et des pieces existantes
	 */
	public Map<String, List<String>> getCategorieCatalogue() ;
}
