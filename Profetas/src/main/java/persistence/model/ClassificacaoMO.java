package persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class ClassificacaoMO implements EntityModel {

	@Id
	@GeneratedValue
	private Long id;

	@NotEmpty
	private String tipo;
	
	public ClassificacaoMO() {} // Hibernate

	public ClassificacaoMO(String tipo) {
		this.tipo = tipo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
