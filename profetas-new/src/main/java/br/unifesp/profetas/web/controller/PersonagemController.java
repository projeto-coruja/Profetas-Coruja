package br.unifesp.profetas.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.unifesp.profetas.business.common.MessageDTO;
import br.unifesp.profetas.business.common.OrderType;
import br.unifesp.profetas.business.common.WrapperGrid;
import br.unifesp.profetas.business.personagem.ManagementPersonagem;
import br.unifesp.profetas.business.personagem.PersonagemDTO;
import br.unifesp.profetas.util.ProfetasConstants;
import br.unifesp.profetas.web.AbstractController;

@Controller
public class PersonagemController extends AbstractController {
	
	@Autowired private ManagementPersonagem mPersonagem;
	
	private static final String MODEL		= "personagem";
	private static final String READONLY	= "readonly";
	private static final String TILES_DEF	= "personagem";
	private static final String TILES_DEF_BASIC	= "basic_personagem";
	
	@ModelAttribute(MODEL)
	public PersonagemDTO init() {
		return new PersonagemDTO();
	}
	
	@RequestMapping(value = "/personagem", method = RequestMethod.GET)
    public String showView(@RequestParam(value = "id", required = false) String id, 
    		@RequestParam(value = "readonly", required = false) boolean readonly,
    		ModelMap model, HttpServletRequest request) {
		
		if(id != null) {
			PersonagemDTO perDTO = mPersonagem.getPersonagemById(Long.parseLong(id));
			if(perDTO != null) {
				model.addAttribute(MODEL, perDTO);
				if(readonly) {
					model.addAttribute(READONLY, readonly);
				}
			}
		}
        return TILES_DEF;
    }
	
	@RequestMapping(value = "/personagem/save", method = RequestMethod.POST, 
			headers = {"content-type=application/json"})
    public @ResponseBody MessageDTO savePersonagem(HttpServletRequest request, @RequestBody PersonagemDTO perDTO){
		if(perDTO.getId() == null){
			return mPersonagem.createPersonagem(perDTO);
		} else {
			return mPersonagem.updatePersonagem(perDTO);
		}
	}
	
	@RequestMapping(value = "/personagem/delete", method = RequestMethod.POST, 
			headers = {"content-type=application/json"})
    public @ResponseBody MessageDTO deletePersonagem(HttpServletRequest request, @RequestBody PersonagemDTO perDTO){
		if(perDTO.getId() != null){
			return mPersonagem.deletePersonagem(perDTO);
		}
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/personagem/list", method = RequestMethod.GET)
	public @ResponseBody WrapperGrid listEncontro(HttpServletRequest request,
			@RequestParam(value = "orderBy", required = true) String strOrderBy,
			@RequestParam(value = "orderType", required = true) String strOrderType,
			@RequestParam(value = "page", required = true) Integer page, 
			@RequestParam(value = "searchBy", required = false) String search) {
		
		OrderType orderType = OrderType.getOrderType(strOrderType);
		return mPersonagem.getPersonagemList(strOrderBy, orderType, page, ProfetasConstants.ITEMS_PER_PAGE);
	}
	
	@RequestMapping(value = "/basic-personagem", method = RequestMethod.GET)
    public String showBasicView(ModelMap model, 
    		@RequestParam(value = "divId", required = false) String divId) {
		PersonagemDTO pDTO = new PersonagemDTO();
		pDTO.setDivId(divId);
		model.addAttribute(MODEL, pDTO);
        return TILES_DEF_BASIC;
    }
}