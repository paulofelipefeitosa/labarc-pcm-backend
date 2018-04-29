package core.model.call;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import core.model.PCMException;

/**
 * 
 * @author pfelipe
 *
 */
public class DefaultCallService implements CallService {
	private List<Call> calls;

	public DefaultCallService() {
		this.calls = Collections.synchronizedList(new ArrayList<Call>());
	}

	@Override
	public synchronized Collection<Call> getAllCalls() {
		Collections.sort(this.calls);
		return this.calls;
	}

	@Override
	public synchronized void deleteCall(String callId) throws PCMException {
		Call callToBeDeleted = null;
		for (Call call : this.calls) {
			String queueCallId = call.getId();
			if (queueCallId.equals(callId)) {
				callToBeDeleted = call;
				break;
			}
		}
		if (callToBeDeleted == null) {
			throw new PCMException(CallService.CALL_DOES_NOT_EXIST + " with id [" + callId + "]");
		}
		this.calls.remove(callToBeDeleted);
	}

	@Override
	public synchronized void deleteAllCalls() throws PCMException {
		this.calls = new ArrayList<Call>();
	}

	@Override
	public synchronized void addCall(Call call) throws PCMException {
		if (call == null) {
			throw new PCMException(CallService.INVALID_CALL);
		}
		if (this.calls.contains(call)) {
			throw new PCMException(CallService.CALL_ALREADY_EXIST);
		}
		this.calls.add(call);
	}
}
