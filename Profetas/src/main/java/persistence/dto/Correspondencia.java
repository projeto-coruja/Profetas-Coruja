package persistence.dto;

import org.jdto.annotation.DTOCascade;

import datatype.SimpleDate;

public class Correspondencia implements DTO {

	private Long id;

	@DTOCascade
	private Personagem remetente;

	@DTOCascade
	private Personagem destinatario;

	private SimpleDate data;

	@DTOCascade
	private Local local;

	public Correspondencia() {
	}

	public Correspondencia(Personagem remetente, Personagem destinatario, SimpleDate data, Local local) {
		this.remetente = remetente;
		this.destinatario = destinatario;
		this.data = data;
		this.local = local;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Personagem getRemetente() {
		return remetente;
	}

	public void setRemetente(Personagem remetente) {
		this.remetente = remetente;
	}

	public Personagem getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Personagem destinatario) {
		this.destinatario = destinatario;
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
