package persistence.dto;

public class AcervoCota implements DTO {

	public Long id;

	public String nome;
	
	public AcervoCota(){} // JDTO
	
	public AcervoCota(Long id, String nome) {
		this.id = id;
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
