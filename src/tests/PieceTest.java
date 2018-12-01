package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import classes.piece.Piece;
import exceptions.ParametreNullException;
import exceptions.ResultatNullException;

class PieceTest {

	/**
	 * On teste si la methode getNom() renvoie bien la bonne exception dans le cas ou
	 * le nom de la piece est null
	 * 
	 * On teste si la methode setNom(String nom) renvoie bien la bonne exception dans le cas
	 * ou on tente de set le nom de la piece a null
	 * @throws ResultatNullException car le nom de la piece est null
	 */
	@Test
	void getNom_setNom_incorrects() throws ResultatNullException {
		Piece pieceTest = new Piece(null, "Piece de test");
		assertThrows(ResultatNullException.class, 
				() -> {pieceTest.getNom();});
		
		assertThrows(NullPointerException.class, 
				() -> {pieceTest.setNom(null);});
	}

	/**
	 * On teste si la methode getDescription() renvoie bien la bonne exception dans le cas
	 * ou la description de la piece est null
	 * 
	 * On teste si la methode setNom(String description) renvoie bien la bonne exception dans le cas
	 * ou on tente de set le description de la piece a null
	 * @throws ResultatNullException
	 */
	@Test
	void getDescription_setDescription_incorrects() throws ResultatNullException{
		Piece pieceTest = new Piece("Piece", null);
		assertThrows(ResultatNullException.class, 
				() -> {pieceTest.getDescription();});
		
		assertThrows(NullPointerException.class, 
				() -> {pieceTest.setDescription(null);});

//		assertThrows(NullPointerException.class, 
//				() -> {pieceTest.setDescription("");});
	}

	/**
	 * On teste si la methode getIncompatibilites() renvoie bien la bonne exception dans le cas
	 * ou le set d'incompatibilites de la piece est null
	 * 
	 * On teste si la methode setIncompatibilites(Set<Piece> incompatibilites) renvoie bien la bonne exception dans le cas
	 * ou on tente de set les incompatibilites de la piece a null
	 * @throws ResultatNullException
	 */
	@Test
	void getIncompatibilite_setIncompatibilites_incorrect() throws ResultatNullException {
		Piece pieceTest = new Piece("Piece", "Piece de test");
		assertThrows(NullPointerException.class, 
				() -> pieceTest.setIncompatibilites(null));
		assertTrue(pieceTest.getIncompatibilites().size() == 0);
		
		assertThrows(NullPointerException.class,
				() -> {pieceTest.setIncompatibilites(null);});

//		assertThrows(ParametreNullException.class, 
//				() -> {pieceTest.setIncompatibilites(new HashSet<>());});
	}

	/**
	 * Differents tests autour de l'ajout d'incompatibilites aux pieces
	 * @throws ResultatNullException 
	 * @throws ParametreNullException 
	 */
	@Test
	void ajoutIncompatibilites() throws ResultatNullException, ParametreNullException {
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
	 */
	@Test
	void ajoutIncompatibiliteNull() {
		Piece pieceTest = new Piece(null, "Piece de test");
		assertThrows(NullPointerException.class, 
				() -> {pieceTest.ajoutIncompatibilite(null);});
	}

	/**
	 * On verifie si la suppression d'une incompatibilite fonctionne dans differents cas
	 * et renvoie bien les bonnes exceptions
	 * @throws ResultatNullException 
	 * @throws ParametreNullException 
	 */
	@Test
	void suppressionIncompatibilites() throws ResultatNullException, ParametreNullException {
		Piece pieceTest = new Piece("Piece", "Piece de test");
		assertTrue(pieceTest.getIncompatibilites().size() == 0);
		
		Piece incompatibilite = new Piece("Incompatibilite", "Incompatibilite de test");
		pieceTest.ajoutIncompatibilite(incompatibilite);
		assertTrue(pieceTest.getIncompatibilites().size() == 1);
		assertThrows(NullPointerException.class, 
				() -> {pieceTest.suppressionIncompatibilite(null);});
		
		pieceTest.suppressionIncompatibilite(incompatibilite);
		assertTrue(pieceTest.getIncompatibilites().size() == 0);
	}

	/**
	 * Differents tests autour de l'ajout de necessites aux pieces
	 * @throws ResultatNullException 
	 * @throws ParametreNullException 
	 */
	@Test
	void ajoutNecessites() throws ResultatNullException, ParametreNullException{
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
		assertThrows(NullPointerException.class, 
				() -> {pieceTest.ajoutNecessite(null);});
	}

	/**
	 * On verifie que la suppression d'une necessite fonctionne
	 * @throws ResultatNullException 
	 * @throws ParametreNullException 
	 */
	@Test
	void suppressionNecessites() throws ResultatNullException, ParametreNullException {
		Piece pieceTest = new Piece("Piece", "Piece de test");

		assertTrue(pieceTest.getNecessites().size() == 0);
		Piece necessite = new Piece("Necessites", "Necessites de test");
		pieceTest.ajoutNecessite(necessite);
		assertTrue(pieceTest.getNecessites().size() == 1);
		
		pieceTest.suppressionNecessite(necessite);
		assertTrue(pieceTest.getNecessites().size() == 0);
	}
}
