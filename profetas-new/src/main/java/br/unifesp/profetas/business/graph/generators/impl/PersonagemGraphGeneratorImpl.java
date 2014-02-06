package br.unifesp.profetas.business.graph.generators.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import br.unifesp.profetas.business.graph.Edge;
import br.unifesp.profetas.business.graph.Graph;
import br.unifesp.profetas.business.graph.Node;
import br.unifesp.profetas.business.graph.generators.PersonagemGraphGenerator;
import br.unifesp.profetas.persistence.domain.EncontroDAO;
import br.unifesp.profetas.persistence.model.Encontro;

public class PersonagemGraphGeneratorImpl implements PersonagemGraphGenerator {

	@Autowired EncontroDAO encontroDAO;

	public Graph getGraph() {
		List<Encontro> encontros = encontroDAO.listEncontro();

		Set<Node> nodes = new HashSet<Node>();
		Set<Edge> edges = new HashSet<Edge>();
		
		for (Encontro e : encontros) {
			Node p1 = new Node();
			p1.setId(e.getPersonagem1().getId());
			p1.setLabel(e.getPersonagem1().getNome());
			nodes.add(p1);
			
			Node p2 = new Node();
			p2.setId(e.getPersonagem2().getId());
			p2.setLabel(e.getPersonagem2().getNome());
			nodes.add(p2);
			
			Edge ed = new Edge();
			ed.setId(e.getId());
			ed.setLabel(e.getNome());
			ed.setSource(p1);
			ed.setTarget(p2);
			edges.add(ed);
		}
		
		Graph g = new Graph();
		g.setNodes(new ArrayList<Node>(nodes));
		g.setEdges(new ArrayList<Edge>(edges));
		return g;
	}

}
