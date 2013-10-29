package br.unifesp.profetas.business.account.dto;

import java.util.ArrayList;
import java.util.List;

import br.unifesp.profetas.business.common.CommonDTO;

public class UserDTO extends CommonDTO {
	
	private String fullName;
	private String email;
	private String password;
	private String passwordCopy;
	private List<RoleDTO> roles =  new ArrayList<RoleDTO>();
	
	public UserDTO() {}
	
	public UserDTO(String fullName, String email, String password, String passwordCopy) {
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.passwordCopy = passwordCopy;
	}

	public UserDTO(String fullName, String email, String password,
			String passwordCopy, List<RoleDTO> roles) {
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.passwordCopy = passwordCopy;
		this.roles = roles;
	}

	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordCopy() {
		return passwordCopy;
	}
	public void setPasswordCopy(String passwordCopy) {
		this.passwordCopy = passwordCopy;
	}
	public List<RoleDTO> getRoles() {
		return roles;
	}
	public void setRoles(List<RoleDTO> roles) {
		this.roles = roles;
	}	
}