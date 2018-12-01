package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import classes.categorie.Categorie;
import classes.config.ConfigVoiture;
import classes.piece.TypePiece;
import exceptions.ActionPieceInvalideException;
import exceptions.ParametreNullException;
import exceptions.ResultatNullException;

class ConfigVoitureTest {

	/**
	 * Ajout de pieces dans notre configuration, conditions a verifier :
	 * - une piece par categorie
	 * - les incompatibilites
	 * - ajout automatique des pieces necessaires 
	 * @throws ActionPieceInvalideException
	 * @throws ParametreNullException
	 * @throws ResultatNullException
	 */
	@Test
	void ajout_de_pieces() throws ParametreNullException, ResultatNullException, ActionPieceInvalideException {
		ConfigVoiture cv = new ConfigVoiture();
		Categorie.initialiserCategories();
		
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
	 * @throws ParametreNullException
	 * @throws ResultatNullException
	 */
	@Test
	void suppression_pieces() throws ActionPieceInvalideException, ParametreNullException, ResultatNullException {
		ConfigVoiture cv = new ConfigVoiture();
		Categorie.initialiserCategories();
		
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
	 * @throws ResultatNullException
	 * @throws ParametreNullException
	 * @throws ActionPieceInvalideException
	 */
	@Test
	public void verification_categories() throws ResultatNullException, ParametreNullException, ActionPieceInvalideException {
		ConfigVoiture cv = new ConfigVoiture();
		Categorie.initialiserCategories();
		
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
	 * @throws ParametreNullException
	 * @throws ResultatNullException
	 * @throws ActionPieceInvalideException
	 */
	@Test
	public void categories_en_fonction_pieces_de_ma_configuration() throws ParametreNullException, ResultatNullException, ActionPieceInvalideException {
		ConfigVoiture cv = new ConfigVoiture();
		Categorie.initialiserCategories();
		assertTrue(cv.ajouterPiece("TSF7"));

		assertEquals(TypePiece.chercherPieceParNom("TSF7"), cv.getPieceParCategorie("TRANSMISSION"));
		assertThrows(ResultatNullException.class, 
				() -> TypePiece.chercherPieceParNom("PieceLambda").equals(cv.getPieceParCategorie("TRANSMISSION")));
	}
	
	/**
	 * Recherche des pieces encore disponibles pour notre configuration
	 * @throws ActionPieceInvalideException
	 * @throws ParametreNullException
	 * @throws ResultatNullException
	 */
	@Test
	public void pieces_disponibles() throws ActionPieceInvalideException, ParametreNullException, ResultatNullException {
		ConfigVoiture cv = new ConfigVoiture();
		Categorie.initialiserCategories();
		
		assertTrue(cv.ajouterPiece("XC"));
		assertTrue(cv.ajouterPiece("TM5"));
		assertTrue(cv.ajouterPiece("ED110"));
		assertTrue(cv.getPiecesPossibles().contains(TypePiece.chercherPieceParNom("IN")));
		assertFalse(cv.getPiecesPossibles().contains(TypePiece.chercherPieceParNom("IS")));
	}

}
