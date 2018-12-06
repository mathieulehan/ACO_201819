package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import classes.categorie.Categorie;
import exceptions.ParametreIncorrectException;
import exceptions.ResultatIncorrectException;

/**
 * 
 * @author GR4
 *
 */
class CategorieTest {


	/**
	 * Initialise toutes les categories et leurs pieces pour tous les tests
	 * @throws ParametreIncorrectException
	 * @throws ResultatIncorrectException
	 */
	@BeforeAll
	static void init() throws ParametreIncorrectException, ResultatIncorrectException {
		Categorie.initialiserCategories();
	}
	
	/**
	 * On verifie si l'initialisation de la Map<Categories, List<String>> categorieCatalogue
	 * se deroule comme prevu. (des entrees sont-elles ajoutees ?)
	 * @throws ResultatIncorrectException 
	 */
	@Test
	void testInitialisation() throws ResultatIncorrectException{
		assertTrue(!Categorie.getCategorieCatalogue().isEmpty());
	}
	
	/**
	 * On verifie si on a bien le bon nombre de categories qui a ete genere
	 * @throws ResultatIncorrectException 
	 */
	@Test
	void testTailleCatalogue() throws ResultatIncorrectException {
		assertTrue(Categorie.getCategorieCatalogue().size() == 4);
	}
	
	/**
	 * On verifie si la categorie ENGINE est bien presente dans la Map
	 * @throws ResultatIncorrectException 
	 * @throws ParametreIncorrectException 
	 */
	@Test
	void testCleExistante() throws ParametreIncorrectException, ResultatIncorrectException {
		assertTrue(Categorie.getCategories() != null);
	}
}
