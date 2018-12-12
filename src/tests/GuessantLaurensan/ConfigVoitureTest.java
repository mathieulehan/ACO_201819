package tests.GuessantLaurensan;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import classes.categorie.Categorie;
import classes.config.ConfigVoiture;
import classes.piece.Piece;
import classes.piece.TypePiece;
import classes.piece.Piece.Couleur;
import exceptions.ActionPieceInvalideException;
import exceptions.ParametreIncorrectException;
import exceptions.ResultatIncorrectException;

/**
 * 
 * @author Charlotte & Thomas
 *
 */
class ConfigVoitureTest {

	private ConfigVoiture configuration;

	/**
	 * Initialise toutes les categories et leurs pieces pour tous les tests
	 * @throws ParametreIncorrectException
	 * @throws ResultatIncorrectException
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@BeforeAll
	private static void init() throws ParametreIncorrectException, ResultatIncorrectException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Categorie.initialiserCategories();
	}

	/**
	 * Reinitialise la configuration pour chaque test
	 * @throws ResultatIncorrectException
	 * @throws ParametreIncorrectException
	 */
	@BeforeEach
	private void init_configVoiture() throws ResultatIncorrectException, ParametreIncorrectException {
		this.configuration = new ConfigVoiture();
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
	public void ajout_de_pieces() throws ParametreIncorrectException, ResultatIncorrectException, ActionPieceInvalideException {
		Piece TM5 = TypePiece.chercherPieceParNom("TM5");
		
		assertTrue(this.configuration.ajouterPiece("TM5"));
		assertTrue(this.configuration.getConfiguration().contains(TM5));
		assertTrue(this.configuration.ajouterPiece("IN"));
		
		assertThrows(ResultatIncorrectException.class,
				() -> this.configuration.ajouterPiece("") );
		assertThrows(ActionPieceInvalideException.class,
				() -> this.configuration.ajouterPiece("TM5") ); //deja ajoutee
		assertThrows(NullPointerException.class,
				() -> this.configuration.ajouterPiece(null) );
		assertThrows(ActionPieceInvalideException.class, 
				() -> { this.configuration.ajouterPiece("IH"); }); // Incompatible avec IN
		
		assertEquals(2, this.configuration.getConfiguration().size());
		assertFalse(this.configuration.getConfiguration().contains(TypePiece.chercherPieceParNom("EG100")));
	}

	/**
	 * Ajouter la piece EG100 et voir les incompatibilites
	 * @throws ActionPieceInvalideException
	 * @throws ParametreIncorrectException
	 * @throws ResultatIncorrectException
	 */
	@Test
	public void getIncompatibilites() throws ActionPieceInvalideException, ResultatIncorrectException, ParametreIncorrectException {

		assertTrue(this.configuration.ajouterPiece("EG100"));

		HashSet<Piece> incompatilibitesSouhaitees = new HashSet<>();
		incompatilibitesSouhaitees.addAll(Arrays.asList(TypePiece.chercherPieceParNom("TA5"), 
														TypePiece.chercherPieceParNom("TSF7"), 
														TypePiece.chercherPieceParNom("XM"), 
														TypePiece.chercherPieceParNom("XS"), 
														TypePiece.chercherPieceParNom("IS")));
		assertEquals(incompatilibitesSouhaitees, this.configuration.getMesIncompatibilites());

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
	public void suppression_de_pieces() throws ActionPieceInvalideException, ParametreIncorrectException, ResultatIncorrectException {

		assertTrue(this.configuration.ajouterPiece("EG133"));
		assertTrue(this.configuration.ajouterPiece("XS"));
		// Ajout de la piece IS car necessaire a la piece XS
		assertEquals(3, this.configuration.getConfiguration().size());

		assertTrue(this.configuration.supprimerPiece("IS"));
		// Suppression de la piece XS car necessaire a la piece IS
		assertEquals(1, this.configuration.getConfiguration().size());
		assertThrows(NullPointerException.class,
				() -> this.configuration.supprimerPiece(null) );
		assertThrows(ActionPieceInvalideException.class, 
				() -> this.configuration.ajouterPiece("EH120")); // Incompatible avec EG133
		assertThrows(ActionPieceInvalideException.class, 
				() -> this.configuration.supprimerPiece("EH120")); // Piece non ajoutee dans la configuration

	}

	/**
	 * Apres une selection de pieces dans notre configuration, on souhaite recuperer :
	 * - les categories dans lesquelles aucune piece n'a ete choisie
	 * @throws ResultatIncorrectException
	 * @throws ParametreIncorrectException
	 * @throws ActionPieceInvalideException
	 */
	@Test
	public void verification_categories_restantes() throws ResultatIncorrectException, ParametreIncorrectException, ActionPieceInvalideException {
		assertTrue(this.configuration.ajouterPiece("EH120")); 
		// Ajout de la piece TC120 car necessaire a la piece EH120
		assertTrue(this.configuration.ajouterPiece("XM"));

		HashSet<String> categoriesRestantes = new HashSet<>();
		categoriesRestantes.add("INTERIOR");
		assertEquals(categoriesRestantes, this.configuration.getCategoriesRestantes());

		assertTrue(this.configuration.supprimerPiece("EH120"));
		HashSet<String> categoriesRestantes2 = new HashSet<>();
		categoriesRestantes2.addAll( Arrays.asList("INTERIOR", "ENGINE", "TRANSMISSION"));
		assertEquals(categoriesRestantes2, this.configuration.getCategoriesRestantes());

		assertThrows(ResultatIncorrectException.class,
				() -> this.configuration.getPieceParCategorie("INTERIOR"));
	}


	/**
	 * Apres une selection de pieces dans notre configuration, on souhaite recuperer :
	 * - Les categories ou une piece a ete choisie
	 * @throws ResultatIncorrectException
	 * @throws ParametreIncorrectException
	 * @throws ActionPieceInvalideException
	 */
	@Test
	public void verification_categories_presentes() throws ResultatIncorrectException, ParametreIncorrectException, ActionPieceInvalideException {
		assertTrue(this.configuration.ajouterPiece("EH120")); 
		// Ajout de la piece TC120 car necessaire a la piece EH120
		assertTrue(this.configuration.ajouterPiece("XM"));

		HashSet<String> categoriesSouhaitees = new HashSet<>();
		categoriesSouhaitees.addAll( Arrays.asList("EXTERIOR", "TRANSMISSION", "ENGINE"));
		assertEquals(categoriesSouhaitees, this.configuration.getMesCategories());

		assertTrue(this.configuration.supprimerPiece("XM"));
		HashSet<String> categoriesSouhaitees2 = new HashSet<>();
		categoriesSouhaitees2.addAll( Arrays.asList("ENGINE", "TRANSMISSION"));
		assertEquals(categoriesSouhaitees2, this.configuration.getMesCategories());
	}

	/**
	 * Recherche de la categorie d'une piece
	 * @throws ParametreIncorrectException
	 * @throws ResultatIncorrectException
	 * @throws ActionPieceInvalideException
	 */
	@Test
	public void categories_en_fonction_pieces_de_ma_configuration() throws ParametreIncorrectException, ResultatIncorrectException, ActionPieceInvalideException {

		assertTrue(this.configuration.ajouterPiece("TSF7"));
		assertEquals(TypePiece.chercherPieceParNom("TSF7"), this.configuration.getPieceParCategorie("TRANSMISSION"));
		assertThrows(ResultatIncorrectException.class, 
				() -> TypePiece.chercherPieceParNom("PieceLambda").equals(this.configuration.getPieceParCategorie("TRANSMISSION")));
	}

	/**
	 * Recherche des pieces encore disponibles pour notre configuration
	 * @throws ActionPieceInvalideException
	 * @throws ParametreIncorrectException
	 * @throws ResultatIncorrectException
	 */
	@Test
	public void pieces_disponibles() throws ActionPieceInvalideException, ParametreIncorrectException, ResultatIncorrectException {

		assertTrue(this.configuration.ajouterPiece("XC"));
		assertTrue(this.configuration.ajouterPiece("TM5"));
		assertTrue(this.configuration.ajouterPiece("ED110"));
		assertTrue(this.configuration.getPiecesPossibles().contains(TypePiece.chercherPieceParNom("IN")));
		assertFalse(this.configuration.getPiecesPossibles().contains(TypePiece.chercherPieceParNom("IS")));

		assertFalse(this.configuration.estComplet());
	}

	/**
	 * Verification que la configuration est complete
	 * @throws ActionPieceInvalideException
	 * @throws ResultatIncorrectException
	 * @throws ParametreIncorrectException
	 */
	@Test
	public void configuration_complete() throws ActionPieceInvalideException, ResultatIncorrectException, ParametreIncorrectException {

		assertTrue(this.configuration.ajouterPiece("ED110"));
		assertTrue(this.configuration.ajouterPiece("TA5"));
		assertTrue(this.configuration.ajouterPiece("XM"));
		assertTrue(this.configuration.ajouterPiece("IH"));
		assertTrue(this.configuration.estComplet());

		assertThrows(ActionPieceInvalideException.class, 
				() -> this.configuration.ajouterPiece("EH120")); // Configuration terminee
		
		assertTrue(this.configuration.estComplet());
	}
	
	/**
	 * Verifier le cout de la configuration courante
	 * @throws ActionPieceInvalideException
	 * @throws ParametreIncorrectException
	 * @throws ResultatIncorrectException
	 */
	@Test
	public void recuperer_cout_configuration() throws ActionPieceInvalideException, ResultatIncorrectException, ParametreIncorrectException {

		this.configuration.ajouterPiece("EH120");
		assertTrue(this.configuration.getPrix() == 2600.0);
		
		this.configuration.ajouterPiece("XC");
		assertTrue(this.configuration.getPrix() == 3600.0);
		
		this.configuration.supprimerPiece("XC");
		assertTrue(this.configuration.getPrix() == 2600.0);
		
		this.configuration.supprimerPiece("TC120");
		assertTrue(this.configuration.getPrix() == 0.0);
	}
	
	/**
	 * Recuperer les couleurs possibles pour la peinture exterieure et changer la couleur de la piece
	 * @throws ActionPieceInvalideException 
	 * @throws ParametreIncorrectException 
	 * @throws ResultatIncorrectException 
	 */
	@Test
	public void modification_couleur_valide() throws ActionPieceInvalideException, ResultatIncorrectException, ParametreIncorrectException {

		this.configuration.ajouterPiece("XM");

		//assertEquals(Couleur.ROUGE, TypePiece.chercherPieceParNom("XM").getPropriete("couleur").get());
		
		HashSet<Couleur> couleursPossibles = new HashSet<>();
		couleursPossibles.addAll(Arrays.asList(Couleur.BLANC, Couleur.BLEU));
		assertEquals(couleursPossibles, this.configuration.getCouleursPossibles("XM"));

		assertTrue(this.configuration.getCouleursPossibles("XM").size() == 2);
		
		this.configuration.setCouleur("XM", Couleur.BLEU);

		HashSet<Couleur> couleursPossibles2 = new HashSet<>();
		couleursPossibles2.addAll(Arrays.asList(Couleur.BLANC, Couleur.ROUGE));
		assertEquals(couleursPossibles2, this.configuration.getCouleursPossibles("XM"));
		
		this.configuration.setCouleur("XM", Couleur.BLANC);

		HashSet<Couleur> couleursPossibles3 = new HashSet<>();
		couleursPossibles3.addAll(Arrays.asList(Couleur.BLEU, Couleur.ROUGE));
		assertEquals(couleursPossibles3, this.configuration.getCouleursPossibles("XM"));

		assertTrue(this.configuration.getCouleursPossibles("XM").size() == 2);
	}
	
	@Test
	public void piece_setCouleur_invalide() throws ResultatIncorrectException, ParametreIncorrectException {
		assertEquals(Collections.emptySet(), this.configuration.getCouleursPossibles("EG100"));
		assertFalse(this.configuration.setCouleur("EG100", Couleur.BLANC));
		
		assertThrows(NullPointerException.class,
				() -> this.configuration.setCouleur(null, Couleur.BLANC));
		assertThrows(ResultatIncorrectException.class,
				() -> this.configuration.setCouleur("", Couleur.BLANC));
		assertThrows(NullPointerException.class,
				() -> this.configuration.setCouleur("XC", null));
	}

}
