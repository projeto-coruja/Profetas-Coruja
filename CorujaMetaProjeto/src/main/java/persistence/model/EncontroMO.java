package persistence.model;

import datatype.SimpleDate;

public class EncontroMO {

	private int id;

	private SimpleDate data;

	private LocalMO local;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
