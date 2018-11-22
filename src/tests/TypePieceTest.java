package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import classes.piece.TypePiece;

class TypePieceTest {

	/**
	 * Test verifiant si la methode initialiserPieces fonctionne comme attendu
	 * On verifie apres execution si toutes les pieces ont etees generees
	 */
	@Test
	void taille_du_catalogue_Piece() {
		TypePiece pieces = new TypePiece();
		pieces.initialiserPieces();
		assertEquals(18, pieces.getPiecesDisponibles().size());
	}
	
	/**
	 * On verifie si on obtient bien la bonne description a partir du nom d'une piece
	 */
	@Test
	void piece_TA5_est_automatic_5_gears() {
		TypePiece pieces = new TypePiece();
		pieces.initialiserPieces();
		assertEquals("Automatic 5 gears", pieces.chercherPieceParNom("TA5").getDescription());
	}
	
	/**
	 * Si on cherche une piece qui n'existe pas, on recoit un null
	 */
	@Test
	void get_piece_inexistance() {
		TypePiece pieces = new TypePiece();
		pieces.initialiserPieces();
		assertEquals(null, pieces.chercherPieceParNom("INEXISTANTE"));
	}

}