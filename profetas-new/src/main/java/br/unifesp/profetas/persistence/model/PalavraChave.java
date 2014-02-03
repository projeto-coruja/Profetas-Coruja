package br.unifesp.profetas.persistence.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "palavra_chave", uniqueConstraints = { @UniqueConstraint (columnNames =  { "id_fontes", "pc_palavra_chave" }) })
public class PalavraChave implements Serializable{

	@Id
	@Column(name = "id_palavrachave", unique = true, nullable = false, insertable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pchave_seq_name")
	@SequenceGenerator(name = "pchave_seq_name", sequenceName = "pchave_seq", allocationSize = 1)
    private Long id;

	@Column(name = "pc_palavra_chave", nullable = false)
    private String palavraChave;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_fontes", nullable = false)
	private FontesObras fontesObras;

    public PalavraChave() {}

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
	public FontesObras getFontesObras() {
		return fontesObras;
	}
	public void setFontesObras(FontesObras fontesObras) {
		this.fontesObras = fontesObras;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(palavraChave, fontesObras.getId());
	}
}