package tests;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import classes.piece.Piece;
import classes.piece.TypePiece;
import exceptions.ParametreIncorrectException;
import exceptions.ResultatIncorrectException;

class TypePieceTest {
	
	/**
	 * Test verifiant si la methode initialiserPieces fonctionne comme attendu
	 * On verifie apres execution si toutes les pieces ont etees generees
	 * @throws ResultatIncorrectException 
	 * @throws ParametreIncorrectException 
	 */
	@Test
	void taille_du_catalogue_Piece() throws ParametreIncorrectException, ResultatIncorrectException {
		TypePiece.initialiserPieces();
		
		assertEquals(18, TypePiece.getPieces().size());
		assertFalse(TypePiece.getPieces().isEmpty());
	}
	
	/**
	 * On verifie si on obtient bien la bonne description a partir du nom d'une piece
	 * @throws ResultatIncorrectException
	 * @throws ParametreIncorrectException 
	 */
	@Test
	void attribut_piece_TA5() throws ResultatIncorrectException, ParametreIncorrectException {
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
	 * @throws ResultatIncorrectException 
	 * @throws ParametreIncorrectException 
	 */
	@Test
	void chercher_piece_inexistance() throws ParametreIncorrectException, ResultatIncorrectException {
		TypePiece.initialiserPieces();
		
		assertThrows(ResultatIncorrectException.class, 
				() -> TypePiece.getPieces().contains(TypePiece.chercherPieceParNom("INEXISTANTE")));
	}

	/**
	 * Si on cherche une piece qui n'existe pas, on recoit un null 
	 * @throws ResultatIncorrectException 
	 * @throws ParametreIncorrectException 
	 */
	@Test
	void chercher_piece_existance() throws ResultatIncorrectException, ParametreIncorrectException {
		TypePiece.initialiserPieces();
		
		assertTrue(TypePiece.getPieces().contains(TypePiece.chercherPieceParNom("XM")));
	}
	
	@Test
	void getIncompatibilites() throws ParametreNullException, ResultatNullException {
		System.out.println("Coin ____________________________");
		TypePiece.initialiserPieces();
		System.out.println("Couin");
		Piece maPiece;
		List<Piece> pieces = TypePiece.getPieces();
		for (Piece piece : pieces) {
			if(piece.getNom().equals("EG100")) {
				maPiece = piece;
			}
		}
		System.out.println("CoinCoinCoinCoin");
		System.out.println("CouinCouinCouinCouin");
//		Set<Piece> mesIncompatibilites = new HashSet();
//		mesIncompatibilites.add(TypePiece.chercherPieceParNom("TA5"));
//		mesIncompatibilites.add(TypePiece.chercherPieceParNom("TSF7"));
//		mesIncompatibilites.add(TypePiece.chercherPieceParNom("XM"));
//		mesIncompatibilites.add(TypePiece.chercherPieceParNom("XS"));
//		mesIncompatibilites.add(TypePiece.chercherPieceParNom("IS"));
		assertTrue(TypePiece.chercherPieceParNom("EG100").getNom() == "EG100");
	}
}