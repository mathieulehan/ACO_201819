package tests;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import classes.categorie.Categorie;
import classes.config.ConfigVoiture;
import classes.piece.Piece;
import classes.piece.TypePiece;
import exceptions.ParametreIncorrectException;
import exceptions.ResultatIncorrectException;

/**
 * 
 * @author YMCA
 *
 */
class TypePieceTest {
	
	ConfigVoiture cv = new ConfigVoiture();
	
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
	 * Reinitialise la configuration pour chaque test
	 * @throws ResultatIncorrectException
	 * @throws ParametreIncorrectException
	 */
	@BeforeEach
	private void init2() throws ResultatIncorrectException, ParametreIncorrectException {
		this.cv = new ConfigVoiture();
	}
	
	/**
	 * Test verifiant si la methode initialiserPieces fonctionne comme attendu
	 * On verifie apres execution si toutes les pieces ont etees generees
	 * @throws ResultatIncorrectException 
	 * @throws ParametreIncorrectException 
	 */
	@Test
	void taille_du_catalogue_Piece() throws ParametreIncorrectException, ResultatIncorrectException {
		
		assertEquals(18, TypePiece.getPieces().size());
		assertFalse(TypePiece.getPieces().isEmpty());
	}
	
	/**
	 * On verifie si on obtient bien la bonne description a partir du nom d'une piece
	 * @throws ResultatIncorrectException
	 * @throws ParametreIncorrectException 
	 */
	@Test
	void attribut_piece_TA5() throws ResultatIncorrectException, ParametreIncorrectException {
		
		assertEquals("Automatic 5 gears", TypePiece.chercherPieceParNom("TA5").getDescription());
		assertEquals("TA5", TypePiece.chercherPieceParNom("TA5").getNom());
		
		HashSet<Piece> incompatibiliteSouhaitee = new HashSet<>();
		incompatibiliteSouhaitee.addAll( Arrays.asList(TypePiece.chercherPieceParNom("EG100")));
		assertEquals(incompatibiliteSouhaitee, TypePiece.chercherPieceParNom("TA5").getIncompatibilites());
		
		HashSet<Piece> necessiteSouhaitee = new HashSet<>();
		assertEquals(necessiteSouhaitee, TypePiece.chercherPieceParNom("TA5").getNecessites());
	}
	
	/**
	 * Si on cherche une piece qui n'existe pas, une exception est levee 
	 * @throws ResultatIncorrectException 
	 * @throws ParametreIncorrectException 
	 */
	@Test
	void chercher_piece_inexistance() throws ParametreIncorrectException, ResultatIncorrectException {
		
		assertThrows(ResultatIncorrectException.class, 
				() -> TypePiece.getPieces().contains(TypePiece.chercherPieceParNom("INEXISTANTE")));
	}

	/**
	 * Si on cherche une piece qui n'existe pas, on recoit un null 
	 * @throws ResultatIncorrectException 
	 * @throws ParametreIncorrectException 
	 */
	@Test
	void chercher_piece_existance() throws ResultatIncorrectException, ParametreIncorrectException {
		
		assertTrue(TypePiece.getPieces().contains(TypePiece.chercherPieceParNom("XM")));
	}
}