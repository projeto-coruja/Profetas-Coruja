package br.unifesp.coruja.meta.persistence.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="user_accounts")
public class UserMO implements EntityModel{
	
private Long id;
	
	@NotEmpty
	private String nickname;
	
	@Email
	private String username;
	
	@NotEmpty
	private String password;
	
	@ManyToOne
	@NotNull
	private GroupMO authGroup;
	
	@NotNull
	private boolean enabled;
	
	@NotNull
	private Date creationDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public GroupMO getAuthGroup() {
		return authGroup;
	}

	public void setAuthGroup(GroupMO authGroup) {
		this.authGroup = authGroup;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date date) {
		this.creationDate = date;
	}


}
