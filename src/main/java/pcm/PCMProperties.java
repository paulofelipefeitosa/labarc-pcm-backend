package pcm;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import pcm.core.exception.PCMException;

@Component
public class PCMProperties {

	@Value("${admin_username}")
	private String adminUsername;

	@Value("${admin_password}")
	private String adminPassword;

	public String getAdminUsername() {
		return adminUsername;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	@PostConstruct
	private void checkProperties() throws PCMException {
		if (this.adminUsername == null || this.adminUsername.trim().isEmpty()) {
			throw new PCMException("There is no admin_username configuration in pcm config file");
		}
		if (this.adminPassword == null || this.adminPassword.trim().isEmpty()) {
			throw new PCMException("There is no admin_password configuration in pcm config file");
		}
	}
}
