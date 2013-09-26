package persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class PalavraChaveMO implements EntityModel {

	@Id
	@GeneratedValue
	private Long id;

	@NotEmpty
	private String palavraChave;

	public PalavraChaveMO() {} // Hibernate

	public PalavraChaveMO(String palavraChave) {
		this.palavraChave = palavraChave;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPalavraChave() {
		return palavraChave;
	}

	public void setPalavraChave(String palavraChave) {
		this.palavraChave = palavraChave;
	}

}
