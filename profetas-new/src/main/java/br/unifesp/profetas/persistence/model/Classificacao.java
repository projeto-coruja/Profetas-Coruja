package br.unifesp.profetas.persistence.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "classificacao")
public class Classificacao implements Serializable {

	@Id
    @Column(name="id_classificacao", unique = true, nullable = false, insertable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clas_seq_name")
	@SequenceGenerator(name = "clas_seq_name", sequenceName = "clas_seq", allocationSize = 1)
	private Integer id;
	
	@Column(name="c_tipo", nullable = false, length = 100)
	private String tipo;

	public Classificacao() {}

	public Classificacao(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}	
}