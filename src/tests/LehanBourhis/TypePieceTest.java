package tests.LehanBourhis;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import classes.piece.Piece;
import classes.piece.TypePiece;
import exceptions.ParametreIncorrectException;
import exceptions.ResultatIncorrectException;

/**
 * 
 * @author Yann & Mathieu
 *
 */
class TypePieceTest {
	
	/**
	 * Initialise les pieces pour tous les tests
	 * @throws ParametreIncorrectException
	 * @throws ResultatIncorrectException
	 */
	@BeforeAll
	static void init() throws ResultatIncorrectException, ParametreIncorrectException  {
		TypePiece.initialiserPieces();
	}
	
	/**
	 * Test verifiant si la methode initialiserPieces fonctionne comme attendu
	 * On verifie apres execution si toutes les pieces ont etees generees
	 * @throws ResultatIncorrectException 
	 */
	@Test
	public void testCataloguePieces() throws ResultatIncorrectException  {
		
		assertEquals(18, TypePiece.getPieces().size());
		assertFalse(TypePiece.getPieces().isEmpty());
		
		assertThrows(ResultatIncorrectException.class, 
				() -> TypePiece.getPieces().contains(TypePiece.chercherPieceParNom("INEXISTANTE")));
		assertTrue(TypePiece.getPieces().contains(TypePiece.chercherPieceParNom("XM")));
	}
	
	/**
	 * On verifie si on obtient bien la bonne description a partir du nom d'une piece
	 * @throws ResultatIncorrectException 
	 */
	@Test
	public void testGetter() throws ResultatIncorrectException {
		Piece TA5 = TypePiece.chercherPieceParNom("TA5");
		
		assertEquals("Automatic 5 gears", TA5.getDescription());
		assertEquals("TA5", TA5.getNom());
	
		HashSet<Piece> incompatibiliteSouhaitee = new HashSet<>();
		incompatibiliteSouhaitee.addAll( Arrays.asList(TypePiece.chercherPieceParNom("EG100")));
		assertEquals(incompatibiliteSouhaitee, TA5.getIncompatibilites());
		
		HashSet<Piece> necessiteSouhaitee = new HashSet<>();
		assertEquals(necessiteSouhaitee, TA5.getNecessites());
	}
	
}