package persistence.dto;

import org.jdto.annotation.DTOCascade;

import datatype.SimpleDate;

public class Encontro implements DTO {

	private Long id;

	private SimpleDate data;

	@DTOCascade
	private Local local;

	public Encontro() {
	}

	public Encontro(Long id, SimpleDate data, Local local) {
		this.id = id;
		this.data = data;
		this.local = local;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SimpleDate getData() {
		return data;
	}

	public void setData(SimpleDate data) {
		this.data = data;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

}
