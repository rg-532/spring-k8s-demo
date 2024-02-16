package app;

public class MessageBoundary {
	private String message;

	public MessageBoundary() {
	}

	public MessageBoundary(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "MessageBoundary [message=" + message + "]";
	}

}
