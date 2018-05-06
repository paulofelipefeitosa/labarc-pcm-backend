package labarcpcmbackend.authentication;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Properties;

import labarcpcmbackend.authentication.model.Token;
import labarcpcmbackend.core.exception.PCMException;

public class DefaultAuthenticationService implements AuthenticationService {

	private static final String ADMIN_USERNAME_KEY = "admin_username";
	private static final String ADMIN_PASSWORD_KEY = "admin_password";

	private Collection<Token> tokens;
	private Properties properties;

	public DefaultAuthenticationService(Properties properties) throws PCMException {
		this.properties = properties;

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