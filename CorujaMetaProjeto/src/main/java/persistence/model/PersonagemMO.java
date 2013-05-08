package persistence.model;

import java.util.List;

import datatype.SimpleDate;

public class PersonagemMO {

	private int id;

	private String nome;

	private String apelido;

	private LocalMO localNascimento;

	private SimpleDate dataNascimento;

	private LocalMO localMorte;

	private SimpleDate dataMorte;

	private String biografia;

	private String ocupacao;

	private String formacao;

	private FontesObrasMO referencia_bibliografica;

	private List<ReligiãoCrencasMO> religião;

	private List<GrupoPersonagemMO> grupo;

	private List<LocaisPersonagensMO> locaisVisitados;

	private List<EncontroMO> encontro;

	private List<FontesObrasMO> Obras;

	private ReligiãoCrencasMO religiãoCrencas;

	private GrupoPersonagemMO grupoPersonagem;

	private LocaisPersonagensMO locaisPersonagens;

	private CorrespondenciaMO correspondencia;

	private FontesObrasMO fontesObras;

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

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public LocalMO getLocalNascimento() {
		return localNascimento;
	}

	public void setLocalNascimento(LocalMO localNascimento) {
		this.localNascimento = localNascimento;
	}

	public SimpleDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(SimpleDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public LocalMO getLocalMorte() {
		return localMorte;
	}

	public void setLocalMorte(LocalMO localMorte) {
		this.localMorte = localMorte;
	}

	public SimpleDate getDataMorte() {
		return dataMorte;
	}

	public void setDataMorte(SimpleDate dataMorte) {
		this.dataMorte = dataMorte;
	}

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}

	public String getOcupacao() {
		return ocupacao;
	}

	public void setOcupacao(String ocupacao) {
		this.ocupacao = ocupacao;
	}

	public String getFormacao() {
		return formacao;
	}

	public void setFormacao(String formacao) {
		this.formacao = formacao;
	}

	public FontesObrasMO getReferencia_bibliografica() {
		return referencia_bibliografica;
	}

	public void setReferencia_bibliografica(FontesObrasMO referencia_bibliografica) {
		this.referencia_bibliografica = referencia_bibliografica;
	}

	public List<ReligiãoCrencasMO> getReligião() {
		return religião;
	}

	public void setReligião(List<ReligiãoCrencasMO> religião) {
		this.religião = religião;
	}

	public List<GrupoPersonagemMO> getGrupo() {
		return grupo;
	}

	public void setGrupo(List<GrupoPersonagemMO> grupo) {
		this.grupo = grupo;
	}

	public List<LocaisPersonagensMO> getLocaisVisitados() {
		return locaisVisitados;
	}

	public void setLocaisVisitados(List<LocaisPersonagensMO> locaisVisitados) {
		this.locaisVisitados = locaisVisitados;
	}

	public List<EncontroMO> getEncontro() {
		return encontro;
	}

	public void setEncontro(List<EncontroMO> encontro) {
		this.encontro = encontro;
	}

	public List<FontesObrasMO> getObras() {
		return Obras;
	}

	public void setObras(List<FontesObrasMO> obras) {
		Obras = obras;
	}

	public ReligiãoCrencasMO getReligiãoCrencas() {
		return religiãoCrencas;
	}

	public void setReligiãoCrencas(ReligiãoCrencasMO religiãoCrencas) {
		this.religiãoCrencas = religiãoCrencas;
	}

	public GrupoPersonagemMO getGrupoPersonagem() {
		return grupoPersonagem;
	}

	public void setGrupoPersonagem(GrupoPersonagemMO grupoPersonagem) {
		this.grupoPersonagem = grupoPersonagem;
	}

	public LocaisPersonagensMO getLocaisPersonagens() {
		return locaisPersonagens;
	}

	public void setLocaisPersonagens(LocaisPersonagensMO locaisPersonagens) {
		this.locaisPersonagens = locaisPersonagens;
	}

	public CorrespondenciaMO getCorrespondencia() {
		return correspondencia;
	}

	public void setCorrespondencia(CorrespondenciaMO correspondencia) {
		this.correspondencia = correspondencia;
	}

	public FontesObrasMO getFontesObras() {
		return fontesObras;
	}

	public void setFontesObras(FontesObrasMO fontesObras) {
		this.fontesObras = fontesObras;
	}

}
