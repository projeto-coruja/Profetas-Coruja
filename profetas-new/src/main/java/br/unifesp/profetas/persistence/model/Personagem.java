package br.unifesp.profetas.persistence.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "personagem")
public class Personagem implements Serializable {
	
	@Id
    @Column(name="id_personagem", unique = true, nullable = false, insertable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pers_seq_name")
	@SequenceGenerator(name = "pers_seq_name", sequenceName = "pers_seq", allocationSize = 1)
	private Long id;
	
	@Column(name="p_nome", nullable = false, length = 100)
	private String nome;
	
	@Column(name="p_apelido", nullable = false, length = 100)
	private String apelido;
	
	@ManyToOne
	@JoinColumn(name = "id_loc_nascimento")
	@ForeignKey(name = "fk_per_loc_nasc")
	private Local localNascimento;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "p_data_nasc", nullable = true, length = 10)
	private Date dataNascimento;
	
	@ManyToOne
	@JoinColumn(name = "id_loc_morte")
	@ForeignKey(name = "fk_per_loc_mort")
	private Local localMorte;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "p_data_morte", nullable = true, length = 10)
	private Date dataMorte;
	
	@Column(name="p_biografia", nullable = true)
    private String biografia;
	
	@Column(name="p_ocupacao", nullable = true)
    private String ocupacao;
	
	@Column(name="p_formacao", nullable = true)
    private String formacao;
    
	public Personagem() {}
	
	public Personagem(Long id) {
		this.id = id;
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
	public Local getLocalNascimento() {
		return localNascimento;
	}
	public void setLocalNascimento(Local localNascimento) {
		this.localNascimento = localNascimento;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Local getLocalMorte() {
		return localMorte;
	}
	public void setLocalMorte(Local localMorte) {
		this.localMorte = localMorte;
	}
	public Date getDataMorte() {
		return dataMorte;
	}
	public void setDataMorte(Date dataMorte) {
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
}