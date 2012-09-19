package br.unifesp.maritaca.business;

import java.io.Serializable;

public class UserDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String username;

	public UserDTO(String username) {
		super();
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}