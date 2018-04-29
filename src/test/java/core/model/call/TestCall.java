package core.model.call;

import org.junit.Assert;
import org.junit.Test;

public class TestCall {

	@Test
	public void testCall() {
		Call call1 = new Call(1, "lccusername1");
		Call call2 = new Call(23, "lccusername2");
		
		Assert.assertEquals(new Integer(1), call1.getMachineNumber());
		Assert.assertEquals("lccusername2", call2.getLccUsername());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCallNullMachineNumber() {
		new Call(null, "lccusername1");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCallLowerBoundInvalidMachineNumber() {
		new Call(0, "lccusername1");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCallUpperBoundInvalidMachineNumber() {
		new Call(24, "lccusername1");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCallEmptyLCCUsername() {
		new Call(1, "  ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCallNullLCCUsername() {
		new Call(1, null);
	}
}
