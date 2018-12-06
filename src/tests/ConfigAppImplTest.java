package tests;

import org.junit.jupiter.api.Test;

import classes.categorie.Categorie;
import classes.config.ConfigAppImpl;
import classes.config.ConfigVoiture;
import exceptions.ActionPieceInvalideException;
import exceptions.ParametreIncorrectException;
import exceptions.ResultatIncorrectException;

/**
 * 
 * @author GR4
 *
 */
class ConfigAppImplTest {

	@Test
	void observer_observable_test() throws ResultatIncorrectException, ActionPieceInvalideException, ParametreIncorrectException {
		ConfigVoiture observable = new ConfigVoiture();
		ConfigAppImpl observer = new ConfigAppImpl();
		Categorie.initialiserCategories();

		observable.addObserver(observer);
		observable.ajouterPiece("TSF7");
		observable.ajouterPiece("IS");
	
	}
}
