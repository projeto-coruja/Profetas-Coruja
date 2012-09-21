package br.unifesp.coruja.meta.persistence.auth;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="user_groups")
public class Group {
	
	@Id
	private Long id;
	
	@NotEmpty
	private String groupname;
	
	@OneToMany
	private List<Permission> roles;

}
