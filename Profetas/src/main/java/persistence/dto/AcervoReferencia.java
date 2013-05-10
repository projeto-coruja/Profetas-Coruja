package persistence.dto;

public class AcervoReferencia implements DTO {

	public Long id;

	public String nome;
	
	public AcervoReferencia(){} // JDTO
	
	public AcervoReferencia(String nome) {
		this.nome = nome;
	}

	@Override
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
