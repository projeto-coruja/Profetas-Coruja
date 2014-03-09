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
import br.unifesp.profetas.business.religiao.ManagementReligiao;
import br.unifesp.profetas.business.religiao.ReligiaoCrencasDTO;
import br.unifesp.profetas.util.ProfetasConstants;
import br.unifesp.profetas.web.AbstractController;

@Controller
public class ReligiaoCrencasController extends AbstractController {
	
	@Autowired private ManagementReligiao mReligiao;
	
	private static final String MODEL = "religiao";
	private static final String TILES_DEF	= "religiao_crencas";
	
	@ModelAttribute(MODEL)
	public ReligiaoCrencasDTO init() {
		return new ReligiaoCrencasDTO();
	}
	
	@RequestMapping(value = "/religiao-crencas", method = RequestMethod.GET)
    public String showView(@RequestParam(value = "id", required = false) String id, 
    		ModelMap model, HttpServletRequest request) {
		
		if(id != null) {
			ReligiaoCrencasDTO relDTO = mReligiao.getReligiaoCrencasById(Long.parseLong(id));
			if(relDTO != null) {
				model.addAttribute(MODEL, relDTO);
			}
		}
        return TILES_DEF;
    }
	
	@RequestMapping(value = "/religiao-crencas/save", method = RequestMethod.POST, 
			headers = {"content-type=application/json"})
    public @ResponseBody MessageDTO saveReligiao(HttpServletRequest request, @RequestBody ReligiaoCrencasDTO relDTO){
		if(relDTO.getId() == null){
			return mReligiao.createReligiaoCrencas(relDTO);
		} else {
			return mReligiao.updateReligiaoCrencas(relDTO);
		}
	}
	
	@RequestMapping(value = "/religiao-crencas/delete", method = RequestMethod.POST, 
			headers = {"content-type=application/json"})
    public @ResponseBody MessageDTO deleteReligiao(HttpServletRequest request, @RequestBody ReligiaoCrencasDTO relDTO){
		if(relDTO.getId() != null){
			return mReligiao.deleteReligiaoCrencas(relDTO);
		}
		return null;
	}
	
	@RequestMapping(value = "/religiao-crencas/list", method = RequestMethod.GET)
	public @ResponseBody WrapperGrid listReligiao(HttpServletRequest request,
			@RequestParam(value = "orderBy", required = true) String strOrderBy,
			@RequestParam(value = "orderType", required = true) String strOrderType,
			@RequestParam(value = "page", required = true) Integer page, 
			@RequestParam(value = "searchBy", required = false) String search) {
		
		OrderType orderType = OrderType.getOrderType(strOrderType);
		return mReligiao.getReligiaoCrencasList(strOrderBy, orderType, page, ProfetasConstants.ITEMS_PER_PAGE);
	}
	
	@RequestMapping(value = "/religiao-crencas/search", method = RequestMethod.GET)
    public @ResponseBody List searchReligioes(HttpServletRequest request,
            @RequestParam(value = "term", required = false) String word) {
       
        return mReligiao.searchReligioes(word);
    }
}