package br.unifesp.coruja.meta.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="user_roles")
public class Permission implements br.unifesp.coruja.meta.model.Entity{
	
	@Id
	private Long id;
	
	@NotEmpty
	private String rolename;

	@Override public Long getId() {	return id;	}
	@Override public void setId(Long id) {	this.id = id; }

	public String getRolename() {	return rolename;	}
	public void setRolename(String rolename) {	this.rolename = rolename;	}
	
}
