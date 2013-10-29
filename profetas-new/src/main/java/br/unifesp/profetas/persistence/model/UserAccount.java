package br.unifesp.profetas.persistence.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "user_account")
public class UserAccount implements Serializable {

	@Id
    @GeneratedValue
    @Column(name="id_user", unique = true, nullable = false)
    private Long id;

	@Column(name="u_name", nullable = false, length = 100)
    private String name;

	@NaturalId (mutable = false)
    @Column(name = "u_email", unique = true, nullable = false, length = 100)
    private String email;

	@Column(name = "u_password", nullable = false, length = 32)
    private String password;
	
	//@Temporal(TemporalType.DATE)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "u_creation_date", nullable = false, length = 10)
	private Date creationDate;

	@ManyToOne
	@PrimaryKeyJoinColumn
	@ForeignKey(name = "fk_user_profile")
	@NotNull
    private Profile profile;

	public UserAccount() {}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}	
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
}