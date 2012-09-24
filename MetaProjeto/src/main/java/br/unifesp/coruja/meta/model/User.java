package br.unifesp.coruja.meta.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="user_accounts")
public class User implements br.unifesp.coruja.meta.model.Entity{
	
	@Id
	private Long id;
	
	@NotEmpty
	private String name;
	
	@Email
	private String email;
	
	@NotEmpty
	private String password;
	
	@NotNull
	@ManyToOne
	private Group authGroup;

	@Override	public Long getId() {	return id;	}
	@Override	public void setId(Long id) {	this.id = id;	}

	public String getName() {	return name;	}
	public void setName(String name) {	this.name = name;	}

	public String getEmail() {	return email;	}
	public void setEmail(String email) {	this.email = email;	}

	public String getPassword() {	return password;	}
	public void setPassword(String password) {	this.password = password;	}

	public Group getAuthGroup() {	return authGroup;	}
	public void setAuthGroup(Group authGroup) {	this.authGroup = authGroup;	}

}
