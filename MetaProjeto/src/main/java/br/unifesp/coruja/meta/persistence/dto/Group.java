package br.unifesp.coruja.meta.persistence.dto;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

public class Group implements DTO{
	
	private Long id;
	
	@NotEmpty
	private String groupname;
	
	private List<Permission> roles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public List<Permission> getRoles() {
		return roles;
	}

	public void setRoles(List<Permission> roles) {
		this.roles = roles;
	}

}
