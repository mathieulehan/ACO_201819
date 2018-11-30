package tests;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import classes.piece.Piece;
import classes.piece.TypePiece;
import exceptions.ParametreNullException;
import exceptions.ResultatNullException;

class TypePieceTest {
	
	/**
	 * Test verifiant si la methode initialiserPieces fonctionne comme attendu
	 * On verifie apres execution si toutes les pieces ont etees generees
	 * @throws ResultatNullException 
	 * @throws ParametreNullException 
	 */
	@Test
	void taille_du_catalogue_Piece() throws ParametreNullException, ResultatNullException {
		TypePiece.initialiserPieces();
		
		assertEquals(18, TypePiece.getPieces().size());
		assertFalse(TypePiece.getPieces().isEmpty());
	}
	
	/**
	 * On verifie si on obtient bien la bonne description a partir du nom d'une piece
	 * @throws ResultatNullException
	 * @throws ParametreNullException 
	 */
	@Test
	void attribut_piece_TA5() throws ResultatNullException, ParametreNullException {
		TypePiece.initialiserPieces();
		
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
	 * @throws ResultatNullException 
	 * @throws ParametreNullException 
	 */
	@Test
	void chercher_piece_inexistance() throws ParametreNullException, ResultatNullException {
		TypePiece.initialiserPieces();
		
		assertThrows(ResultatNullException.class, 
				() -> TypePiece.getPieces().contains(TypePiece.chercherPieceParNom("INEXISTANTE")));
	}

	/**
	 * Si on cherche une piece qui n'existe pas, on recoit un null 
	 * @throws ResultatNullException 
	 * @throws ParametreNullException 
	 */
	@Test
	void chercher_piece_existance() throws ResultatNullException, ParametreNullException {
		TypePiece.initialiserPieces();
		
		assertTrue(TypePiece.getPieces().contains(TypePiece.chercherPieceParNom("XM")));
	}
}