package classes.config;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import classes.piece.Piece;
import exceptions.ResultatNullException;

/**
 * Classe contenant les donnees : rassemblement de donnees (= de pieces)
 * - Point d'entree de l'application
 * - Recuperer configuration courante + categories + pieces associees
 * 
 * Cette classe est une classe Observer (qui recupere les donnees de ConfigurationTest) ET Observable (Qui envoie les donnees a ConfigVoiture)
 * 
 * @author Charlotte & Thomas
 *
 */
public class ConfigAppImpl implements Configuration, Observer {

	private List<ConfigVoiture> configs = new LinkedList<>();


	public void update(Observable observable, Object o) {

		ConfigVoiture cv = (ConfigVoiture) observable; 
		configs.add(cv);
		Iterator<Piece> it = cv.getConfiguration().iterator();
		System.out.println("Ma configuration de voiture est composée de : ");
		while(it.hasNext()) {
			try {
				System.out.println("- la piece " + it.next().getNom());
			} catch (ResultatNullException e) {
				e.printStackTrace();
			}
		}

	}
}
