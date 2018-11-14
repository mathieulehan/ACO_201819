package classes.piece;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Piece implements PieceInterface {
	private Map<String, List<String>> pieceCatalogue;
	
	public Piece () {
		this.pieceCatalogue = new HashMap<>();;
	}
	 
	public void initialiserPiece () {
		pieceCatalogue.put("EG100", new LinkedList<String>(Arrays.asList("Gasoline 100kW", "", "")));
		pieceCatalogue.put("EG133", new LinkedList<String>(Arrays.asList("Gasoline 133kW", "", "")));
		pieceCatalogue.put("EG210", new LinkedList<String>(Arrays.asList("Gasoline 210kW", "", "")));
		pieceCatalogue.put("ED110", new LinkedList<String>(Arrays.asList("Diesel 110kW", "", "")));
		pieceCatalogue.put("ED180", new LinkedList<String>(Arrays.asList("Diesel 180kW", "", "")));
		pieceCatalogue.put("EH120", new LinkedList<String>(Arrays.asList("Gasoline/electric hybrid 120kW", "", "TC120")));
		pieceCatalogue.put("TM5", new LinkedList<String>(Arrays.asList("Manual 5 gears", "", "")));
		pieceCatalogue.put("TM6", new LinkedList<String>(Arrays.asList("Manual 6 gears", "", "")));
		pieceCatalogue.put("TA5", new LinkedList<String>(Arrays.asList("Automatic 5 gears", "EG100", "")));
		pieceCatalogue.put("TS6", new LinkedList<String>(Arrays.asList("Sequential 6 gears", "", "")));
		pieceCatalogue.put("TSF7", new LinkedList<String>(Arrays.asList("Sequential 7 gears 4 wheels drive", "EG100 + EG133 + ED110", "")));
		pieceCatalogue.put("TC120", new LinkedList<String>(Arrays.asList("Converter 120kW max", "", "EH120")));
		pieceCatalogue.put("XC", new LinkedList<String>(Arrays.asList("Classic paint", "EG210", "")));
		pieceCatalogue.put("XM", new LinkedList<String>(Arrays.asList("Metallic paint", "EG100", "")));
		pieceCatalogue.put("XS", new LinkedList<String>(Arrays.asList("Red paint and sport decoration", "EG100", "IS")));
		pieceCatalogue.put("IN", new LinkedList<String>(Arrays.asList("Standard interior", "", "")));
		pieceCatalogue.put("IH", new LinkedList<String>(Arrays.asList("High-end interior", "", "")));
		pieceCatalogue.put("IS", new LinkedList<String>(Arrays.asList("Sport-finish", "EG100 + TM5", "XS")));

	}
	
	public List<String> getPiece(String nomPiece) {
		return pieceCatalogue.get(nomPiece);
	}
	
	public static void main (String[] args) {
		Piece pieces = new Piece();
		pieces.initialiserPiece();
		
		
		System.out.println(pieces.getPiece("EH120"));
		//System.out.println("Size of map is:- " + pieces.size()); 
	}
}
