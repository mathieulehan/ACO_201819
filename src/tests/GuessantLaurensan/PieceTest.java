package tests.GuessantLaurensan;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import classes.piece.Exterior;
import classes.piece.Piece;
import classes.piece.TypePiece;
import exceptions.ParametreIncorrectException;
import exceptions.ResultatIncorrectException;

/**
 * 
 * @author Charlotte & Thomas
 *
 */
class PieceTest {

	private String COULEUR = "couleur";
	/**
	 * Initialise toutes les categories et leurs pieces pour tous les tests
	 * @throws ParametreIncorrectException
	 * @throws ResultatIncorrectException
	 */
	@BeforeAll
	public static void init() throws ParametreIncorrectException, ResultatIncorrectException {
		TypePiece.initialiserPieces();
	}

	/**
	 * Erreur de constructeur dans le cas ou :
	 * - Nom de piece ou description null 	-> NullPointerException
	 * - Nom de piece egal a "" 			-> ParametreIncorrectException
	 * - Nom de piece deja utilisee 		-> ParametreIncorrectException
	 */
	@Test
	public void constructeur_piece_invalide() {
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
	 */
	@Test
	public void modification_nom_invalide() throws ParametreIncorrectException {
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
	public void modification_description_invalide() throws ParametreIncorrectException {
		Piece pieceTest = new Piece("Piece", "Piece de test");
		assertThrows(NullPointerException.class, 
				() -> {pieceTest.setDescription(null);});
		assertEquals(pieceTest.getDescription(), "Piece de test");
	}

	/**
	 * Setter de nom et de description valide
	 * @throws ParametreIncorrectException 
	 */
	@Test
	public void modification_nom_description_valide() throws ParametreIncorrectException {
		Piece pieceTest = new Piece("Piece", "Piece de test");

		pieceTest.setNom("Nouveau nom");
		assertEquals(pieceTest.getNom(), "Nouveau nom");

		pieceTest.setDescription("Nouvelle description");
		assertEquals(pieceTest.getDescription(), "Nouvelle description");
	}

	/**
	 * On initialise un set d'incompatibilites grace au setter, le set peut etre vide
	 * Si le parametre du setter est null -> NullPointerException
	 * @throws ParametreIncorrectException 
	 */
	@Test
	public void modification_incompatibilites_invalide() throws ParametreIncorrectException {
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
	 * @throws ParametreIncorrectException 
	 */
	@Test
	public void modification_necessites_invalide() throws ParametreIncorrectException {
		Piece pieceTest = new Piece("Piece", "Piece de test");
		assertThrows(NullPointerException.class, 
				() -> pieceTest.setNecessites(null));
		assertTrue(pieceTest.getNecessites().size() == 0);

		pieceTest.setNecessites(new HashSet<>());
		assertEquals(pieceTest.getNecessites(), new HashSet<>());
	}

	/**
	 * L'administration souhaite ajouter une incompatibilite pour une piece
	 * @throws ParametreIncorrectException 
	 */
	@Test
	public void ajout_incompatibilite_valide() throws ParametreIncorrectException {
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
	 * L'administration souhaite ajouter une necessite pour une piece
	 * @throws ParametreIncorrectException
	 */
	@Test
	void ajout_necessite_valide() throws ParametreIncorrectException {
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
	void ajout_incompatibilite_necessite_invalide() throws ParametreIncorrectException {
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
	 * L'administration souhaite supprimer une incompatibilite pour une piece
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
	 * L'administration souhaite supprimer une incompatibilite pour une piece
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
	void suppression_incompatibilite_necessite_invalide() throws ParametreIncorrectException {
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

	/**
	 * Verification de la methode permettant de savoir si deux pieces sont incompatibles entre elles
	 * @throws ResultatIncorrectException 
	 */
	@Test
	public void piece_estIncompatible() throws ResultatIncorrectException {
		Piece EG100 = TypePiece.chercherPieceParNom("EG100");
		Piece TSF7 = TypePiece.chercherPieceParNom("TSF7");
		Piece TM5 = TypePiece.chercherPieceParNom("TM5");

		assertTrue(EG100.estIncompatible(TSF7));
		assertFalse(EG100.estIncompatible(TM5));
		assertFalse(TSF7.estIncompatible(TM5));
	}

	/**
	 * Recuperation et verification du prix des pieces
	 * Prix modifie en fonction de la couleur utilisee sur la categorie EXTERIOR
	 * @throws ResultatIncorrectException
	 * @throws ParametreIncorrectException 
	 */
	@Test
	public void recuperer_prix() throws ResultatIncorrectException, ParametreIncorrectException {
		Exterior EXTERIOR = new Exterior("EXT", "Description EXT");
		Piece TA5 = TypePiece.chercherPieceParNom("TA5");
		Piece EG100 = TypePiece.chercherPieceParNom("EG100");
		Piece IS = TypePiece.chercherPieceParNom("IS");
		Piece XS = (Exterior) TypePiece.chercherPieceParNom("XS");
		assertEquals(0.0, EXTERIOR.getPrix());
		assertEquals(600.0, TA5.getPrix());
		assertEquals(1000.0, EG100.getPrix());
		assertEquals(800.0, IS.getPrix());

		XS.setPropriete(COULEUR, "BLANC"); // Blanc coute 0
		assertEquals(1000.0, XS.getPrix());

		XS.setPropriete(COULEUR, "ROUGE"); // Rouge coute 400
		assertEquals(1400.0, XS.getPrix());
		
		XS.setPropriete(COULEUR, "BLEU"); // Bleu coute 500
		assertEquals(1500.0, XS.getPrix());
	}

	/**
	 * Categorie EXTERIOR possede une propriete couleur donc on recupere cette propriete et on la modifie
	 * @throws ResultatIncorrectException
	 * @throws ParametreIncorrectException 
	 */
	@Test
	public void recuperer_propriete_couleur_EXTERIOR_valide() throws ResultatIncorrectException, ParametreIncorrectException {
		Piece XM = TypePiece.chercherPieceParNom("XM");

		HashSet<String> nomProprieteAttendu = new HashSet<>();
		nomProprieteAttendu.add("couleur");
		assertEquals(nomProprieteAttendu, XM.getNomsProprietes());

		HashSet<String> valeursProprieteAttendues = new HashSet<>();
		valeursProprieteAttendues.addAll(Arrays.asList("BLEU", "BLANC", "ROUGE"));
		assertEquals(valeursProprieteAttendues, XM.getValeursProprietePossibles(COULEUR));

		String couleurInitiale = new String("ROUGE");
		assertEquals(couleurInitiale, XM.getPropriete(COULEUR).get());

		XM.setPropriete("couleur", "BLANC");
		String couleurModifiee = new String("BLANC");
		assertEquals(couleurModifiee, XM.getPropriete(COULEUR).get());
	}
	
	/**
	 * Cas ou les parametres d'une propriete sont incorrects ou null
	 * @throws ResultatIncorrectException
	 * @throws ParametreIncorrectException 
	 */
	@Test
	public void recuperer_propriete_couleur_EXTERIOR_invalide() throws ResultatIncorrectException, ParametreIncorrectException {
		Piece XS = TypePiece.chercherPieceParNom("XS");
		
		assertEquals(Optional.empty(), XS.getPropriete("INEXISTANTE"));
		
		HashSet<String> mesValeurs = new HashSet<>();
		assertEquals(mesValeurs, XS.getValeursProprietePossibles("INEXISTANTE"));

		assertThrows(NullPointerException.class,
				() -> XS.setPropriete("couleur", null));
		assertThrows(NullPointerException.class,
				() -> XS.setPropriete(null, "BLEU"));
		assertThrows(IllegalArgumentException.class,
				() -> XS.setPropriete("couleur", "INEXISTANTE"));
		assertThrows(NullPointerException.class, 
				() -> XS.getPropriete(null) );

	}
}
