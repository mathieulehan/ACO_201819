package classes.config;

import java.util.Iterator;
import java.util.Observable;

import classes.categorie.Categorie;
import classes.piece.Piece;
import exceptions.ActionPieceInvalideException;
import exceptions.ParametreNullException;
import exceptions.ResultatNullException;

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
	
	/**
	 * main
	 * @param args
	 * @throws ResultatNullException 
	 * @throws ParametreNullException
	 * @throws ActionPieceInvalideException 
	 */
	public static void main(String[] args) throws ParametreNullException, ResultatNullException, ActionPieceInvalideException {
		Categorie.initialiserCategories(); // initialisation des categories
		ConfigVoiture configV = new ConfigVoiture();
		configV.ajouterPiece("EG100");
		Iterator<Piece> it = configV.maConfig.iterator();
		while(it.hasNext()) {
	        Piece p = it.next();
	        System.out.println(p.getNom());
		}
		
		//Il me faut toutes les pieces par categorie 
		//que je puisse choisir une piece dans une categorie et la mettre dans ma configuration actuelle
		//il faut mettre a jour en fonction des incompatibilites
		//mettre une configuration de base 
	}

}
