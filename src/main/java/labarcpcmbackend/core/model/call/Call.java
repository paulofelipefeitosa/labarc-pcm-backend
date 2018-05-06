package labarcpcmbackend.core.model.call;

import java.util.UUID;

public class Call implements Comparable<Call> {
	public static final String MACHINE_NUMBER_INVALID_MSG = "Machine number should be between 1 and 23";
	public static final String LCC_USERNAME_INVALID_MSG = "The LCC username should not be null or empty";

	private String id;
	private Integer machineNumber;
	private String lccUsername;
	private Long createAt;

	public Call(Integer machineNumber, String lccUsername) {
		this.verifyArguments(machineNumber, lccUsername);
		
		this.id = UUID.randomUUID().toString();
		this.machineNumber = machineNumber;
		this.lccUsername = lccUsername;
		this.createAt = System.currentTimeMillis();
	}

	private void verifyArguments(Integer machineNumber, String lccUsername) {
		if (machineNumber == null || machineNumber < 1 || machineNumber > 23) {
			throw new IllegalArgumentException(Call.MACHINE_NUMBER_INVALID_MSG);
		}
		if (lccUsername == null || lccUsername.trim().isEmpty()) {
			throw new IllegalArgumentException(Call.LCC_USERNAME_INVALID_MSG);
		}
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((machineNumber == null) ? 0 : machineNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Call other = (Call) obj;
		if (machineNumber == null) {
			if (other.machineNumber != null)
				return false;
		} else if (!machineNumber.equals(other.machineNumber))
			return false;
		return true;
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
