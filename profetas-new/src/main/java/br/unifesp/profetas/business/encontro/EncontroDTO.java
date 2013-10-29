package br.unifesp.profetas.business.encontro;

import br.unifesp.profetas.business.common.CommonDTO;
import br.unifesp.profetas.business.local.LocalDTO;

public class EncontroDTO extends CommonDTO {
	
	private Long id;
	private String data;
	
	private Long idLocal;
	private LocalDTO local;
	
	public EncontroDTO() {}

	public EncontroDTO(Long id, String data, Long idLocal) {
		this.id = id;
		this.data = data;
		this.idLocal = idLocal;
	}

	public EncontroDTO(Long id, String data, LocalDTO local) {
		this.id = id;
		this.data = data;
		this.local = local;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public LocalDTO getLocal() {
		return local;
	}
	public void setLocal(LocalDTO local) {
		this.local = local;
	}
}