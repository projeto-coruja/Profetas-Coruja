package persistence.dto;

public class PalavraChave implements DTO {

	private Long id;

	private String palavraChave;

	public PalavraChave() {} // JDTO

	public PalavraChave(String palavraChave) {
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
