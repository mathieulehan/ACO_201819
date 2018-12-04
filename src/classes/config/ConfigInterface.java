package classes.config;

import java.util.Set;

import classes.piece.Piece;
import exceptions.ActionPieceInvalideException;
import exceptions.ParametreIncorrectException;
import exceptions.ResultatIncorrectException;

public interface ConfigInterface {

	/**
	 * Verifie que la configuration courante est complete (toutes les categories)
	 * @return
	 * @throws ParametreIncorrectException 
	 * @throws ResultatIncorrectException 
	 */
	public boolean estComplet() throws ResultatIncorrectException, ParametreIncorrectException;

	/**
	 * Verifie que la configuration courante est valide
	 * @return
	 */
	public boolean estValide();
	
	/**
	 * Verifie que la configuration courante est observable
	 * @return
	 */
	public boolean estObservable();
	
	/**
	 * Supprimer une piece de ma configuration
	 * @param piece
	 * @return true si la piece est supprimee de ma configuration, false sinon
	 * @throws ActionPieceInvalideException 
	 * @throws ResultatIncorrectException 
	 * @throws ParametreIncorrectException si la piece n'est pas dans ma configuration
	 */
	public boolean supprimerPiece (String piece) throws ActionPieceInvalideException, ResultatIncorrectException, ParametreIncorrectException  ;
	
	/**
	 * Ajouter une piece dans ma configuration
	 * @param piece
	 * @return true si la piece est ajoutee a la configuration, false sinon
	 * @throws ResultatIncorrectException 
	 * @throws ParametreIncorrectException
	 * @throws ActionPieceInvalideException si la piece est deja dans ma configuration
	 */
	public boolean ajouterPiece(String p) throws ActionPieceInvalideException, ParametreIncorrectException, ResultatIncorrectException;

	/**
	 * Renvoie un set de categories non presentes dans ma configuration
	 * @return un set de categories
	 */
	public Set<String> getCategoriesRestantes() throws ResultatIncorrectException;
	
	/**
	 * Renvoie une piece par categorie
	 * @throws ResultatIncorrectException si aucune piece n'a ete choisi dans cette categorie
	 * @throws ParametreIncorrectException si categorie inexistante
	 */
	public Piece getPieceParCategorie(String categorie) throws ParametreIncorrectException, ResultatIncorrectException;

	/**
	 * Recupere un set de toutes les pieces que l'on peut encore ajouter a ma configuration
	 * @return un set de pieces
	 * @throws ParametreIncorrectException
	 * @throws ResultatIncorrectException
	 */
	public Set<Piece> getPiecesPossibles() throws ParametreIncorrectException, ResultatIncorrectException;
}
