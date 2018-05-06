package labarcpcmbackend.core.model;

import java.util.Collection;

import labarcpcmbackend.core.exception.PCMException;
import labarcpcmbackend.core.model.call.Call;

public interface CallService {
	public static final String CALL_ALREADY_EXIST = "Call already exist";
	public static final String CALL_DOES_NOT_EXIST = "There is no call";
	public static final String INVALID_CALL = "Invalid call";

	public void addCall(Call call) throws PCMException;
	
	public Collection<Call> getAllCalls();
	
	public void deleteCall(String callId) throws PCMException;
	
	public void deleteAllCalls() throws PCMException;
}
