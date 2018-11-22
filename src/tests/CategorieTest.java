package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import classes.categorie.Categorie;

class CategorieTest {

	Categorie categorieTest = new Categorie();
			
	/**
	 * On verifie si l'initialisation de la Map<Categories, List<String>> categorieCatalogue
	 * se deroule comme prevu. (des entrees sont-elles ajoutees ?)
	 */
	@Test
	void testInitialisation(){
		categorieTest.initialiserCategorie();
		assertTrue(!categorieTest.getCategorieCatalogue().isEmpty());
	}
	
	/**
	 * On verifie si on a bien le bon nombre de categories qui a ete genere
	 */
	@Test
	void testTailleCatalogue() {
		categorieTest.initialiserCategorie();
		assertTrue(categorieTest.getCategorieCatalogue().size() == 4);
	}
	
	/**
	 * On verifie si la categorie ENGINE est bien presente dans la Map
	 */
	@Test
	void testCleExistante() {
		categorieTest.initialiserCategorie();
		assertTrue(categorieTest.getCategorie("ENGINE") != null);
	}
}
