package br.unifesp.profetas.persistence.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

/* 
@Entity
@Subselect(value = "SELECT p.id_personagem as id_personagem, p.p_nome as p_nome, ln.l_name as ln_name FROM personagem as p, local as ln WHERE p.id_loc_nascimento = ln.id_local")
@Synchronize({"personagem", "local"})
	@Id
	@Column(name="id_personagem")
	private Long id;	
	@Column(name="p_nome")
	private String nomePersonagem;	
	@Column(name="ln_nome")
	private String locNascNome;
*/

//@Entity
//@Table(name = "personagem_view")
public class PersonagemView implements Serializable {

	@Id
	@Column(name="id_personagem")
	private Long id;
	
	@Column(name="p_nome")
	private String nomePersonagem;
	
	@Column(name="ln_name")
	private String locNascNome;

	public PersonagemView() {}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomePersonagem() {
		return nomePersonagem;
	}
	public void setNomePersonagem(String nomePersonagem) {
		this.nomePersonagem = nomePersonagem;
	}
	public String getLocNascNome() {
		return locNascNome;
	}
	public void setLocNascNome(String locNascNome) {
		this.locNascNome = locNascNome;
	}
}