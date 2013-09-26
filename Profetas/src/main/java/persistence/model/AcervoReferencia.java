package persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class AcervoReferencia implements IdentifiedEntity {

	@Id
	@GeneratedValue
	public Long id;

	@NotEmpty
	public String nome;
	
	public AcervoReferencia(){} // Hibernate
	
	public AcervoReferencia(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return null;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
