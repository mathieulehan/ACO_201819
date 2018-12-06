package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import classes.categorie.Categorie;
import classes.piece.Piece;
import exceptions.ParametreIncorrectException;
import exceptions.ResultatIncorrectException;

/**
 * 
 * @author GR4
 *
 */
class PieceTest {
	
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
	 * Erreur de constructeur dans le cas ou :
	 * - Nom de piece ou description null 	-> NullPointerException
	 * - Nom de piece egal a "" 			-> ParametreIncorrectException
	 * - Nom de piece deja utilisee 		-> ParametreIncorrectException
	 * @throws ParametreIncorrectException 
	 * @throws ResultatIncorrectException 
	 */
	@Test
	public void constructeur_piece_incorrects() throws ParametreIncorrectException, ResultatIncorrectException {
		assertThrows(NullPointerException.class, 
				() -> {new Piece(null, "Piece de test");});
		assertThrows(ParametreIncorrectException.class, 
				() -> {new Piece("", "Piece de test");});
		assertThrows(ParametreIncorrectException.class, 
				() -> {new Piece("EG100", "Piece de test");});
		assertThrows(NullPointerException.class, 
				() -> {new Piece("Piece", null);});
	}
	
	/**
	 * Erreur dans le setter de nom dans le cas ou :
	 * - Nom de piece null 			-> NullPointerException
	 * - Nom de piece egal a "" 	-> ParametreIncorrectException
	 * - Nom de piece deja utilisee -> ParametreIncorrectException
	 * @throws ParametreIncorrectException 
	 * @throws ResultatIncorrectException 
	 */
	@Test
	public void modification_nom_incorrects() throws ParametreIncorrectException, ResultatIncorrectException {
		Piece pieceTest = new Piece("Piece", "Piece de test");
		assertThrows(NullPointerException.class, 
				() -> {pieceTest.setNom(null);});
		assertThrows(ParametreIncorrectException.class, 
				() -> {pieceTest.setNom("");});
		assertThrows(ParametreIncorrectException.class, 
				() -> {pieceTest.setNom("IS");});
		assertEquals(pieceTest.getNom(), "Piece");
	}

	/**
	 * Erreur dans le setter de description dans le cas ou la description de la piece est nulle -> NullPointerException
	 * @throws ParametreIncorrectException 
	 */
	@Test
	public void modification_description_incorrect() throws ParametreIncorrectException {
		Piece pieceTest = new Piece("Piece", "Piece de test");
		assertThrows(NullPointerException.class, 
				() -> {pieceTest.setDescription(null);});
		assertEquals(pieceTest.getDescription(), "Piece de test");
	}
	
	/**
	 * On initialise un set d'incompatibilites grace au setter, le set peut etre vide
	 * Si le parametre du setter est null -> NullPointerException
	 * @throws ResultatIncorrectException
	 * @throws ParametreIncorrectException 
	 */
	@Test
	public void modification_incompatibilites_incorrect() throws ParametreIncorrectException {
		Piece pieceTest = new Piece("Piece", "Piece de test");
		assertThrows(NullPointerException.class, 
				() -> pieceTest.setIncompatibilites(null));
		assertTrue(pieceTest.getIncompatibilites().size() == 0);

		pieceTest.setIncompatibilites(new HashSet<>());
		assertEquals(pieceTest.getIncompatibilites(), new HashSet<>());
		assertTrue(pieceTest.getIncompatibilites().size() == 0);
	}

	/**
	 * On initialise un set de necessites grace au setter, le set peut etre vide
	 * Si le parametre du setter est null -> NullPointerException
	 * @throws ResultatIncorrectException
	 * @throws ParametreIncorrectException 
	 */
	@Test
	public void modification_necessites_incorrect() throws ResultatIncorrectException, ParametreIncorrectException {
		Piece pieceTest = new Piece("Piece", "Piece de test");
		assertThrows(NullPointerException.class, 
				() -> pieceTest.setIncompatibilites(null));
		assertTrue(pieceTest.getIncompatibilites().size() == 0);

		pieceTest.setIncompatibilites(new HashSet<>());
		assertEquals(pieceTest.getIncompatibilites(), new HashSet<>());
	}
	
	/**
	 * Ajout d'une incompatibilite pour une piece
	 * @throws ParametreIncorrectException 
	 */
	@Test
	void ajout_incompatibilite_valide() throws ParametreIncorrectException  {
		Piece pieceTest = new Piece("Piece", "Piece de test");
		assertTrue(pieceTest.getIncompatibilites().size() == 0);
		
		Piece incompatibilite = new Piece("Incompatibilite", "Incompatibilte de test");
		pieceTest.ajoutIncompatibilite(incompatibilite);
		assertTrue(pieceTest.getIncompatibilites().size() == 1);
		assertTrue(pieceTest.getIncompatibilites().contains(incompatibilite));
		
		Piece incompatibilite2 = new Piece("Deuxieme incompatibilite", "Incompatibilite de test");
		pieceTest.ajoutIncompatibilite(incompatibilite2);
		assertTrue(pieceTest.getIncompatibilites().size() == 2);
		assertTrue(pieceTest.getIncompatibilites().contains(incompatibilite) 
				&& (pieceTest.getIncompatibilites().contains(incompatibilite2)));
	}
	
	/**	 
	 * Ajout d'une necessite pour une piece
	 * @throws ParametreIncorrectException
	 */
	@Test
	void ajout_necessite_valide() throws ParametreIncorrectException{
		Piece pieceTest = new Piece("Piece", "Piece de test");
		assertTrue(pieceTest.getNecessites().size() == 0);
		
		Piece necessite = new Piece("Necessite", "Necessite de test");
		pieceTest.ajoutNecessite(necessite);
		assertTrue(pieceTest.getNecessites().size() == 1);
		assertTrue(pieceTest.getNecessites().contains(necessite));
		
		Piece necessite2 = new Piece("Deuxieme necessite", "Necessite de test");
		pieceTest.ajoutNecessite(necessite2);
		assertTrue(pieceTest.getNecessites().size() == 2);
		assertTrue((pieceTest.getNecessites().contains(necessite) 
				&& (pieceTest.getNecessites().contains(necessite2))));
	}

	/**
	 * Ajout d'incompatibilite OU ajout de necessite en erreur
	 * Si le parametre des methodes "ajoutIncompatibilite()" OU "ajoutNecessite()" est null 	-> NullPointerException
	 * Si la piece possede deja cette incompatibilite OU necessite, elle ne peut pas s'ajouter 	-> ParametreIncorrectException
	 * @throws ParametreIncorrectException 
	 */
	@Test
	void ajout_incompatibilite_necessite_incorrect() throws ParametreIncorrectException {
		Piece pieceTest = new Piece("Piece", "Piece de test");

		Piece incompatibilite = new Piece("Incompatibilite", "Incompatibilte de test");
		pieceTest.ajoutIncompatibilite(incompatibilite);
		assertThrows(ParametreIncorrectException.class,
				() -> pieceTest.ajoutIncompatibilite(incompatibilite)); // Piece deja ajoutee, donc elle ne s'ajoute pas
		assertThrows(NullPointerException.class, 
				() -> {pieceTest.ajoutIncompatibilite(null);}); 

		Piece necessite = new Piece("Necessite", "Necessite de test");
		pieceTest.ajoutNecessite(necessite);
		assertThrows(ParametreIncorrectException.class,
				() -> pieceTest.ajoutNecessite(necessite)); // Piece deja ajoutee, donc elle ne s'ajoute pas
		assertThrows(NullPointerException.class, 
				() -> {pieceTest.ajoutNecessite(null);});
	}
	
	/**
	 * Suppression d'une incompatibilite pour une piece
	 * @throws ParametreIncorrectException 
	 */
	@Test
	void suppression_incompatibilite_valide() throws ParametreIncorrectException {
		Piece pieceTest = new Piece("Piece", "Piece de test");
		assertTrue(pieceTest.getIncompatibilites().size() == 0);
		
		Piece incompatibilite = new Piece("Incompatibilite", "Incompatibilite de test");
		pieceTest.ajoutIncompatibilite(incompatibilite);
		assertTrue(pieceTest.getIncompatibilites().size() == 1);
		
		pieceTest.suppressionIncompatibilite(incompatibilite);
		assertTrue(pieceTest.getIncompatibilites().size() == 0);
	}
	
	/**
	 * Suppression d'une incompatibilite pour une piece
	 * @throws ParametreIncorrectException 
	 */
	@Test
	void suppression_necessite_valide() throws ParametreIncorrectException {
		Piece pieceTest = new Piece("Piece", "Piece de test");
		assertTrue(pieceTest.getNecessites().size() == 0);
		
		Piece necessite = new Piece("Necessites", "Necessites de test");
		pieceTest.ajoutNecessite(necessite);
		assertTrue(pieceTest.getNecessites().size() == 1);
		
		pieceTest.suppressionNecessite(necessite);
		assertTrue(pieceTest.getNecessites().size() == 0);
	}
	
	/**
	 * Suppression d'une incompatibilite OU d'une necessite en erreur
	 * Si le parametre des methodes "suppressionIncompatibilite()" OU "suppressionNecessite()" est null -> NullPointerException
	 * Si la piece ne possede pas cette incompatibilite OU necessite, on ne peut pas la supprimer 		-> ParametreIncorrectException 
	 * @throws ParametreIncorrectException 
	 */
	@Test
	void suppression_incompatibilite_necessite_incorrect() throws ParametreIncorrectException {
		Piece pieceTest = new Piece("Piece", "Piece de test");

		Piece incompatibilite = new Piece("Incompatibilite", "Incompatibilte de test");
		assertThrows(ParametreIncorrectException.class,
				() -> pieceTest.suppressionIncompatibilite(incompatibilite)); // Piece jamais ajoutee
		assertThrows(NullPointerException.class, 
				() -> {pieceTest.suppressionIncompatibilite(null);});

		Piece necessite = new Piece("Necessite", "Necessite de test");
		assertThrows(ParametreIncorrectException.class,
				() -> pieceTest.suppressionNecessite(necessite)); // Piece jamais ajoutee
		assertThrows(NullPointerException.class, 
				() -> {pieceTest.suppressionNecessite(null);});
	}
}
