package persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Profile implements IdentifiedEntity {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NaturalId
	@NotEmpty
	private String profile;

	private String[] permissions;

	private boolean isDefault;
	
	public Profile(String profile, String[] permissions, boolean isDefault) {
		this.profile = profile;
		this.permissions = permissions;
		this.isDefault = isDefault;
	}
	
	public Profile() {} // Hibernate

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String[] getPermissions() {
		return permissions;
	}

	public void setPermissions(String[] permissions) {
		this.permissions = permissions;
	}

	public boolean isDefault() {
		return isDefault;
	}

	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

}
