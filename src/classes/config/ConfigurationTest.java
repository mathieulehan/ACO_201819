package classes.config;

import java.util.Observable;

/**
 * Exemple d'une configuration avec une piece pour le moment
 * Cette classe est une classe Observable
 * @author Thomas
 *
 */
public class ConfigurationTest extends Observable {
	
	private int piece = 0;
	
	/**
	 * Exemple !! C'est juste une configuration avec une seule piece
	 * @param p
	 */
	public ConfigurationTest(int p) {
		this.piece = p;
	}
	
	/**
	 * Ajoute une nouvelle piece a la place de l'anciene
	 * @param p
	 */
	public void setPiece(int p)
	   {
	      this.piece = p;
	      setChanged();
	      notifyObservers();
	   }
	
	/**
	 * Recupere la piece de la config
	 * @return
	 */
	public int getPiece()
	   {
	      return this.piece;
	   }

}
