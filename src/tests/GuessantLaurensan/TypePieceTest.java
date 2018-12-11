package tests.GuessantLaurensan;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
	static Piece XM;
	
	/**
	 * Initialise les pieces pour tous les tests
	 * @throws ParametreIncorrectException
	 * @throws ResultatIncorrectException
	 */
	@BeforeAll
	public static void init() throws ResultatIncorrectException, ParametreIncorrectException  {
		TypePiece.initialiserPieces();
		XM = TypePiece.chercherPieceParNom("XM");
		
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
	public void attribut_piece_XM() throws ResultatIncorrectException {
		
		assertEquals("Metallic paint", XM.getDescription());
		assertEquals("XM", XM.getNom());
	}
	
	/**
	 * On verifie si XM a pour incompatibilite EG100
	 * @throws ResultatIncorrectException
	 */
	@Test
	public void getIncompatibilites_XM() throws ResultatIncorrectException {
		
		HashSet<Piece> incompatibiliteSouhaitee = new HashSet<>();
		incompatibiliteSouhaitee.add(TypePiece.chercherPieceParNom("EG100"));
		assertEquals(incompatibiliteSouhaitee, XM.getIncompatibilites());
		
	}
	
	/**
	 * On regarde si XM a des necessites, ce qui n'est pas le cas ici
	 * @throws ResultatIncorrectException 
	 */
	@Test
	public void getNecessite_XM() throws ResultatIncorrectException {
		
		HashSet<Piece> necessiteSouhaitee = new HashSet<>();
		assertEquals(necessiteSouhaitee, XM.getNecessites());
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
		
		assertTrue(TypePiece.getPieces().contains(XM));
	}
}