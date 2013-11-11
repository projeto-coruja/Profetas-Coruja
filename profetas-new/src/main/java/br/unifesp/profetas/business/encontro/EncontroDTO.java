package br.unifesp.profetas.business.encontro;

import br.unifesp.profetas.business.common.CommonDTO;
import br.unifesp.profetas.business.local.LocalDTO;

public class EncontroDTO extends CommonDTO {
	
	private Long id;
	private String nome;
	private String data;
	
	private Long idLocal;
	private String desclocal;
	
	public EncontroDTO() {}

	public Long getId() {
		return id;
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
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Long getIdLocal() {
		return idLocal;
	}
	public void setIdLocal(Long idLocal) {
		this.idLocal = idLocal;
	}
	public String getDesclocal() {
		return desclocal;
	}
	public void setDesclocal(String desclocal) {
		this.desclocal = desclocal;
	}
}