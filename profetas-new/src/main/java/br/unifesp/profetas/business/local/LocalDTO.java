package br.unifesp.profetas.business.local;

import br.unifesp.profetas.business.common.CommonDTO;

public class LocalDTO extends CommonDTO {

	private Long id;	
	private String nome;	
	private String latitude;	
	private String longitude;	
	private String place;
	
	public LocalDTO() {}
	
	public LocalDTO(Long id, String nome, String latitude, String longitude,
			String place) {
		this.id = id;
		this.nome = nome;
		this.latitude = latitude;
		this.longitude = longitude;
		this.place = place;
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
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}	
}