package persistence.model;
import java.util.List;

import datatype.SimpleDate;

public class GrupoMovimentoMO {

	private int id;

	private String nome;

	private SimpleDate anoInicio;

	private SimpleDate anoFim;

	private String descricao;

	private List<LocalMO> local;

	private GrupoPersonagemMO grupoPersonagem;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public SimpleDate getAnoInicio() {
		return anoInicio;
	}

	public void setAnoInicio(SimpleDate anoInicio) {
		this.anoInicio = anoInicio;
	}

	public SimpleDate getAnoFim() {
		return anoFim;
	}

	public void setAnoFim(SimpleDate anoFim) {
		this.anoFim = anoFim;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<LocalMO> getLocal() {
		return local;
	}

	public void setLocal(List<LocalMO> local) {
		this.local = local;
	}

	public GrupoPersonagemMO getGrupoPersonagem() {
		return grupoPersonagem;
	}

	public void setGrupoPersonagem(GrupoPersonagemMO grupoPersonagem) {
		this.grupoPersonagem = grupoPersonagem;
	}

}
