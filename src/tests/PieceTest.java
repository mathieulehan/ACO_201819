package tests;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import classes.piece.Piece;
import exceptions.ParametreNullException;
import exceptions.PasDIncompatibilitesException;
import exceptions.PasDeNecessitesException;
import exceptions.ResultatNullException;

class PieceTest {

	/**
	 * On teste si la methode getNom() renvoie bien la bonne exception dans le cas ou
	 * le nom de la piece est null
	 * @throws ResultatNullException car le nom de la piece est null
	 */
	@Test
	void getNomRetourneNull() throws ResultatNullException{
		Piece pieceTest = new Piece(null, "Piece de test");
		assertThrows(ResultatNullException.class, ()->{pieceTest.getNom();});
	}

	/**
	 * On teste si la methode setNom(String nom) renvoie bien la bonne exception dans le cas
	 * ou on tente de set le nom de la piece a null
	 * @throws ParametreNullException car le parametre est null
	 */
	@Test
	void setNomNull() throws ParametreNullException{
		Piece pieceTest = new Piece("Piece", "Piece de test");
		assertThrows(ParametreNullException.class, ()->{pieceTest.setNom(null);});
	}

	/**
	 * On teste si la methode getDescription() renvoie bien la bonne exception dans le cas
	 * ou la description de la piece est null
	 * @throws ResultatNullException
	 */
	@Test
	void getDescriptionRetourneNull() throws ResultatNullException{
		Piece pieceTest = new Piece("Piece", null);
		assertThrows(ResultatNullException.class, ()->{pieceTest.getDescription();});
	}

	/**
	 * On teste si la methode setNom(String description) renvoie bien la bonne exception dans le cas
	 * ou on tente de set le description de la piece a null
	 * @throws ParametreNullException car le parametre est null
	 */
	@Test
	void setDescriptionNull() throws ParametreNullException{
		Piece pieceTest = new Piece("Piece", "Piece de test");
		assertThrows(ParametreNullException.class, ()->{pieceTest.setDescription(null);});
	}

	/**
	 * On teste si la methode getIncompatibilites() renvoie bien la bonne exception dans le cas
	 * ou le set d'incompatibilites de la piece est null
	 * @throws ResultatNullException
	 * @throws ParametreNullException
	 * TODO Mocker incompatibilites en null
	 */
	@Test
	void getIncompatibiliteRetourneNull() throws ResultatNullException, ParametreNullException{
		Piece mockPiece = Mockito.mock(Piece.class);
	}

	/**
	 * On teste si la methode setIncompatibilites(Set<Piece> incompatibilites) renvoie bien la bonne exception dans le cas
	 * ou on tente de set les incompatibilites de la piece a null
	 * @throws ParametreNullException car le parametre est null
	 */
	@Test
	void setIncompatibilitesNull() throws ParametreNullException{
		Piece pieceTest = new Piece("Piece", "Piece de test");
		assertThrows(ParametreNullException.class, ()->{pieceTest.setIncompatibilites(null);});
	}

	/**
	 * Differents tests autour de l'ajout d'incompatibilites aux pieces
	 * @throws ResultatNullException 
	 * @throws ParametreNullException 
	 */
	@Test
	void ajoutIncompatibilites() throws ParametreNullException, ResultatNullException {
		Piece pieceTest = new Piece("Piece", "Piece de test");
		assertTrue(pieceTest.getIncompatibilites().size() == 0);
		Piece incompatibilite = new Piece("Incompatibilite", "Incompatibilte de test");
		pieceTest.ajoutIncompatibilite(incompatibilite);
		assertTrue(pieceTest.getIncompatibilites().size() == 1);
		assertTrue(pieceTest.getIncompatibilites().contains(incompatibilite));
		Piece incompatibilite2 = new Piece("Deuxieme incompatibilite", "Incompatibilite de test");
		pieceTest.ajoutIncompatibilite(incompatibilite2);
		assertTrue(pieceTest.getIncompatibilites().size() == 2);
		assertTrue((pieceTest.getIncompatibilites().contains(incompatibilite) && (pieceTest.getIncompatibilites().contains(incompatibilite2))));
	}

	/**
	 * On teste si la methode ajoutIncompatibilite(Piece incompatibilite) renvoie bien la bonne exception dans le cas ou
	 * l'incompatibilite est null
	 * @throws ParametreNullException car le nom de la piece est null
	 */
	@Test
	void ajoutIncompatibiliteNull() throws ParametreNullException{
		Piece pieceTest = new Piece(null, "Piece de test");
		assertThrows(ParametreNullException.class, ()->{pieceTest.ajoutIncompatibilite(null);});
	}

	/**
	 * On verifie si la suppression d'une incompatibilite fonctionne dans differents cas
	 * et renvoie bien les bonnes exceptions
	 * @throws PasDIncompatibilitesException
	 * @throws ResultatNullException 
	 * @throws ParametreNullException 
	 */
	@Test
	void suppressionIncompatibilites() throws PasDIncompatibilitesException, ParametreNullException, ResultatNullException {
		Piece pieceTest = new Piece("Piece", "Piece de test");
		assertTrue(pieceTest.getIncompatibilites().size() == 0);
		Piece incompatibilite = new Piece("Incompatibilite", "Incompatibilite de test");
		pieceTest.ajoutIncompatibilite(incompatibilite);
		assertTrue(pieceTest.getIncompatibilites().size() == 1);
		assertThrows(ParametreNullException.class, ()->{pieceTest.suppressionIncompatibilite(null);});
		pieceTest.suppressionIncompatibilite(incompatibilite);
		assertTrue(pieceTest.getIncompatibilites().size() == 0);
	}

	/**
	 * Differents tests autour de l'ajout de necessites aux pieces
	 * @throws ResultatNullException 
	 * @throws ParametreNullException 
	 */
	@Test
	void ajoutNecessites() throws ResultatNullException, ParametreNullException {
		Piece pieceTest = new Piece("Piece", "Piece de test");
		assertTrue(pieceTest.getNecessites().size() == 0);
		Piece necessite = new Piece("Necessite", "Necessite de test");
		pieceTest.ajoutNecessite(necessite);
		assertTrue(pieceTest.getNecessites().size() == 1);
		assertTrue(pieceTest.getNecessites().contains(necessite));
		Piece necessite2 = new Piece("Deuxieme necessite", "Necessite de test");
		pieceTest.ajoutNecessite(necessite2);
		assertTrue(pieceTest.getNecessites().size() == 2);
		assertTrue((pieceTest.getNecessites().contains(necessite) && (pieceTest.getNecessites().contains(necessite2))));
		assertThrows(ParametreNullException.class, ()->{pieceTest.ajoutNecessite(null);});
	}

	/**
	 * On verifie que la suppression d'une necessite fonctionne
	 * @throws PasDIncompatibilitesException
	 * @throws ResultatNullException 
	 * @throws ParametreNullException 
	 * @throws PasDeNecessitesException 
	 */
	@Test
	void suppressionNecessites() throws PasDIncompatibilitesException, ResultatNullException, ParametreNullException, PasDeNecessitesException {
		Piece pieceTest = new Piece("Piece", "Piece de test");
		assertTrue(pieceTest.getNecessites().size() == 0);
		Piece necessite = new Piece("Necessites", "Necessites de test");
		pieceTest.ajoutNecessite(necessite);
		assertTrue(pieceTest.getNecessites().size() == 1);
		pieceTest.suppressionNecessite(necessite);
		assertTrue(pieceTest.getNecessites().size() == 0);
	}
}
