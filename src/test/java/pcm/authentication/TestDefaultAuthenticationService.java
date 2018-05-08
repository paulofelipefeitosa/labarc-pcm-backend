package pcm.authentication;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import pcm.PCMProperties;
import pcm.authentication.DefaultAuthenticationService;
import pcm.authentication.model.Token;
import pcm.core.exception.PCMException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDefaultAuthenticationService {

	@Autowired
	private DefaultAuthenticationService authenticationService;

	@Autowired
	private PCMProperties properties;

	@Test
	public void testCreateValidToken() throws PCMException {
		String acessId = this.authenticationService.createToken(this.properties.getAdminUsername(),
				this.properties.getAdminPassword());

		Assert.assertNotNull(acessId);
		Assert.assertFalse(acessId.trim().isEmpty());
	}

	@Test(expected = PCMException.class)
	public void testCreateInvalidTokenByUsername() throws PCMException {
		this.authenticationService.createToken("invalid-username",
				this.properties.getAdminPassword());
	}

	@Test(expected = PCMException.class)
	public void testCreateInvalidTokenByPassword() throws PCMException {
		this.authenticationService.createToken(this.properties.getAdminUsername(),
				"invalid-password");
	}

	@Test
	public void testIsTokenValidWithValidToken() throws PCMException {
		String accessId = this.authenticationService.createToken(this.properties.getAdminUsername(),
				this.properties.getAdminPassword());

		Assert.assertTrue(this.authenticationService.isTokenValid(accessId));
	}

	@Test
	public void testIsTokenValidWithInvalidToken() throws PCMException {
		this.authenticationService.createToken(this.properties.getAdminUsername(),
				this.properties.getAdminPassword());

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
