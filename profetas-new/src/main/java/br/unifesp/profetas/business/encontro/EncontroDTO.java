package br.unifesp.profetas.business.encontro;

import br.unifesp.profetas.business.common.CommonDTO;

public class EncontroDTO extends CommonDTO {
   
    private Integer indice;
	private Long id;
    private String nome;
    private String data;
   
    private Long idPersonagem;
    private String descPersonagem;
   
    private Long idLocal;
    private String descLocal;
   
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
    public Long getIdPersonagem() {
        return idPersonagem;
    }
    public void setIdPersonagem(Long idPersonagem) {
        this.idPersonagem = idPersonagem;
    }
    public String getDescPersonagem() {
        return descPersonagem;
    }
    public void setDescPersonagem(String descPersonagem) {
        this.descPersonagem = descPersonagem;
    }
    public Long getIdLocal() {
        return idLocal;
    }
    public void setIdLocal(Long idLocal) {
        this.idLocal = idLocal;
    }
    public String getDescLocal() {
        return descLocal;
    }
    public void setDescLocal(String descLocal) {
        this.descLocal = descLocal;
    }
	public Integer getIndice() {
		return indice;
	}
	public void setIndice(Integer indice) {
		this.indice = indice;
	}
}