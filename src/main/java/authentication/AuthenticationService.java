package authentication;

import core.model.PCMException;

public interface AuthenticationService {
	
	public static final String INVALID_CREDENTIALS_MSG = "Invalid Credentials";
	
	public String createToken(String username, String userPassword) throws PCMException;
	
	public boolean isTokenValid(String acessId);
}
