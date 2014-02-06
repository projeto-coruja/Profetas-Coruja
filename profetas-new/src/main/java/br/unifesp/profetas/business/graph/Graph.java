package br.unifesp.profetas.business.graph;

import java.util.List;

public class Graph {

	private List<Node> nodes;
	private List<Edge> edges;
	
	public List<Node> getNodes() {
		return nodes;
	}
	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}
	public List<Edge> getEdges() {
		return edges;
	}
	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}
}
