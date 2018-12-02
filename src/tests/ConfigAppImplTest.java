package tests;

import org.junit.jupiter.api.Test;

import classes.categorie.Categorie;
import classes.config.ConfigAppImpl;
import classes.config.ConfigVoiture;
import exceptions.ActionPieceInvalideException;
import exceptions.ParametreNullException;
import exceptions.ResultatNullException;

class ConfigAppImplTest {

	@Test
	void observer_observable_test() throws ResultatNullException, ActionPieceInvalideException, ParametreNullException {
		ConfigVoiture observable = new ConfigVoiture();
		ConfigAppImpl observer = new ConfigAppImpl();
		Categorie.initialiserCategories();

		observable.addObserver(observer);
		observable.ajouterPiece("TSF7");
		observable.ajouterPiece("IS");
	
	}
}
