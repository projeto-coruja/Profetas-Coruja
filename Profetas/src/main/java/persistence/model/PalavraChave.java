package persistence.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class PalavraChave implements EntityModel {

	@Id
	@NaturalId
	@NotEmpty
	private String palavraChave;

	public PalavraChave() {} // Hibernate

	public PalavraChave(String palavraChave) {
		this.palavraChave = palavraChave;
	}

	public String getPalavraChave() {
		return palavraChave;
	}

	public void setPalavraChave(String palavraChave) {
		this.palavraChave = palavraChave;
	}

}
