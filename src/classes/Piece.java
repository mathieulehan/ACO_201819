package classes;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe Piece, implémentant une interface PieceInterface, s'agissant d'une Map ayant pour : 
 * - Clé : le nom de la pièce
 * - Valeurs : Description
 * 
 * @author math & chach
 *
 */
public class Piece implements PieceInterface {
	/**
	 * Variable locale
	 */
	private Map<String, String> pieceCatalogue;
	
	/**
	 * Constructeur Piece
	 */
	public Piece () {
		this.pieceCatalogue = new HashMap<>();;
	}
	 
	/**
	 * Initialsation des clés (piece) suivies de ses valeurs (description, incompatibilités, nécessités)
	 */
	public void initialiserPiece () {
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

	}
	
	
	public static void main (String[] args) {
		Piece pieces = new Piece();
		pieces.initialiserPiece();
		
		System.out.println(pieces.pieceCatalogue.get("XS"));
		System.out.println("Size of map is:- " + pieces.pieceCatalogue.size()); 
	}
}
