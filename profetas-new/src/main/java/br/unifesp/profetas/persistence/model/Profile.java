package br.unifesp.profetas.persistence.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "profile")
public class Profile implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="id_profile", unique = true, nullable = false, insertable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prof_seq_name")
	@SequenceGenerator(name = "prof_seq_name", sequenceName = "prof_seq", allocationSize = 1)
	private Integer id;
	
	@Column(name="p_name", nullable = false, length = 20)
    private String name;
	
	@Column(name="p_description", nullable = true, length = 100)
    private String description;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "profile_role", joinColumns = { 
			@JoinColumn(name = "id_profile", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "id_role", 
			nullable = false, updatable = false) })
	private Set<Role> roles = new HashSet<Role>(0);
    
	public Profile() {}	
	
	public Profile(Integer id) {
		this.id = id;
	}

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
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}