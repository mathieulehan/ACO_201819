package classes.config;

import java.util.List;
import java.util.Objects;

import classes.categorie.Categorie;
import classes.piece.Piece;

//observeur de configurationTest
//observable de configVoiture
/**
 * Classe contenant les donnees : rassemblement de donnees (= de pieces)
 * - Point d'entree de l'application
 * - Recuperer configuration courante + categories + pieces associees
 * 
 * @author Charlotte
 *
 */
public class ConfigAppImpl implements Configuration {

	public ConfigVoiture getConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}
	
private List<ConfigVoiture> configs;
	
	public void notificationConfigTest(ConfigurationTest conf) {
		//modifier ce dont on a besoin à l'aide de la conf
		
	}
	
	public void ajouterConfigApp(ConfigVoiture conf) {
		Objects.requireNonNull(conf, conf+" cannot be null");
		if (configs.contains(conf)) {
			throw new IllegalArgumentException(conf+" is registered already");
		}
		configs.add(conf);
	}
	
	public void supprimerConfigApp(ConfigVoiture conf) {
		Objects.requireNonNull(conf, conf+" cannot be null");
		if (!configs.contains(conf)) {
			throw new IllegalArgumentException(conf+" is not registered");
		}
		configs.remove(conf);
	}

	public void notifierConfigApp() {
		for (ConfigVoiture conf : configs) {
			conf.notificationConfigTest(this);
		}
	}

	@Override
	public boolean estComplet() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean estValide() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean estObservable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void supprimerPiece(Piece piece) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ajouterPiece(Piece piece) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getPieces(Categorie categorie) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notificationConfigTest(ConfigAppImpl conf) {
		// TODO Auto-generated method stub
		
	}

}
