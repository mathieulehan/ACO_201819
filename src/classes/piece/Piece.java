package classes.piece;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;

import exceptions.ParametreIncorrectException;

/**
 * Classe possedant des methodes permettant de gerer les pieces
 * @author YMCA
 *
 */
public class Piece implements ProprieteManagerInterface, GestionCompatibilite, VerifCompatibilite {

	private String nom;
	private String description;
	private Set<Piece> incompatibilites;
	private Set<Piece> necessites;
	public enum Couleur{ROUGE, BLANC, BLEU};
	
	private class Propriete{
		public final Supplier<String> getter;
		public final Consumer<String> setter;
		public final Set<String> valeursPossibles;
		public Propriete(Supplier<String> getter, Consumer<String> setter, Set<String> valeursPossibles){
			this.getter = getter;
			this.setter = setter;
			this.valeursPossibles = valeursPossibles;
		}
	}
	
	private Map<String, Propriete> proprietes = new HashMap<>();
	
	/**
	 * Permet d'ajouter une propriete, utilisee pour la couleur de la peinture
	 * @param nom
	 * @param getter
	 * @param setter
	 * @param valeursPossibles
	 */
	protected void ajoutPropriete(String nom, Supplier<String> getter, Consumer<String> setter, Set<String> valeursPossibles) {
		proprietes.put(nom, new Propriete(getter, setter, valeursPossibles));
	}
	
	/**
	 * Retourne un set des noms de proprietes gerees par le manager
	 */
	@Override
	public Set<String> getNomsProprietes(){
		return Collections.unmodifiableSet(proprietes.keySet());
	}
	
	/**
	 * Retourne la valeur de la propriete
	 * Si l'objet ne gere pas cette propriete, return vide
	 * @param nomPropriete
	 * @return
	 */
	@Override
	public Optional<String> getPropriete(String nomPropriete){
		Objects.requireNonNull(nomPropriete);
		if(proprietes.containsKey(nomPropriete)) {
			return Optional.of(proprietes.get(nomPropriete).getter.get());
		}
		return Optional.empty();
	}
	
	/**
	 * Met en place la valeur de la propriete passee en parametre
	 * Si la propriete n'existe pas (ou non modifiable), ou si la valeur est invalide,
	 * return une exception IllegalArgumentException
	 * @param nomPropriete
	 * @param valeurPropriete
	 * @throws IllegalArgumentException
	 */
	@Override
	public void setPropriete(String nomPropriete, String valeurPropriete) {
		Objects.requireNonNull(nomPropriete);
		Objects.requireNonNull(valeurPropriete);
		if((proprietes.containsKey(nomPropriete)) && (proprietes.get(nomPropriete).setter != null) && getValeursProprietePossibles(nomPropriete).contains(valeurPropriete)) {
			proprietes.get(nomPropriete).setter.accept(valeurPropriete);
		}
		else {
			throw new IllegalArgumentException("nom ou valeur invalide pour : " + nomPropriete);
		}
	}
	
	/**
	 * Retourne un set des valeurs de la propriete en parametre
	 * Pour les proprietes qui n'ont pas de valeurs possibles ou pour un
	 * nom de propriete inexistant, retourne un set vide
	 * 
	 * @param nomPropriete
	 * @return immutable set
	 */
	@Override
	public Set<String> getValeursProprietePossibles(String nomPropriete){
		if(proprietes.containsKey(nomPropriete)) {
			return Collections.unmodifiableSet(proprietes.get(nomPropriete).valeursPossibles);
		}
		return Collections.emptySet();
	}
	
	/**
	 * Constructeur
	 * @param nom le nom de la piece
	 * @param description la description de la piece
	 * @throws ParametreIncorrectException si nom de piece deja existant 
	 */
	public Piece(String nom, String description) throws ParametreIncorrectException {
		this.incompatibilites = new HashSet<>();
		this.necessites = new HashSet<>();
		String nouveauNom = Objects.requireNonNull(nom);
		String nouvelleDescription = Objects.requireNonNull(description);
		if(nouveauNom == "") throw new ParametreIncorrectException("Le nom de la nouvelle piece est incorrect");
		for(Piece p : TypePiece.getPieces()) {
			if(nouveauNom == p.getNom()) {
				throw new ParametreIncorrectException("Le nom de la nouvelle piece existe deja ");
			}
		}
		this.nom = nouveauNom;
		this.description = nouvelleDescription;
	}

	/**
	 * @return le nom de la piece
	 */
	public String getNom() {
		return this.nom;
	}
	
	/**
	 * Change le nom de la piece
	 * @param nom
	 * @throws ParametreIncorrectException
	 */
	public void setNom(String nom) throws ParametreIncorrectException {
		String nouveauNom = Objects.requireNonNull(nom);
		if(nouveauNom == "") throw new ParametreIncorrectException("Le nom de la nouvelle piece est incorrect");
		for(Piece p : TypePiece.getPieces()) {
			if(nouveauNom == p.getNom()) {
				throw new ParametreIncorrectException("Le nom de la nouvelle piece existe deja");
			}
		}
		this.nom = nouveauNom;
	}

	/**
	 * @return la description de la piece
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * @param description la description souhaitee pour la piece
	 */
	public void setDescription(String description) {
		String nouvelleDescription = Objects.requireNonNull(description);
		this.description = nouvelleDescription;
	}

	/**
	 * @param piecesIncomp les incompatibilites a ajouter a la piece
	 */
	@Override
	public void setIncompatibilites(Set<Piece> piecesIncomp){
		Set<Piece> nouvellesIncompatibilites = Objects.requireNonNull(piecesIncomp);
		this.incompatibilites = nouvellesIncompatibilites;
	}

	/**
	 * @return le set de pieces incompatibles
	 */
	@Override
	public Set<Piece> getIncompatibilites() {
		return this.incompatibilites;
	}

	/**
	 * Ajout d'une incompatibilite
	 * @param une incompatibilite (non null) a ajouter a la piece
	 * @throws ParametreIncorrectException 
	 */
	@Override
	public void ajoutIncompatibilite(Piece incompatibilite) throws ParametreIncorrectException {
		Piece nouvelleIncompatilibite = Objects.requireNonNull(incompatibilite);
		if (this.incompatibilites.contains(nouvelleIncompatilibite)) throw new ParametreIncorrectException("L'incompatibilite en parametre existe deja pour cette piece");
		this.incompatibilites.add(nouvelleIncompatilibite);
	}

	/**
	 * Suppression d'une incompatibilite
	 * @param une incompatibilite que l'on souhaite supprimer pour la piece
	 * @throws ParametreIncorrectException si la piece ne possede pas cette incompatibilite
	 */
	@Override
	public void suppressionIncompatibilite(Piece incompatibilite) throws ParametreIncorrectException {
		Piece ancienneIncompatilibite = Objects.requireNonNull(incompatibilite);
		if (!this.incompatibilites.contains(ancienneIncompatilibite)) throw new ParametreIncorrectException("L'incompatibilite en parametre n'existe pas pour cette piece");
		this.incompatibilites.remove(ancienneIncompatilibite);
	}

	/**
	 * @param le Set de necessites a ajouter a la piece
	 */
	@Override
	public void setNecessites(Set<Piece> necessites) {
		Set<Piece> nouvellesNecessites = Objects.requireNonNull(necessites);
		this.necessites = nouvellesNecessites;
	}

	/**
	 * @return le Set de necessites de la piece
	 */
	@Override
	public Set<Piece> getNecessites() {
		return this.necessites;
	}

	/**
	 * Ajout d'une necessite
	 * @param la necessite a ajouter a la piece
	 * @throws ParametreIncorrectException 
	 */
	@Override
	public void ajoutNecessite(Piece necessite) throws ParametreIncorrectException  {
		Piece nouvelleNecessite = Objects.requireNonNull(necessite);
		if (this.necessites.contains(nouvelleNecessite)) throw new ParametreIncorrectException("La necessite en parametre existe deja pour cette piece");
		this.necessites.add(nouvelleNecessite);
	}

	/**
	 * Suppression d'une necessite
	 * @param la necessite a supprimer de la piece
	 * @throws ParametreIncorrectException si la piece n'a pas la necessite en parametre
	 */
	@Override
	public void suppressionNecessite(Piece necessite) throws ParametreIncorrectException {
		Piece ancienneNecessite = Objects.requireNonNull(necessite);
		if (!this.necessites.contains(ancienneNecessite)) throw new ParametreIncorrectException("La necessite en parametre n'existe pas pour cette piece");
		this.necessites.remove(ancienneNecessite);
	}

	/**
	 * On verifie qu'une piece n'est pas incompatible avec une autre piece
	 * @return true si la piece n'est pas presente dans les incompatibilites, false sinon
	 */
	public boolean estIncompatible (Piece piece) {
		Piece autrePiece = Objects.requireNonNull(piece);
		return this.incompatibilites.contains(autrePiece);
	}
	
	public double getPrix() {
		return 0;
	}

}
