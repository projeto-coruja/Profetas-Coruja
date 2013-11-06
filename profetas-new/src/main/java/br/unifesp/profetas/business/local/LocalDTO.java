package br.unifesp.profetas.business.local;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import br.unifesp.profetas.business.common.CommonDTO;

@JsonIgnoreProperties({"latitude", "longitude"})
public class LocalDTO extends CommonDTO {

	private Long id;	
	private String nome;	
	private String latitude;	
	private String longitude;	
	private String country;
	private String state;
	private String city;	
	
	public LocalDTO() {}
	
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
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
}