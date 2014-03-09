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
import br.unifesp.profetas.business.fontesobras.FontesObrasDTO;
import br.unifesp.profetas.business.fontesobras.ManagementFontesObras;
import br.unifesp.profetas.util.ProfetasConstants;
import br.unifesp.profetas.web.AbstractController;

@Controller
public class FontesObrasController extends AbstractController {

	@Autowired private ManagementFontesObras mFontesObras;
	
	private static final String MODEL		= "fontes";
	private static final String READONLY	= "readonly";
	private static final String TILES_DEF	= "fontes_obras";
	
	@ModelAttribute(MODEL)
	public FontesObrasDTO init() {
		return new FontesObrasDTO();
	}
	
	@RequestMapping(value = "/fontes-obras", method = RequestMethod.GET)
    public String showView(@RequestParam(value = "id", required = false) String id,
    		@RequestParam(value = "readonly", required = false) boolean readonly,
    		ModelMap model, HttpServletRequest request) {
		
		if(id != null) {
			FontesObrasDTO fobDTO = mFontesObras.getFontesObrasById(Long.parseLong(id));
			if(fobDTO != null) {
				model.addAttribute(MODEL, fobDTO);
				if(readonly) {
					model.addAttribute(READONLY, readonly);
				}
			}
		}
        return TILES_DEF;
    }
	
	@RequestMapping(value = "/fontes-obras/save", method = RequestMethod.POST, 
			headers = {"content-type=application/json"})
    public @ResponseBody MessageDTO saveFontesObras(HttpServletRequest request, @RequestBody FontesObrasDTO fobDTO){
		if(fobDTO.getId() == null){
			return mFontesObras.createFontesObras(fobDTO);
		} else {
			return mFontesObras.updateFontesObras(fobDTO);
		}
	}
	
	@RequestMapping(value = "/fontes-obras/delete", method = RequestMethod.POST, 
			headers = {"content-type=application/json"})
    public @ResponseBody MessageDTO deleteFontesObras(HttpServletRequest request, @RequestBody FontesObrasDTO fobDTO){
		if(fobDTO.getId() != null){
			return mFontesObras.deleteFontesObras(fobDTO);
		}
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/fontes-obras/list", method = RequestMethod.GET)
	public @ResponseBody WrapperGrid listFontesObras(HttpServletRequest request,
			@RequestParam(value = "orderBy", required = true) String strOrderBy,
			@RequestParam(value = "orderType", required = true) String strOrderType,
			@RequestParam(value = "page", required = true) Integer page, 
			@RequestParam(value = "searchBy", required = false) String search) {
		
		OrderType orderType = OrderType.getOrderType(strOrderType);
		return mFontesObras.getFontesObrasList(strOrderBy, orderType, page, ProfetasConstants.ITEMS_PER_PAGE);
	}
	
	@RequestMapping(value = "/fontes-obras/search", method = RequestMethod.GET)
    public @ResponseBody List searchFontesObras(HttpServletRequest request,
            @RequestParam(value = "term", required = false) String word) {
       
        return mFontesObras.searchFontesObras(word);
    }
}