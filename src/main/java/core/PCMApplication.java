package core;

import authentication.AuthenticationService;
import core.model.call.CallService;

/**
 * 
 * @author pfelipe
 *
 */
public class PCMApplication {
	private AuthenticationService authenticationService;
	private CallService callService;

	public PCMApplication(AuthenticationService authenticationService, CallService callService) {
		this.authenticationService = authenticationService;
		this.callService = callService;
	}
}
