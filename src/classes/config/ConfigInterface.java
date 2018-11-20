package classes.config;

import classes.categorie.Categorie;
import classes.piece.Piece;

public interface ConfigInterface {

	/**
	 * Verifie que la configuration courante est complete (toutes les categories)
	 * @return
	 */
	public boolean estComplet();

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
	 */
	public void supprimerPiece (Piece piece);
	
	/**
	 * Ajouter une piece dans la configuration
	 * @param piece
	 */
	public void ajouterPiece (Piece piece);
	
	/**
	 * Recupere les pieces selon categorie
	 */
	public void getPieces(Categorie categorie);

}
