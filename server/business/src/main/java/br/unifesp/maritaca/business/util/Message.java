package br.unifesp.maritaca.business.util;

public class Message {
	
	private String message;
	private Boolean success;
	
	public Message() {
	}
	public Message(String message, Boolean success) {
		this.message = message;
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
}