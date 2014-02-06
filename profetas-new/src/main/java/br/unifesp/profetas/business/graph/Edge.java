package br.unifesp.profetas.business.graph;

import java.util.Objects;

public class Edge {

	private Long id;
	private String label;
	private Node source;
	private Node target;
	
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
	public Node getSource() {
		return source;
	}
	public void setSource(Node source) {
		this.source = source;
	}
	public Node getTarget() {
		return target;
	}
	public void setTarget(Node target) {
		this.target = target;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, label, source, target);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Edge) {
			Edge that = (Edge) obj;
			return Objects.equals(this.id, that.id) 
					&& Objects.equals(this.label, that.label) 
					&& Objects.equals(this.source, that.source)
					&& Objects.equals(this.target, that.target);
		}
		return false;
	}
	
	
}
