package pcm.authentication;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Properties;

import pcm.authentication.model.Token;
import pcm.core.exception.PCMException;

public class DefaultAuthenticationService implements AuthenticationService {

	protected static final String ADMIN_USERNAME_KEY = "admin_username";
	protected static final String ADMIN_PASSWORD_KEY = "admin_password";

	private Collection<Token> tokens;
	private Properties properties;

	public DefaultAuthenticationService(Properties properties) {
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
	public boolean isTokenValid(String accessId) {
		this.updateValidTokens();

		Token token = this.getTokenByAccessId(accessId);
		boolean result = false;
		if (token != null) {
			result = true;
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

	private Token getTokenByAccessId(String accessId) {
		Token result = null;
		for (Token token : this.tokens) {
			String tokenId = token.getId();
			if (tokenId.equals(accessId)) {
				result = token;
			}
		}
		return result;
	}
	
	/**
	 * Method created for test.
	 * @param token
	 */
	protected void addToken(Token token) {
		this.tokens.add(token);
	}
}