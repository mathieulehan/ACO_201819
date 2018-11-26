package classes.piece;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Classe Piece, implementant une interface PieceInterface, s'agissant d'une Map ayant pour : 
 * - Cle : le nom de la piece
 * - Valeurs : Description
 * 
 * Gestion des incompatibilites et necessites de pieces
 * 
 * @author math & chach44
 *
 */
public class TypePiece {
	/**
	 * Variable locale
	 */

	private List<Piece> piecesDisponibles;
	private Map<String, String> pieceCatalogue;

	/**
	 * Constructeur Pieces
	 */
	public TypePiece () {
		this.pieceCatalogue = new HashMap<String, String>();
		this.piecesDisponibles = new LinkedList<Piece>();
	}

	public List<Piece> getPiecesDisponibles() {
		return piecesDisponibles;
	}
	
	/**
	 * Initialisation des pieces (objets TypePiece) et de leurs incompatibilites & necessites
	 */
	public void initialiserPieces () {
		// donnees brutes
		pieceCatalogue.put("EG100", "Gasoline 100kW");
		pieceCatalogue.put("EG133", "Gasoline 133kW");
		pieceCatalogue.put("EG210", "Gasoline 210kW");
		pieceCatalogue.put("ED110", "Diesel 110kW");
		pieceCatalogue.put("ED180", "Diesel 180kW");
		pieceCatalogue.put("EH120", "Gasoline/electric hybrid 120kW");
		pieceCatalogue.put("TM5", "Manual 5 gears");
		pieceCatalogue.put("TM6", "Manual 6 gears");
		pieceCatalogue.put("TA5", "Automatic 5 gears");
		pieceCatalogue.put("TS6", "Sequential 6 gears");
		pieceCatalogue.put("TSF7", "Sequential 7 gears 4 wheels drive");
		pieceCatalogue.put("TC120", "Converter 120kW max");
		pieceCatalogue.put("XC", "Classic paint");
		pieceCatalogue.put("XM", "Metallic paint");
		pieceCatalogue.put("XS", "Red paint and sport decoration");
		pieceCatalogue.put("IN",  "Standard interior");
		pieceCatalogue.put("IH", "High-end interior");
		pieceCatalogue.put("IS", "Sport-finish");

		// initialisation des pieces du catalogue
		for (String nomPiece : pieceCatalogue.keySet()) {
			Piece nouvellePiece = new Piece(nomPiece, pieceCatalogue.get(nomPiece));
			piecesDisponibles.add(nouvellePiece);
		}
		// ajout des incompatibilites et des necessites des pieces instanciees
		for (Piece nouvellePiece : piecesDisponibles) {

			switch (pieceCatalogue.keySet().toString()) {
			case "TA5":
				nouvellePiece.getIncompatibilites().add(chercherPieceParNom("EG100"));
				break;
			case "TSF7":
				nouvellePiece.getIncompatibilites().add(chercherPieceParNom("EG100"));
				nouvellePiece.getIncompatibilites().add(chercherPieceParNom("EG110"));
				nouvellePiece.getIncompatibilites().add(chercherPieceParNom("EG133"));
				break;
			case "XC":
				nouvellePiece.getIncompatibilites().add(chercherPieceParNom("EG210"));
				break;
			case "XM":
				nouvellePiece.getIncompatibilites().add(chercherPieceParNom("EG100"));
				break;
			case "XS":
				nouvellePiece.getIncompatibilites().add(chercherPieceParNom("EG100"));
				nouvellePiece.getNecessites().add(chercherPieceParNom("IS"));
				break;
			case "IS":
				nouvellePiece.getIncompatibilites().add(chercherPieceParNom("EG100"));
				nouvellePiece.getIncompatibilites().add(chercherPieceParNom("TM5"));
				nouvellePiece.getNecessites().add(chercherPieceParNom("XS"));
				break;
			case "EG100":
				nouvellePiece.getIncompatibilites().add(chercherPieceParNom("TA5"));
				nouvellePiece.getIncompatibilites().add(chercherPieceParNom("TSF7"));
				nouvellePiece.getIncompatibilites().add(chercherPieceParNom("XM"));
				nouvellePiece.getIncompatibilites().add(chercherPieceParNom("XS"));
				nouvellePiece.getIncompatibilites().add(chercherPieceParNom("IS"));
				break;
			case "EG133":
				nouvellePiece.getIncompatibilites().add(chercherPieceParNom("TSF7"));
				break;
			case "ED110":
				nouvellePiece.getIncompatibilites().add(chercherPieceParNom("TSF7"));
				break;
			case "EG210":
				nouvellePiece.getIncompatibilites().add(chercherPieceParNom("XC"));
				break;
			case "EH120":
				nouvellePiece.getNecessites().add(chercherPieceParNom("TC120"));
				break;
			case "TC120":
				nouvellePiece.getNecessites().add(chercherPieceParNom("EH120"));
				break;
			case "TM5":
				nouvellePiece.getIncompatibilites().add(chercherPieceParNom("IS"));
				break;
			}
		}
	}

	public Piece chercherPieceParNom(String nomPiece) {
		for (Piece piece : piecesDisponibles) {
			if (piece.getNom().equals(nomPiece)) {
				return piece;
			}
		}
		return null;
	}
}
