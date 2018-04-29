package core.model.call;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import core.model.PCMException;

public class TestDefaultCallService {
	private CallService callService;

	@Before
	public void setUp() {
		this.callService = new DefaultCallService();
	}

	@Test
	public void testAddCall() throws PCMException {
		Call call = new Call(1, "lccusername");
		this.callService.addCall(call);
		Assert.assertEquals(1, this.callService.getAllCalls().size());
	}

	@Test(expected = PCMException.class)
	public void testAddExistingCall() throws PCMException {
		Call call = new Call(1, "lccusername");
		this.callService.addCall(call);
		this.callService.addCall(call);
	}

	@Test(expected = PCMException.class)
	public void testAddNullCall() throws PCMException {
		this.callService.addCall(null);
	}

	@Test
	public void testGetAllCalls() throws PCMException {
		Call call1 = new Call(1, "lccusername1");
		Call call2 = new Call(2, "lccusername2");
		Call call3 = new Call(3, "lccusername3");

		this.callService.addCall(call1);
		this.callService.addCall(call2);

		Assert.assertEquals(2, this.callService.getAllCalls().size());

		this.callService.addCall(call3);

		Assert.assertEquals(3, this.callService.getAllCalls().size());
	}

	@Test
	public void testDeleteCall() throws PCMException {
		Call call = new Call(1, "lccusername");
		this.callService.addCall(call);

		Assert.assertEquals(1, this.callService.getAllCalls().size());

		String callId = call.getId();

		this.callService.deleteCall(callId);

		Assert.assertEquals(0, this.callService.getAllCalls().size());
	}

	@Test(expected = PCMException.class)
	public void testDeleteCallThatDoesNotExist() throws PCMException {
		Call call = new Call(1, "lccusername");
		this.callService.addCall(call);

		Assert.assertEquals(1, this.callService.getAllCalls().size());

		Call call2 = new Call(2, "lccusername");
		String callId = call2.getId();

		this.callService.deleteCall(callId);
	}

	@Test(expected = PCMException.class)
	public void testDeleteCallWithNullCallId() throws PCMException {
		Call call = new Call(1, "lccusername");
		this.callService.addCall(call);

		Assert.assertEquals(1, this.callService.getAllCalls().size());

		this.callService.deleteCall(null);
	}

	@Test(expected = PCMException.class)
	public void testDeleteCallWithEmptyCallId() throws PCMException {
		Call call = new Call(1, "lccusername");
		this.callService.addCall(call);

		Assert.assertEquals(1, this.callService.getAllCalls().size());

		this.callService.deleteCall("");
	}

	@Test(expected = PCMException.class)
	public void testDeleteCallWithEmptyCallInService() throws PCMException {
		Call call = new Call(1, "lccusername");

		this.callService.deleteCall(call.getId());
	}

	@Test
	public void testDeleteAllCalls() throws PCMException {
		Call call = new Call(1, "lccusername");
		this.callService.addCall(call);

		Assert.assertEquals(1, this.callService.getAllCalls().size());

		this.callService.deleteAllCalls();

		Assert.assertEquals(0, this.callService.getAllCalls().size());
	}

	@Test
	public void testDeleteAllCallsWithEmptyCallService() throws PCMException {
		Assert.assertEquals(0, this.callService.getAllCalls().size());

		this.callService.deleteAllCalls();

		Assert.assertEquals(0, this.callService.getAllCalls().size());
	}
}
