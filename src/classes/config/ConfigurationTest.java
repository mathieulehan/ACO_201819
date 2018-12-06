package classes.config;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import java.util.Observable;

import classes.categorie.Categorie;
import classes.piece.Piece;
import exceptions.ActionPieceInvalideException;
import exceptions.ParametreIncorrectException;
import exceptions.ResultatIncorrectException;

/**
 * Exemple d'une configuration avec une piece pour le moment
 * Cette classe est une classe Observable
 * @author GR4
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
	 * @throws ResultatIncorrectException 
	 * @throws ParametreIncorrectException
	 * @throws ActionPieceInvalideException 
	 */
	public static void main(String[] args) throws ParametreIncorrectException, ResultatIncorrectException, ActionPieceInvalideException {
		Categorie.initialiserCategories(); // initialisation des categories & des pieces
		ConfigVoiture configV = new ConfigVoiture();
		configV.ajouterPiece("EG100");
		Iterator<Piece> it = configV.getConfiguration().iterator();
		while(it.hasNext()) {
	        Piece p = it.next();
	        System.out.println(p.getNom());
		}
		
		//Il me faut toutes les pieces par categorie 
		//que je puisse choisir une piece dans une categorie et la mettre dans ma configuration actuelle
		//mettre une configuration de base 
		
	}
	
	/**
	 * Configuration de base
	 * @param cv
	 * @throws ParametreIncorrectException 
	 * @throws ResultatIncorrectException 
	 * @throws ActionPieceInvalideException 
	 */
	public void setConfigExemple(ConfigVoiture cv) throws ActionPieceInvalideException, ResultatIncorrectException, ParametreIncorrectException {
		cv.ajouterPiece("EG133");
		cv.ajouterPiece("XS");
		// Ajout de la piece IS car necessaire a la piece XS
		cv.ajouterPiece("TA5");
	}

}
