package classes.piece;

public interface GestionCompatibilite {

	public void setIncompatibilite(String piece);
	
	public void ajouterIncompatibilite(String piece);
	
	public void supprimerIncompatibilite(String piece);
	
	
	public void setNecessite(String piece);
	
	public void ajouterNecessite(String piece);
	
	public void supprimerNecessite(String piece);
}
