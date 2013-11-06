package br.unifesp.profetas.business.classificacao;

import br.unifesp.profetas.business.common.CommonDTO;

public class ClassificacaoDTO extends CommonDTO {

	private Integer id;
	private String tipo;

	public ClassificacaoDTO(Integer id, String tipo) {
		this.id = id;
		this.tipo = tipo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}