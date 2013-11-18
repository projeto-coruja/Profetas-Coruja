package br.unifesp.profetas.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.unifesp.profetas.business.common.WrapperGrid;
import br.unifesp.profetas.business.fontesobras.FontesObrasDTO;
import br.unifesp.profetas.business.personagem.PersonagemDTO;

@Controller
public class SearchController {
	
	private static final String TILES_DEF	= "search";
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
    public String showView() {
        return TILES_DEF;
    }
	
	@RequestMapping(value = "/search/personagem", method = RequestMethod.GET)
    public @ResponseBody WrapperGrid<PersonagemDTO> searchByPersonagem(HttpServletRequest request,
			@RequestParam(value = "orderBy", required = true) String strOrderBy,
			@RequestParam(value = "orderType", required = true) String strOrderType,
			@RequestParam(value = "page", required = true) Integer page, 
			@RequestParam(value = "searchBy", required = true) String search) {
        return null;
    }
	
	@RequestMapping(value = "/search/fonte", method = RequestMethod.GET)
    public @ResponseBody WrapperGrid<FontesObrasDTO> searchByFonteObra(HttpServletRequest request,
			@RequestParam(value = "orderBy", required = true) String strOrderBy,
			@RequestParam(value = "orderType", required = true) String strOrderType,
			@RequestParam(value = "page", required = true) Integer page, 
			@RequestParam(value = "searchBy", required = true) String search) {
        return null;
    }
}