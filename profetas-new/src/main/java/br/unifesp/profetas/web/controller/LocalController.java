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
import br.unifesp.profetas.business.local.LocalDTO;
import br.unifesp.profetas.business.local.ManagementLocal;
import br.unifesp.profetas.util.ProfetasConstants;
import br.unifesp.profetas.web.AbstractController;

@Controller
public class LocalController extends AbstractController {
	
	@Autowired private ManagementLocal mLocal;
	
	private static final String MODEL = "local";
	private static final String TILES_DEF	= "local";
	
	@ModelAttribute(MODEL)
	public LocalDTO init() {
		return new LocalDTO();
	}
	
	@RequestMapping(value = "/local", method = RequestMethod.GET)
    public String showView(@RequestParam(value = "id", required = false) String id, 
    		ModelMap model, HttpServletRequest request) {
		
		if(id != null) {
			LocalDTO locDTO = mLocal.getLocalById(Long.parseLong(id));
			if(locDTO != null) {
				model.addAttribute(MODEL, locDTO);
			}
		}
        return TILES_DEF;
    }
	
	@RequestMapping(value = "/local/save", method = RequestMethod.POST, 
			headers = {"content-type=application/json"})
    public @ResponseBody MessageDTO saveLocal(HttpServletRequest request, @RequestBody LocalDTO locDTO){
		if(locDTO.getId() == null){
			return mLocal.createLocal(locDTO);
		} else {
			return mLocal.updateLocal(locDTO);
		}
	}
	
	@RequestMapping(value = "/local/delete", method = RequestMethod.POST, 
			headers = {"content-type=application/json"})
    public @ResponseBody MessageDTO deleteLocal(HttpServletRequest request, @RequestBody LocalDTO locDTO){
		if(locDTO.getId() != null){
			return mLocal.deleteLocal(locDTO);
		}
		return null;
	}
	
	@RequestMapping(value = "/local/list", method = RequestMethod.GET)
	public @ResponseBody WrapperGrid listLocal(HttpServletRequest request,
			@RequestParam(value = "orderBy", required = true) String strOrderBy,
			@RequestParam(value = "orderType", required = true) String strOrderType,
			@RequestParam(value = "page", required = true) Integer page, 
			@RequestParam(value = "searchBy", required = false) String search) {
		
		OrderType orderType = OrderType.getOrderType(strOrderType);
		return mLocal.getLocalList(strOrderBy, orderType, page, ProfetasConstants.ITEMS_PER_PAGE);
	}
	
	@RequestMapping(value = "/local/search", method = RequestMethod.GET)
    public @ResponseBody List searchLocal(HttpServletRequest request,
            @RequestParam(value = "term", required = false) String word) {
       
        return mLocal.searchLocal(word);
    }
}