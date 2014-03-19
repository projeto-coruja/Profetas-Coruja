package br.unifesp.profetas.web.controller;

import java.util.List;

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
import br.unifesp.profetas.business.correspondencia.CorrespondenciaDTO;
import br.unifesp.profetas.business.correspondencia.ManagementCorrespondencia;
import br.unifesp.profetas.util.ProfetasConstants;

@Controller
public class CorrespondenciaController {

	@Autowired private ManagementCorrespondencia mCorrespondencia;
	
	private static final String MODEL		= "correspondencia";
	private static final String TILES_DEF	= "correspondencia";
	
	@ModelAttribute(MODEL)
	public CorrespondenciaDTO init() {
		return new CorrespondenciaDTO();
	}
	
	@RequestMapping(value = "/correspondencia", method = RequestMethod.GET)
    public String showView(@RequestParam(value = "id", required = false) String id, 
    		ModelMap model, HttpServletRequest request) {
		
		if(id != null) {
			CorrespondenciaDTO corDTO = mCorrespondencia.getCorrespondenciaById(Long.parseLong(id));
			if(corDTO != null) {
				model.addAttribute(MODEL, corDTO);
			}
		}
        return TILES_DEF;
    }
	
	@RequestMapping(value = "/correspondencia/save", method = RequestMethod.POST, 
			headers = {"content-type=application/json"})
    public @ResponseBody MessageDTO saveCorrespondencia(HttpServletRequest request, @RequestBody CorrespondenciaDTO corDTO){
		if(corDTO.getId() == null){
			return mCorrespondencia.createCorrespondencia(corDTO);
		} else {
			return mCorrespondencia.updateCorrespondencia(corDTO);
		}
	}
	
	@RequestMapping(value = "/correspondencia/delete", method = RequestMethod.POST, 
			headers = {"content-type=application/json"})
    public @ResponseBody MessageDTO deleteCorrespondencia(HttpServletRequest request, @RequestBody CorrespondenciaDTO corDTO){
		if(corDTO.getId() != null){
			return mCorrespondencia.deleteCorrespondencia(corDTO);
		}
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/correspondencia/list", method = RequestMethod.GET)
	public @ResponseBody WrapperGrid listCorrespondencia(HttpServletRequest request,
			@RequestParam(value = "orderBy", required = true) String strOrderBy,
			@RequestParam(value = "orderType", required = true) String strOrderType,
			@RequestParam(value = "page", required = true) Integer page, 
			@RequestParam(value = "searchBy", required = false) String search) {
		
		OrderType orderType = OrderType.getOrderType(strOrderType);
		return mCorrespondencia.getCorrespondenciaList(strOrderBy, orderType, page, ProfetasConstants.ITEMS_PER_PAGE);
	}
	
	@RequestMapping(value = "/correspondencia/search", method = RequestMethod.GET)
    public @ResponseBody List searchCorrespondencia(HttpServletRequest request,
            @RequestParam(value = "term", required = false) String word) {
       
        return mCorrespondencia.searchCorrespondencia(word);
    }
}