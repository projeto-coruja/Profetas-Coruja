package br.unifesp.profetas.business.profile;

import br.unifesp.profetas.business.common.CommonDTO;

public class RoleDTO extends CommonDTO {
	
	private Integer id;
	private String name;
	
	public RoleDTO() {}
	
	public RoleDTO(Integer id) {
		this.id = id;
	}
	
	public RoleDTO(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
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
}