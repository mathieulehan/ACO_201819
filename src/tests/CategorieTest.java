package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import classes.categorie.Categorie;
import exceptions.ParametreIncorrectException;
import exceptions.ResultatIncorrectException;

/**
 * 
 * @author YMCA
 *
 */
class CategorieTest {

	/**
	 * Initialise toutes les categories et leurs pieces pour tous les tests
	 * @throws ParametreIncorrectException
	 * @throws ResultatIncorrectException
	 */
	@BeforeAll
	static void init() throws ResultatIncorrectException, ParametreIncorrectException {
		Categorie.initialiserCategories();
	}
	
	/**
	 * On verifie si l'initialisation de la Map<Categories, List<String>> categorieCatalogue se deroule comme prevu
	 */
	@Test
	void catalogue_categorie_non_vide() {
		assertFalse(Categorie.getCategorieCatalogue().isEmpty());
	}
	
	/**
	 * On verifie si on a bien le bon nombre de categories qui a ete genere
	 */
	@Test
	void taille_catalogue_categorie_valide() {
		assertTrue(Categorie.getCategorieCatalogue().size() == 4);
	}
	
	/**
	 * On verifie si la categorie ENGINE est bien presente dans la Map
	 */
	@Test
	void categorie_engine_existante() {
		assertTrue(Categorie.getCategories().contains("ENGINE"));
	}
	
	/**
	 * On verifie si la categorie WHEEL est bien presente dans la Map
	 */
	@Test
	void categorie_wheel_inexistante() {
		assertFalse(Categorie.getCategories().contains("WHEEL"));
	}
}
