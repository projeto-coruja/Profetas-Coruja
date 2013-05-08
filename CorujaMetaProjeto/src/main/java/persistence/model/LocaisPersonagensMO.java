package persistence.model;

import datatype.SimpleDate;

public class LocaisPersonagensMO {

	private int id;

	private SimpleDate anoChegada;

	private SimpleDate anoSaida;

	private LocalMO local;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SimpleDate getAnoChegada() {
		return anoChegada;
	}

	public void setAnoChegada(SimpleDate anoChegada) {
		this.anoChegada = anoChegada;
	}

	public SimpleDate getAnoSaida() {
		return anoSaida;
	}

	public void setAnoSaida(SimpleDate anoSaida) {
		this.anoSaida = anoSaida;
	}

	public LocalMO getLocal() {
		return local;
	}

	public void setLocal(LocalMO local) {
		this.local = local;
	}

}
