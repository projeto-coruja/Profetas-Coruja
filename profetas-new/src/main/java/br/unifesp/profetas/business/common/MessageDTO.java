package br.unifesp.profetas.business.common;

public class MessageDTO {
	
	private String message;
	private MessageType type;
	private Object data;
	
	public MessageDTO() {}

	public MessageDTO(String message, MessageType type) {
		this.message = message;
		this.type = type;
	}

	public MessageDTO(String message, MessageType type, Object data) {
		this.message = message;
		this.type = type;
		this.data = data;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public MessageType getType() {
		return type;
	}
	public void setType(MessageType type) {
		this.type = type;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}