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
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@BeforeAll
	static void init() throws ResultatIncorrectException, ParametreIncorrectException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Categorie.initialiserCategories();
	}
	
	/**
	 * Catalogue de categorie non null
	 * Taille catalogue de categorie == 4
	 */
	@Test
	public void testVerifierCatalogueCategorie() {
		assertFalse(Categorie.getCategorieCatalogue().isEmpty());
		assertTrue(Categorie.getCategorieCatalogue().size() == 4);
	}
	
	/**
	 * Categorie ENGINE presente dans la Map
	 * Categorie TRIM (enjoliveur) non presente dans la Map
	 */
	@Test
	public void testGetCategories() {
		assertTrue(Categorie.getCategories().contains("INTERIOR"));
		assertFalse(Categorie.getCategories().contains("TRIM"));
	}
	
	/**
	 * Recuperer les pieces en fonction de la categorie
	 * @throws ResultatIncorrectException
	 * @throws ParametreIncorrectException
	 */
	@Test
	public void testGetPiecesParCategorie() throws ResultatIncorrectException, ParametreIncorrectException {
		LinkedList<Piece> piecesAttendues = new LinkedList<Piece>(Arrays.asList(TypePiece.chercherPieceParNom("EG100"),
																				TypePiece.chercherPieceParNom("EG133"), 
																				TypePiece.chercherPieceParNom("EG210"),
																				TypePiece.chercherPieceParNom("ED110"),
																				TypePiece.chercherPieceParNom("ED180"), 
																				TypePiece.chercherPieceParNom("EH120")));
	
		assertEquals(piecesAttendues, Categorie.getPiecesParCategorie("ENGINE"));
	}
	
	@Test
	public void testExpectedException() {
		assertThrows(ParametreIncorrectException.class,
				() -> Categorie.getPiecesParCategorie("TRIM"));
	}
}
