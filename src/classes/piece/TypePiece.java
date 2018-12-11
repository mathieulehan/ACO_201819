package classes.piece;

import java.util.LinkedList;
import java.util.List;
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
	 * Getter de la liste de Piece 
	 * @return List de piece
	 */
	public static List<Piece> getPieces() {
		return pieces;
	}

	/**
	 * Initialisation des pieces (objets TypePiece), de leurs incompatibilites et de leurs necessites
	 * @throws ParametreIncorrectException
	 */
	public static void initialiserPieces () throws ParametreIncorrectException {

		/**
		 * Initialisation du notre catalogue de pieces
		 */
		Piece ED110 = new ED110("ED110", "Diesel 110kW");
		Piece ED180 = new ED180("ED180", "Diesel 180kW");
		Piece EG100 = new EG100("EG100", "Gasoline 100kW");
		Piece EG133 = new EG133("EG133", "Gasoline 133kW");
		Piece EG210 = new EG210("EG210", "Gasoline 210kW");
		Piece EH120 = new EH120("EH120", "Gasoline/electric hybrid 120kW");
		Piece IH = new IH("IH", "High-end interior");
		Piece IN = new IN("IN", "Standard interior");
		Piece IS = new IS("IS", "Sport-finish");
		Piece TA5 = new TA5("TA5", "Automatic 5 gears");
		Piece TC120 = new TC120("TC120", "Converter 120kW max");
		Piece TM5 = new TM5("TM5", "Manual 5 gears");
		Piece TM6 = new TM6("TM6", "Manual 6 gears");
		Piece TS6 = new TS6("TS6", "Sequential 6 gears");
		Piece TSF7 = new TSF7("TSF7", "Sequential 7 gears 4 wheels drive");
		Piece XC = new XC("XC", "Classic paint");
		Piece XM = new XM("XM", "Metallic paint");
		Piece XS = new XS("XS", "Red paint and sport decoration");
		

		pieces.add(ED110);
		pieces.add(ED180);
		pieces.add(EG100);
		pieces.add(EG133);
		pieces.add(EG210);
		pieces.add(EH120);
		pieces.add(IH);
		pieces.add(IN);
		pieces.add(IS);
		pieces.add(TA5);
		pieces.add(TC120);
		pieces.add(TM5);
		pieces.add(TM6);
		pieces.add(TS6);
		pieces.add(TSF7);
		pieces.add(XC);
		pieces.add(XM);
		pieces.add(XS);

		/**
		 * Ajout des incompatibilites et des necessites des pieces instanciees
		 */
		TA5.ajoutIncompatibilite(EG100);
		TSF7.ajoutIncompatibilite(EG100);
		TSF7.ajoutIncompatibilite(EG133);
		TSF7.ajoutIncompatibilite(ED110);
		XC.ajoutIncompatibilite(EG210);
		XM.ajoutIncompatibilite(EG100);
		XS.ajoutIncompatibilite(EG100);
		XS.ajoutNecessite(IS);
		IS.ajoutIncompatibilite(EG100);
		IS.ajoutIncompatibilite(TM5);
		IS.ajoutNecessite(XS);
		EG100.ajoutIncompatibilite(TA5);
		EG100.ajoutIncompatibilite(TSF7);
		EG100.ajoutIncompatibilite(XM);
		EG100.ajoutIncompatibilite(XS);
		EG100.ajoutIncompatibilite(IS);
		EG133.ajoutIncompatibilite(TSF7);
		ED110.ajoutIncompatibilite(TSF7);
		EG210.ajoutIncompatibilite(XC);
		EH120.ajoutNecessite(TC120);
		TC120.ajoutNecessite(EH120);
		TM5.ajoutIncompatibilite(IS);
		
		
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