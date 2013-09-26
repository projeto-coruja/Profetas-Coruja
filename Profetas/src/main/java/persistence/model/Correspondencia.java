package persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import datatype.SimpleDate;

@Entity
public class Correspondencia implements EntityModel {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@ManyToOne
	private Personagem remetente;

	@NotNull
	@ManyToOne
	private Personagem destinatario;

	@Type(type = "persistence.util.SimpleDateHibernateType")
	private SimpleDate data;

	@ManyToOne
	private Local local;

	public Correspondencia() {} // Hibernate

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
