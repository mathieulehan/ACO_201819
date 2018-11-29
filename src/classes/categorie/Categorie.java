package classes.categorie;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import classes.piece.Piece;
import classes.piece.TypePiece;
import exceptions.ParametreNullException;
import exceptions.ResultatNullException;

/**
 * Classe Categorie, implementant une interface CategorieInterface, s'agissant d'une Map ayant pour : 
 * - Cle : le nom de la categorie (enumeration)
 * - Valeurs (List) : les pieces associees a� une categorie
 * 
 * @author math & chach44
 *
 */
public class Categorie implements CategorieInterface {

	/**
	 * Enumeration des categories existantes
	 */
	private static Set<String> categories = new HashSet<String>();
	private static Map<String, List<Piece>> categorieCatalogue = new HashMap<String, List<Piece>>();

	/**
	 * @return le set de categories
	 * @throws ResultatNullException si le Set de categories renvoye est null
	 */
	public static Set<String> getCategories() throws ResultatNullException {
		if(categories == null) throw new ResultatNullException("Le Set de categories est null");
		return categories;
	}

	/**
	 * Initialisation des cles (categorie) suivies de ses valeurs (pieces associees)
	 * @throws ResultatNullException 
	 * @throws ParametreNullException 
	 */
	public static void initialiserCategories () throws ParametreNullException, ResultatNullException {
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
	 * @param la categorie des pieces que l'on souhaite r�cup�rer
	 * @throws ParametreNullException si la categorie en parametre est null
	 * @throws ResultatNullException 
	 */
	public static List<Piece> getPiecesCategorie(String categorie) throws ParametreNullException, ResultatNullException {
		if (categories.contains(categorie)) {
			throw new ParametreNullException("La cat�gorie en param�tre n'existe pas");
		}
		return getCategorieCatalogue().get(categorie);
	}

	/**
	 * @return Chaque categorie avec ses pieces, sous forme de Map
	 * @throws ResultatNullException si la Map que l'on retourne est null
	 */
	public static Map<String, List<Piece>> getCategorieCatalogue() throws ResultatNullException {
		if(categorieCatalogue == null) throw new ResultatNullException("Il n'y a pas de categorie.");
		return categorieCatalogue;
	}
}
