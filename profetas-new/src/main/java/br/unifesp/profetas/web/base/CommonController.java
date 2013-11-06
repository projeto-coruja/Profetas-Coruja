package br.unifesp.profetas.web.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.unifesp.profetas.business.common.ManagementCommon;
import br.unifesp.profetas.business.profile.ProfileDTO;
import br.unifesp.profetas.util.ProfetasConstants;

@Controller
public class CommonController {
	
	@Autowired private ManagementCommon mCommon;
	
	@RequestMapping(value = "/profiles", method = RequestMethod.GET)
	public @ResponseBody List<ProfileDTO> profileList(SecurityContextHolderAwareRequestWrapper request) {
		if(request.isUserInRole(ProfetasConstants.ROLE_NAME_ADMIN)){
			return mCommon.profileList();
		} else {
			return null;
		}		
	}

}