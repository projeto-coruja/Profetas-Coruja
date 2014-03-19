package br.unifesp.profetas.persistence.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "encontro")
public class Encontro implements Serializable {

	@Id
    @Column(name="id_encontro", unique = true, nullable = false, insertable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "enco_seq_name")
	@SequenceGenerator(name = "enco_seq_name", sequenceName = "enco_seq", allocationSize = 1)
	private Long id;
	
	@Column(name="e_name", nullable = false, length = 100)
	private String nome;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "e_data", nullable = true, length = 10)
	private Date data;
	
	@ManyToOne
	@PrimaryKeyJoinColumn
	@ForeignKey(name = "fk_encontro_personagem_1")
	private Personagem personagem1;
	
	@ManyToOne
	@PrimaryKeyJoinColumn
	@ForeignKey(name = "fk_encontro_personagem_2")
	private Personagem personagem2;
	
	@ManyToOne
	@PrimaryKeyJoinColumn
	@ForeignKey(name = "fk_encontro_local")
	private Local local;
	
	@Type(type="yes_no")
	@Column(name = "active")
	private Boolean active;
	
	public Encontro() {}

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
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}	
	public Personagem getPersonagem1() {
		return personagem1;
	}
	public void setPersonagem1(Personagem personagem) {
		this.personagem1 = personagem;
	}
	public Personagem getPersonagem2() {
		return personagem2;
	}
	public void setPersonagem2(Personagem personagem) {
		this.personagem2 = personagem;
	}
	public Local getLocal() {
		return local;
	}
	public void setLocal(Local local) {
		this.local = local;
	}
	

	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(personagem1.hashCode() + personagem2.hashCode(), data, local, nome);
	}
}