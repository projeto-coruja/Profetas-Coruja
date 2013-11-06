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
@Table(name = "palavrachave")
public class PalavraChave implements Serializable{

	@Id
	@Column(name = "id_palavrachave", unique = true, nullable = false, insertable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pchave_seq_name")
	@SequenceGenerator(name = "pchave_seq_name", sequenceName = "pchave_seq", allocationSize = 1)
    private Long id;

	@Column(name = "pc_palavra_chave", nullable = false, unique = true)
    private String palavraChave;

    public PalavraChave() {}

    public PalavraChave(String palavraChave) {
    	this.palavraChave = palavraChave;
    }

    public Long getId() {
    	return id;
    }
    
    public void setId(Long id) {
    	this.id = id;
    }

    public String getPalavraChave() {
    	return palavraChave;
    }

    public void setPalavraChave(String palavraChave) {
    	this.palavraChave = palavraChave;
    }
}
