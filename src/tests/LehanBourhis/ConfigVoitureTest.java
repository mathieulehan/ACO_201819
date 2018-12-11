package tests.LehanBourhis;

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

/**
 * 
 * @author Yann & Mathieu
 *
 */
class ConfigVoitureTest {

	ConfigVoiture cv;

	/**
	 * Initialise toutes les categories et leurs pieces pour tous les tests
	 * @throws ParametreIncorrectException
	 * @throws ResultatIncorrectException
	 */
	@BeforeAll
	static void init() throws ParametreIncorrectException, ResultatIncorrectException {
		Categorie.initialiserCategories();
	}

	/**
	 * Reinitialise la configuration pour chaque test
	 * @throws ResultatIncorrectException
	 * @throws ParametreIncorrectException
	 */
	@BeforeEach
	private void init2() throws ResultatIncorrectException, ParametreIncorrectException {
		this.cv = new ConfigVoiture();
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
	public void testAjouterPiece() throws ParametreIncorrectException, ResultatIncorrectException, ActionPieceInvalideException {

		assertTrue(this.cv.ajouterPiece("TSF7"));
		assertTrue(this.cv.ajouterPiece("IS"));
		assertEquals(3, this.cv.getConfiguration().size());
		 
		assertTrue(this.cv.getConfiguration().contains(TypePiece.chercherPieceParNom("TSF7")));
		assertTrue(this.cv.getConfiguration().contains(TypePiece.chercherPieceParNom("IS")));
		assertFalse(this.cv.getConfiguration().contains(TypePiece.chercherPieceParNom("IH")));
	}

	/**
	 * Ajouter la piece TM5 et voir les incompatibilites
	 * @throws ActionPieceInvalideException
	 * @throws ParametreIncorrectException
	 * @throws ResultatIncorrectException
	 */
	@Test
	public void testGetMesIncompatibilites() throws ActionPieceInvalideException, ResultatIncorrectException, ParametreIncorrectException {

		assertTrue(this.cv.ajouterPiece("TM5"));

		HashSet<Piece> incompatilibitesSouhaitees = new HashSet<>();
		incompatilibitesSouhaitees.add(TypePiece.chercherPieceParNom("IS"));
		assertEquals(incompatilibitesSouhaitees, this.cv.getMesIncompatibilites());
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
	public void testSupprimerPiece() throws ActionPieceInvalideException, ParametreIncorrectException, ResultatIncorrectException {

		assertTrue(this.cv.ajouterPiece("EG133"));
		assertEquals(1, this.cv.getConfiguration().size());

		assertTrue(this.cv.supprimerPiece("EG133"));
		assertEquals(0, this.cv.getConfiguration().size());

		assertTrue(this.cv.ajouterPiece("EH120"));
		assertEquals(2, this.cv.getConfiguration().size());

	}

	/**
	 * Apres une selection de pieces dans notre configuration, on souhaite recuperer :
	 * - les categories dans lesquelles aucune piece n'a ete choisie
	 * @throws ResultatIncorrectException
	 * @throws ParametreIncorrectException
	 * @throws ActionPieceInvalideException
	 */
	@Test
	public void testGetCategories() throws ResultatIncorrectException, ParametreIncorrectException, ActionPieceInvalideException {
		assertTrue(this.cv.ajouterPiece("EG100")); 
		assertTrue(this.cv.ajouterPiece("XC"));

		HashSet<String> categoriesRestantes = new HashSet<>();
		categoriesRestantes.addAll( Arrays.asList("TRANSMISSION", "INTERIOR"));
		assertEquals(categoriesRestantes, this.cv.getCategoriesRestantes());
		
		HashSet<String> categoriesSouhaitees = new HashSet<>();
		categoriesSouhaitees.addAll( Arrays.asList("EXTERIOR", "ENGINE"));
		assertEquals(categoriesSouhaitees, this.cv.getMesCategories());

		assertTrue(this.cv.supprimerPiece("EG100"));
		
		HashSet<String> categoriesRestantes2 = new HashSet<>();
		categoriesRestantes2.addAll( Arrays.asList("ENGINE", "TRANSMISSION", "INTERIOR"));
		assertEquals(categoriesRestantes2, this.cv.getCategoriesRestantes());
		
		HashSet<String> categoriesSouhaitees2 = new HashSet<>();
		categoriesSouhaitees2.addAll( Arrays.asList("EXTERIOR"));
		assertEquals(categoriesSouhaitees2, this.cv.getMesCategories());
	}

	/**
	 * Recherche de la categorie d'une piece
	 * @throws ParametreIncorrectException
	 * @throws ResultatIncorrectException
	 * @throws ActionPieceInvalideException
	 */
	@Test
	public void testGetPieceParCategorie() throws ParametreIncorrectException, ResultatIncorrectException, ActionPieceInvalideException {

		assertTrue(this.cv.ajouterPiece("EG100")); 
		assertTrue(this.cv.ajouterPiece("XC"));
		assertEquals(TypePiece.chercherPieceParNom("EG100"), this.cv.getPieceParCategorie("ENGINE"));
		assertEquals(TypePiece.chercherPieceParNom("XC"), this.cv.getPieceParCategorie("EXTERIOR"));
	}

	/**
	 * Recherche des pieces encore disponibles pour notre configuration
	 * @throws ActionPieceInvalideException
	 * @throws ParametreIncorrectException
	 * @throws ResultatIncorrectException
	 */
	@Test
	public void testGetPiecesPossibles() throws ActionPieceInvalideException, ParametreIncorrectException, ResultatIncorrectException {

		assertTrue(this.cv.ajouterPiece("XC"));
		assertTrue(this.cv.ajouterPiece("TSF7"));
		HashSet<Piece> piecesSouhaitees = new HashSet<>();
		piecesSouhaitees.addAll(Arrays.asList(TypePiece.chercherPieceParNom("IN"),
											TypePiece.chercherPieceParNom("IH"), 
											TypePiece.chercherPieceParNom("IS"),
											TypePiece.chercherPieceParNom("EH120"),
											TypePiece.chercherPieceParNom("ED180")));
		assertEquals(piecesSouhaitees, this.cv.getPiecesPossibles());
	}

	@Test
	public void testConfigurationComplete() throws ActionPieceInvalideException, ResultatIncorrectException, ParametreIncorrectException {

		assertTrue(this.cv.ajouterPiece("EG133"));
		assertTrue(this.cv.ajouterPiece("TA5"));
		assertFalse(this.cv.estComplet());
		
		assertTrue(this.cv.ajouterPiece("XS"));
		assertTrue(this.cv.estComplet());
	}
	
	@Test
	public void testExpectedException() throws ActionPieceInvalideException, ResultatIncorrectException, ParametreIncorrectException {
		assertTrue(this.cv.ajouterPiece("IS"));
		assertTrue(this.cv.ajouterPiece("EG133"));

		assertThrows(ResultatIncorrectException.class,
				() -> this.cv.ajouterPiece("") );
		assertThrows(NullPointerException.class,
				() -> this.cv.ajouterPiece(null) );
		assertThrows(ActionPieceInvalideException.class, 
				() -> this.cv.ajouterPiece("EH120"));
		
		assertThrows(NullPointerException.class,
				() -> this.cv.supprimerPiece(null) );
		assertThrows(ActionPieceInvalideException.class, 
				() -> this.cv.supprimerPiece("EH120"));
		
		assertThrows(ResultatIncorrectException.class,
				() -> this.cv.getPieceParCategorie("TRANSMISSION"));

		assertThrows(ResultatIncorrectException.class, 
				() -> TypePiece.chercherPieceParNom("X").equals(this.cv.getPieceParCategorie("TRANSMISSION")));		
	}

}
