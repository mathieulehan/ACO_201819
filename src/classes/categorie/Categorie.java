package classes.categorie;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import classes.piece.Piece;
import classes.piece.TypePiece;
import exceptions.ParametreNullException;
import exceptions.ResultatNullException;

/**
 * Classe Categorie, implementant une interface CategorieInterface, s'agissant d'une Map ayant pour : 
 * - Cle : le nom de la categorie
 * - Valeurs (List) : les pieces associees a une categorie
 * 
 * @author math & chach44
 *
 */
public class Categorie implements CategorieInterface {

	private static Set<String> categories = new HashSet<>();
	private static Map<String, List<Piece>> categorieCatalogue = new HashMap<>();

	/**
	 * @return le set de categories
	 */
	public static Set<String> getCategories() {
		return categories;
	}

	/**
	 * Initialisation des cles (categorie) suivies de ses valeurs (pieces associees)
	 * @throws ResultatNullException 
	 */
	public static void initialiserCategories () throws ResultatNullException {
		TypePiece.initialiserPieces();
		categories.add("ENGINE");
		categories.add("TRANSMISSION");
		categories.add("EXTERIOR");
		categories.add("INTERIOR");
		categorieCatalogue.put("ENGINE", new LinkedList<Piece>(Arrays.asList(
				TypePiece.chercherPieceParNom("EG100"),
				TypePiece.chercherPieceParNom("EG133"), 
				TypePiece.chercherPieceParNom("EG210"), 
				TypePiece.chercherPieceParNom("ED110"), 
				TypePiece.chercherPieceParNom("ED180"), 
				TypePiece.chercherPieceParNom("EH120"))));
		categorieCatalogue.put("TRANSMISSION", new LinkedList<Piece>(Arrays.asList(
				TypePiece.chercherPieceParNom("TM5"), 
				TypePiece.chercherPieceParNom("TM6"), 
				TypePiece.chercherPieceParNom("TA5"), 
				TypePiece.chercherPieceParNom("TS6"), 
				TypePiece.chercherPieceParNom("TSF7"), 
				TypePiece.chercherPieceParNom("TC120"))));
		categorieCatalogue.put("EXTERIOR", new LinkedList<Piece>(Arrays.asList(
				TypePiece.chercherPieceParNom("XC"),
				TypePiece.chercherPieceParNom("XM"), 
				TypePiece.chercherPieceParNom("XS"))));
		categorieCatalogue.put("INTERIOR", new LinkedList<Piece>(Arrays.asList(
				TypePiece.chercherPieceParNom("IN"),
				TypePiece.chercherPieceParNom("IH"), 
				TypePiece.chercherPieceParNom("IS"))));
	}

	/**
	 * @param la categorie des pieces que l'on souhaite recuperer
	 * @throws ParametreNullException si la categorie en parametre est null
	 */
	public static List<Piece> getPiecesParCategorie(String categorie) throws ParametreNullException {
		String catNonNull = Objects.requireNonNull(categorie);
		if (!categorieCatalogue.containsKey(catNonNull)) {
			throw new ParametreNullException("La categorie en parametre n'existe pas");
		}
		return categorieCatalogue.get(catNonNull);
	}

	/**
	 * @return Chaque categorie avec ses pieces, sous forme de Map
	 */
	public static Map<String, List<Piece>> getCategorieCatalogue() {
		return categorieCatalogue;
	}
}
