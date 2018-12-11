package classes.config;

import java.util.Observable;
import java.util.Set;

import classes.piece.Piece;
import classes.piece.Piece.Couleur;
import exceptions.ActionPieceInvalideException;
import exceptions.ParametreIncorrectException;
import exceptions.ResultatIncorrectException;

/**
 * Classe en lien avec l'utilisateur, il s'agit du point d'entree de l'application
 * Elle cree et recupere la configuration en cours (ainsi que ses pieces associees)
 * 
 * Cette classe est observee par ConfigApplImpl et lui envoie les changements d'etats de la configuration courante
 * 
 * @author YMCA
 *
 */
public class ConfigurationTest extends Observable {
	
	private ConfigVoiture cv;
	
	/**
	 * Creation d'une nouvelle configuration par l'utilisateur (ConfigVoiture)
	 * @throws ResultatIncorrectException
	 * @throws ParametreIncorrectException
	 */
	public ConfigurationTest() throws ResultatIncorrectException, ParametreIncorrectException {
		this.cv = new ConfigVoiture();
	}
	
	/**
	 * L'utilisateur ajoute une piece dans sa configuration
	 * @param la piece a ajoute
	 * @throws ActionPieceInvalideException
	 * @throws ResultatIncorrectException
	 * @throws ParametreIncorrectException
	 */
	public void actionAjouterPiece(String p) throws ActionPieceInvalideException, ResultatIncorrectException, ParametreIncorrectException {
		this.cv.ajouterPiece(p);
		notifierObserver();
	}
	
	/**
	 * L'utilisateur supprime une piece de sa configuration
	 * @param la piece a supprime
	 * @throws ActionPieceInvalideException
	 * @throws ResultatIncorrectException
	 * @throws ParametreIncorrectException
	 */
	public void actionSupprimerPiece(String p) throws ActionPieceInvalideException, ResultatIncorrectException, ParametreIncorrectException {
		this.cv.supprimerPiece(p);
		notifierObserver();
	}
	
	/**
	 * Recupere la configuration en cours de l'utilisateur
	 * @return un set de pieces
	 */
	public Set<Piece> actionGetConfiguration() {
		return this.cv.getConfiguration();
	}
	
	/**
	 * L'utilisateur demande a connaitre le prix de la configuration actuelle
	 * @return le cout total de sa configuration
	 * @throws ResultatIncorrectException
	 */
	public Double actionGetPrix() throws ResultatIncorrectException{
		return this.cv.getPrix();
	}
	
	/**
	 * L'utilisateur affiche les categories restantes de sa configuration
	 * @return un set de categories
	 */
	public Set<String> actionGetCategoriesRestantes() {
		return this.cv.getCategoriesRestantes();
	}
	
	/**
	 * L'utilisateur affiche les pieces incompatibles à sa configuration courante
	 * @return un set de pieces
	 */
	public Set<Piece> actionGetPiecesIncompatibles() {
		return this.cv.getMesIncompatibilites();
	}
	
	/**
	 * L'utilisateur souhaite valider sa configuration 
	 * @return true si la configuration comporte 4 pieces compatibles (une piece dans chaque categorie), false sinon
	 */
	public boolean actionValidationConfiguration() {
		return this.cv.estComplet();
	}
	
	/**
	 * Configuration de base
	 * @throws ParametreIncorrectException 
	 * @throws ResultatIncorrectException 
	 * @throws ActionPieceInvalideException 
	 */
	public void getConfigurationStandard() throws ActionPieceInvalideException, ResultatIncorrectException, ParametreIncorrectException {
		cv.ajouterPiece("ED180");
		cv.ajouterPiece("XS");
		// Ajout de la piece IS car necessaire a la piece XS
		cv.ajouterPiece("TA5");
		
		notifierObserver();
	}
	
	/**
	 * L'utilisateur demande a recuperer les couleurs possibles pour la peinture exterieure
	 * @param le nom de la piece
	 * @return un set de couleur possible
	 * @throws ParametreIncorrectException 
	 * @throws ResultatIncorrectException 
	 */
	public Set<Couleur> actionGetCouleursPossibles(String p) throws ResultatIncorrectException, ParametreIncorrectException{
		return this.cv.getCouleursPossibles(p);
	}
	
	/**
	 * L'utilisateur souhaite changer la couleur de la piece
	 * @param p la piece
	 * @param c la couleur souhaitee
	 * @throws ResultatIncorrectException
	 * @throws ParametreIncorrectException 
	 */
	public void actionSetCouleur(String p, Couleur c) throws ResultatIncorrectException, ParametreIncorrectException {
		this.cv.setCouleur(p, c);
	}
	
	public void actionGetDescription() throws ResultatIncorrectException {
		this.cv.getDescription(null);
	}
	
	/**
	 *  Methode permettant de notifier l'observateur (ConfigAppImpl) d'un changement d'etat de la configuration de la voiture
	 */
	public void notifierObserver() {
		setChanged();
		notifyObservers();
	}

}
