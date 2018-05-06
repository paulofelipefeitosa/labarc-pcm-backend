package labarcpcmbackend.authentication;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Properties;

import labarcpcmbackend.authentication.model.Token;
import labarcpcmbackend.core.exception.PCMException;

public class DefaultAuthenticationService implements AuthenticationService {

	private static final String ADMIN_CONFIGURARION_FILE = "config/pcm.conf";
	private static final String ADMIN_USERNAME_KEY = "admin_username";
	private static final String ADMIN_PASSWORD_KEY = "admin_password";

	public static final String PCM_CONF_FILE_NOT_FOUND_MSG = "There is no pcm configuration file";

	private Collection<Token> tokens;
	private Properties properties;

	public DefaultAuthenticationService() throws PCMException, IOException {
		File file = new File(DefaultAuthenticationService.ADMIN_CONFIGURARION_FILE);
		if (!file.exists()) {
			throw new PCMException(DefaultAuthenticationService.PCM_CONF_FILE_NOT_FOUND_MSG);
		}
		InputStream inputStream = new FileInputStream(file);
		this.properties.load(inputStream);

		this.tokens = new LinkedList<Token>();
	}

	@Override
	public String createToken(String username, String userPassword) throws PCMException {
		String adminUsername = this.properties
				.getProperty(DefaultAuthenticationService.ADMIN_USERNAME_KEY);
		String adminPassword = this.properties
				.getProperty(DefaultAuthenticationService.ADMIN_PASSWORD_KEY);
		Token token = null;
		if (adminUsername.equals(username) && adminPassword.equals(userPassword)) {
			token = new Token();
			this.tokens.add(token);
		} else {
			throw new PCMException(AuthenticationService.INVALID_CREDENTIALS_MSG);
		}
		return token.getId();
	}

	@Override
	public boolean isTokenValid(String acessId) {
		this.updateValidTokens();

		boolean result = false;
		for (Token token : this.tokens) {
			String tokenId = token.getId();
			if (tokenId.equals(acessId)) {
				result = true;
			}
		}
		return result;
	}

	private void updateValidTokens() {
		for (Token token : this.tokens) {
			if (!token.isValid()) {
				this.tokens.remove(token);
			}
		}
	}
}