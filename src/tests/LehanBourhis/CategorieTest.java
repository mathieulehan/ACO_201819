package tests.LehanBourhis;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.LinkedList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import classes.categorie.Categorie;
import classes.piece.Piece;
import classes.piece.TypePiece;
import exceptions.ParametreIncorrectException;
import exceptions.ResultatIncorrectException;

/**
 * 
 * @author Yann & Mathieu
 *
 */
class CategorieTest {

	/**
	 * Initialise toutes les categories et leurs pieces pour tous les tests
	 * @throws ParametreIncorrectException
	 * @throws ResultatIncorrectException
	 */
	@BeforeAll
	public static void init() throws ResultatIncorrectException, ParametreIncorrectException {
		Categorie.initialiserCategories();
	}
	
	/**
	 * On verifie si l'initialisation de la Map<Categories, List<String>> categorieCatalogue se deroule comme prevu
	 * On verifie si on a bien le bon nombre de categories qui a ete genere
	 */
	@Test
	public void verifierCatalogueCategorie() {
		assertFalse(Categorie.getCategorieCatalogue().isEmpty());
		assertTrue(Categorie.getCategorieCatalogue().size() == 4);
	}
	
	/**
	 * On verifie si la categorie ENGINE est bien presente dans la Map
	 */
	@Test
	public void categorie_engine_existante() {
		assertTrue(Categorie.getCategories().contains("ENGINE"));
	}
	
	/**
	 * Categorie TRIM (enjoliveur) presente dans la Map
	 */
	@Test
	public void verifierCategorieInexitante() {
		assertFalse(Categorie.getCategories().contains("TRIM"));
	}
	
	/**
	 * Recuperer les pieces en fonction du categorie
	 * @throws ResultatIncorrectException
	 * @throws ParametreIncorrectException
	 */
	@Test
	public void getPiecesParCategorie() throws ResultatIncorrectException, ParametreIncorrectException {
		LinkedList<Piece> piecesAttendues = new LinkedList<Piece>(Arrays.asList(TypePiece.chercherPieceParNom("IC"),
																				TypePiece.chercherPieceParNom("IM"), 
																				TypePiece.chercherPieceParNom("IS")));
		assertEquals(piecesAttendues, Categorie.getPiecesParCategorie("INTERIOR"));
		
		assertThrows(ParametreIncorrectException.class,
				() -> Categorie.getPiecesParCategorie("TRIM"));
	}
}
