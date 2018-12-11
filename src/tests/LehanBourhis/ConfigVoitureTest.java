package tests.LehanBourhis;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import classes.categorie.Categorie;
import classes.config.ConfigVoiture;
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
class ConfigVoitureTest {

	private ConfigVoiture configVoiture;

	/**
	 * Lance l'initialisation des categories et des pieces existantes au lancement de la classe de test
	 * @throws ParametreIncorrectException
	 * @throws ResultatIncorrectException
	 */
	@BeforeAll
	static void init() throws ParametreIncorrectException, ResultatIncorrectException {
		Categorie.initialiserCategories();
	}

	/**
	 * On reinitialise la configuration avant chacun des tests de la classe
	 * @throws ResultatIncorrectException
	 * @throws ParametreIncorrectException
	 */
	@BeforeEach
	private void init2() throws ResultatIncorrectException, ParametreIncorrectException {
		this.configVoiture = new ConfigVoiture();
	}

	/**
	 * Des conditions sont a veririfier avant d'ajouter une piece a la configuration
	 * On doit avoir une et une seule piece par categorie
	 * On doit verifier que la piece qu'on souhaite ajouter n'est pas incompatible avec une autre deja presente dans la configuration
	 * On doit verifier que les pieces necessaires a la piece qu'on ajoute soit ajoutees automatiquement
	 * @throws ActionPieceInvalideException
	 * @throws ParametreIncorrectException
	 * @throws ResultatIncorrectException
	 */
	@Test
	public void testAjouterPiece() throws ParametreIncorrectException, ResultatIncorrectException, ActionPieceInvalideException {

		assertTrue(this.configVoiture.ajouterPiece("TSF7"));
		assertTrue(this.configVoiture.ajouterPiece("IS"));
		assertEquals(3, this.configVoiture.getConfiguration().size());
		 
		assertTrue(this.configVoiture.getConfiguration().contains(TypePiece.chercherPieceParNom("TSF7")));
		assertTrue(this.configVoiture.getConfiguration().contains(TypePiece.chercherPieceParNom("IS")));
		assertFalse(this.configVoiture.getConfiguration().contains(TypePiece.chercherPieceParNom("IH")));
	}

	/**
	 * Lors de l'ajout de la piece TM5, la configuration doit pouvoir retourner les incompatibilites de cette piece
	 * @throws ActionPieceInvalideException
	 * @throws ParametreIncorrectException
	 * @throws ResultatIncorrectException
	 */
	@Test
	public void testGetMesIncompatibilites() throws ActionPieceInvalideException, ResultatIncorrectException, ParametreIncorrectException {

		assertTrue(this.configVoiture.ajouterPiece("TM5"));

		HashSet<Piece> incompatilibitesSouhaitees = new HashSet<>();
		incompatilibitesSouhaitees.add(TypePiece.chercherPieceParNom("IS"));
		assertEquals(incompatilibitesSouhaitees, this.configVoiture.getMesIncompatibilites());
	}

	/**
	 * Lors de la suppression de pieces dans la configuration, on doit :
	 * Verifier que les bonnes pieces ont etees supprimees
	 * @throws ActionPieceInvalideException
	 * @throws ParametreIncorrectException
	 * @throws ResultatIncorrectException
	 */
	@Test
	public void testSupprimerPiece() throws ActionPieceInvalideException, ParametreIncorrectException, ResultatIncorrectException {

		assertTrue(this.configVoiture.ajouterPiece("EG133"));
		assertEquals(1, this.configVoiture.getConfiguration().size());

		assertTrue(this.configVoiture.supprimerPiece("EG133"));
		assertEquals(0, this.configVoiture.getConfiguration().size());

		assertTrue(this.configVoiture.ajouterPiece("EH120"));
		assertEquals(2, this.configVoiture.getConfiguration().size());

	}

	/**
	 * Si on a ajoute des pieces a notre configuration voiture, l'application doit pouvoir
	 * nous renvoyer les categories pour lesquelles nous n'avons pas ajoute de piece
	 * @throws ResultatIncorrectException
	 * @throws ParametreIncorrectException
	 * @throws ActionPieceInvalideException
	 */
	@Test
	public void testGetCategories() throws ResultatIncorrectException, ParametreIncorrectException, ActionPieceInvalideException {
		assertTrue(this.configVoiture.ajouterPiece("EG100")); 
		assertTrue(this.configVoiture.ajouterPiece("XC"));
		// On a ajoute des pieces dans deux des quatre categories
		HashSet<String> categoriesRestantes = new HashSet<>();
		categoriesRestantes.addAll( Arrays.asList("TRANSMISSION", "INTERIOR"));
		// L'application doit pouvoir nous dire pour quelles categories nous n'avons pas ajoute de piece
		assertEquals(categoriesRestantes, this.configVoiture.getCategoriesRestantes());
		// L'application doit aussi pouvoir nous dire pour quelles categories nous avons deja choisi des pieces
		HashSet<String> categoriesSouhaitees = new HashSet<>();
		categoriesSouhaitees.addAll( Arrays.asList("EXTERIOR", "ENGINE"));
		assertEquals(categoriesSouhaitees, this.configVoiture.getMesCategories());

		assertTrue(this.configVoiture.supprimerPiece("EG100"));
		
		HashSet<String> categoriesRestantes2 = new HashSet<>();
		categoriesRestantes2.addAll( Arrays.asList("ENGINE", "TRANSMISSION", "INTERIOR"));
		assertEquals(categoriesRestantes2, this.configVoiture.getCategoriesRestantes());
		
		HashSet<String> categoriesSouhaitees2 = new HashSet<>();
		categoriesSouhaitees2.addAll( Arrays.asList("EXTERIOR"));
		assertEquals(categoriesSouhaitees2, this.configVoiture.getMesCategories());
	}

	/**
	 * On doit pouvoir savoir quelle piece a ete choisie pour une categorie
	 * @throws ParametreIncorrectException
	 * @throws ResultatIncorrectException
	 * @throws ActionPieceInvalideException
	 */
	@Test
	public void testGetPieceParCategorie() throws ParametreIncorrectException, ResultatIncorrectException, ActionPieceInvalideException {

		assertTrue(this.configVoiture.ajouterPiece("EG100")); 
		assertTrue(this.configVoiture.ajouterPiece("XC"));
		assertEquals(TypePiece.chercherPieceParNom("EG100"), this.configVoiture.getPieceParCategorie("ENGINE"));
		assertEquals(TypePiece.chercherPieceParNom("XC"), this.configVoiture.getPieceParCategorie("EXTERIOR"));
	}

	/**
	 * On doit pouvoir obtenir la liste des pieces que l'on peut ajouter a la configuration, 
	 * en tenant compte des pieces deja ajoutees
	 * @throws ActionPieceInvalideException
	 * @throws ParametreIncorrectException
	 * @throws ResultatIncorrectException
	 */
	@Test
	public void testGetPiecesPossibles() throws ActionPieceInvalideException, ParametreIncorrectException, ResultatIncorrectException {

		assertTrue(this.configVoiture.ajouterPiece("XC"));
		assertTrue(this.configVoiture.ajouterPiece("TSF7"));
		HashSet<Piece> piecesSouhaitees = new HashSet<>();
		piecesSouhaitees.addAll(Arrays.asList(TypePiece.chercherPieceParNom("IN"),
											TypePiece.chercherPieceParNom("IH"), 
											TypePiece.chercherPieceParNom("IS"),
											TypePiece.chercherPieceParNom("EH120"),
											TypePiece.chercherPieceParNom("ED180")));
		assertEquals(piecesSouhaitees, this.configVoiture.getPiecesPossibles());
	}

	/**
	 * L'application doit pouvoir nous confirmer que la configuration est complete, dans le 
	 * cas ou l'utilisateur a bien choisi une piece dans chaque categorie
	 * @throws ActionPieceInvalideException
	 * @throws ResultatIncorrectException
	 * @throws ParametreIncorrectException
	 */
	@Test
	public void testConfigurationComplete() throws ActionPieceInvalideException, ResultatIncorrectException, ParametreIncorrectException {

		assertTrue(this.configVoiture.ajouterPiece("EG133"));
		assertTrue(this.configVoiture.ajouterPiece("TA5"));
		assertFalse(this.configVoiture.estComplet());
		
		assertTrue(this.configVoiture.ajouterPiece("XS"));
		assertTrue(this.configVoiture.estComplet());
	}
	
	/**
	 * L'application ne doit pas pouvoir : 
	 * - ajouter une piece dont le nom n'est pas specifie
	 * - ajouter une piece nulle
	 * - ajouter une piece incompatible
	 * - supprimer une piece nulle
	 * - supprimer une piece non-ajoutee
	 * - renvoyer une piece ajoutee a une categorie si l'utilisateur n'a pas rajoute cette piece a la configuration
	 * - rechercher dans la liste des pieces existante une piece n'existant pas et la renvoyer
	 * @throws ActionPieceInvalideException
	 * @throws ResultatIncorrectException
	 * @throws ParametreIncorrectException
	 */
	@Test
	public void testExpectedException() throws ActionPieceInvalideException, ResultatIncorrectException, ParametreIncorrectException {
		assertTrue(this.configVoiture.ajouterPiece("IS"));
		assertTrue(this.configVoiture.ajouterPiece("EG133"));

		assertThrows(ResultatIncorrectException.class,
				() -> this.configVoiture.ajouterPiece("") );
		assertThrows(NullPointerException.class,
				() -> this.configVoiture.ajouterPiece(null) );
		assertThrows(ActionPieceInvalideException.class, 
				() -> this.configVoiture.ajouterPiece("EH120"));
		
		assertThrows(NullPointerException.class,
				() -> this.configVoiture.supprimerPiece(null) );
		assertThrows(ActionPieceInvalideException.class, 
				() -> this.configVoiture.supprimerPiece("EH120"));
		
		assertThrows(ResultatIncorrectException.class,
				() -> this.configVoiture.getPieceParCategorie("TRANSMISSION"));

		assertThrows(ResultatIncorrectException.class, 
				() -> TypePiece.chercherPieceParNom("X").equals(this.configVoiture.getPieceParCategorie("TRANSMISSION")));		
	}

}
