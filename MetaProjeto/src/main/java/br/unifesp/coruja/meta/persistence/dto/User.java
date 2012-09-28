package br.unifesp.coruja.meta.persistence.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User implements DTO, UserDetails{
	
	private static final long serialVersionUID = 4864187238327821050L;

	private Long id;
	
	@NotEmpty
	private String nickname;
	
	@Email
	private String username;
	
	@NotEmpty
	private String password;
	
	@NotNull
	private Group authGroup;
	
	@NotNull
	private boolean enabled;
	
	@NotNull
	private Date creationDate;

	public User() {
		//For JDTO use
	}

	public User(String nickname, String username, String password,
			Group authGroup, boolean enabled, Date creationDate) {
		super();
		this.nickname = nickname;
		this.username = username;
		this.password = password;
		this.authGroup = authGroup;
		this.enabled = enabled;
		this.creationDate = creationDate;
	}



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

	public Group getAuthGroup() {
		return authGroup;
	}

	public void setAuthGroup(Group authGroup) {
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
	
	/*
	 * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
	 */

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();
		for(Permission p : authGroup.getRoles()) {
			list.add(new SimpleGrantedAuthority(p.getRolename()));
		}
		return list;
	}

	@Override
	public boolean isAccountNonExpired() {
		return enabled;
	}

	@Override
	public boolean isAccountNonLocked() {
		return enabled;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return enabled;
	}

}
