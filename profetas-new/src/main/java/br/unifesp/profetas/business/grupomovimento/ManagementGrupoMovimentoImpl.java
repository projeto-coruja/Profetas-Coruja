package br.unifesp.profetas.business.grupomovimento;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unifesp.profetas.business.AbstractBusiness;
import br.unifesp.profetas.business.common.MessageDTO;
import br.unifesp.profetas.business.common.MessageType;
import br.unifesp.profetas.business.common.OrderType;
import br.unifesp.profetas.business.common.WrapperGrid;
import br.unifesp.profetas.persistence.domain.GrupoMovimentoDAO;
import br.unifesp.profetas.persistence.domain.LocalDAO;
import br.unifesp.profetas.persistence.model.GrupoMovimento;
import br.unifesp.profetas.persistence.model.Local;
import br.unifesp.profetas.util.AutoCompleteDTO;
import br.unifesp.profetas.util.ProfetasConstants;
import br.unifesp.profetas.util.UtilValidator;

@Service("mGrupoMovimento")
public class ManagementGrupoMovimentoImpl extends AbstractBusiness implements ManagementGrupoMovimento {
	
	private static Logger logger = Logger.getLogger(ManagementGrupoMovimentoImpl.class);
	
	@Autowired private GrupoMovimentoDAO grupoMovimentoDAO;
	@Autowired private LocalDAO localDAO;

	public GrupoMovimentoDTO getGrupoMovimentoById(Long id) {
		GrupoMovimento gMovimento = grupoMovimentoDAO.getGrupoMovimentoById(id);
		if(gMovimento != null){
			GrupoMovimentoDTO gmDTO = new GrupoMovimentoDTO();
			gmDTO.setId(gMovimento.getId());
			gmDTO.setNome(gMovimento.getNome());
			gmDTO.setAnoInicio(gMovimento.getAnoInicio() != null ? String.valueOf(gMovimento.getAnoInicio()) : "");
			gmDTO.setAnoFim(gMovimento.getAnoFim() != null ? String.valueOf(gMovimento.getAnoFim()) : "");
			gmDTO.setDescricao(gMovimento.getDescricao());
			//locais
				Set<Local> locaisSet = gMovimento.getLocais();
			if(!locaisSet.isEmpty()){
				List<Long> locais = new ArrayList<Long>(locaisSet.size());
				for(Local l : locaisSet){
					locais.add(l.getId());
				}
				if(!locais.isEmpty()){
					gmDTO.setStrLocais(locais.toString());
				}
			}
			return gmDTO;
		}
		return null;
	}
	
	private MessageDTO isNotValid(GrupoMovimentoDTO gMovimentoDTO, boolean isNew){
		if(!UtilValidator.validateNotEmptyField(gMovimentoDTO.getNome())){
			return new MessageDTO(getText("err_g_movimento_nome_required"), MessageType.ERROR);
		}
		return null;
	}
	
	private GrupoMovimento getGrupoMovimento(GrupoMovimento grupoMovimento, GrupoMovimentoDTO gMovimentoDTO){
		grupoMovimento.setNome(gMovimentoDTO.getNome());
		if(gMovimentoDTO.getAnoInicio() != null && !"".equals(gMovimentoDTO.getAnoInicio())){
			grupoMovimento.setAnoInicio(Integer.parseInt(gMovimentoDTO.getAnoInicio()));
		}
		if(gMovimentoDTO.getAnoFim() != null && !"".equals(gMovimentoDTO.getAnoFim())){
			grupoMovimento.setAnoFim(Integer.parseInt(gMovimentoDTO.getAnoFim()));
		}
		grupoMovimento.setDescricao(gMovimentoDTO.getDescricao());
		
		//Local
		if(gMovimentoDTO.getIdLocais() != null){
			int locaisLength = gMovimentoDTO.getIdLocais().length;
			if(locaisLength > 0){
				List<Local> locals = new ArrayList<Local>(locaisLength);
				for(int i = 0; i < locaisLength; i++){
					Local local = localDAO.getLocalById(gMovimentoDTO.getIdLocais()[i]);
					if(local != null){
						locals.add(local);
					} else {
						logger.error("GrupoMovimento: " + gMovimentoDTO.getIdLocais()[i] + " does not exist");
					}
				}
				grupoMovimento.setLocais(new HashSet<Local>(locals));
			}
		}
		
		grupoMovimento.setActive(true);
		return grupoMovimento;
	}

	public MessageDTO createGrupoMovimento(GrupoMovimentoDTO gMovimentoDTO) {
		MessageDTO isNotValid = isNotValid(gMovimentoDTO, true);
		if(isNotValid != null)
			return isNotValid;
		
		try{
			GrupoMovimento grupoMovimento = new GrupoMovimento();
			grupoMovimento = getGrupoMovimento(grupoMovimento, gMovimentoDTO);
			grupoMovimentoDAO.saveGrupoMovimento(grupoMovimento);
			if(grupoMovimento.getId() != null){
				return new MessageDTO(getText("msg_grupo_movimento_created"), MessageType.SUCCESS);
			}
			return new MessageDTO(getText("err_grupo_movimento_not_created"), MessageType.ERROR);
		} catch(Exception e){
			return new MessageDTO(getText("err_grupo_movimento_not_created"), MessageType.ERROR);
		}
	}

	public MessageDTO updateGrupoMovimento(GrupoMovimentoDTO gMovimentoDTO) {
		if(gMovimentoDTO.getId() == null){
			return new MessageDTO(getText("err_grupo_movimento_not_updated"), MessageType.ERROR);
		}
		MessageDTO isNotValid = isNotValid(gMovimentoDTO, false);
		if(isNotValid != null)
			return isNotValid;
		
		try{
			GrupoMovimento grupoMovimento = grupoMovimentoDAO.getGrupoMovimentoById(gMovimentoDTO.getId());
			if(grupoMovimento != null){
				grupoMovimento = getGrupoMovimento(grupoMovimento, gMovimentoDTO);
				grupoMovimentoDAO.updateGrupoMovimento(grupoMovimento);
				if(grupoMovimento.getId() != null){
					return new MessageDTO(getText("msg_grupo_movimento_updated"), MessageType.SUCCESS);
				}
				return new MessageDTO(getText("err_grupo_movimento_not_updated"), MessageType.ERROR);
			}
			return new MessageDTO(getText("err_grupo_movimento_not_updated"), MessageType.ERROR);
		} catch(Exception e){
			return new MessageDTO(getText("err_grupo_movimento_not_updated"), MessageType.ERROR);
		}
	}

	public MessageDTO deleteGrupoMovimento(GrupoMovimentoDTO gMovimentoDTO) {
		if(gMovimentoDTO.getId() == null)
			return new MessageDTO(getText("err_grupo_movimento_not_deleted"), MessageType.ERROR);
		
		try{
			GrupoMovimento grupoMovimento = grupoMovimentoDAO.getGrupoMovimentoById(gMovimentoDTO.getId());
			if(grupoMovimento != null){
				grupoMovimento.setActive(false);
				grupoMovimentoDAO.updateGrupoMovimento(grupoMovimento);
				return new MessageDTO(getText("msg_grupo_movimento_deleted"), MessageType.SUCCESS);
			}
			return new MessageDTO(getText("err_grupo_movimento_not_deleted"), MessageType.ERROR);
		}
		catch(Exception e){
			return new MessageDTO(getText("err_grupo_movimento_not_deleted"), MessageType.ERROR);
		}
	}

	public WrapperGrid<GrupoMovimentoDTO> getGrupoMovimentoList(String orderBy,
			OrderType orderType, int page, int numRows) {
		List<GrupoMovimento> list = grupoMovimentoDAO.listGrupoMovimentoWithLimit(page, numRows, 
				orderType.getDescription(), orderBy);
		int total = grupoMovimentoDAO.getTotalOfGrupoMovimentos().intValue();
		List<GrupoMovimentoDTO> listDTO = new ArrayList<GrupoMovimentoDTO>(total);
		for(GrupoMovimento gm : list){
			GrupoMovimentoDTO gmDTO = new GrupoMovimentoDTO();
			gmDTO.setId(gm.getId());
			gmDTO.setNome(gm.getNome());
			gmDTO.setAnoInicio(gm.getAnoInicio() != null ? String.valueOf(gm.getAnoInicio()) : "");
			gmDTO.setAnoFim(gm.getAnoFim() != null ? String.valueOf(gm.getAnoFim()) : "");
			listDTO.add(gmDTO);
		}		
		return getWrapper(listDTO, orderBy, orderType, page, numRows, total, null);
	}

	@Override
	public List searchGrupoMovimento(String word) {
		int min = ProfetasConstants.AUTOCOMPLETE_LENGTH;
        List<AutoCompleteDTO> lista = new ArrayList<AutoCompleteDTO>();
        if (word.length() > min) {
            List<GrupoMovimento> grupos = grupoMovimentoDAO.searchGrupoMovimento(word);
            if(grupos == null || grupos.isEmpty()){
            	AutoCompleteDTO o = new AutoCompleteDTO();
                o.setLabel(getText("msg_autocomplete_no_results"));
                o.setId(null);
                lista.add(o);
                return lista;
            }
            
            for(GrupoMovimento g : grupos){
                AutoCompleteDTO o = new AutoCompleteDTO(g.getId(), g.getNome());
                lista.add(o);
            }
        } else {
            AutoCompleteDTO o = new AutoCompleteDTO();
            o.setLabel(getText("msg_autocomplete_length", new Object[] { min }));
            o.setId(null);
            lista.add(o);
        }
        return lista;
	}


}