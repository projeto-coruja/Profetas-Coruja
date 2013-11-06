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
import br.unifesp.profetas.business.profile.ManagementProfile;
import br.unifesp.profetas.business.profile.ProfileDTO;
import br.unifesp.profetas.business.profile.RoleDTO;
import br.unifesp.profetas.util.ProfetasConstants;
import br.unifesp.profetas.web.AbstractController;

@Controller
public class ProfileController extends AbstractController {

	@Autowired private ManagementProfile mProfile;
	
	private static final String MODEL 	= "profile";
	private static final String TILES_DEF	= "profile";
	
	@ModelAttribute(MODEL)
	public ProfileDTO init() {
		return new ProfileDTO();
	}
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String showView(@RequestParam(value = "id", required = false) String id, 
    		ModelMap model, HttpServletRequest request) {
		
		if(id != null) {
			ProfileDTO proDTO = mProfile.getProfileById(Integer.parseInt(id));
			if(proDTO != null) {
				model.addAttribute(MODEL, proDTO);
			}
		}
        return TILES_DEF;
    }
	
	@RequestMapping(value = "/profile/save", method = RequestMethod.POST, 
			headers = {"content-type=application/json"})
    public @ResponseBody MessageDTO saveProfile(HttpServletRequest request, @RequestBody ProfileDTO proDTO){
		if(proDTO.getId() == null){
			return mProfile.createProfile(proDTO);
		} else {
			return mProfile.updateProfile(proDTO);
		}
	}
	
	@RequestMapping(value = "/profile/delete", method = RequestMethod.POST, 
			headers = {"content-type=application/json"})
    public @ResponseBody MessageDTO deleteProfile(HttpServletRequest request, @RequestBody ProfileDTO proDTO){
		if(proDTO.getId() != null){
			return mProfile.deleteProfile(proDTO);
		}
		return null;
	}
	
	@RequestMapping(value = "/profile/list", method = RequestMethod.GET)
	public @ResponseBody WrapperGrid listProfile(HttpServletRequest request,
			@RequestParam(value = "orderBy", required = true) String strOrderBy,
			@RequestParam(value = "orderType", required = true) String strOrderType,
			@RequestParam(value = "page", required = true) Integer page, 
			@RequestParam(value = "searchBy", required = false) String search) {
		
		OrderType orderType = OrderType.getOrderType(strOrderType);
		return mProfile.getProfileList(strOrderBy, orderType, page, ProfetasConstants.ITEMS_PER_PAGE);
	}
	
	/**/
	@RequestMapping(value = "/profile/roles", method = RequestMethod.GET)
	public @ResponseBody List<RoleDTO> roleList() {
		return mProfile.roleList();
	}
}