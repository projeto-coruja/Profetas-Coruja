package br.unifesp.profetas.business.common;

public enum MessageType {

	SUCCESS	("success"),
	ERROR	("error"),
	WARN	("warn");
	
	private String description;

	private MessageType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}	
}