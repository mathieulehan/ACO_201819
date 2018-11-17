package classes.config;

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
}
