package pcm.rest;

import java.util.Collection;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pcm.core.PCMApplication;
import pcm.core.exception.PCMException;
import pcm.core.model.call.Call;

@CrossOrigin
@RestController
@RequestMapping(value = "users/calls")
public class UsersCallsController {
	
	private PCMApplication pcmApplication;

	public UsersCallsController() throws PCMException {
		this.pcmApplication = new PCMApplication();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public Collection<Call> getCalls() {
		return this.pcmApplication.getAllCalls();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Call createCall(@RequestBody Call call) throws PCMException {
		this.pcmApplication.addCall(call);
		return call;
	}
}