package br.unifesp.profetas.persistence.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "local")
public class Local implements Serializable {

	@Id
    @Column(name="id_local", unique = true, nullable = false, insertable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loca_seq_name")
	@SequenceGenerator(name = "loca_seq_name", sequenceName = "loca_seq", allocationSize = 1)
	private Long id;
	
	@Column(name="l_name", nullable = false, length = 100)
	private String nome;
	
	@Column(name="l_latitude", nullable = true)
	private Double latitude;
	
	@Column(name="l_longitude", nullable = true)
	private Double longitude;
	
	
	@Column(name="l_country", nullable = false, length = 100)
	private String country;
	
	@Column(name="l_state", nullable = true, length = 100)
	private String state;
	
	@Column(name="l_city", nullable = true, length = 100)
	private String city;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "locais")
	private Set<GrupoMovimento> gMovimentos = new HashSet<GrupoMovimento>(0);

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
	public Set<GrupoMovimento> getgMovimentos() {
		return gMovimentos;
	}
	public void setgMovimentos(Set<GrupoMovimento> gMovimentos) {
		this.gMovimentos = gMovimentos;
	}
}