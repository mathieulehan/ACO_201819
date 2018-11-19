package tests;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
	
	@Test
	void get_piece_inexistance() {
		Piece pieces = new Piece();
		pieces.initialiserPiece();
		assertEquals(null, pieces.pieceCatalogue.get("INEXISTANTE"));
	}

	@Test
	void ajout_incompatibilite() {
		Piece piece = new Piece();
		String nomPiece = "Piece";
		String nouvelleIncompatibilite = "Nouvelle incomptabilite";
		LinkedList<String> listeVerif = new LinkedList<String>(Arrays.asList(nouvelleIncompatibilite));
		piece.ajoutIncompatibilite(nomPiece, nouvelleIncompatibilite);
		assertEquals(listeVerif, piece.pieceIncompatibilites.get(nomPiece));
	}
}
