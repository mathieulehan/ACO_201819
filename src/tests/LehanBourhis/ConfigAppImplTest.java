package tests.LehanBourhis;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import classes.categorie.Categorie;
import classes.config.ConfigAppImpl;
import classes.config.ConfigurationTest;
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
class ConfigAppImplTest {
	
	ConfigurationTest observable;
	ConfigAppImpl observer;

	@BeforeAll
	static void init() throws ParametreIncorrectException, ResultatIncorrectException {
		Categorie.initialiserCategories();
	}

	@BeforeEach
	private void init2() throws ResultatIncorrectException, ParametreIncorrectException {
		this.observable = new ConfigurationTest();
		this.observer = new ConfigAppImpl();
		this.observable.addObserver(observer);
	}
	
	
	/**
	 * L'utilsateur ajoute une piece dans sa configuration (ajout des pieces necessaires automatique)
	 * @throws ResultatIncorrectException
	 * @throws ActionPieceInvalideException
	 * @throws ParametreIncorrectException
	 */
	@Test
	public void testActionAjouterPiece() throws ResultatIncorrectException, ActionPieceInvalideException, ParametreIncorrectException {

		this.observable.actionAjouterPiece("XS");
		assertEquals(2, this.observable.actionGetConfiguration().size());
		
		this.observable.actionAjouterPiece("TA5");
		assertEquals(3, this.observable.actionGetConfiguration().size());
	}
	
	/**
	 * L'utilisateur supprime une piece de sa configuration (suppression des pieces necessaires automatique)
	 * @throws ResultatIncorrectException
	 * @throws ActionPieceInvalideException
	 * @throws ParametreIncorrectException
	 */
	@Test
	public void testActionSupressionPiece() throws ResultatIncorrectException, ActionPieceInvalideException, ParametreIncorrectException {

		this.observable.actionAjouterPiece("IS");
		this.observable.actionAjouterPiece("ED110");
		assertEquals(3, this.observable.actionGetConfiguration().size());

		this.observable.actionSupprimerPiece("IS");
		assertEquals(1, this.observable.actionGetConfiguration().size());

		HashSet<Piece> incompatilibitesSouhaitees = new HashSet<>();
		incompatilibitesSouhaitees.addAll(Arrays.asList(TypePiece.chercherPieceParNom("TSF7")));
		assertEquals(incompatilibitesSouhaitees, this.observable.actionGetPiecesIncompatibles());
	}	
	
	/**
	 * L'utilisateur souhaite voir les categories restantes dans sa configuration
	 * @throws ActionPieceInvalideException
	 * @throws ResultatIncorrectException
	 * @throws ParametreIncorrectException
	 */
	@Test
	public void testActionGetCategorieRestantes() throws ActionPieceInvalideException, ResultatIncorrectException, ParametreIncorrectException {
		
		HashSet<String> categoriesRestantes = new HashSet<>();
		categoriesRestantes.addAll(Arrays.asList("TRANSMISSION", "ENGINE", "INTERIOR", "EXTERIOR"));
		assertEquals(categoriesRestantes, this.observable.actionGetCategoriesRestantes());

		this.observable.actionAjouterPiece("TC120");
		
		HashSet<String> categoriesRestantes2 = new HashSet<>();
		categoriesRestantes2.addAll(Arrays.asList("INTERIOR", "EXTERIOR"));
		assertEquals(categoriesRestantes2, this.observable.actionGetCategoriesRestantes());
	}
	
	/**
	 * L'utilisateur souhaite instancier la configuration existant deja, y ajouter une nouvelle piece (impossible) 
	 * et valider cette configuration
	 * @throws ActionPieceInvalideException
	 * @throws ResultatIncorrectException
	 * @throws ParametreIncorrectException
	 */
	@Test
	public void testActionGetConfigurationStandard() throws ActionPieceInvalideException, ResultatIncorrectException, ParametreIncorrectException {

		this.observable.getConfigurationStandard();
		assertEquals(4, this.observable.actionGetConfiguration().size());

		assertFalse(this.observable.actionGetConfiguration().contains(TypePiece.chercherPieceParNom("EH120")));
		
		assertTrue(this.observable.actionValidationConfiguration());
	}	
	
	/**
	 * L'utilisateur a une configuration invalide
	 * @throws ActionPieceInvalideException
	 * @throws ResultatIncorrectException
	 * @throws ParametreIncorrectException
	 */
	@Test
	public void testActionValidationConfiguration() throws ActionPieceInvalideException, ResultatIncorrectException, ParametreIncorrectException {

		this.observable.getConfigurationStandard();
		assertTrue(this.observable.actionGetConfiguration().contains(TypePiece.chercherPieceParNom("TA5")));
		assertTrue(this.observable.actionValidationConfiguration());
		
		this.observable.actionSupprimerPiece("TA5");
		assertFalse(this.observable.actionGetConfiguration().contains(TypePiece.chercherPieceParNom("TA5")));
		assertFalse(this.observable.actionValidationConfiguration());
	}
	
	@Test
	public void testExpectedException() throws ActionPieceInvalideException, ResultatIncorrectException, ParametreIncorrectException {
		this.observable.actionAjouterPiece("TM5");
		this.observable.actionAjouterPiece("ED110");		

		assertThrows(ResultatIncorrectException.class,
				() -> this.observable.actionAjouterPiece("") );
		assertThrows(NullPointerException.class,
				() -> this.observable.actionAjouterPiece(null) );
		assertThrows(ActionPieceInvalideException.class, 
				() -> this.observable.actionAjouterPiece("EH120"));
		
		assertThrows(NullPointerException.class,
				() -> this.observable.actionSupprimerPiece(null) );
		assertThrows(ActionPieceInvalideException.class, 
				() -> this.observable.actionSupprimerPiece("EH120"));
		
	}
}
