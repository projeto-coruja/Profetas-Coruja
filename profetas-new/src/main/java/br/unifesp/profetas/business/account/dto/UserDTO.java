package br.unifesp.profetas.business.account.dto;

import java.util.ArrayList;
import java.util.List;

import br.unifesp.profetas.business.common.CommonDTO;

public class UserDTO extends CommonDTO {
	
	private Long id;
	private String fullName;
	private String email;
	private String password;
	private String passwordCopy;
	
	private Integer idProfile;
	private String profileName;
	
	private List<RoleDTO> roles =  new ArrayList<RoleDTO>();
	
	public UserDTO() {}
	
	public UserDTO(Long id, String fullName, String email, String profileName) {
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.profileName = profileName;
	}
	
	public UserDTO(Long id, String fullName, String email, Integer idProfile) {
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.idProfile = idProfile;
	}
	
	public UserDTO(Long id, Integer idProfile) {
		super();
		this.id = id;
		this.idProfile = idProfile;
	}

	//
	
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

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Integer getIdProfile() {
		return idProfile;
	}
	public void setIdProfile(Integer idProfile) {
		this.idProfile = idProfile;
	}	
	public String getProfileName() {
		return profileName;
	}
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	public List<RoleDTO> getRoles() {
		return roles;
	}
	public void setRoles(List<RoleDTO> roles) {
		this.roles = roles;
	}	
}