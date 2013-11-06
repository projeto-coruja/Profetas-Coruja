package br.unifesp.profetas.business.profile;

import br.unifesp.profetas.business.common.CommonDTO;

public class ProfileDTO extends CommonDTO {

	private Integer id;
	private String name;
	private Integer[] idRoles;
	
	public ProfileDTO() {}
	
	public Integer getId() {
		return id;
	}
	public ProfileDTO(Integer id, String name) {
		this.id = id;
		this.name = name;
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
	public Integer[] getIdRoles() {
		return idRoles;
	}
	public void setIdRoles(Integer[] idRoles) {
		this.idRoles = idRoles;
	}
}