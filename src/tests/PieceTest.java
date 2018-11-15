package tests;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

import classes.piece.Piece;

class PieceTest {

	@Test
	void taille_du_catalogue_Piece() {
		Piece pieces = new Piece();
		pieces.initialiserPiece();
		assertEquals(18, pieces.pieceCatalogue.size());
	}
	
	@Test
	void piece_TA5_est_automatic_5_gears() {
		Piece pieces = new Piece();
		pieces.initialiserPiece();
		assertEquals("Automatic 5 gears", pieces.pieceCatalogue.get("TA5"));
	}

}
