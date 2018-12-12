package tests.LehanBourhis;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
 * @author Yann & Mathieu
 *
 */
class ConfigVoitureTest {

	ConfigVoiture configVoiture;

	/**
	 * Lance l'initialisation des categories et des pieces existantes au lancement de la classe de test
	 * @throws ParametreIncorrectException
	 * @throws ResultatIncorrectException
	 */
	@BeforeAll
	static void init() throws ParametreIncorrectException, ResultatIncorrectException, InstantiationException, IllegalAccessException, ClassNotFoundException {
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

		HashSet<String> categoriesRestantes = new HashSet<>();
		categoriesRestantes.addAll( Arrays.asList("TRANSMISSION", "INTERIOR"));
		assertEquals(categoriesRestantes, this.configVoiture.getCategoriesRestantes());
		
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
	public void testConfigurationComplete() throws ActionPieceInvalideException, ResultatIncorrectException, ParametreIncorrectException, FileNotFoundException {

		assertTrue(this.configVoiture.ajouterPiece("EG133"));
		assertTrue(this.configVoiture.ajouterPiece("TA5"));
		assertFalse(this.configVoiture.estComplet());
		
		assertTrue(this.configVoiture.ajouterPiece("XS"));
		assertTrue(this.configVoiture.estComplet());
	}
	
	/**
	 * On doit pouvoir ajouter une couleur existante a une piece
	 * L'application doit pouvoir communiquer a l'utilisateur les couleurs disponibles pour une piece
	 * L'utilisateur ne doit pas pouvoir demander a colorer une piece dans une couleur si elle est deja de cette couleur
	 * Quand on colore une piece, elle est bien coloree de cette couleur et non d'une autre
	 * @throws ResultatIncorrectException
	 * @throws ParametreIncorrectException
	 */
	@Test
	public void changementDeCouleur() throws ResultatIncorrectException, ParametreIncorrectException {
		assertTrue(TypePiece.chercherPieceParNom("XS").getPropriete("couleur").get() == Couleur.ROUGE.name());
		assertTrue(this.configVoiture.setCouleur("XS", Couleur.BLANC));
		Set<Couleur> couleursSouhaitees = new HashSet<>();
		couleursSouhaitees.addAll( Arrays.asList(Couleur.BLEU, Couleur.ROUGE));
		assertEquals(couleursSouhaitees, this.configVoiture.getCouleursPossibles("XS"));
		assertFalse(this.configVoiture.setCouleur("XS", Couleur.BLANC));
		assertFalse(TypePiece.chercherPieceParNom("XS").getPropriete("couleur").get() == Couleur.ROUGE.name());
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

	@Test
	public void printStream() throws ResultatIncorrectException, ActionPieceInvalideException, ParametreIncorrectException {
		PrintStream ps = new PrintStream(System.out);
		assertFalse(this.cv.getDescription(ps));
		assertTrue(this.cv.ajouterPiece("EG133"));
		assertTrue(this.cv.ajouterPiece("TA5"));
		assertTrue(this.cv.ajouterPiece("XS"));
		assertTrue(this.cv.getDescription(ps));
	}
	
}
