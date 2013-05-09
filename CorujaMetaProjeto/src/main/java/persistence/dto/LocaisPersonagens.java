package persistence.dto;

import org.jdto.annotation.DTOCascade;

import datatype.SimpleDate;

public class LocaisPersonagens implements DTO {

	private Long id;

	private SimpleDate anoChegada;

	private SimpleDate anoSaida;

	@DTOCascade
	private Local local;

	public LocaisPersonagens() {
	}

	public LocaisPersonagens(Long id, SimpleDate anoChegada,
			SimpleDate anoSaida, Local local) {
		this.id = id;
		this.anoChegada = anoChegada;
		this.anoSaida = anoSaida;
		this.local = local;
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

}
