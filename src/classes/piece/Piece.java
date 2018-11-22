package classes.piece;

import java.util.HashSet;
import java.util.Set;

import classes.categorie.Categorie;
import classes.config.ConfigVoiture;
import exceptions.PasDIncompatibilitesException;

/**
 * Classe possedant des methodes permettant de gerer les pieces
 * @author mathi & chach44 & yann
 *
 */
public class Piece implements PieceInterface, GestionCompatibilite, VerifCompatibilite {

	private String nom;
	private String description;
	private Set<Piece> incompatibilites;
	private Set<Piece> necessites;

	/**
	 * Constructeur
	 * @param nom le nom de la piece
	 * @param description la descriptiond de la piece
	 */
	public Piece(String nom, String description) {
		this.nom = nom;
		this.description = description;
		this.incompatibilites = new HashSet<Piece>();
		this.necessites = new HashSet<Piece>();
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategorie() {
		Categorie cat = new Categorie();
		cat.initialiserCategorie();
		for (String categorie : cat.getCategories()) {
			if(cat.getCategorie(categorie).contains(this.nom)) {
				return categorie;
			}
		}
		return null;
	}

	@Override
	public void setIncompatibilites(Set<Piece> incompatibilites) {
		this.incompatibilites = incompatibilites;
	}
	
	@Override
	public Set<Piece> getIncompatibilites() {
		return incompatibilites;
	}
	
	/**
	 * Ajout d'une incompatibilite
	 */
	@Override
	public void ajoutIncompatibilite(Piece incompatibilite) {
		this.incompatibilites.add(incompatibilite);
	}

	/**
	 * Suppression d'une incompatibilite
	 */
	@Override
	public void suppressionIncompatibilite(Piece incompatibilite) throws PasDIncompatibilitesException {
		this.incompatibilites.remove(incompatibilite);
	}

	@Override
	public void setNecessites(Set<Piece> necessites) {
		this.necessites = necessites;
	}
	
	@Override
	public Set<Piece> getNecessites() {
		return necessites;
	}
	
	/**
	 * Ajout d'une necessite
	 */
	@Override
	public void ajoutNecessite(Piece necessite) {
		this.necessites.add(necessite);
	}

	/**
	 * Suppression d'une necessite
	 */
	@Override
	public void suppressionNecessite(Piece necessite) throws PasDIncompatibilitesException {
		this.necessites.remove(necessite);
	}

	/**
	 * On verifie que la piece 
	 * @param nomPiece
	 * @return
	 */
	@Override
	public boolean verification (Piece piece) {
		return ConfigVoiture.mesIncompatibilites.contains(piece);
	}
}
