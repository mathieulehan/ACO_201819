package classes.config;

import java.util.List;
import java.util.Objects;

//observable
public class ConfigurationTest {
	
	private List<ConfigAppImpl> configs;
	
	public void ajouterConfigApp(ConfigAppImpl conf) {
		Objects.requireNonNull(conf, conf+" cannot be null");
		if (configs.contains(conf)) {
			throw new IllegalArgumentException(conf+" is registered already");
		}
		configs.add(conf);
	}
	
	public void supprimerConfigApp(ConfigAppImpl conf) {
		Objects.requireNonNull(conf, conf+" cannot be null");
		if (!configs.contains(conf)) {
			throw new IllegalArgumentException(conf+" is not registered");
		}
		configs.remove(conf);
	}

	public void notifierConfigApp() {
		for (ConfigAppImpl conf : configs) {
			conf.notificationConfigTest(this);
		}
	}

}
