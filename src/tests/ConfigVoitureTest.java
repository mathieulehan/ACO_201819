package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import classes.categorie.Categorie;
import classes.config.ConfigVoiture;
import classes.piece.TypePiece;
import exceptions.ActionPieceInvalideException;
import exceptions.ParametreNullException;
import exceptions.ResultatNullException;

class ConfigVoitureTest {

	private ConfigVoiture cv;
	
	@BeforeEach
	public void init () throws ParametreNullException, ResultatNullException {
		cv = new ConfigVoiture();
		Categorie.initialiserCategories();
	}
	
	@Test
	void ajout_de_pieces() throws ActionPieceInvalideException, ParametreNullException, ResultatNullException {
		assertTrue(cv.ajouterPiece("EG100"));
		assertTrue(cv.ajouterPiece("XC"));
		assertTrue(cv.ajouterPiece("IN"));
		assertEquals(3, cv.maConfig.size());
		assertThrows(ActionPieceInvalideException.class, () -> cv.ajouterPiece("IH"));
	}
	
	void suppression_pieces() throws ActionPieceInvalideException, ParametreNullException, ResultatNullException {
		assertTrue(cv.ajouterPiece("EG100"));
		assertTrue(cv.ajouterPiece("XC"));
		assertTrue(cv.ajouterPiece("IN"));
		
		assertTrue(cv.supprimerPiece("EG100"));
		assertThrows(ActionPieceInvalideException.class, () -> cv.ajouterPiece("TS6"));
	}
	
	@Test
	public void verification_categories() throws ResultatNullException, ParametreNullException, ActionPieceInvalideException {
		assertTrue(cv.ajouterPiece("EG100"));
		assertTrue(cv.ajouterPiece("XC"));
		assertTrue(cv.ajouterPiece("IN"));
		
		HashSet<String> categoriesSouhaitees = new HashSet<>();
		categoriesSouhaitees.addAll( Arrays.asList("EXTERIOR", "INTERIOR", "ENGINE"));
		assertEquals(categoriesSouhaitees, cv.getMesCategories());
		
		HashSet<String> categoriesRestantes = new HashSet<>();
		categoriesRestantes.addAll( Arrays.asList("TRANSMISSION"));
		assertEquals(categoriesRestantes, cv.getCategoriesRestantes());
	}
	
	@Test
	public void jhgfds() throws ParametreNullException, ResultatNullException, ActionPieceInvalideException {
		assertTrue(cv.ajouterPiece("ED180"));
		assertTrue(cv.ajouterPiece("TSF7"));

		assertEquals(TypePiece.chercherPieceParNom("TSF7"), cv.getPieceCategorie("TRANSMISSION"));
		assertThrows(ResultatNullException.class, () -> TypePiece.chercherPieceParNom("PieceLambda").equals(cv.getPieceCategorie("TRANSMISSION")));
	}

}
