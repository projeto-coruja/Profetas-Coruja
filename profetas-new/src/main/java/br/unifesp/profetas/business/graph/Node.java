package br.unifesp.profetas.business.graph;

import java.util.Objects;

public class Node {

	private Long id;
	private String label;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, label);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Node) {
			Node that = (Node) obj;
			return Objects.equals(this.id, that.id);
		}
		
		return false;
	}
	
	
}
