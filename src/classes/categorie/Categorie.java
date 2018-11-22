package classes.categorie;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Classe Categorie, implementant une interface CategorieInterface, s'agissant d'une Map ayant pour : 
 * - Cle : le nom de la categorie (enumeration)
 * - Valeurs (List) : les pieces associees a  une categorie
 * 
 * @author math & chach44
 *
 */
public class Categorie implements CategorieInterface {

	/**
	 * Enumeration des categories existantes
	 */
	private Set<String> categories = new HashSet<String>();
	private Map<String, List<String>> categorieCatalogue;
	
	/**
	 * Constructeur Categorie
	 */
	public Categorie () {
		this.categorieCatalogue = new HashMap<>();
}
	
	public Set<String> getCategories() {
		return categories;
	}

	/**
	 * Initialisation des cles (categorie) suivies de ses valeurs (pieces associees)
	 */
	public void initialiserCategorie () {
		this.categories.add("ENGINE");
		this.categories.add("TRNASMISSION");
		this.categories.add("EXTERIOR");
		this.categories.add("INTERIOR");
		this.categorieCatalogue.put("ENGINE", new LinkedList<String>(Arrays.asList("EG100", "EG133", "EG210", "ED100", "ED180", "EH120")));
		this.categorieCatalogue.put("TRANSMISSION", new LinkedList<String>(Arrays.asList("TM5", "TM6", "TA5", "TS6", "TSF7", "TC120")));
		this.categorieCatalogue.put("EXTERIOR", new LinkedList<String>(Arrays.asList("XC", "XM", "XS")));
		this.categorieCatalogue.put("INTERIOR", new LinkedList<String>(Arrays.asList("IN", "IH", "IS")));
	}
	
	public List<String> getCategorie(String categorie) {
		return getCategorieCatalogue().get(categorie);
	}

	public Map<String, List<String>> getCategorieCatalogue() {
		return categorieCatalogue;
	}
}
