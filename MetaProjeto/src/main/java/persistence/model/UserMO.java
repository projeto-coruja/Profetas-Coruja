package persistence.model;

import general.SimpleDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name="user_accounts")
public class UserMO implements EntityModel{
	
	@Id
	@GeneratedValue
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
	@Type(type="general.SimpleDateHibernateType")
	private SimpleDate creationDate;

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

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public SimpleDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(SimpleDate date) {
		this.creationDate = date;
	}


}
