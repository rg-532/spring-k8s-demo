package app;

import org.springframework.data.annotation.Id;

public class MessageEntity {
	@Id private String id;
	private String message;

	public MessageEntity() {
	}

	public MessageEntity(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "MessageEntity [id=" + id + ", message=" + message + "]";
	}

}
