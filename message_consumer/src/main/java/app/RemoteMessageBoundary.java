package app;

public class RemoteMessageBoundary {
	private String message;

	public RemoteMessageBoundary() {
	}

	public RemoteMessageBoundary(String message) {
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
