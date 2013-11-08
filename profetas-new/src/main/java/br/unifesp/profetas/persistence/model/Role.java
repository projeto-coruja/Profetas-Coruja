package br.unifesp.profetas.persistence.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="id_role", unique = true, nullable = false, insertable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq_name")
	@SequenceGenerator(name = "role_seq_name", sequenceName = "role_seq", allocationSize = 1)
	private Integer id;
	
	@Column(name="r_name", nullable = false, length = 20)
    private String name;
	
	@Column(name="r_description", nullable = true, length = 100)
    private String description;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
	private Set<Profile> profiles = new HashSet<Profile>(0);
	
	public Role() {}

	public Role(String name) {
		this.name = name;
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
	public Set<Profile> getProfiles() {
		return profiles;
	}
	public void setProfiles(Set<Profile> profiles) {
		this.profiles = profiles;
	}	
}