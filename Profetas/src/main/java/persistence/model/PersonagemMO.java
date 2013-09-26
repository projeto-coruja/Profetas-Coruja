package persistence.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

import datatype.SimpleDate;

@Entity
public class PersonagemMO implements EntityModel {

	@Id
	@GeneratedValue
	private Long id;

	@NotEmpty
	private String nome;

	private String apelido;

	@ManyToOne
	private LocalMO localNascimento;

	@Type(type = "persistence.util.SimpleDateHibernateType")
	private SimpleDate dataNascimento;

	@ManyToOne
	private LocalMO localMorte;

	@Type(type = "persistence.util.SimpleDateHibernateType")
	private SimpleDate dataMorte;

	private String biografia;

	private String ocupacao;

	private String formacao;

	@ManyToOne
	private FontesObrasMO referencia_bibliografica;

	@ManyToMany
	private List<ReligiaoCrencasMO> religião;

	@ManyToMany
	private List<GrupoPersonagemMO> grupo;

	@OneToMany
	private List<LocaisPersonagensMO> locaisVisitados;

	@ManyToMany
	private List<EncontroMO> encontro;

	@ManyToMany
	@JoinTable(name = "fontesobrasmo_personagemmo", 
    joinColumns = {@JoinColumn(name = "personagens_id") }, 
    inverseJoinColumns = { @JoinColumn(name = "fontesobrasmo_id") })
	private List<FontesObrasMO> obras;

	public PersonagemMO() {} //JDTO

	public PersonagemMO(String nome, String apelido,
			LocalMO localNascimento, SimpleDate dataNascimento, LocalMO localMorte,
			SimpleDate dataMorte, String biografia, String ocupacao,
			String formacao, FontesObrasMO referencia_bibliografica,
			List<ReligiaoCrencasMO> religião, List<GrupoPersonagemMO> grupo,
			List<LocaisPersonagensMO> locaisVisitados, List<EncontroMO> encontro,
			List<FontesObrasMO> obras) {
		this.nome = nome;
		this.apelido = apelido;
		this.localNascimento = localNascimento;
		this.dataNascimento = dataNascimento;
		this.localMorte = localMorte;
		this.dataMorte = dataMorte;
		this.biografia = biografia;
		this.ocupacao = ocupacao;
		this.formacao = formacao;
		this.referencia_bibliografica = referencia_bibliografica;
		this.religião = religião;
		this.grupo = grupo;
		this.locaisVisitados = locaisVisitados;
		this.encontro = encontro;
		this.obras = obras;
	}
	
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

	public void setReferencia_bibliografica(
			FontesObrasMO referencia_bibliografica) {
		this.referencia_bibliografica = referencia_bibliografica;
	}

	public List<ReligiaoCrencasMO> getReligião() {
		return religião;
	}

	public void setReligião(List<ReligiaoCrencasMO> religião) {
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
		return obras;
	}

	public void setObras(List<FontesObrasMO> obras) {
		this.obras = obras;
	}

}
