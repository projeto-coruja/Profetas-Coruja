package br.unifesp.profetas.persistence.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "correspondencia")
public class Correspondencia implements Serializable {

	@Id
    @Column(name="id_correspondencia", unique = true, nullable = false, insertable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "corr_seq_name")
	@SequenceGenerator(name = "corr_seq_name", sequenceName = "corr_seq", allocationSize = 1)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="id_pers_remetente")
	@ForeignKey(name = "fk_pers_remetente")
	private Personagem remetente;
	
	@ManyToOne
	@JoinColumn(name="id_pers_destinatario")
	@ForeignKey(name = "fk_pers_destinatario")
	private Personagem destinatario;
	
	@ManyToOne
	@PrimaryKeyJoinColumn
	@ForeignKey(name = "fk_correspondencia_local")
	private Local local;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "c_data", nullable = true, length = 10)
	private Date data;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "correspondencias")
	private Set<Personagem> corByPersonagem = new HashSet<Personagem>(0);
	
	@Type(type="yes_no")
	@Column(name = "active")
	private Boolean active;

	public Correspondencia() {}

	public Correspondencia(Long id, Personagem remetente,
			Personagem destinatario, Local local, Date data) {
		this.id = id;
		this.remetente = remetente;
		this.destinatario = destinatario;
		this.local = local;
		this.data = data;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Personagem getRemetente() {
		return remetente;
	}
	public void setRemetente(Personagem remetente) {
		this.remetente = remetente;
	}
	public Personagem getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(Personagem destinatario) {
		this.destinatario = destinatario;
	}
	public Local getLocal() {
		return local;
	}
	public void setLocal(Local local) {
		this.local = local;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Set<Personagem> getCorByPersonagem() {
		return corByPersonagem;
	}
	public void setCorByPersonagem(Set<Personagem> corByPersonagem) {
		this.corByPersonagem = corByPersonagem;
	}

	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
}