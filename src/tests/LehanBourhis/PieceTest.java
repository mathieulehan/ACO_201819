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
	 * Au lancement de la classe de test, on initialise les categories et les pieces existantes
	 * @throws ParametreIncorrectException
	 * @throws ResultatIncorrectException
	 */
	@BeforeAll
	static void init() throws ParametreIncorrectException, ResultatIncorrectException {
		TypePiece.initialiserPieces();
	}

	/**
	 * Differentes verification de bon fonctionnement des getters et setters de la classe Piece
	 * @throws ParametreIncorrectException
	 */
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
	 * L'utilisateur ajoute une incompatibilite a une piece
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
	 * L'utilisateur ajoute une necessite pour une piece
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
	 * L'utilisateur supprime une incompatibilite pour une piece
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
	 * On doit pouvoir ajouter et supprimer une necessite pour une piece donnee
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
	 * La methode estIncompatible doit permettre de savoir si des pieces sont incompatibles entre elles
	 * @throws ResultatIncorrectException 
	 */
	@Test
	public void testPiecesIncompatibles() throws ResultatIncorrectException {
		assertTrue(TypePiece.chercherPieceParNom("EG100").estIncompatible(TypePiece.chercherPieceParNom("TSF7")));
		assertFalse(TypePiece.chercherPieceParNom("EG100").estIncompatible(TypePiece.chercherPieceParNom("TM5")));
		assertFalse(TypePiece.chercherPieceParNom("TSF7").estIncompatible(TypePiece.chercherPieceParNom("TM5")));		
	}	
	
	/**
	 * Pour une piece donnee, on doit pouvoir : 
	 * - connaitre les valeurs possibles d'une propriete
	 * - connaitre la couleur initiale
	 * - changer la valeur d'une propriete
	 * @throws ResultatIncorrectException
	 */
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

	/**
	 * On ne doit pas pouvoir : 
	 * - creer une piece avec une description nulle
	 * - crer une piece avec un nom null
	 * - creer une piece avec un nom vide
	 * - creer une piece avec un nom deja pris par une autre piece
	 * - changer le nom d'une piece a null
	 * - changer le nom d'une piece pour un nom vide
	 * - changer le nom d'une piece pour le nom d'une piece deja existante
	 * - changer la description d'une piece a null
	 * - changer les incompatibilite s d'une piece a nulls
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

}
