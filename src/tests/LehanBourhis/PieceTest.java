package tests.LehanBourhis;

import static org.junit.jupiter.api.Assertions.*;

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

	@Test
	public void testSetterGetter() throws ParametreIncorrectException {
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
	public void testAjoutIncompatibilites() throws ParametreIncorrectException {
		Piece pieceX = new Piece("X", "Description X");
		assertTrue(pieceX.getIncompatibilites().size() == 0);
		
		Piece pieceA = new Piece("A", "Description A");
		pieceX.ajoutIncompatibilite(pieceA);
		assertTrue(pieceX.getIncompatibilites().size() == 1);
		assertTrue(pieceX.getIncompatibilites().contains(pieceA));
		
		Piece pieceB = new Piece("B", "Description B");
		pieceX.ajoutIncompatibilite(pieceB);
		assertTrue(pieceX.getIncompatibilites().size() == 2);
		assertTrue(pieceX.getIncompatibilites().contains(pieceA) 
				&& (pieceX.getIncompatibilites().contains(pieceB)));
	}
	
	/**	 
	 * Ajout d'une necessite pour une piece
	 * @throws ParametreIncorrectException
	 */
	@Test
	public void testAjoutNecessites() throws ParametreIncorrectException {
		Piece pieceX = new Piece("X", "Description X");
		assertTrue(pieceX.getNecessites().size() == 0);
		
		Piece pieceA = new Piece("A", "Description A");
		pieceX.ajoutNecessite(pieceA);
		assertTrue(pieceX.getNecessites().size() == 1);
		assertTrue(pieceX.getNecessites().contains(pieceA));
		
		Piece pieceB = new Piece("B", "Description B");
		pieceX.ajoutNecessite(pieceB);
		assertTrue(pieceX.getNecessites().size() == 2);
		assertTrue((pieceX.getNecessites().contains(pieceA) 
				&& (pieceX.getNecessites().contains(pieceB))));
	}
	
	/**
	 * Suppression d'une incompatibilite pour une piece
	 * @throws ParametreIncorrectException 
	 */
	@Test
	public void testSupressionIncompatibilites() throws ParametreIncorrectException {
		Piece pieceX = new Piece("X", "Description X");
		assertTrue(pieceX.getIncompatibilites().size() == 0);
		
		Piece pieceA = new Piece("A", "Description A");
		pieceX.ajoutIncompatibilite(pieceA);
		assertTrue(pieceX.getIncompatibilites().size() == 1);
		
		pieceX.suppressionIncompatibilite(pieceA);
		assertTrue(pieceX.getIncompatibilites().size() == 0);
	}
	
	/**
	 * Suppression d'une incompatibilite pour une piece
	 * @throws ParametreIncorrectException 
	 */
	@Test
	public void testSuppressionNecessites() throws ParametreIncorrectException {
		Piece pieceX = new Piece("X", "Description X");
		assertTrue(pieceX.getNecessites().size() == 0);
		
		Piece pieceA = new Piece("A", "Description A");
		pieceX.ajoutNecessite(pieceA);
		assertTrue(pieceX.getNecessites().size() == 1);
		
		pieceX.suppressionNecessite(pieceA);
		assertTrue(pieceX.getNecessites().size() == 0);
	}
	
	/**
	 * Verification de la methode permettant de savoir si deux pieces sont incompatibles entre elles
	 * @throws ResultatIncorrectException 
	 */
	@Test
	public void testPiecesIncompatibles() throws ResultatIncorrectException {
		assertTrue(TypePiece.chercherPieceParNom("EG100").estIncompatible(TypePiece.chercherPieceParNom("TSF7")));
		assertFalse(TypePiece.chercherPieceParNom("EG100").estIncompatible(TypePiece.chercherPieceParNom("TM5")));
		assertFalse(TypePiece.chercherPieceParNom("TSF7").estIncompatible(TypePiece.chercherPieceParNom("TM5")));		
	}	
	
	@Test
	public void getProprietes() throws ResultatIncorrectException {
		Piece TA5 = TypePiece.chercherPieceParNom("TA5");
		
		HashSet<String> valeurProprieteAttendu = new HashSet<>();
		valeurProprieteAttendu.addAll(Arrays.asList("white", "blue", "red"));
		assertEquals(valeurProprieteAttendu, TA5.getValeursProprietePossibles("couleur"));

		HashSet<String> nomProprieteAttendu = new HashSet<>();
		nomProprieteAttendu.add("couleur");
		assertEquals(nomProprieteAttendu, TA5.getNomsProprietes());
		
		String couleurInitiale = new String("white");
		assertEquals(couleurInitiale, TA5.getPropriete("couleur").get());
		
		TypePiece.chercherPieceParNom("TA5").setPropriete("couleur", "red");
		String couleurModifiee = new String("red");
		assertEquals(couleurModifiee, TA5.getPropriete("couleur").get());
		
		TypePiece.chercherPieceParNom("TA5").setPropriete("couleur", "blue");
		String couleurModifiee2 = new String("blue");
		assertEquals(couleurModifiee2, TA5.getPropriete("couleur").get());
		
		assertEquals(valeurProprieteAttendu, TA5.getValeursProprietePossibles("couleur"));
	}

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

}
