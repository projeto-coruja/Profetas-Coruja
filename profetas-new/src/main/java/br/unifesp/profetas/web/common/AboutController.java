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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.unifesp.profetas.persistence.domain.FontesObrasDAO;
import br.unifesp.profetas.persistence.model.FontesObras;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer;

@Controller
public class AboutController {
	
	@Autowired FontesObrasDAO fontesObrasDAO;
	
	@RequestMapping(value = "/about/export", method = RequestMethod.GET, produces = "image/png")
    public @ResponseBody BufferedImage exportGraph(HttpServletRequest request, HttpServletResponse response) throws IOException {	
		List<FontesObras> obras = fontesObrasDAO.listFontesObras();
		
		Graph<String, Integer> g = new DirectedSparseMultigraph<String, Integer>();
		
		int i = 0;
		for(FontesObras o : obras) {
			g.addVertex(o.getTitulo());
			for(FontesObras citadas : o.getObrasCitadas()) {
				g.addVertex(citadas.getTitulo());
				g.addEdge(i++, o.getTitulo(), citadas.getTitulo());
			}
		}
		
		
		VisualizationImageServer<String, Integer> vis =
				new VisualizationImageServer<String, Integer>(
						new CircleLayout<String, Integer>(g), new Dimension(500, 500));
		
		vis.setDoubleBuffered(false);
		vis.setBackground(Color.WHITE);
		vis.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<Integer>());
		vis.getRenderContext().setEdgeShapeTransformer(new EdgeShape.Line<String, Integer>());
		vis.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<String>());
		vis.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.CNTR);
		
		BufferedImage image = (BufferedImage) vis.getImage(
				new Point2D.Double(
						vis.getGraphLayout().getSize().getWidth()/2, 
						vis.getGraphLayout().getSize().getHeight()/2), 
					vis.getGraphLayout().getSize());
		
		return image;
	}

	
	@RequestMapping(value = "/about", method = RequestMethod.GET)
    public String showView() {
        return "page_about";
    }
}