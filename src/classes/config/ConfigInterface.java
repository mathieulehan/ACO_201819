package classes.config;

import java.util.Set;

import classes.piece.Piece;
import exceptions.ActionPieceInvalideException;
import exceptions.ParametreNullException;
import exceptions.ResultatNullException;

public interface ConfigInterface {

	/**
	 * Verifie que la configuration courante est complete (toutes les categories)
	 * @return
	 * @throws ParametreNullException 
	 * @throws ResultatNullException 
	 */
	public boolean estComplet() throws ResultatNullException, ParametreNullException;

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
	 * @throws ResultatNullException 
	 * @throws ParametreNullException si la piece n'est pas dans ma configuration
	 */
	public boolean supprimerPiece (String piece) throws ActionPieceInvalideException, ResultatNullException, ParametreNullException  ;
	
	/**
	 * Ajouter une piece dans ma configuration
	 * @param piece
	 * @return true si la piece est ajoutee a la configuration, false sinon
	 * @throws ResultatNullException 
	 * @throws ParametreNullException
	 * @throws ActionPieceInvalideException si la piece est deja dans ma configuration
	 */
	public boolean ajouterPiece(String p) throws ActionPieceInvalideException, ParametreNullException, ResultatNullException;

	/**
	 * Renvoie un set de categories non presentes dans ma configuration
	 * @return un set de categories
	 */
	public Set<String> getCategoriesRestantes() throws ResultatNullException;
	
	/**
	 * Renvoie une piece par categorie
	 * @throws ResultatNullException si aucune piece n'a ete choisi dans cette categorie
	 * @throws ParametreNullException si categorie inexistante
	 */
	public Piece getPieceParCategorie(String categorie) throws ParametreNullException, ResultatNullException;

	/**
	 * Recupere un set de toutes les pieces que l'on peut encore ajouter a ma configuration
	 * @return un set de pieces
	 * @throws ParametreNullException
	 * @throws ResultatNullException
	 */
	public Set<Piece> getPiecesPossibles() throws ParametreNullException, ResultatNullException;
}
