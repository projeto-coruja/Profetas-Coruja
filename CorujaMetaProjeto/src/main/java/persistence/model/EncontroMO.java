package persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.Type;

import datatype.SimpleDate;

@Entity
public class EncontroMO implements EntityModel {

	@Id
	@GeneratedValue
	private Long id;

	@Type(type = "persistence.util.SimpleDateHibernateType")
	private SimpleDate data;

	private LocalMO local;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

}
