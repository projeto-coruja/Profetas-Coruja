package br.unifesp.profetas.business.account.dto;

import br.unifesp.profetas.business.common.CommonDTO;

public class RoleDTO extends CommonDTO {
	
	private short id;
	private String name;
	
	public RoleDTO() {}

	public RoleDTO(short id, String name) {
		this.id = id;
		this.name = name;
	}

	public short getId() {
		return id;
	}
	public void setId(short id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}