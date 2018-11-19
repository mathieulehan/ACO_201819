package classes.piece;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import exceptions.PasDIncompatibilitesException;

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
public class Piece implements PieceInterface, GestionCompatibilite, VerifCompatibilite {
	/**
	 * Variable locale
	 */


	private String nom;
	private String description;
	private Set<String> incompatibilites;
	private Set<String> necessites;
	private List<Piece> piecesDisponibles;
	private Map<String, String> pieceCatalogue;
	private Map<String, List<String>> pieceIncompatibilites;
	private Map<String, List<String>> pieceRequises;

	/**
	 * Constructeur Piece
	 */
	public Piece (String nom, String description) {
		this.nom = nom;
		this.description = description;
		this.incompatibilites = new HashSet<String>();
		this.necessites = new HashSet<String>();
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

		for (String nomPiece : pieceCatalogue.keySet()) {
			Piece nouvellePiece = new Piece(nomPiece, pieceCatalogue.get(nomPiece));
			piecesDisponibles.add(nouvellePiece);
			switch (pieceCatalogue.keySet().toString()) {
			case "TA5":
				nouvellePiece.incompatibilites.add("EG100");
				break;
			case "TSF7":
				nouvellePiece.incompatibilites.add("EG100");
				nouvellePiece.incompatibilites.add("EG110");
				nouvellePiece.incompatibilites.add("EG133");
				break;
			case "XC":
				nouvellePiece.incompatibilites.add("EG210");
				break;
			case "XM":
				nouvellePiece.incompatibilites.add("EG100");
				break;
			case "XS":
				nouvellePiece.incompatibilites.add("EG100");
				nouvellePiece.necessites.add("IS");
				break;
			case "IS":
				nouvellePiece.incompatibilites.add("EG100");
				nouvellePiece.incompatibilites.add("TM5");
				nouvellePiece.necessites.add("XS");
				break;
			case "EG100":
				nouvellePiece.incompatibilites.add("TA5");
				nouvellePiece.incompatibilites.add("TSF7");
				nouvellePiece.incompatibilites.add("XM");
				nouvellePiece.incompatibilites.add("XS");
				nouvellePiece.incompatibilites.add("IS");
				break;
			case "EG133":
				nouvellePiece.incompatibilites.add("TSF7");
				break;
			case "ED110":
				nouvellePiece.incompatibilites.add("TSF7");
				break;
			case "EG210":
				nouvellePiece.incompatibilites.add("XC");
				break;
			case "EH120":
				nouvellePiece.necessites.add("TC120");
				break;
			case "TC120":
				nouvellePiece.necessites.add("EH120");
				break;
			case "TM5":
				nouvellePiece.incompatibilites.add("IS");
				break;
			}
		}
	} 
	
	//		pieceIncompatibilites.put("TA5", new LinkedList<String>(Arrays.asList("EG100")));
	//		pieceIncompatibilites.put("TSF7", new LinkedList<String>(Arrays.asList("EG100", "EG133", "ED110")));
	//		pieceIncompatibilites.put("XC", new LinkedList<String>(Arrays.asList("EG210")));
	//		pieceIncompatibilites.put("XM", new LinkedList<String>(Arrays.asList("EG100")));
	//		pieceIncompatibilites.put("XS", new LinkedList<String>(Arrays.asList("EG100")));
	//		pieceIncompatibilites.put("IS", new LinkedList<String>(Arrays.asList("EG100", "TM5")));

	//		pieceRequises.put("EH120",new LinkedList<String>(Arrays.asList("TC120")));
	//		pieceRequises.put("TC120", new LinkedList<String>(Arrays.asList("EH120")));
	//		pieceRequises.put("XS", new LinkedList<String>(Arrays.asList("IS")));
	//		pieceRequises.put("IS", new LinkedList<String>(Arrays.asList("XS")));


	//	/**
	//	 * On verifie que la piece 
	//	 * @param nomPiece
	//	 * @return
	//	 */
	//	@Override
	//	public boolean verification (String nomPiece) {
	//		return ConfigVoiture.mesIncompatibilites.contains(nomPiece);
	//	}

	@Override
	public void setIncompatibilites(Set<String> newIncompatibilites) {
		// TODO Auto-generated method stub
		this.incompatibilites = newIncompatibilites;
	}


	/**
	 * Methode permettant de rajouter une incompatibilite a la piece en parametre
	 */
	@Override
	public void ajoutIncompatibilite(String newIncompatibilite) {
		if(!incompatibilites.contains(newIncompatibilite)) {
			incompatibilites.add(newIncompatibilite);
		}
	}

	/**
	 * Methode permettant de supprimer une incompatibilite de la piece en parametre
	 * @throws PasDIncompatibilitesException 
	 */
	@Override
	public void suppressionIncompatibilite(String newIncompatibilite) throws PasDIncompatibilitesException {
		if(incompatibilites.contains(newIncompatibilite)) {
			incompatibilites.remove(newIncompatibilite);
		}
		else {
			throw new PasDIncompatibilitesException("Cette piece ne dispose pas d'incompatibilites");
		}
	}

	/**
	 * Methode permettant de rajouter une necessite a la piece en parametre
	 */
	@Override
	public void ajoutNecessite(String newNecessite) {
		if(!necessites.contains(newNecessite)) {
			necessites.add(newNecessite);
		}
	}

	/**
	 * Methode permettant de supprimer une necessite de la piece en parametre
	 * @throws PasDIncompatibilitesException 
	 */
	@Override
	public void suppressionNecessite(String newNecessite) throws PasDIncompatibilitesException {
		if(necessites.contains(newNecessite)) {
			necessites.remove(newNecessite);
		}
		else {
			throw new PasDIncompatibilitesException("Cette piece ne dispose pas d'incompatibilites");
		}
	}

	@Override
	public void setNecessites(Set<String> newNecessites) {
		// TODO Auto-generated method stub
		this.necessites = newNecessites;
	}
}
