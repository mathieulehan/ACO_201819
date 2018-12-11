package tests.LehanBourhis;

import static org.junit.jupiter.api.Assertions.*;

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
class PieceTest {
	
	/**
	 * Initialise toutes les categories et leurs pieces pour tous les tests
	 * @throws ParametreIncorrectException
	 * @throws ResultatIncorrectException
	 */
	@BeforeAll
	static void init() throws ParametreIncorrectException, ResultatIncorrectException {
		TypePiece.initialiserPieces();
	}

	/**
	 * Erreur de constructeur dans le cas ou :
	 * - Nom de piece ou description null 	-> NullPointerException
	 * - Nom de piece egal a "" 			-> ParametreIncorrectException
	 * - Nom de piece deja utilisee 		-> ParametreIncorrectException
	 * @throws ParametreIncorrectException 
	 */
	@Test
	public void testExpectedException() throws ParametreIncorrectException {

		Piece pieceX = new Piece("X", "Description X");
		
		assertThrows(NullPointerException.class, 
				() -> {new Piece(null, "Description X");});
		assertThrows(ParametreIncorrectException.class, 
				() -> {new Piece("", "Description X");});
		assertThrows(ParametreIncorrectException.class, 
				() -> {new Piece("EG100", "Description X");});
		assertThrows(NullPointerException.class, 
				() -> {new Piece("X", null);});
		

		assertThrows(NullPointerException.class, 
				() -> {pieceX.setNom(null);});
		assertThrows(ParametreIncorrectException.class, 
				() -> {pieceX.setNom("");});
		assertThrows(ParametreIncorrectException.class, 
				() -> {pieceX.setNom("IS");});
		

		assertThrows(NullPointerException.class, 
				() -> {pieceX.setDescription(null);});
		

		assertThrows(NullPointerException.class, 
				() -> pieceX.setIncompatibilites(null));
		
		Piece incompatibilite = new Piece("Inc", "Description Inc");
		pieceX.ajoutIncompatibilite(incompatibilite);
		assertThrows(ParametreIncorrectException.class,
				() -> pieceX.ajoutIncompatibilite(incompatibilite)); // Piece deja ajoutee, donc elle ne s'ajoute pas
		assertThrows(NullPointerException.class, 
				() -> {pieceX.ajoutIncompatibilite(null);}); 

		Piece necessite = new Piece("Nec", "Description Nec");
		pieceX.ajoutNecessite(necessite);
		assertThrows(ParametreIncorrectException.class,
				() -> pieceX.ajoutNecessite(necessite)); // Piece deja ajoutee, donc elle ne s'ajoute pas
		assertThrows(NullPointerException.class, 
				() -> {pieceX.ajoutNecessite(null);});
	}

	@Test
	public void testSetterCorrect() throws ParametreIncorrectException {
		Piece pieceX = new Piece("X", "Description X");
		assertEquals(pieceX.getNom(), "X");
		assertEquals(pieceX.getDescription(), "Description X");

		pieceX.setNom("Nouveau nom");
		assertEquals(pieceX.getNom(), "Nouveau nom");
		
		pieceX.setDescription("Nouvelle description");
		assertEquals(pieceX.getDescription(), "Nouvelle description");

		assertTrue(pieceX.getIncompatibilites().size() == 0);
		pieceX.setIncompatibilites(new HashSet<>());
		assertEquals(pieceX.getIncompatibilites(), new HashSet<>());
		assertTrue(pieceX.getIncompatibilites().size() == 0);
	
		assertTrue(pieceX.getNecessites().size() == 0);
		pieceX.setNecessites(new HashSet<>());
		assertEquals(pieceX.getNecessites(), new HashSet<>());
		assertTrue(pieceX.getNecessites().size() == 0);
	}
	
	/**
	 * Ajout d'une incompatibilite pour une piece
	 * @throws ParametreIncorrectException 
	 */
	@Test
	public void getAjoutNecessite() throws ParametreIncorrectException {
		Piece pieceX = new Piece("X", "Description X");
		assertTrue(pieceX.getIncompatibilites().size() == 0);
		
		Piece incompatibilite = new Piece("Inc", "Description Inc");
		pieceX.ajoutIncompatibilite(incompatibilite);
		assertTrue(pieceX.getIncompatibilites().size() == 1);
		assertTrue(pieceX.getIncompatibilites().contains(incompatibilite));
		
		Piece incompatibilite2 = new Piece("Inc2", "Description Inc");
		pieceX.ajoutIncompatibilite(incompatibilite2);
		assertTrue(pieceX.getIncompatibilites().size() == 2);
		assertTrue(pieceX.getIncompatibilites().contains(incompatibilite) 
				&& (pieceX.getIncompatibilites().contains(incompatibilite2)));
	}
	
	/**	 
	 * Ajout d'une necessite pour une piece
	 * @throws ParametreIncorrectException
	 */
	@Test
	public void testAjoutNecessites() throws ParametreIncorrectException {
		Piece pieceX = new Piece("X", "Description X");
		assertTrue(pieceX.getNecessites().size() == 0);
		
		Piece necessite = new Piece("Nec", "Description Nec");
		pieceX.ajoutNecessite(necessite);
		assertTrue(pieceX.getNecessites().size() == 1);
		assertTrue(pieceX.getNecessites().contains(necessite));
		
		Piece necessite2 = new Piece("Nec2", "Description Nec");
		pieceX.ajoutNecessite(necessite2);
		assertTrue(pieceX.getNecessites().size() == 2);
		assertTrue((pieceX.getNecessites().contains(necessite) 
				&& (pieceX.getNecessites().contains(necessite2))));
	}
	
	/**
	 * Suppression d'une incompatibilite pour une piece
	 * @throws ParametreIncorrectException 
	 */
	@Test
	public void testGetIncompatibilites() throws ParametreIncorrectException {
		Piece pieceX = new Piece("X", "Description X");
		assertTrue(pieceX.getIncompatibilites().size() == 0);
		
		Piece incompatibilite = new Piece("Inc", "Description Inc");
		pieceX.ajoutIncompatibilite(incompatibilite);
		assertTrue(pieceX.getIncompatibilites().size() == 1);
		
		pieceX.suppressionIncompatibilite(incompatibilite);
		assertTrue(pieceX.getIncompatibilites().size() == 0);
	}
	
	/**
	 * Suppression d'une incompatibilite pour une piece
	 * @throws ParametreIncorrectException 
	 */
	@Test
	public void testGetNecessites() throws ParametreIncorrectException {
		Piece pieceX = new Piece("X", "Description X");
		assertTrue(pieceX.getNecessites().size() == 0);
		
		Piece necessite = new Piece("Nec", "Description Nec");
		pieceX.ajoutNecessite(necessite);
		assertTrue(pieceX.getNecessites().size() == 1);
		
		pieceX.suppressionNecessite(necessite);
		assertTrue(pieceX.getNecessites().size() == 0);
	}
	
	/**
	 * Verification de la methode permettant de savoir si deux pieces sont incompatibles entre elles
	 * @throws ResultatIncorrectException 
	 */
	@Test
	public void testPiecesIncompatibles() throws ResultatIncorrectException {
		Piece EG100 = TypePiece.chercherPieceParNom("EG100");
		Piece TSF7 = TypePiece.chercherPieceParNom("TSF7");
		Piece TM5 = TypePiece.chercherPieceParNom("TM5");
		
		assertTrue(EG100.estIncompatible(TSF7));
		assertFalse(EG100.estIncompatible(TM5));
		assertFalse(TSF7.estIncompatible(TM5));		
	}
}
