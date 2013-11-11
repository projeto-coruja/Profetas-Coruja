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
import br.unifesp.profetas.business.grupomovimento.GrupoMovimentoDTO;
import br.unifesp.profetas.business.grupomovimento.ManagementGrupoMovimento;
import br.unifesp.profetas.util.ProfetasConstants;
import br.unifesp.profetas.web.AbstractController;

@Controller
public class GrupoMovimentoController extends AbstractController {
	
	@Autowired private ManagementGrupoMovimento mGrupoMovimento;
	
	private static final String MODEL = "grupoMovimento";
	private static final String TILES_DEF	= "grupo_movimento";
	
	@ModelAttribute(MODEL)
	public GrupoMovimentoDTO init() {
		return new GrupoMovimentoDTO();
	}
	
	@RequestMapping(value = "/grupo-movimento", method = RequestMethod.GET)
    public String showView(@RequestParam(value = "id", required = false) String id, 
    		ModelMap model, HttpServletRequest request) {
		
		if(id != null) {
			GrupoMovimentoDTO gMoDTO = mGrupoMovimento.getGrupoMovimentoById(Long.parseLong(id));
			if(gMoDTO != null) {
				model.addAttribute(MODEL, gMoDTO);
			}
		}
        return TILES_DEF;
    }
	
	@RequestMapping(value = "/grupo-movimento/save", method = RequestMethod.POST, 
			headers = {"content-type=application/json"})
    public @ResponseBody MessageDTO saveGrupoMovimento(HttpServletRequest request, @RequestBody GrupoMovimentoDTO gMoDTO){
		if(gMoDTO.getId() == null){
			return mGrupoMovimento.createGrupoMovimento(gMoDTO);
		} else {
			return mGrupoMovimento.updateGrupoMovimento(gMoDTO);
		}
	}
	
	@RequestMapping(value = "/grupo-movimento/delete", method = RequestMethod.POST, 
			headers = {"content-type=application/json"})
    public @ResponseBody MessageDTO deleteGrupoMovimento(HttpServletRequest request, @RequestBody GrupoMovimentoDTO gMoDTO){
		if(gMoDTO.getId() != null){
			return mGrupoMovimento.deleteGrupoMovimento(gMoDTO);
		}
		return null;
	}

	@RequestMapping(value = "/grupo-movimento/list", method = RequestMethod.GET)
	public @ResponseBody WrapperGrid listGrupoMovimento(HttpServletRequest request,
			@RequestParam(value = "orderBy", required = true) String strOrderBy,
			@RequestParam(value = "orderType", required = true) String strOrderType,
			@RequestParam(value = "page", required = true) Integer page, 
			@RequestParam(value = "searchBy", required = false) String search) {
		
		OrderType orderType = OrderType.getOrderType(strOrderType);
		WrapperGrid<GrupoMovimentoDTO> wrapper = mGrupoMovimento.getGrupoMovimentoList(strOrderBy, orderType, page, ProfetasConstants.ITEMS_PER_PAGE);
		return wrapper;
	}
}