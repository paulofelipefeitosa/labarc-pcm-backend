package pcm.core;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pcm.authentication.AuthenticationService;
import pcm.authentication.DefaultAuthenticationService;
import pcm.core.exception.PCMException;
import pcm.core.model.CallService;
import pcm.core.model.DefaultCallService;
import pcm.core.model.call.Call;

@Component
public class PCMApplication {

	private AuthenticationService authenticationService;
	private CallService callService;

	@Autowired
	public PCMApplication(DefaultAuthenticationService authenticationService) throws PCMException {
		this.authenticationService = authenticationService;
		this.callService = new DefaultCallService();
	}

	public Collection<Call> getAllCalls() {
		return this.callService.getAllCalls();
	}

	public void addCall(Call call) throws PCMException {
		this.callService.addCall(call);
	}

	public void deleteCall(String callId) throws PCMException {
		this.callService.deleteCall(callId);
	}

	public void deleteAllCalls() throws PCMException {
		this.callService.deleteAllCalls();
	}

	public String createToken(String username, String password) throws PCMException {
		String accessId = this.authenticationService.createToken(username, password);
		return accessId;
	}

	public boolean isTokenValid(String accessId) {
		return this.authenticationService.isTokenValid(accessId);
	}
}
