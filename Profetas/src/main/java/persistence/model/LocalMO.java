package persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class LocalMO implements EntityModel {
	
	@Id
	@GeneratedValue
	private Long id;

	@NotEmpty
	private String nome;

	private double latitude;

	private double longitude;

	public LocalMO() {} // JDTO

	public LocalMO(String nome, double latitude, double longitude) {
		this.nome = nome;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

}
