package br.unifesp.profetas.business.correspondencia;

import br.unifesp.profetas.business.common.CommonDTO;

public class CorrespondenciaDTO extends CommonDTO {
	
	private Long id;

	private Long idRemetente;
	private String nomeRemetente;
	
	private Long idDestinatario;
	private String nomeDestinatario;

	private Long idLocal;
	private String nomeLocal;
	
	private String data;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdRemetente() {
		return idRemetente;
	}
	public void setIdRemetente(Long idRemetente) {
		this.idRemetente = idRemetente;
	}
	public String getNomeRemetente() {
		return nomeRemetente;
	}
	public void setNomeRemetente(String nomeRemetente) {
		this.nomeRemetente = nomeRemetente;
	}
	public Long getIdDestinatario() {
		return idDestinatario;
	}
	public void setIdDestinatario(Long idDestinatario) {
		this.idDestinatario = idDestinatario;
	}
	public String getNomeDestinatario() {
		return nomeDestinatario;
	}
	public void setNomeDestinatario(String nomeDestinatario) {
		this.nomeDestinatario = nomeDestinatario;
	}
	public Long getIdLocal() {
		return idLocal;
	}
	public void setIdLocal(Long idLocal) {
		this.idLocal = idLocal;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getNomeLocal() {
		return nomeLocal;
	}
	public void setNomeLocal(String nomeLocal) {
		this.nomeLocal = nomeLocal;
	}
}