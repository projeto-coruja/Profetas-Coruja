package br.unifesp.coruja.meta.persistence.dto;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;
import org.jdto.annotation.DTOCascade;

public class Group implements DTO{
	
	private Long id;
	
	@NotEmpty
	private String groupname;
	
	@DTOCascade
	private List<Permission> roles;
	
	public Group() {
		//For JDTO use
	}
	
	public Group(String groupname, List<Permission> roles) {
		super();
		this.groupname = groupname;
		this.roles = roles;
	}

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
