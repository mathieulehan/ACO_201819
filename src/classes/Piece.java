package classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Piece implements PieceInterface {

	public static void main (String [] args) {
		Map<String, List<String>> pieceCategorie = new HashMap<>();
		pieceCategorie.put("EG100", new LinkedList<String>(Arrays.asList("Gasoline 100kW", "", "")));
		pieceCategorie.put("EG133", new LinkedList<String>(Arrays.asList("Gasoline 133kW", "", "")));
		pieceCategorie.put("EG210", new LinkedList<String>(Arrays.asList("Gasoline 210kW", "", "")));
		pieceCategorie.put("ED110", new LinkedList<String>(Arrays.asList("Diesel 110kW", "", "")));
		pieceCategorie.put("ED180", new LinkedList<String>(Arrays.asList("Diesel 180kW", "", "")));
		pieceCategorie.put("EH120", new LinkedList<String>(Arrays.asList("Gasoline/electric hybrid 120kW", "", "TC120")));
		pieceCategorie.put("TM5", new LinkedList<String>(Arrays.asList("Manual 5 gears", "", "")));
		pieceCategorie.put("TM6", new LinkedList<String>(Arrays.asList("Manual 6 gears", "", "")));
		pieceCategorie.put("TA5", new LinkedList<String>(Arrays.asList("Automatic 5 gears", "EG100", "")));
		pieceCategorie.put("TS6", new LinkedList<String>(Arrays.asList("Sequential 6 gears", "", "")));
		pieceCategorie.put("TSF7", new LinkedList<String>(Arrays.asList("Sequential 7 gears 4 wheels drive", "EG100 + EG133 + ED110", "")));
		pieceCategorie.put("TC120", new LinkedList<String>(Arrays.asList("Converter 120kW max", "", "EH120")));
		pieceCategorie.put("XC", new LinkedList<String>(Arrays.asList("Classic paint", "EG210", "")));
		pieceCategorie.put("XM", new LinkedList<String>(Arrays.asList("Metallic paint", "EG100", "")));
		pieceCategorie.put("XS", new LinkedList<String>(Arrays.asList("Red paint and sport decoration", "EG100", "IS")));
		pieceCategorie.put("IN", new LinkedList<String>(Arrays.asList("Standard interior", "", "")));
		pieceCategorie.put("IH", new LinkedList<String>(Arrays.asList("High-end interior", "", "")));
		pieceCategorie.put("IS", new LinkedList<String>(Arrays.asList("Sport-finish", "EG100 + TM5", "XS")));
		System.out.println(pieceCategorie.get("EG100"));
		System.out.println("Size of map is:- " + pieceCategorie.size()); 
	}
}
