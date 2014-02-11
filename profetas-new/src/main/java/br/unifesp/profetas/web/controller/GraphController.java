package br.unifesp.profetas.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.unifesp.profetas.business.graph.Graph;
import br.unifesp.profetas.business.graph.generators.FontesObrasGraphGenerator;
import br.unifesp.profetas.business.graph.generators.impl.PersonagemGraphGeneratorImpl;

@Controller
public class GraphController {
	
	@Autowired private PersonagemGraphGeneratorImpl personagemGraph;
	@Autowired private FontesObrasGraphGenerator fontesObrasGraph;
	
	private static final String PERS_TILES_DEF = "pers_graph";
	private static final String FOOB_TILES_DEF = "foob_graph";
	
	@RequestMapping(value = "/personagem-graph", method = RequestMethod.GET)
    public String showViewPersonagem() {		
        return PERS_TILES_DEF;
    }
	
	@RequestMapping(value = "/personagem-graph/view", method = RequestMethod.GET)
	public @ResponseBody Graph showGraphPersonagem(HttpServletRequest request) {
		return personagemGraph.getGraph();
	}
	
	//
	@RequestMapping(value = "/fontes-obras-graph", method = RequestMethod.GET)
    public String showViewFontesObras() {		
        return FOOB_TILES_DEF;
    }
	
	@RequestMapping(value = "/fontes-obras-graph/view", method = RequestMethod.GET)
	public @ResponseBody Graph showGraphFontesObras(HttpServletRequest request) {
		return fontesObrasGraph.getGraph();
	}
}