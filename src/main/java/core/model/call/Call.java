package core.model.call;

import java.util.UUID;

/**
 * 
 * @author paulofelipe
 *
 */
public class Call implements Comparable<Call> {

	private String id;
	private Integer machineNumber;
	private String lccUsername;
	private Long createAt;

	public Call(Integer machineNumber, String lccUsername) {
		this.id = UUID.randomUUID().toString();
		this.machineNumber = machineNumber;
		this.lccUsername = lccUsername;
		this.createAt = System.currentTimeMillis();
	}

	public String getId() {
		return id;
	}

	public Integer getMachineNumber() {
		return machineNumber;
	}

	public String getLccUsername() {
		return lccUsername;
	}

	public Long getCreateAt() {
		return createAt;
	}

	@Override
	public int compareTo(Call otherCall) {
		int result = 0;
		if (this.createAt < otherCall.getCreateAt()) {
			result = -1;
		} else if (this.createAt > otherCall.getCreateAt()) {
			result = 1;
		}
		return result;
	}

}
