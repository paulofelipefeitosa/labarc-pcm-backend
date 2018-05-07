package pcm.authentication;

import java.util.Collection;
import java.util.LinkedList;

import pcm.PCMProperties;
import pcm.authentication.model.Token;
import pcm.core.exception.PCMException;

public class DefaultAuthenticationService implements AuthenticationService {

	protected static final String ADMIN_USERNAME_KEY = "adminUsername";
	protected static final String ADMIN_PASSWORD_KEY = "adminPassword";

	private Collection<Token> tokens;

	private PCMProperties properties;

	@PostConstruct
	public DefaultAuthenticationService() throws PCMException {
		this.properties = new PCMProperties();
		this.checkProperties(this.properties);

		this.tokens = new LinkedList<Token>();
	}

	private void checkProperties(PCMProperties properties) throws PCMException {
		String adminUsername = properties.getAdminUsername();
		if (adminUsername == null) {
			throw new PCMException("There is no admin_username configuration in pcm config file");
		}
		String adminPassword = properties.getAdminPassword();
		if (adminPassword == null) {
			throw new PCMException("There is no admin_password configuration in pcm config file");
		}
	}

	@Override
	public String createToken(String username, String userPassword) throws PCMException {
		String adminUsername = this.properties.getAdminUsername();
		String adminPassword = this.properties.getAdminPassword();
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
	 * 
	 * @param token
	 */
	protected void addToken(Token token) {
		this.tokens.add(token);
	}
}