package br.unifesp.coruja.meta.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="user_groups")
public class Group implements br.unifesp.coruja.meta.model.Entity{
	
	@Id
	private Long id;
	
	@NotEmpty
	private String groupname;
	
	@OneToMany
	private List<Permission> roles;

	@Override	public void setId(Long id) {	this.id = id;	}
	@Override	public Long getId() {	return this.id;	}
	
	public List<Permission> getListRoles(){
		return null;
	}

}
