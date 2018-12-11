package tests.LehanBourhis;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import classes.piece.Piece;
import classes.piece.TypePiece;
import classes.piece.Piece.Couleur;
import exceptions.ParametreIncorrectException;
import exceptions.ResultatIncorrectException;

/**
 * 
 * @author Yann & Mathieu
 *
 */
class TypePieceTest {
	
	/**
	 * Au lancement de la classe de test, va initialiser toutes les pieces
	 * @throws ParametreIncorrectException
	 * @throws ResultatIncorrectException
	 */
	@BeforeAll
	static void init() throws ResultatIncorrectException, ParametreIncorrectException  {
		TypePiece.initialiserPieces();
	}
	
	/**
	 * Test verifiant si la methode initialiserPieces fonctionne comme attendu
	 * On verifie apres execution si toutes les pieces ont etees generees
	 * @throws ResultatIncorrectException 
	 */
	@Test
	public void testCataloguePieces() throws ResultatIncorrectException  {
		
		assertEquals(18, TypePiece.getPieces().size());
		assertFalse(TypePiece.getPieces().isEmpty());
		
		assertThrows(ResultatIncorrectException.class, 
				() -> TypePiece.getPieces().contains(TypePiece.chercherPieceParNom("A")));
		assertTrue(TypePiece.getPieces().contains(TypePiece.chercherPieceParNom("XM")));
	}
	
	/**
	 * On verifie si on obtient bien la bonne description, les bonnes incompatibilites et les 
	 * bonnes necessites a partir du nom d'une piece
	 * @throws ResultatIncorrectException 
	 */
	@Test
	public void testGetter() throws ResultatIncorrectException {
		Piece TA5 = TypePiece.chercherPieceParNom("TA5");
		
		assertEquals("Automatic 5 gears", TA5.getDescription());
		assertEquals("TA5", TA5.getNom());
	
		HashSet<Piece> incompatibiliteSouhaitee = new HashSet<>();
		incompatibiliteSouhaitee.addAll( Arrays.asList(TypePiece.chercherPieceParNom("EG100")));
		assertEquals(incompatibiliteSouhaitee, TA5.getIncompatibilites());
		
		HashSet<Piece> necessiteSouhaitee = new HashSet<>();
		assertEquals(necessiteSouhaitee, TA5.getNecessites());
	}
	
	/**
	 * On doit pouvoir recuperer une propriete d'une piece par son nom, et obtenir les valeurs de cette propriete
	 * @throws ResultatIncorrectException
	 */
	@Test
	void testXS() throws ResultatIncorrectException{
		System.out.println(TypePiece.chercherPieceParNom("XS").getNom());
		System.out.println(TypePiece.chercherPieceParNom("XS").getPropriete("couleur").get());
		TypePiece.chercherPieceParNom("XS").setPropriete("couleur", Couleur.BLEU.name());
		System.out.println(TypePiece.chercherPieceParNom("XS").getPropriete("couleur").get());
		System.out.println(TypePiece.chercherPieceParNom("XS").getClass().getSuperclass().getSimpleName());
	}
	
}