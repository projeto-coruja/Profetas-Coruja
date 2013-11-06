package br.unifesp.profetas.persistence.model;

import java.io.Serializable;
import java.util.Date;

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

@Entity
@Table(name = "encontro")
public class Encontro implements Serializable {

	@Id
    @Column(name="id_encontro", unique = true, nullable = false, insertable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "enco_seq_name")
	@SequenceGenerator(name = "enco_seq_name", sequenceName = "enco_seq", allocationSize = 1)
	private Long id;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "e_data", nullable = false, length = 10)
	private Date data;
	
	@ManyToOne
	@PrimaryKeyJoinColumn
	@ForeignKey(name = "fk_encontro_local")
	private Local local;

	public Encontro() {}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Local getLocal() {
		return local;
	}
	public void setLocal(Local local) {
		this.local = local;
	}	
}