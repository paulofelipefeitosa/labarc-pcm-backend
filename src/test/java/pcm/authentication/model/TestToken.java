package pcm.authentication.model;

import org.junit.Assert;
import org.junit.Test;

import pcm.authentication.model.Token;

public class TestToken {
	
	@Test
	public void testToken() {
		Token token = new Token();
		
		Assert.assertFalse(token.getId().trim().isEmpty());
		Assert.assertTrue(token.isValid());
	}
	
	@Test
	public void testTokenInvalid() throws InterruptedException {
		Token token = new Token();
		token.setValidity(System.currentTimeMillis());
		Thread.sleep(100);
		
		Assert.assertFalse(token.isValid());
	}
}
