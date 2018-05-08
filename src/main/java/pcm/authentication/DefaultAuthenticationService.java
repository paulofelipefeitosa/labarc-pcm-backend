package pcm.authentication;

import java.util.Collection;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pcm.PCMProperties;
import pcm.authentication.model.Token;
import pcm.core.exception.PCMException;

@Component
public class DefaultAuthenticationService implements AuthenticationService {

	private Collection<Token> tokens;

	private PCMProperties properties;

	@Autowired
	public DefaultAuthenticationService(PCMProperties properties) throws PCMException {
		this.properties = properties;

		this.tokens = new LinkedList<Token>();
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