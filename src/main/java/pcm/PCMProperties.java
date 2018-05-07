package pcm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:config/pcm.conf")
public class PCMProperties {
	
	@Value("${admin-username}")
	private String adminUsername;
	
	@Value("${admin-password}")
	private String adminPassword;
	
	public String getAdminUsername() {
		return adminUsername;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
}
