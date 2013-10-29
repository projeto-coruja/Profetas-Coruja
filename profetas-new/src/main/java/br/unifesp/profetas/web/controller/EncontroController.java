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
import br.unifesp.profetas.business.encontro.EncontroDTO;
import br.unifesp.profetas.business.encontro.ManagementEncontro;
import br.unifesp.profetas.persistence.model.Local;
import br.unifesp.profetas.util.ProfetasConstants;
import br.unifesp.profetas.web.AbstractController;
@Controller
public class EncontroController extends AbstractController  {

@Autowired private ManagementEncontro mEncontro;
	
	private static final String MODEL		= "encontro";
	private static final String TILES_DEF	= "encontro";
	
	@ModelAttribute(MODEL)
	public EncontroDTO init() {
		return new EncontroDTO();
	}
	
	@RequestMapping(value = "/encontro", method = RequestMethod.GET)
    public String showView(@RequestParam(value = "id", required = false) String id, 
    		ModelMap model, HttpServletRequest request) {
		
		if(id != null) {
			EncontroDTO encDTO = mEncontro.getEncontroById(Long.parseLong(id));
			if(encDTO != null) {
				model.addAttribute(MODEL, encDTO);
			}
		}
        return TILES_DEF;
    }
	
	@RequestMapping(value = "/encontro/save", method = RequestMethod.POST, 
			headers = {"content-type=application/json"})
    public @ResponseBody MessageDTO saveEncontro(HttpServletRequest request, @RequestBody EncontroDTO encDTO){
		if(encDTO.getId() == null){
			return mEncontro.createEncontro(encDTO);
		} else {
			return mEncontro.updateEncontro(encDTO);
		}
	}
	
	@RequestMapping(value = "/encontro/delete", method = RequestMethod.POST, 
			headers = {"content-type=application/json"})
    public @ResponseBody MessageDTO deleteEncontro(HttpServletRequest request, @RequestBody EncontroDTO encDTO){
		if(encDTO.getId() != null){
			return mEncontro.deleteEncontro(encDTO);
		}
		return null;
	}
	
	@RequestMapping(value = "/encontro/list", method = RequestMethod.GET)
	public @ResponseBody WrapperGrid listEncontro(HttpServletRequest request,
			@RequestParam(value = "orderBy", required = true) String strOrderBy,
			@RequestParam(value = "orderType", required = true) String strOrderType,
			@RequestParam(value = "page", required = true) Integer page, 
			@RequestParam(value = "searchBy", required = false) String search) {
		
		OrderType orderType = OrderType.getOrderType(strOrderType);
		return mEncontro.getEncontroList(strOrderBy, orderType, page, ProfetasConstants.ITEMS_PER_PAGE);
	}
	
	@RequestMapping(value = "/encontro/local", method = RequestMethod.GET)
	public @ResponseBody List<Local> listAllLocal() {
		return mEncontro.getAllLocal();
	}
}