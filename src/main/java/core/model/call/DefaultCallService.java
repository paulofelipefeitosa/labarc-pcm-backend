package core.model.call;

import java.util.Collection;
import java.util.PriorityQueue;

import core.model.PCMException;

/**
 * 
 * @author pfelipe
 *
 */
public class DefaultCallService implements CallService {
	Collection<Call> callQueue;

	public DefaultCallService() {
		this.callQueue = new PriorityQueue<Call>();
	}

	@Override
	public Collection<Call> getAllCalls() {
		return this.callQueue;
	}

	@Override
	public void deleteCall(String callId) throws PCMException {
		Call callToBeDeleted = null;
		for (Call call : this.callQueue) {
			String queueCallId = call.getId();
			if (queueCallId.equals(callId)) {
				callToBeDeleted = call;
				break;
			}
		}
		if (callToBeDeleted == null) {
			throw new PCMException(CallService.CALL_DOES_NOT_EXIST + " with id [" + callId + "]");
		}
		this.callQueue.remove(callToBeDeleted);
	}

	@Override
	public void deleteAllCalls() throws PCMException {
		this.callQueue = new PriorityQueue<Call>();
	}

	@Override
	public void addCall(Call call) throws PCMException {
		if (this.callQueue.contains(call)) {
			throw new PCMException(CallService.CALL_ALREADY_EXIST);
		}
		this.callQueue.add(call);
	}
}
