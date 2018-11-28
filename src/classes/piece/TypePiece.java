package classes.piece;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import exceptions.ParametreNullException;
import exceptions.ResultatNullException;

/**
 * Classe TypePiece sous forme de List avec : 
 * - le nom de la piece
 * - la description
 * - les pieces incompatibles
 * - les pieces necessaires 
 * 
 * @author math & chach44
 *
 */
public class TypePiece {

	private List<Piece> pieces ;
	/**
	 * Donnees brutes dans le catalogue, il s'agit des pieces standards
	 */
	private Map<String, String> cataloguePiecesStandards  = new HashMap<>();

	public TypePiece () {
		this.pieces = new LinkedList<Piece>();
	}

	/**
	 * Getter de la liste de Piece 
	 * @return List de piece
	 */
	public List<Piece> getPieces() {
		return pieces;
	}

	/**
	 * Initialisation des pieces (objets TypePiece), de leurs incompatibilites et de leurs necessites
	 * @throws ResultatNullException 
	 * @throws ParametreNullException 
	 */
	public void initialiserPieces () throws ParametreNullException, ResultatNullException {
		
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
		for (Piece nomPiece : pieces) {

			switch (cataloguePiecesStandards.keySet().toString()) {
			case "TA5":
				nomPiece.getIncompatibilites().add(chercherPieceParNom("EG100"));
				break;
			case "TSF7":
				nomPiece.getIncompatibilites().add(chercherPieceParNom("EG100"));
				nomPiece.getIncompatibilites().add(chercherPieceParNom("EG110"));
				nomPiece.getIncompatibilites().add(chercherPieceParNom("EG133"));
				break;
			case "XC":
				nomPiece.getIncompatibilites().add(chercherPieceParNom("EG210"));
				break;
			case "XM":
				nomPiece.getIncompatibilites().add(chercherPieceParNom("EG100"));
				break;
			case "XS":
				nomPiece.getIncompatibilites().add(chercherPieceParNom("EG100"));
				nomPiece.getNecessites().add(chercherPieceParNom("IS"));
				break;
			case "IS":
				nomPiece.getIncompatibilites().add(chercherPieceParNom("EG100"));
				nomPiece.getIncompatibilites().add(chercherPieceParNom("TM5"));
				nomPiece.getNecessites().add(chercherPieceParNom("XS"));
				break;
			case "EG100":
				nomPiece.getIncompatibilites().add(chercherPieceParNom("TA5"));
				nomPiece.getIncompatibilites().add(chercherPieceParNom("TSF7"));
				nomPiece.getIncompatibilites().add(chercherPieceParNom("XM"));
				nomPiece.getIncompatibilites().add(chercherPieceParNom("XS"));
				nomPiece.getIncompatibilites().add(chercherPieceParNom("IS"));
				break;
			case "EG133":
				nomPiece.getIncompatibilites().add(chercherPieceParNom("TSF7"));
				break;
			case "ED110":
				nomPiece.getIncompatibilites().add(chercherPieceParNom("TSF7"));
				break;
			case "EG210":
				nomPiece.getIncompatibilites().add(chercherPieceParNom("XC"));
				break;
			case "EH120":
				nomPiece.getNecessites().add(chercherPieceParNom("TC120"));
				break;
			case "TC120":
				nomPiece.getNecessites().add(chercherPieceParNom("EH120"));
				break;
			case "TM5":
				nomPiece.getIncompatibilites().add(chercherPieceParNom("IS"));
				break;
			}
		}
	}

	/**
	 * Initialisation des donnees brutes dans le catalogue de base
	 */
	private void initDonneesBrutes () {
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
	 * @throws ResultatNullException
	 */
	public Piece chercherPieceParNom(String nomPiece) throws ResultatNullException {
		for (Piece piece : pieces) {
			if (piece.getNom().equals(nomPiece)) {
				return piece;
			}
		}
		throw new ResultatNullException("Piece introuvable par son nom");
	}
}
