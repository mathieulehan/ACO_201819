package classes.piece;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import exceptions.ParametreIncorrectException;
import exceptions.ResultatIncorrectException;

/**
 * Classe TypePiece sous forme de List avec : 
 * - le nom de la piece
 * - la description
 * - les pieces incompatibles
 * - les pieces necessaires 
 * 
 * @author YMCA
 *
 */
public class TypePiece {

	private static List<Piece> pieces = new LinkedList<>();
	/**
	 * Donnees brutes dans un catalogue, il s'agit des pieces standards
	 */
	private static Map<String, String> cataloguePiecesStandards  = new HashMap<>();

	/**
	 * Getter de la liste de Piece 
	 * @return List de piece
	 */
	public static List<Piece> getPieces() {
		return pieces;
	}

	/**
	 * Initialisation des pieces (objets TypePiece), de leurs incompatibilites et de leurs necessites
	 * @throws ParametreIncorrectException 
	 * @throws ResultatIncorrectException 
	 */
	public static void initialiserPieces () throws ResultatIncorrectException, ParametreIncorrectException {

		initDonneesBrutes();

		/**
		 * Initialisation du notre catalogue de pieces
		 */
		for (String nomPiece : cataloguePiecesStandards.keySet()) {
			Piece nouvellePiece = new Piece(nomPiece, cataloguePiecesStandards.get(nomPiece));
			pieces.add(nouvellePiece);
		}

		/**
		 * Ajout des incompatibilites et des necessites des pieces instanciees
		 */
		for (Piece piece : pieces) {
			Set<Piece> mesIncompatibilites = new HashSet<>();
			Set<Piece> mesNecessites = new HashSet<>();
			switch (piece.getNom()) {
			case "TA5":
				mesIncompatibilites.add(chercherPieceParNom("EG100"));
				break;
			case "TSF7":
				mesIncompatibilites.add(chercherPieceParNom("EG100"));
				mesIncompatibilites.add(chercherPieceParNom("EG133"));
				mesIncompatibilites.add(chercherPieceParNom("ED110"));
				break;
			case "XC":
				mesIncompatibilites.add(chercherPieceParNom("EG210"));
				break;
			case "XM":
				mesIncompatibilites.add(chercherPieceParNom("EG100"));
				break;
			case "XS":
				mesIncompatibilites.add(chercherPieceParNom("EG100"));
				mesNecessites.add(chercherPieceParNom("IS"));
				break;
			case "IS":
				mesIncompatibilites.add(chercherPieceParNom("EG100"));
				mesIncompatibilites.add(chercherPieceParNom("TM5"));
				mesNecessites.add(chercherPieceParNom("XS"));
				break;
			case "EG100":
				mesIncompatibilites.add(chercherPieceParNom("TA5"));
				mesIncompatibilites.add(chercherPieceParNom("TSF7"));
				mesIncompatibilites.add(chercherPieceParNom("XM"));
				mesIncompatibilites.add(chercherPieceParNom("XS"));
				mesIncompatibilites.add(chercherPieceParNom("IS"));
				break;
			case "EG133":
				mesIncompatibilites.add(chercherPieceParNom("TSF7"));
				break;
			case "ED110":
				mesIncompatibilites.add(chercherPieceParNom("TSF7"));
				break;
			case "EG210":
				mesIncompatibilites.add(chercherPieceParNom("XC"));
				break;
			case "EH120":
				mesNecessites.add(chercherPieceParNom("TC120"));
				break;
			case "TC120":
				mesNecessites.add(chercherPieceParNom("EH120"));
				break;
			case "TM5":
				mesIncompatibilites.add(chercherPieceParNom("IS"));
				break;
			default :
				break;
			}
			piece.setIncompatibilites(mesIncompatibilites);
			piece.setNecessites(mesNecessites);
		}
	}

	/**
	 * Initialisation des donnees brutes dans le catalogue de base
	 */
	private static void initDonneesBrutes () {
		cataloguePiecesStandards.put("EG100", "Gasoline 100kW");
		cataloguePiecesStandards.put("EG133", "Gasoline 133kW");
		cataloguePiecesStandards.put("EG210", "Gasoline 210kW");
		cataloguePiecesStandards.put("ED110", "Diesel 110kW");
		cataloguePiecesStandards.put("ED180", "Diesel 180kW");
		cataloguePiecesStandards.put("EH120", "Gasoline/electric hybrid 120kW");
		cataloguePiecesStandards.put("TM5", "Manual 5 gears");
		cataloguePiecesStandards.put("TM6", "Manual 6 gears");
		cataloguePiecesStandards.put("TA5", "Automatic 5 gears");
		cataloguePiecesStandards.put("TS6", "Sequential 6 gears");
		cataloguePiecesStandards.put("TSF7", "Sequential 7 gears 4 wheels drive");
		cataloguePiecesStandards.put("TC120", "Converter 120kW max");
		cataloguePiecesStandards.put("XC", "Classic paint");
		cataloguePiecesStandards.put("XM", "Metallic paint");
		cataloguePiecesStandards.put("XS", "Red paint and sport decoration");
		cataloguePiecesStandards.put("IN",  "Standard interior");
		cataloguePiecesStandards.put("IH", "High-end interior");
		cataloguePiecesStandards.put("IS", "Sport-finish");
	}

	/**
	 * Methode recherchant parmis la liste des pieces celle dont le nom est passe en parametre
	 * @param nomPiece
	 * @return Piece si elle est trouvee
	 * @throws ResultatIncorrectException
	 */
	public static Piece chercherPieceParNom(String nomPiece) throws ResultatIncorrectException {
		for (Piece piece : pieces) {
			if (piece.getNom().equals(nomPiece)) {
				return piece;
			}
		}
		throw new ResultatIncorrectException("Piece introuvable par son nom");

	}

}