package persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

import datatype.SimpleDate;

@Entity
public class LocaisPersonagensMO implements EntityModel {

	@Id
	@GeneratedValue
	private Long id;

	@Type(type = "persistence.util.SimpleDateHibernateType")
	private SimpleDate anoChegada;

	@Type(type = "persistence.util.SimpleDateHibernateType")
	private SimpleDate anoSaida;

	@ManyToOne
	private LocalMO local;

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

	public LocalMO getLocal() {
		return local;
	}

	public void setLocal(LocalMO local) {
		this.local = local;
	}

}
