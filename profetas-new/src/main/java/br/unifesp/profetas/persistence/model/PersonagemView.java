package br.unifesp.profetas.persistence.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "personagem_view")
public class PersonagemView implements Serializable {

	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="texto")
	private String texto;

	public PersonagemView() { }

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
}