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
	void ajout_de_pieces() throws ParametreIncorrectException, ResultatIncorrectException, ActionPieceInvalideException {
		
		assertTrue(this.cv.ajouterPiece("TM5"));
		assertTrue(this.cv.ajouterPiece("IN"));
		//assertNull(this.cv.ajouterPiece(""));
		assertEquals(2, this.cv.getConfiguration().size());

		assertThrows(NullPointerException.class,
				() -> this.cv.ajouterPiece(null) );
		assertThrows(ActionPieceInvalideException.class, 
				() -> { this.cv.ajouterPiece("IH"); }); // Incompatible avec IN
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
		
		assertTrue(this.cv.ajouterPiece("EG133"));
		assertTrue(this.cv.ajouterPiece("XS"));
		// Ajout de la piece IS car necessaire a la piece XS
		assertEquals(3, this.cv.getConfiguration().size());
		
		assertTrue(this.cv.supprimerPiece("IS"));
		// Suppression de la piece XS car necessaire a la piece IS
		assertEquals(1, this.cv.getConfiguration().size());
		assertThrows(NullPointerException.class,
				() -> this.cv.supprimerPiece(null) );
		assertThrows(ActionPieceInvalideException.class, 
				() -> this.cv.ajouterPiece("EH120")); // Incompatible avec EG133
		assertThrows(ActionPieceInvalideException.class, 
				() -> this.cv.supprimerPiece("EH120")); // Piece non ajoutee dans la configuration
		
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
		assertTrue(this.cv.ajouterPiece("EH120")); 
		// Ajout de la piece TC120 car necessaire a la piece EH120
		assertTrue(this.cv.ajouterPiece("XM"));
		HashSet<String> categoriesSouhaitees = new HashSet<>();
		categoriesSouhaitees.addAll( Arrays.asList("EXTERIOR", "TRANSMISSION", "ENGINE"));
		assertEquals(categoriesSouhaitees, this.cv.getMesCategories());
		
		HashSet<String> categoriesRestantes = new HashSet<>();
		categoriesRestantes.addAll( Arrays.asList("INTERIOR"));
		assertEquals(categoriesRestantes, this.cv.getCategoriesRestantes());
		
	}
	
	/**
	 * Recherche de la categorie d'une piece
	 * @throws ParametreIncorrectException
	 * @throws ResultatIncorrectException
	 * @throws ActionPieceInvalideException
	 */
	@Test
	public void categories_en_fonction_pieces_de_ma_configuration() throws ParametreIncorrectException, ResultatIncorrectException, ActionPieceInvalideException {
		
		assertTrue(this.cv.ajouterPiece("TSF7"));

		assertEquals(TypePiece.chercherPieceParNom("TSF7"), this.cv.getPieceParCategorie("TRANSMISSION"));
		assertThrows(ResultatIncorrectException.class, 
				() -> TypePiece.chercherPieceParNom("PieceLambda").equals(this.cv.getPieceParCategorie("TRANSMISSION")));
	}
	
	/**
	 * Recherche des pieces encore disponibles pour notre configuration
	 * @throws ActionPieceInvalideException
	 * @throws ParametreIncorrectException
	 * @throws ResultatIncorrectException
	 */
	@Test
	public void pieces_disponibles() throws ActionPieceInvalideException, ParametreIncorrectException, ResultatIncorrectException {
		
		assertTrue(this.cv.ajouterPiece("XC"));
		assertTrue(this.cv.ajouterPiece("TM5"));
		assertTrue(this.cv.ajouterPiece("ED110"));
		assertTrue(this.cv.getPiecesPossibles().contains(TypePiece.chercherPieceParNom("IN")));
		assertFalse(this.cv.getPiecesPossibles().contains(TypePiece.chercherPieceParNom("IS")));
	}

}
