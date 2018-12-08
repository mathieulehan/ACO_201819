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
import exceptions.ParametreIncorrectException;
import exceptions.ResultatIncorrectException;

/**
 * Classe Categorie est une Map ayant pour : 
 * - Cle : le nom de la categorie
 * - Valeurs (List) : les pieces associees a une categorie
 * 
 * @author YMCA
 *
 */
public class Categorie {

	private static Set<String> categories = new HashSet<>();
	private static Map<String, List<Piece>> categorieCatalogue = new HashMap<>();

	/**
	 * @return le set de categories
	 */
	public static Set<String> getCategories() {
		return categories;
	}

	/**
	 * Initialisation des cles (categorie) suivies de leurs valeurs (pieces associees)
	 * @throws ResultatIncorrectException 
	 * @throws ParametreIncorrectException 
	 */
	public static void initialiserCategories () throws ResultatIncorrectException, ParametreIncorrectException {
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
	 * @throws ParametreIncorrectException si la categorie en parametre est null
	 * @return Liste de pieces correspondant a la categorie en parametre
	 */
	public static List<Piece> getPiecesParCategorie(String categorie) throws ParametreIncorrectException {
		String catNonNull = Objects.requireNonNull(categorie);
		if (!categorieCatalogue.containsKey(catNonNull)) {
			throw new ParametreIncorrectException("La categorie en parametre n'existe pas");
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
