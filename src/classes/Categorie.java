package classes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Classe Categorie, implémentant une interface CategorieInterface, s'agissant d'une Map ayant pour : 
 * - Clé : le nom de la catégorie (énumération)
 * - Valeurs (List) : les pièces associées à une catégorie
 * 
 * @author math & chach
 *
 */
public class Categorie implements CategorieInterface {

	/**
	 * Enumération des catégories existantes
	 */
	private enum categories {ENGINE, TRANSMISSION, EXTERIOR, INTERIOR};
	private Map<categories, List<String>> categorieCatalogue;
	
	/**
	 * Constructeur Categorie
	 */
	public Categorie () {
		this.categorieCatalogue = new HashMap<>();;
	}
	
	/**
	 * Initialsation des clés (catégorie) suivies de ses valeurs (pièces associées)
	 */
	public void initialiserCategorie () {
		categorieCatalogue.put(categories.ENGINE, new LinkedList<String>(Arrays.asList("EG100", "EG133", "EG210", "ED100", "ED180", "EH120")));
		categorieCatalogue.put(categories.TRANSMISSION, new LinkedList<String>(Arrays.asList("TM5", "TM6", "TA5", "TS6", "TSF7", "TC120")));
		categorieCatalogue.put(categories.EXTERIOR, new LinkedList<String>(Arrays.asList("XC", "XM", "XS")));
		categorieCatalogue.put(categories.INTERIOR, new LinkedList<String>(Arrays.asList("IN", "IH", "IS")));
	}
}
