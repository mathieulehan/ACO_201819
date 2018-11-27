package classes.categorie;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import exceptions.ParametreNullException;
import exceptions.ResultatNullException;

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

	/**
	 * @return le set de categoriesq
	 * @throws ResultatNullException si le Set de categories renvoye est null
	 */
	public Set<String> getCategories() throws ResultatNullException {
		if(categories == null) throw new ResultatNullException("Le Set de categories est null");
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

	/**
	 * @param la categorie que l'on souhaite récupérer
	 * @throws ParametreNullException si la categorie en parametre est null
	 * @throws ResultatNullException 
	 */
	public List<String> getCategorie(String categorie) throws ParametreNullException, ResultatNullException {
		if (categorie == null) {
			throw new ParametreNullException("La catégorie en paramètre est nulle");
		}
		return getCategorieCatalogue().get(categorie);
	}

	/**
	 * @return Chaque categorie avec ces pieces, sous forme de Map
	 * @throws ResultatNullException si la Map que l'on retourne est null
	 */
	public Map<String, List<String>> getCategorieCatalogue() throws ResultatNullException {
		if(categorieCatalogue == null) throw new ResultatNullException("La Map<String, List<String>> est null.");
		return categorieCatalogue;
	}
}
