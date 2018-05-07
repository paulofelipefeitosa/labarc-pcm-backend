package pcm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
}
