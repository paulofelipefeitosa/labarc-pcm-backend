package pcm.authentication.model;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Token {
	private String id;
	private Long validity;

	public Token() {
		this.id = UUID.randomUUID().toString();
		this.validity = System.currentTimeMillis() + TimeUnit.HOURS.toMillis(2);
	}

	public String getId() {
		return id;
	}

	public boolean isValid() {
		return this.validity > System.currentTimeMillis();
	}
	
	protected void setValidity(Long validity) {
		this.validity = validity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Token other = (Token) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
