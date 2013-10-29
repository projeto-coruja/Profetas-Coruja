package br.unifesp.profetas.persistence.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "local")
public class Local implements Serializable {

	@Id
    @GeneratedValue
    @Column(name="id_local", unique = true, nullable = false)
	private Long id;
	
	@Column(name="l_name", nullable = false, length = 100)
	private String nome;
	
	@Column(name="l_latitude", nullable = true)
	private Double latitude;
	
	@Column(name="l_longitude", nullable = true)
	private Double longitude;
	
	@Column(name="l_place", nullable = true, length = 100)
	private String place;

	public Local() {}
	
	public Local(Long id) {
		this.id = id;
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
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}	
}