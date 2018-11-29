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
	 * Supprimer une piece de la configuration
	 * @param piece
	 * @return 
	 * @throws ActionPieceInvalideException 
	 * @throws ResultatNullException 
	 * @throws ParametreNullException 
	 */
	public boolean supprimerPiece (String piece) throws ActionPieceInvalideException, ResultatNullException, ParametreNullException  ;
	
	/**
	 * Ajouter une piece dans la configuration
	 * @param piece
	 * @return 
	 * @throws ResultatNullException 
	 * @throws ParametreNullException 
	 * @throws ActionPieceInvalideException 
	 */
	public boolean ajouterPiece(String p) throws ActionPieceInvalideException, ParametreNullException, ResultatNullException;
	
	/**
	 * Recupere la piece en fonction de la categorie
	 */
	public Piece getPieceCategorie(String categorie) throws ParametreNullException, ResultatNullException;

	/**
	 * Renvoie les categories presentes dans ma configuration
	 * @return
	 * @throws ResultatNullException 
	 * @throws ParametreNullException 
	 */
	//public Set<String> getConfigCategories() throws ResultatNullException, ParametreNullException;

	Set<Piece> getPiecesPossibles() throws ParametreNullException, ResultatNullException;

	Set<String> getCategoriesRestantes() throws ResultatNullException, ParametreNullException;

	

}
