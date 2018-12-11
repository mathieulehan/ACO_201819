package tests.GuessantLaurensan;

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
 * @author Charlotte & Thomas
 *
 */
class TypePieceTest {
	
	/**
	 * Initialise les pieces pour tous les tests
	 * @throws ParametreIncorrectException
	 * @throws ResultatIncorrectException
	 */
	@BeforeAll
	public static void init() throws ResultatIncorrectException, ParametreIncorrectException  {
		TypePiece.initialiserPieces();
	}
	
	/**
	 * Test verifiant si la methode initialiserPieces fonctionne comme attendu
	 * On verifie apres execution si toutes les pieces ont etees generees
	 */
	@Test
	public void taille_du_catalogue_piece()  {
		
		assertEquals(18, TypePiece.getPieces().size());
		assertFalse(TypePiece.getPieces().isEmpty());
	}
	
	/**
	 * On verifie si on obtient bien la bonne description a partir du nom d'une piece
	 * @throws ResultatIncorrectException 
	 */
	@Test
	public void attribut_piece_TA5() throws ResultatIncorrectException {
		
		assertEquals("Automatic 5 gears", TypePiece.chercherPieceParNom("TA5").getDescription());
		assertEquals("TA5", TypePiece.chercherPieceParNom("TA5").getNom());
	}
	
	/**
	 * On verifie si TA5 a pour incompatibilite EG100
	 * @throws ResultatIncorrectException
	 */
	@Test
	public void getIncompatibilites_TA5() throws ResultatIncorrectException {
		
		HashSet<Piece> incompatibiliteSouhaitee = new HashSet<>();
		incompatibiliteSouhaitee.addAll( Arrays.asList(TypePiece.chercherPieceParNom("EG100")));
		assertEquals(incompatibiliteSouhaitee, TypePiece.chercherPieceParNom("TA5").getIncompatibilites());
		
	}
	
	/**
	 * On regarde si TA5 a des necessites, ce qui n'est pas le cas ici
	 * @throws ResultatIncorrectException 
	 */
	@Test
	public void getNecessite_TA5() throws ResultatIncorrectException {
		
		HashSet<Piece> necessiteSouhaitee = new HashSet<>();
		assertEquals(necessiteSouhaitee, TypePiece.chercherPieceParNom("TA5").getNecessites());
	}
	
	/**
	 * Si on cherche une piece qui n'existe pas, ResultatIncorrectException est levee 
	 */
	@Test
	public void chercher_piece_inexistance() {
		
		assertThrows(ResultatIncorrectException.class, 
				() -> TypePiece.getPieces().contains(TypePiece.chercherPieceParNom("INEXISTANTE")));
	}

	/**
	 * Recherche une piece existante
	 * @throws ResultatIncorrectException 
	 */
	@Test
	public void chercher_piece_existance() throws ResultatIncorrectException {
		
		assertTrue(TypePiece.getPieces().contains(TypePiece.chercherPieceParNom("XM")));
	}
}