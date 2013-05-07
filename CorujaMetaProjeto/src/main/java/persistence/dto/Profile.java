package persistence.dto;


public class Profile implements DTO {
	
	private Long id;
	
	private String profile;

	private String[] permissions;
	
	public Profile(String profile, String[] permissions) {
		this.profile = profile;
		this.permissions = permissions;
	}
	
	public Profile() {
		//JDTO
	}

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

}
