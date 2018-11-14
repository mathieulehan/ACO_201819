package classes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Categorie implements CategorieInterface {

	private enum categories {ENGINE, TRANSMISSION, EXTERIOR, INTERIOR};
	private Map<categories, List<String>> categorieCatalogue;
	
	public Categorie () {
		this.categorieCatalogue = new HashMap<>();;
	}
	
	public void initialiserCategorie () {
		categorieCatalogue.put(categories.ENGINE, new LinkedList<String>(Arrays.asList("EG100", "EG133", "EG210", "ED100", "ED180", "EH120")));
		categorieCatalogue.put(categories.TRANSMISSION, new LinkedList<String>(Arrays.asList("TM5", "TM6", "TA5", "TS6", "TSF7", "TC120")));
		categorieCatalogue.put(categories.EXTERIOR, new LinkedList<String>(Arrays.asList("XC", "XM", "XS")));
		categorieCatalogue.put(categories.INTERIOR, new LinkedList<String>(Arrays.asList("IN", "IH", "IS")));
	}
	
	public List<String> getCategorie(categories categorie) {
		return categorieCatalogue.get(categorie);
	}
}
