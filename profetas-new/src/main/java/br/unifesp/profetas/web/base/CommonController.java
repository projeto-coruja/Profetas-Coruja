package br.unifesp.profetas.web.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.unifesp.profetas.business.common.ManagementCommon;
import br.unifesp.profetas.business.correspondencia.CorrespondenciaDTO;
import br.unifesp.profetas.business.encontro.EncontroDTO;
import br.unifesp.profetas.business.fontesobras.ClassificacaoDTO;
import br.unifesp.profetas.business.fontesobras.FontesObrasDTO;
import br.unifesp.profetas.business.grupomovimento.GrupoMovimentoDTO;
import br.unifesp.profetas.business.local.LocalDTO;
import br.unifesp.profetas.business.personagem.PersonagemDTO;
import br.unifesp.profetas.business.profile.ProfileDTO;
import br.unifesp.profetas.business.religiao.ReligiaoCrencasDTO;
import br.unifesp.profetas.persistence.domain.PersonagemViewDAO;
import br.unifesp.profetas.persistence.model.PersonagemView;
import br.unifesp.profetas.util.ProfetasConstants;

@Controller
public class CommonController {
	
	@Autowired private ManagementCommon mCommon;
	@Autowired private PersonagemViewDAO personagemViewDAO;
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public @ResponseBody List<PersonagemView> getView(SecurityContextHolderAwareRequestWrapper request) {
		return personagemViewDAO.listPersonagemView();
	}
	
	@RequestMapping(value = "/profiles", method = RequestMethod.GET)
	public @ResponseBody List<ProfileDTO> getProfiles(SecurityContextHolderAwareRequestWrapper request) {
		if(request.isUserInRole(ProfetasConstants.ROLE_NAME_ADMIN)){
			return mCommon.getProfiles();
		} else {
			return null;
		}		
	}
	
	@RequestMapping(value = "/personagens", method = RequestMethod.GET)
	public @ResponseBody List<PersonagemDTO> getPersonagems(SecurityContextHolderAwareRequestWrapper request) {
		if(request.isUserInRole(ProfetasConstants.ROLE_NAME_SAVE)){
			return mCommon.getPersonagens();
		} else {
			return null;
		}		
	}
	
	@RequestMapping(value = "/locals", method = RequestMethod.GET)
	public @ResponseBody List<LocalDTO> getLocals(SecurityContextHolderAwareRequestWrapper request) {
		if(request.isUserInRole(ProfetasConstants.ROLE_NAME_SAVE)){
			return mCommon.getLocals();
		} else {
			return null;
		}		
	}
	
	@RequestMapping(value = "/classificacoes", method = RequestMethod.GET)
	public @ResponseBody List<ClassificacaoDTO> getClassificacoes(SecurityContextHolderAwareRequestWrapper request) {
		if(request.isUserInRole(ProfetasConstants.ROLE_NAME_SAVE)){
			return mCommon.getClassificacoes();
		} else {
			return null;
		}		
	}
	
	@RequestMapping(value = "/gru-movimentos", method = RequestMethod.GET)
	public @ResponseBody List<GrupoMovimentoDTO> getGruMovimentos(SecurityContextHolderAwareRequestWrapper request) {
		if(request.isUserInRole(ProfetasConstants.ROLE_NAME_SAVE)){
			return mCommon.getGruMovimentos();
		} else {
			return null;
		}		
	}
	
	@RequestMapping(value = "/obras", method = RequestMethod.GET)
	public @ResponseBody List<FontesObrasDTO> getObras(SecurityContextHolderAwareRequestWrapper request) {
		if(request.isUserInRole(ProfetasConstants.ROLE_NAME_SAVE)){
			return mCommon.getObrasCitadas();
		} else {
			return null;
		}		
	}
	
	@RequestMapping(value = "/religioes-crencas", method = RequestMethod.GET)
	public @ResponseBody List<ReligiaoCrencasDTO> getReligioesCrencas(SecurityContextHolderAwareRequestWrapper request) {
		if(request.isUserInRole(ProfetasConstants.ROLE_NAME_SAVE)){
			return mCommon.getReligioes();
		} else {
			return null;
		}		
	}
	
	@RequestMapping(value = "/encontros", method = RequestMethod.GET)
	public @ResponseBody List<EncontroDTO> getEncontrosCrencas(SecurityContextHolderAwareRequestWrapper request) {
		if(request.isUserInRole(ProfetasConstants.ROLE_NAME_SAVE)){
			return mCommon.getEncontros();
		} else {
			return null;
		}		
	}
	
	@RequestMapping(value = "/correspondencias", method = RequestMethod.GET)
	public @ResponseBody List<CorrespondenciaDTO> getCorrespondencias(SecurityContextHolderAwareRequestWrapper request) {
		if(request.isUserInRole(ProfetasConstants.ROLE_NAME_SAVE)){
			return mCommon.getCorrespondencias();
		} else {
			return null;
		}		
	}
}