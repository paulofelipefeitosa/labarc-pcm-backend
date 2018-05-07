package pcm.authentication;

import java.util.Properties;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import pcm.authentication.DefaultAuthenticationService;
import pcm.authentication.model.Token;
import pcm.core.exception.PCMException;

public class TestDefaultAuthenticationService {

	private Properties properties;
	
	private DefaultAuthenticationService authenticationService;
	
	private final String username = "fake-admin";
	private final String password = "fake-password";
	
	@Before
	public void setUp() throws PCMException {
		this.properties = new Properties();
		this.properties.put(DefaultAuthenticationService.ADMIN_USERNAME_KEY, this.username);
		this.properties.put(DefaultAuthenticationService.ADMIN_PASSWORD_KEY, this.password);
		
		this.authenticationService = new DefaultAuthenticationService(this.properties);
	}
	
	@Test(expected = PCMException.class)
	public void createAuthenticationServiceWithoutAdminUsername() throws PCMException {
		Properties properties = new Properties();
		properties.put(DefaultAuthenticationService.ADMIN_PASSWORD_KEY, this.password);
		
		new DefaultAuthenticationService(properties);
	}
	
	@Test(expected = PCMException.class)
	public void createAuthenticationServiceWithoutAdminPassword() throws PCMException {
		Properties properties = new Properties();
		properties.put(DefaultAuthenticationService.ADMIN_USERNAME_KEY, this.username);
		
		new DefaultAuthenticationService(properties);
	}
	
	@Test
	public void testCreateValidToken() throws PCMException {
		String acessId = this.authenticationService.createToken(this.username, this.password);
		
		Assert.assertNotNull(acessId);
		Assert.assertFalse(acessId.trim().isEmpty());
	}
	
	@Test(expected = PCMException.class)
	public void testCreateInvalidTokenByUsername() throws PCMException {
		this.authenticationService.createToken("invalid-username", this.password);
	}
	
	@Test(expected = PCMException.class)
	public void testCreateInvalidTokenByPassword() throws PCMException {
		this.authenticationService.createToken(this.username, "invalid-password");
	}
	
	@Test
	public void testIsTokenValidWithValidToken() throws PCMException {
		String accessId = this.authenticationService.createToken(this.username, this.password);
		
		Assert.assertTrue(this.authenticationService.isTokenValid(accessId));
	}
	
	@Test
	public void testIsTokenValidWithInvalidToken() throws PCMException {
		this.authenticationService.createToken(this.username, this.password);
		
		Assert.assertFalse(this.authenticationService.isTokenValid("fake-accessId"));
	}
	
	@Test
	public void testIsTokenValidWithTokenExpired() {
		Token token = Mockito.spy(new Token());
		Mockito.doReturn(false).when(token).isValid();
		
		this.authenticationService.addToken(token);
		
		String accessId = token.getId();
		
		Assert.assertFalse(this.authenticationService.isTokenValid(accessId));
	}
}
