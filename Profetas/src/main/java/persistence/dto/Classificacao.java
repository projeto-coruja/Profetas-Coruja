package persistence.dto;

public class Classificacao implements DTO {

	private Long id;

	private String tipo;

	public Classificacao() {} // JDTO

	public Classificacao(String tipo) {
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
