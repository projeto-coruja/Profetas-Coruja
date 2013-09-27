package persistence.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Classificacao implements EntityModel {
	
	@Id
	@NotEmpty
	private String tipo;

	public Classificacao() {} // Hibernate

	public Classificacao(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
