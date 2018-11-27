package classes.config;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import classes.categorie.Categorie;
import classes.piece.Piece;

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
public class ConfigAppImpl extends Observable implements Configuration, Observer {
	
	private ConfigurationTest conf = null;
	
	public ConfigAppImpl(ConfigurationTest c) {
		this.conf = c;
	}

	public ConfigVoiture getConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private List<ConfigVoiture> configs;
	
	public void notificationConfigTest(ConfigurationTest conf) {
		//modifier ce dont on a besoin à l'aide de la conf
			
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if (o == conf)
	      {
	         System.out.println(conf.getPiece());
	      }
	}

}
