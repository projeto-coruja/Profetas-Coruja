package persistence.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.NaturalId;

@Entity
public class Configuration implements EntityModel {

	@Id
	@NaturalId
	private String entry;
	
	private String value;
	
	public Configuration(String entry, String value){
		this.entry = entry;
		this.value = value;
	}
	
	public Configuration(){} // Hibernate
	
	public String getEntry() {
		return entry;
	}

	public void setEntry(String entry) {
		this.entry = entry;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
