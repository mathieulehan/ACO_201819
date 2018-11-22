package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import classes.categorie.Categorie;
import classes.categorie.Categorie.Categories;

class CategorieTest {

	Categorie categorieTest = new Categorie();
			
	@Test
	void testInitialisation(){
		categorieTest.initialiserCategorie();
		assertTrue(!categorieTest.getCategorieCatalogue().isEmpty());
	}
	
	@Test
	void testTailleCatalogue() {
		categorieTest.initialiserCategorie();
		assertTrue(categorieTest.getCategorieCatalogue().size() == 4);
	}
	
	@Test
	void testCleExistante() {
		categorieTest.initialiserCategorie();
		assertTrue(categorieTest.getCategorie(Categories.ENGINE) != null);
	}

	
}
