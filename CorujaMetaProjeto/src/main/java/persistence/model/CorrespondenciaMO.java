package persistence.model;

import datatype.SimpleDate;

public class CorrespondenciaMO {

	private int id;

	private PersonagemMO remetente;

	private PersonagemMO destinatario;

	private SimpleDate data;

	private LocalMO local;

	private PersonagemMO personagem;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PersonagemMO getRemetente() {
		return remetente;
	}

	public void setRemetente(PersonagemMO remetente) {
		this.remetente = remetente;
	}

	public PersonagemMO getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(PersonagemMO destinatario) {
		this.destinatario = destinatario;
	}

	public SimpleDate getData() {
		return data;
	}

	public void setData(SimpleDate data) {
		this.data = data;
	}

	public LocalMO getLocal() {
		return local;
	}

	public void setLocal(LocalMO local) {
		this.local = local;
	}

	public PersonagemMO getPersonagem() {
		return personagem;
	}

	public void setPersonagem(PersonagemMO personagem) {
		this.personagem = personagem;
	}
	
}
