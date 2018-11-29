package tests;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import classes.piece.TypePiece;
import exceptions.ParametreNullException;
import exceptions.ResultatNullException;

class TypePieceTest {

	TypePiece pieces;
	
	/**
	 * Initialisation pour chaque test d'un object TypePiece et de son initialisation
	 * @throws ParametreNullException
	 * @throws ResultatNullException
	 */
	@BeforeEach
	void init () throws ParametreNullException, ResultatNullException {
		TypePiece.initialiserPieces();
	}
	
	/**
	 * Test verifiant si la methode initialiserPieces fonctionne comme attendu
	 * On verifie apres execution si toutes les pieces ont etees generees
	 */
	@Test
	void taille_du_catalogue_Piece() {
		assertEquals(18, TypePiece.getPieces().size());
		assertFalse(TypePiece.getPieces().isEmpty());
	}
	
	/**
	 * On verifie si on obtient bien la bonne description a partir du nom d'une piece
	 * @throws ResultatNullException
	 */
	@Test
	void piece_TA5_est_automatic_5_gears() throws ResultatNullException {
		assertEquals("Automatic 5 gears", TypePiece.chercherPieceParNom("TA5").getDescription());
	}
	
	/**
	 * Si on cherche une piece qui n'existe pas, une exception est levee 
	 */
	@Test
	void chercher_piece_inexistance() {
		assertThrows(ResultatNullException.class, 
				() -> { TypePiece.getPieces().contains(TypePiece.chercherPieceParNom("INEXISTANTE")); });
	}

	/**
	 * Si on cherche une piece qui n'existe pas, on recoit un null 
	 * @throws ResultatNullException 
	 */
	@Test
	void chercher_piece_existance() throws ResultatNullException {
		assertTrue(TypePiece.getPieces().contains(TypePiece.chercherPieceParNom("XM")));
	}
}