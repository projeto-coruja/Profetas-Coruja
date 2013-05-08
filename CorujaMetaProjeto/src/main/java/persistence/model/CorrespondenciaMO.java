package persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import datatype.SimpleDate;

@Entity
public class CorrespondenciaMO implements EntityModel {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	private PersonagemMO remetente;

	@NotNull
	private PersonagemMO destinatario;

	@Type(type = "persistence.util.SimpleDateHibernateType")
	private SimpleDate data;

	private LocalMO local;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PersonagemMO getRemetente() {
		return remetente;
	}

	public void setRemetente(PersonagemMO remetente) {
		this.remetente = remetente;
	}

	public PersonagemMO getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(PersonagemMO destinatario) {
		this.destinatario = destinatario;
	}

	public SimpleDate getData() {
		return data;
	}

	public void setData(SimpleDate data) {
		this.data = data;
	}

	public LocalMO getLocal() {
		return local;
	}

	public void setLocal(LocalMO local) {
		this.local = local;
	}

}
