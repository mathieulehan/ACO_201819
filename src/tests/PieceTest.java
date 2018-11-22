package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import classes.piece.Piece;
import exceptions.PasDIncompatibilitesException;

class PieceTest {

	/**
	 * On souhaite obtenir la categorie de la piece
	 */
	@Test
	void getCategorieExistante() {
		Piece pieceTest = new Piece("EG100", "Description de l'EG100");
		assertEquals("ENGINE", pieceTest.getCategorie());
	}
	
	/**
	 * On souhaite obtenir la categorie de la piece, mais elle n'en a pas
	 * la methode contains utilisee pour retrouver la categorie de la piece
	 * va donc renvoyer un {@link NullPointerException}
	 */
	@Test
	void getCategorieInexistante() {
		Piece pieceTest = new Piece("EG1000", "Description de la piece inexistante");
		Assertions.assertThrows(NullPointerException.class, () -> pieceTest.getCategorie());
	}

	/**
	 * Differents tests autour de l'ajout d'incompatibilites aux pieces
	 */
	@Test
	void ajoutIncompatibilites() {
		Piece pieceTest = new Piece("Piece", "Piece de test");
		assertTrue(pieceTest.getIncompatibilites().size() == 0);
		Piece incompatibilite = new Piece("Incompatibilite", "Incompatibilte de test");
		pieceTest.ajoutIncompatibilite(incompatibilite);
		assertTrue(pieceTest.getIncompatibilites().size() == 1);
		assertTrue(pieceTest.getIncompatibilites().contains(incompatibilite));
		Piece incompatibilite2 = new Piece("Deuxieme incompatibilite", "Incompatibilite de test");
		pieceTest.ajoutIncompatibilite(incompatibilite2);
		assertTrue(pieceTest.getIncompatibilites().size() == 2);
		assertTrue((pieceTest.getIncompatibilites().contains(incompatibilite) && (pieceTest.getIncompatibilites().contains(incompatibilite2))));
	}

	/**
	 * On verifie si la suppression d'une incompatibilite fonctionne
	 * @throws PasDIncompatibilitesException
	 */
	@Test
	void suppressionIncompatibilites() throws PasDIncompatibilitesException {
		Piece pieceTest = new Piece("Piece", "Piece de test");
		assertTrue(pieceTest.getIncompatibilites().size() == 0);
		Piece incompatibilite = new Piece("Incompatibilite", "Incompatibilite de test");
		pieceTest.ajoutIncompatibilite(incompatibilite);
		assertTrue(pieceTest.getIncompatibilites().size() == 1);
		pieceTest.suppressionIncompatibilite(incompatibilite);
		assertTrue(pieceTest.getIncompatibilites().size() == 0);
	}

	/**
	 * Differents tests autour de l'ajout de necessites aux pieces
	 */
	@Test
	void ajoutNecessites() {
		Piece pieceTest = new Piece("Piece", "Piece de test");
		assertTrue(pieceTest.getNecessites().size() == 0);
		Piece necessite = new Piece("Necessite", "Necessite de test");
		pieceTest.ajoutNecessite(necessite);
		assertTrue(pieceTest.getNecessites().size() == 1);
		assertTrue(pieceTest.getNecessites().contains(necessite));
		Piece necessite2 = new Piece("Deuxieme necessite", "Necessite de test");
		pieceTest.ajoutNecessite(necessite2);
		assertTrue(pieceTest.getNecessites().size() == 2);
		assertTrue((pieceTest.getNecessites().contains(necessite) && (pieceTest.getNecessites().contains(necessite2))));
	}

	/**
	 * On verifie que la suppression d'une necessite fonctionne
	 * @throws PasDIncompatibilitesException
	 */
	@Test
	void suppressionNecessites() throws PasDIncompatibilitesException {
		Piece pieceTest = new Piece("Piece", "Piece de test");
		assertTrue(pieceTest.getNecessites().size() == 0);
		Piece necessite = new Piece("Necessites", "Necessites de test");
		pieceTest.ajoutNecessite(necessite);
		assertTrue(pieceTest.getNecessites().size() == 1);
		pieceTest.suppressionNecessite(necessite);
		assertTrue(pieceTest.getNecessites().size() == 0);
	}
}
