package core.model.call;

import java.util.Collection;

import core.model.PCMException;

/**
 * 
 * @author pfelipe
 *
 */
public interface CallService {
	
	public static final String CALL_ALREADY_EXIST = "Call already exist";
	public static final String CALL_DOES_NOT_EXIST = "There is no call";

	public void addCall(Call call) throws PCMException;
	
	public Collection<Call> getAllCalls();
	
	public void deleteCall(String callId) throws PCMException;
	
	public void deleteAllCalls() throws PCMException;
}
