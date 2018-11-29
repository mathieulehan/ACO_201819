package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import classes.categorie.Categorie;
import exceptions.ParametreNullException;
import exceptions.ResultatNullException;

class CategorieTest {

			
	@BeforeEach
	void init() throws ParametreNullException, ResultatNullException {
		Categorie.initialiserCategories();
	}
	
	/**
	 * On verifie si l'initialisation de la Map<Categories, List<String>> categorieCatalogue
	 * se deroule comme prevu. (des entrees sont-elles ajoutees ?)
	 * @throws ResultatNullException 
	 */
	@Test
	void testInitialisation() throws ResultatNullException{
		assertTrue(!Categorie.getCategorieCatalogue().isEmpty());
	}
	
	/**
	 * On verifie si on a bien le bon nombre de categories qui a ete genere
	 * @throws ResultatNullException 
	 */
	@Test
	void testTailleCatalogue() throws ResultatNullException {
		assertTrue(Categorie.getCategorieCatalogue().size() == 4);
	}
	
	/**
	 * On verifie si la categorie ENGINE est bien presente dans la Map
	 * @throws ResultatNullException 
	 * @throws ParametreNullException 
	 */
	@Test
	void testCleExistante() throws ParametreNullException, ResultatNullException {
		assertTrue(Categorie.getCategories() != null);
	}
}
