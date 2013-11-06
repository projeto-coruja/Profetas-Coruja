package br.unifesp.profetas.business.palavrachave;

import br.unifesp.profetas.business.common.CommonDTO;

public class PalavraChaveDTO extends CommonDTO {

	private Long id;
	private String palavraChave;

	public PalavraChaveDTO(Long id, String palavraChave) {
		this.id = id;
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

	public void setPalavraChave(String data) {
		this.palavraChave = data;
	}
}