package classes.piece;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import classes.config.ConfigVoiture;

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
public class Piece implements PieceInterface {
	/**
	 * Variable locale
	 */
	public Map<String, String> pieceCatalogue;
	public Map<String, List<String>> pieceIncompatibilites;
	public Map<String, List<String>> pieceRequises;
	
	/**
	 * Constructeur Piece
	 */
	public Piece () {
		this.pieceCatalogue = new HashMap<>();
		this.pieceIncompatibilites = new HashMap<>();
		this.pieceRequises = new HashMap<>();
	}
	 
	/**
	 * Initialisation des cles (piece) suivies de sa description
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
		

		pieceIncompatibilites.put("TA5", new LinkedList<String>(Arrays.asList("EG100")));
		pieceIncompatibilites.put("TSF7", new LinkedList<String>(Arrays.asList("EG100", "EG133", "ED110")));
		pieceIncompatibilites.put("XC", new LinkedList<String>(Arrays.asList("EG210")));
		pieceIncompatibilites.put("XM", new LinkedList<String>(Arrays.asList("EG100")));
		pieceIncompatibilites.put("XS", new LinkedList<String>(Arrays.asList("EG100")));
		pieceIncompatibilites.put("IS", new LinkedList<String>(Arrays.asList("EG100", "TM5")));
		

		pieceRequises.put("EH120",new LinkedList<String>(Arrays.asList("TC120")));
		pieceRequises.put("TC120", new LinkedList<String>(Arrays.asList("EH120")));
		pieceRequises.put("XS", new LinkedList<String>(Arrays.asList("IS")));
		pieceRequises.put("IS", new LinkedList<String>(Arrays.asList("XS")));
	}
//
//	/**
//	 * On verifie que la piece 
//	 * @param nomPiece
//	 * @return
//	 */
//	@Override
//	public boolean verification (String nomPiece) {
//		return ConfigVoiture.mesIncompatibilites.contains(nomPiece);
//	}

	/**
	 * Methode permettant de rajouter une incompatibilite a la piece en parametre
	 */
	@Override
	public void ajoutIncompatibilite(String nomPiece, String incompatibilite) {
		List<String> incompatibilites = new LinkedList<String>();
		if(pieceIncompatibilites.get(nomPiece) != null) {
			incompatibilites = pieceIncompatibilites.get(nomPiece);
			incompatibilites.add(incompatibilite);
		}
		else {
			incompatibilites = new LinkedList<String>();
			incompatibilites.add(incompatibilite);
		}
		pieceIncompatibilites.put(nomPiece, incompatibilites);	
	}

	/**
	 * Methode permettant de supprimer une incompatibilite de la piece en parametre
	 */
	@Override
	public void suppressionIncompatibilite(String nomPiece, String incompatibilite) {
		List<String> incompatibilites = new LinkedList<String>();
		if(!pieceIncompatibilites.get(nomPiece).isEmpty() || pieceIncompatibilites.get(nomPiece) != null) {
			incompatibilites = pieceIncompatibilites.get(nomPiece);
			incompatibilites.remove(incompatibilite);
		}
		else {
			// y'a pas d'incompatibilites pour cette piece. TODO Exception la piece n'a pas d'incompatibilite(s)
		}
	}

	/**
	 * Methode permettant de rajouter une necessite a la piece en parametre
	 */
	@Override
	public void ajoutNecessite(String nomPiece, String necessite) {
		List<String> necessites = new LinkedList<String>();
		if(pieceRequises.get(nomPiece) != null) {
			necessites = pieceIncompatibilites.get(nomPiece);
			necessites.add(necessite);
		}
		else {
			necessites = new LinkedList<String>();
			necessites.add(necessite);
		}
		pieceRequises.put(nomPiece, necessites);
	}

	/**
	 * Methode permettant de supprimer une necessite de la piece en parametre
	 */
	@Override
	public void suppressionNecessite(String nomPiece, String necessite) {
		List<String> necessites = new LinkedList<String>();
		if(!pieceRequises.get(nomPiece).isEmpty() || pieceRequises.get(nomPiece) != null) {
			necessites = pieceRequises.get(nomPiece);
			necessites.remove(necessite);
		}
		else {
			// y'a pas de necessites pour cette piece. TODO Exception
		}
	}
}
