package classes.config;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import classes.piece.Piece;

/**
 * Classe contenant les donnees : rassemblement de donnees (= de pieces)
 * - Point d'entree de l'application
 * - Recuperer configuration courante + categories + pieces associees
 * 
 * Cette classe est une classe Observer (qui recupere les donnees de ConfigurationTest)
 * ET Observable (Qui envoie les donnees a ConfigVoiture)
 * 
 * @author GR4
 *
 */
public class ConfigAppImpl implements Configuration, Observer {

//	private List<ConfigVoiture> configs = new LinkedList<>(); // Gestion de plusieurs configurations pas dans la v1


	public void update(Observable observable, Object o) {

		ConfigVoiture cv = (ConfigVoiture) observable;
//		if(!configs.contains(cv)) {
//			configs.add(cv);
//		}
		Iterator<Piece> it = cv.getConfiguration().iterator();
		System.out.println("Ma configuration de voiture est compos�e de :");
		while(it.hasNext()) {
			System.out.println(" - la piece " + it.next().getNom());
		}

	}
}
