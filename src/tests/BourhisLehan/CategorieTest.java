package tests.BourhisLehan;

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
	 * Lance l'initialisation des categories et de leurs pieces au lancement de la classe de test
	 * @throws ParametreIncorrectException
	 * @throws ResultatIncorrectException
	 */
	@BeforeAll
	static void init() throws ResultatIncorrectException, ParametreIncorrectException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Categorie.initialiserCategories();
	}
	
	/**
	 * Catalogue de categorie non vide
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
		assertTrue(Categorie.getCategories().contains("ENGINE"));
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
	
	/**
	 * On tente de recuperer les pieces d'une categorie qui n'existe pas
	 */
	@Test
	public void testExpectedException() {
		assertThrows(ParametreIncorrectException.class,
				() -> Categorie.getPiecesParCategorie("TRIM"));
	}
}
