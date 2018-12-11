package tests.GuessantLaurensan;

import static org.junit.jupiter.api.Assertions.*;

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
 * @author Charlotte & Thomas
 *
 */
class ConfigAppImplTest {
	
	private ConfigurationTest observable;
	private ConfigAppImpl observer;

	/**
	 * Initialise toutes les categories et leurs pieces pour tous les tests
	 * @throws ParametreIncorrectException
	 * @throws ResultatIncorrectException
	 */
	@BeforeAll
	private static void init() throws ParametreIncorrectException, ResultatIncorrectException {
		Categorie.initialiserCategories();
	}

	/**
	 * Reinitialise la configuration pour chaque test
	 * @throws ResultatIncorrectException
	 * @throws ParametreIncorrectException
	 */
	@BeforeEach
	public void init_configuration_observer_observable() throws ResultatIncorrectException, ParametreIncorrectException {
		this.observable = new ConfigurationTest();
		this.observer = new ConfigAppImpl();
		this.observable.addObserver(observer);
	}
	
	/**
	 * L'utilisateur ajoute une piece dans sa configuration
	 * @throws ResultatIncorrectException
	 * @throws ActionPieceInvalideException
	 * @throws ParametreIncorrectException
	 */
	@Test
	public void ajout_piece_dans_configuration() throws ResultatIncorrectException, ActionPieceInvalideException, ParametreIncorrectException {

		this.observable.actionAjouterPiece("TSF7");
		this.observable.actionAjouterPiece("IS");
		// Ajout de la piece XS car necessaire a la piece IS
		assertEquals(3, this.observable.actionGetConfiguration().size());
		
		assertThrows(ActionPieceInvalideException.class, 
				() -> this.observable.actionAjouterPiece("XS"));
		assertEquals(3, this.observable.actionGetConfiguration().size());
	}
	
	/**
	 * L'utilisateur supprime une piece de sa configuration
	 * @throws ResultatIncorrectException
	 * @throws ActionPieceInvalideException
	 * @throws ParametreIncorrectException
	 */
	@Test
	public void suppression_piece_dans_configuration() throws ResultatIncorrectException, ActionPieceInvalideException, ParametreIncorrectException {

		this.observable.actionAjouterPiece("TC120");
		// Ajout de la piece EH120 car necessaire a la piece TC120
		assertEquals(2, this.observable.actionGetConfiguration().size());

		assertThrows(ActionPieceInvalideException.class, 
				() -> this.observable.actionSupprimerPiece("EG100"));
		assertEquals(2, this.observable.actionGetConfiguration().size());

		this.observable.actionSupprimerPiece("TC120");
		// Suppression de la piece EH120 car necessaire a la piece TC120
		assertEquals(0, this.observable.actionGetConfiguration().size());
	}
	
	/**
	 * L'utilisateur veut connaitre les pieces incompatibles a celles presentes dans la configuration
	 * @throws ActionPieceInvalideException
	 * @throws ParametreIncorrectException
	 * @throws ResultatIncorrectException
	 */
	@Test
	public void getIncompatibilites() throws ActionPieceInvalideException, ResultatIncorrectException, ParametreIncorrectException {

		this.observable.actionAjouterPiece("XC");

		HashSet<Piece> incompatilibitesSouhaitees = new HashSet<>();
		incompatilibitesSouhaitees.add(TypePiece.chercherPieceParNom("EG210"));
		assertEquals(incompatilibitesSouhaitees, this.observable.actionGetPiecesIncompatibles());

	}
	
	
	/**
	 * L'utilisateur souhaite instancier la configuration existante 
	 * @throws ActionPieceInvalideException
	 * @throws ResultatIncorrectException
	 * @throws ParametreIncorrectException
	 */
	@Test
	public void recuperer_configuration_standard() throws ActionPieceInvalideException, ResultatIncorrectException, ParametreIncorrectException {

		this.observable.getConfigurationStandard();
		assertEquals(4, this.observable.actionGetConfiguration().size());
		
		assertTrue(this.observable.actionGetConfiguration().contains(TypePiece.chercherPieceParNom("EG133")));
		assertTrue(this.observable.actionGetConfiguration().contains(TypePiece.chercherPieceParNom("XS")));
		assertTrue(this.observable.actionGetConfiguration().contains(TypePiece.chercherPieceParNom("IS")));
		assertTrue(this.observable.actionGetConfiguration().contains(TypePiece.chercherPieceParNom("TA5")));

		assertFalse(this.observable.actionGetConfiguration().contains(TypePiece.chercherPieceParNom("EH120")));
	}
	
	
	/**
	 * L'utilisateur souhaite voir les categories restantes dans sa configuration
	 * @throws ActionPieceInvalideException
	 * @throws ResultatIncorrectException
	 * @throws ParametreIncorrectException
	 */
	@Test
	public void voir_list_categories_restantes() throws ActionPieceInvalideException, ResultatIncorrectException, ParametreIncorrectException {

		this.observable.getConfigurationStandard();
		this.observable.actionSupprimerPiece("TA5");
		
		HashSet<String> categoriesRestantes = new HashSet<>();
		categoriesRestantes.add("TRANSMISSION");
		assertEquals(categoriesRestantes, this.observable.actionGetCategoriesRestantes());
	}
	
	
	/**
	 * L'utilisateur a une configuration valide
	 * @throws ActionPieceInvalideException
	 * @throws ResultatIncorrectException
	 * @throws ParametreIncorrectException
	 */
	@Test
	public void configuration_valide() throws ActionPieceInvalideException, ResultatIncorrectException, ParametreIncorrectException {

		this.observable.getConfigurationStandard();
		assertTrue(this.observable.actionValidationConfiguration());
	}
	
	/**
	 * L'utilisateur a une configuration invalide
	 * @throws ActionPieceInvalideException
	 * @throws ResultatIncorrectException
	 * @throws ParametreIncorrectException
	 */
	@Test
	public void configuration_invalide() throws ActionPieceInvalideException, ResultatIncorrectException, ParametreIncorrectException {

		this.observable.getConfigurationStandard();
		this.observable.actionSupprimerPiece("TA5");
		assertFalse(this.observable.actionValidationConfiguration());
	}
}
