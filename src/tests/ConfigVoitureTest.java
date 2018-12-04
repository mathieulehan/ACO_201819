package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import classes.categorie.Categorie;
import classes.config.ConfigVoiture;
import classes.piece.Piece;
import classes.piece.TypePiece;
import exceptions.ActionPieceInvalideException;
import exceptions.ParametreIncorrectException;
import exceptions.ResultatIncorrectException;

class ConfigVoitureTest {
	
	ConfigVoiture cv;
	
	@BeforeAll
	static void init() throws ParametreIncorrectException, ResultatIncorrectException {
		Categorie.initialiserCategories();
	}
	
	@BeforeEach
	private void init2() throws ResultatIncorrectException, ParametreIncorrectException {
		cv = new ConfigVoiture();
		System.out.println("COINCOINCOIN");
		for(Piece p: cv.getPiecesPossibles()) {
			System.out.println(p.getNom());
		}
	}

	/**
	 * Ajout de pieces dans notre configuration, conditions a verifier :
	 * - une piece par categorie
	 * - les incompatibilites
	 * - ajout automatique des pieces necessaires 
	 * @throws ActionPieceInvalideException
	 * @throws ParametreIncorrectException
	 * @throws ResultatIncorrectException
	 */
	@Test
	void ajout_de_pieces() throws ParametreIncorrectException, ResultatIncorrectException, ActionPieceInvalideException {
		
		assertTrue(cv.ajouterPiece("TM5"));
		assertTrue(cv.ajouterPiece("IN"));
		//assertNull(cv.ajouterPiece(""));
		assertEquals(2, cv.getConfiguration().size());

		assertThrows(NullPointerException.class,
				() -> cv.ajouterPiece(null) );
		assertThrows(ActionPieceInvalideException.class, 
				() -> { cv.ajouterPiece("IH"); }); // Incompatible avec IN
	}
	
	/**
	 * Suppression de pieces dans notre configuration, conditions a verifier : 
	 * - piece presente dans la configuration
	 * - suppression des pieces necessaires
	 * @throws ActionPieceInvalideException
	 * @throws ParametreIncorrectException
	 * @throws ResultatIncorrectException
	 */
	@Test
	void suppression_pieces() throws ActionPieceInvalideException, ParametreIncorrectException, ResultatIncorrectException {
		
		assertTrue(cv.ajouterPiece("EG133"));
		assertTrue(cv.ajouterPiece("XS"));
		// Ajout de la piece IS car necessaire a la piece XS
		assertEquals(3, cv.getConfiguration().size());
		
		assertTrue(cv.supprimerPiece("IS"));
		// Suppression de la piece XS car necessaire a la piece IS
		assertEquals(1, cv.getConfiguration().size());
		assertThrows(NullPointerException.class,
				() -> cv.supprimerPiece(null) );
		assertThrows(ActionPieceInvalideException.class, 
				() -> cv.ajouterPiece("EH120")); // Incompatible avec EG133
		assertThrows(ActionPieceInvalideException.class, 
				() -> cv.supprimerPiece("EH120")); // Piece non ajoutee dans la configuration
		
	}
	
	/**
	 * Apres une selection de pieces dans notre configuration, on souhaite recuperer :
	 * - Les categories ou une piece a ete choisie
	 * - les categories ou aucune piece n'a ete choisie
	 * @throws ResultatIncorrectException
	 * @throws ParametreIncorrectException
	 * @throws ActionPieceInvalideException
	 */
	@Test
	public void verification_categories() throws ResultatIncorrectException, ParametreIncorrectException, ActionPieceInvalideException {
		assertTrue(cv.ajouterPiece("EH120")); 
		// Ajout de la piece TC120 car necessaire a la piece EH120
		assertTrue(cv.ajouterPiece("XM"));
		HashSet<String> categoriesSouhaitees = new HashSet<>();
		categoriesSouhaitees.addAll( Arrays.asList("EXTERIOR", "TRANSMISSION", "ENGINE"));
		assertEquals(categoriesSouhaitees, cv.getMesCategories());
		
		HashSet<String> categoriesRestantes = new HashSet<>();
		categoriesRestantes.addAll( Arrays.asList("INTERIOR"));
		assertEquals(categoriesRestantes, cv.getCategoriesRestantes());
		
	}
	
	/**
	 * Recherche de la categorie d'une piece
	 * @throws ParametreIncorrectException
	 * @throws ResultatIncorrectException
	 * @throws ActionPieceInvalideException
	 */
	@Test
	public void categories_en_fonction_pieces_de_ma_configuration() throws ParametreIncorrectException, ResultatIncorrectException, ActionPieceInvalideException {
		
//		System.out.println("Aled");
//		int nbPiece = 0;
//		for(Piece p: cv.getPiecesPossibles()) {
//			System.out.println(p.getNom());
//			nbPiece++;
//		}
//		System.out.println(nbPiece);
//		
//		System.out.println("Mes incompatibilites :");
//		for(Piece p: cv.getMesIncompatibilites()) {
//			System.out.println(p.getNom());
//		}
//
//		System.out.println("Mes categories restantes :");
//		System.out.println(cv.getCategoriesRestantes());
//		System.out.println("Ma configuration actuelle :");
//		System.out.println(cv.getConfiguration());
		assertTrue(cv.ajouterPiece("TSF7"));

		assertEquals(TypePiece.chercherPieceParNom("TSF7"), cv.getPieceParCategorie("TRANSMISSION"));
		assertThrows(ResultatIncorrectException.class, 
				() -> TypePiece.chercherPieceParNom("PieceLambda").equals(cv.getPieceParCategorie("TRANSMISSION")));
	}
	
	/**
	 * Recherche des pieces encore disponibles pour notre configuration
	 * @throws ActionPieceInvalideException
	 * @throws ParametreIncorrectException
	 * @throws ResultatIncorrectException
	 */
	@Test
	public void pieces_disponibles() throws ActionPieceInvalideException, ParametreIncorrectException, ResultatIncorrectException {
		
		assertTrue(cv.ajouterPiece("XC"));
		assertTrue(cv.ajouterPiece("TM5"));
		assertTrue(cv.ajouterPiece("ED110"));
		assertTrue(cv.getPiecesPossibles().contains(TypePiece.chercherPieceParNom("IN")));
		assertFalse(cv.getPiecesPossibles().contains(TypePiece.chercherPieceParNom("IS")));
	}

}
