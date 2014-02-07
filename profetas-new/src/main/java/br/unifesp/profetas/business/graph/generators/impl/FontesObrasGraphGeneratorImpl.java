package br.unifesp.profetas.business.graph.generators.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unifesp.profetas.business.graph.Edge;
import br.unifesp.profetas.business.graph.Graph;
import br.unifesp.profetas.business.graph.Node;
import br.unifesp.profetas.business.graph.generators.FontesObrasGraphGenerator;
import br.unifesp.profetas.persistence.domain.FontesObrasDAO;
import br.unifesp.profetas.persistence.model.FontesObras;

@Service("fontesObrasGraph")
public class FontesObrasGraphGeneratorImpl implements FontesObrasGraphGenerator {

	@Autowired private FontesObrasDAO fontesObrasDAO;
	
	public Graph getGraph() {
		List<FontesObras> obras = fontesObrasDAO.listFontesObras();

		Set<Node> nodes = new HashSet<Node>();
		Set<Edge> edges = new HashSet<Edge>();
		
		Long count = 0L;
		for (FontesObras o : obras) {
			Node n1 = new Node();
			n1.setId(o.getId());
			n1.setLabel(o.getTitulo());
			nodes.add(n1);
			for (FontesObras citada : o.getObrasCitadas()) {
				Node n2 = new Node();
				n2.setId(citada.getId());
				n2.setLabel(citada.getTitulo());
				nodes.add(n2);
				
				Edge ed = new Edge();
				ed.setId(count++);
				ed.setLabel(""); //TODO
				ed.setSource(n1);
				ed.setTarget(n2);
				edges.add(ed);
			}
		}
		
		Graph g = new Graph();
		g.setNodes(new ArrayList<Node>(nodes));
		g.setEdges(new ArrayList<Edge>(edges));
		return g;
	}

}
