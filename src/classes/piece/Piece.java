package classes.piece;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Classe Piece, implementant une interface PieceInterface, s'agissant d'une Map ayant pour : 
 * - Cle : le nom de la pièce
 * - Valeurs : Description
 * 
 * Gestion des incompatibilites et necessites de pieces
 * 
 * @author math & chach44
 *
 */
public class Piece implements PieceInterface {
	/**
	 * Variable locale
	 */
	public Map<String, String> pieceCatalogue;
	Map<String, List<String>> pieceImcompatibilites;
	
	/**
	 * Constructeur Piece
	 */
	public Piece () {
		this.pieceCatalogue = new HashMap<>();
		this.pieceImcompatibilites = new HashMap<>();
	}
	 
	/**
	 * Initialisation des cles (piece) suivies de ses valeurs (description, incompatibilites, necessites)
	 */
	@Override
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
		

		pieceImcompatibilites.put("TA5", new LinkedList<String>(Arrays.asList("EG100")));
		pieceImcompatibilites.put("TSF7", new LinkedList<String>(Arrays.asList("EG100", "EG133", "ED110")));
		pieceImcompatibilites.put("XC", new LinkedList<String>(Arrays.asList("EG210")));
		pieceImcompatibilites.put("XM", new LinkedList<String>(Arrays.asList("EG100")));
		pieceImcompatibilites.put("XS", new LinkedList<String>(Arrays.asList("EG100")));
		pieceImcompatibilites.put("IS", new LinkedList<String>(Arrays.asList("EG100", "TM5")));
	}

	/**
	 * 
	 * @param nomPiece
	 * @return
	 */
	@Override
	public boolean verification (String nomPiece) {
		return ConfigVoiture.mesIncompatibilites.contains(nomPiece);
	}

	@Override
	public void ajoutIncompatibilite() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void suppressionIncompatibilite() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ajoutNecessite() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void suppressionNecessite() {
		// TODO Auto-generated method stub
		
	}
}
