package tests.GuessantLaurensan;

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
 * @author Charlotte & Thomas
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
	public static void init() throws ResultatIncorrectException, ParametreIncorrectException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Categorie.initialiserCategories();
	}
	
	/**
	 * On verifie si l'initialisation de la Map<Categories, List<String>> categorieCatalogue se deroule comme prevu
	 */
	@Test
	public void catalogue_categorie_non_vide() {
		assertFalse(Categorie.getCategorieCatalogue().isEmpty());
	}
	
	/**
	 * On verifie si on a bien le bon nombre de categories qui a ete genere
	 */
	@Test
	public void taille_catalogue_categorie_valide() {
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
	 * On verifie si la categorie WHEEL est bien presente dans la Map
	 */
	@Test
	public void categorie_wheel_inexistante() {
		assertFalse(Categorie.getCategories().contains("WHEEL"));
	}
	
	/**
	 * Recuperer les pieces en fonction du categorie
	 * @throws ResultatIncorrectException
	 * @throws ParametreIncorrectException
	 */
	@Test
	public void recuperer_piece_par_categorie() throws ResultatIncorrectException, ParametreIncorrectException {
		assertThrows(ParametreIncorrectException.class,
				() -> Categorie.getPiecesParCategorie("WHEEL"));
		
		LinkedList<Piece> piecesAttendues = new LinkedList<Piece>(Arrays.asList(TypePiece.chercherPieceParNom("XC"),
																				TypePiece.chercherPieceParNom("XM"), 
																				TypePiece.chercherPieceParNom("XS")));
		assertEquals(piecesAttendues, Categorie.getPiecesParCategorie("EXTERIOR"));
		
	}
}
