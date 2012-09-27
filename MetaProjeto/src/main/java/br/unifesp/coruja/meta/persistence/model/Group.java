package br.unifesp.coruja.meta.persistence.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="user_groups")
public class Group implements EntityModel{
	
	@Id
	private Long id;
	
	@NotEmpty
	private String groupname;
	
	@OneToMany
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
