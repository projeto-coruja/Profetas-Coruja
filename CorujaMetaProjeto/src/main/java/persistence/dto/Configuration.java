package persistence.dto;

public class Configuration implements DTO {

	private Long id;
	
	private String entry;
	
	private String value;
	
	public Configuration(String entry, String value){
		this.entry = entry;
		this.value = value;
	}
	
	public Configuration(){} // JDTO
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

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
