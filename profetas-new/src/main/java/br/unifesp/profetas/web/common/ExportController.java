package br.unifesp.profetas.web.common;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.unifesp.profetas.persistence.domain.EncontroDAO;
import br.unifesp.profetas.persistence.domain.FontesObrasDAO;
import br.unifesp.profetas.persistence.domain.PersonagemDAO;
import br.unifesp.profetas.persistence.model.Encontro;
import br.unifesp.profetas.persistence.model.FontesObras;

import com.google.common.collect.ImmutableMap;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer;

@Controller
public class ExportController {
	
	@Autowired FontesObrasDAO fontesObrasDAO;
	@Autowired PersonagemDAO personagemDAO;
	@Autowired EncontroDAO encontroDAO;
	
	private static enum GraphType {
		PERSONAGEM("personagem"),
		FONTES_OBRAS("fontes-obras");
		
		String path;
		
		GraphType(String path) {
			this.path = path;
		}
		
		private static ImmutableMap<String, GraphType> pathMap = ImmutableMap.<String, GraphType>builder()
				.put(PERSONAGEM.path, PERSONAGEM)
				.put(FONTES_OBRAS.path, FONTES_OBRAS)
				.build();
		
		static GraphType forPath(String path) {
			return pathMap.get(path);
		}
	}
	
	@RequestMapping(value = "/graph/{type}", method = RequestMethod.GET, produces = "image/png")
    public @ResponseBody BufferedImage exportGraph(@PathVariable("type") String type, HttpServletRequest request, HttpServletResponse response) throws IOException {	
		BufferedImage image = null;
		switch(GraphType.forPath(type)) {
		case FONTES_OBRAS:
			image = getImage(circleVisualization(fontesObraGraph())); break;
		case PERSONAGEM:
			image = getImage(circleVisualization(personagemGraph())); break;
		}

		return image;
	}
	
	private Graph<String, Integer> fontesObraGraph() {
		List<FontesObras> obras = fontesObrasDAO.listFontesObras();

		Graph<String, Integer> g = new DirectedSparseMultigraph<String, Integer>();

		int i = 0;
		for (FontesObras o : obras) {
			g.addVertex(o.getTitulo());
			for (FontesObras citadas : o.getObrasCitadas()) {
				g.addVertex(citadas.getTitulo());
				g.addEdge(i++, o.getTitulo(), citadas.getTitulo());
			}
		}
		
		return g;
	}
	
	private Graph<String, String> personagemGraph() {
		List<Encontro> encontros = encontroDAO.listEncontro();

		Graph<String, String> g = new SparseMultigraph<String, String>();

		for (Encontro e : encontros) {
			g.addVertex(e.getPersonagem1().getNome());
			g.addVertex(e.getPersonagem2().getNome());
			g.addEdge(e.getNome(), e.getPersonagem1().getNome(), e.getPersonagem2().getNome());
		}
		
		return g;
	}
	
	private <V, E> VisualizationImageServer<V, E> circleVisualization(Graph<V, E> g) {
		VisualizationImageServer<V, E> vis =
				new VisualizationImageServer<V, E>(
						new CircleLayout<V, E>(g), new Dimension(500, 500));
		
		vis.setDoubleBuffered(false);
		vis.setBackground(Color.WHITE);
		vis.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<E>());
		vis.getRenderContext().setEdgeShapeTransformer(new EdgeShape.Line<V, E>());
		vis.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<V>());
		vis.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.CNTR);
		
		return vis;
	}
	
	private BufferedImage getImage(VisualizationImageServer<?, ?> vis) {
		BufferedImage image = (BufferedImage) vis.getImage(
				new Point2D.Double(
						vis.getGraphLayout().getSize().getWidth()/2, 
						vis.getGraphLayout().getSize().getHeight()/2), 
					vis.getGraphLayout().getSize());
		return image;
	}

}
