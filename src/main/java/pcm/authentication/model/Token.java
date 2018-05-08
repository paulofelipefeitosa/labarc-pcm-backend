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

}
