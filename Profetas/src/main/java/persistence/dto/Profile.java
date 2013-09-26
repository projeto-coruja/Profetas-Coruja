package persistence.dto;


public class Profile implements DTO {
	
	private Long id;
	
	private String profile;

	private String[] permissions;

	private boolean isDefault;
	
	public Profile(String profile, String[] permissions, boolean isDefault) {
		this.profile = profile;
		this.permissions = permissions;
		this.isDefault = isDefault;
	}
	
	public Profile() {} // JDTO

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
