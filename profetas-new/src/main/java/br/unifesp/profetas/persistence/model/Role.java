package br.unifesp.profetas.persistence.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Role implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
    private String name;
    private String description;
    private Set profiles = new HashSet(0);
    
	public Role() {}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set getProfiles() {
		return profiles;
	}
	public void setProfiles(Set profiles) {
		this.profiles = profiles;
	}	
}