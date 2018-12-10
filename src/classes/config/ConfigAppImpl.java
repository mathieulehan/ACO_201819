package classes.config;

import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import classes.piece.Piece;

/**
 * Cette classe est une classe Observer, elle observe les actions faites par l'utilisateur dans ConfigurationTest
 * Elle est notifiee des changements d'etat via la methode "notifierObserver()" dans ConfigurationTest
 * 
 * @author YMCA
 *
 */
public class ConfigAppImpl implements Observer {
	
	/**
	 * A chaque changement d'etat de la classe ConfigurationTest, l'observateur est mise a jour
	 */
	public void update(Observable observable, Object o) {

		ConfigurationTest configuration = (ConfigurationTest) observable;
		Iterator<Piece> it = configuration.actionGetConfiguration().iterator();
		System.out.println("Ma configuration de voiture est composée de :");
		while(it.hasNext()) {
			System.out.println(" - la piece " + it.next().getNom());
		}

	}
	
	
	
}
