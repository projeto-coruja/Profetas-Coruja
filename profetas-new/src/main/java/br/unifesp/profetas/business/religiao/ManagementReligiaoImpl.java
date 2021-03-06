package br.unifesp.profetas.business.religiao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unifesp.profetas.business.AbstractBusiness;
import br.unifesp.profetas.business.common.MessageDTO;
import br.unifesp.profetas.business.common.MessageType;
import br.unifesp.profetas.business.common.OrderType;
import br.unifesp.profetas.business.common.WrapperGrid;
import br.unifesp.profetas.persistence.domain.ReligiaoCrencasDAO;
import br.unifesp.profetas.persistence.model.Correspondencia;
import br.unifesp.profetas.persistence.model.ReligiaoCrencas;
import br.unifesp.profetas.util.AutoCompleteDTO;
import br.unifesp.profetas.util.ProfetasConstants;
import br.unifesp.profetas.util.UtilValidator;

@Service("mReligiao")
public class ManagementReligiaoImpl extends AbstractBusiness implements ManagementReligiao {
	
	@Autowired private ReligiaoCrencasDAO religiaoCrencasDAO;

	public ReligiaoCrencasDTO getReligiaoCrencasById(Long id) {
		ReligiaoCrencas religiao = religiaoCrencasDAO.getReligiaoCrencasById(id);
		if(religiao != null){
			ReligiaoCrencasDTO rDTO = new ReligiaoCrencasDTO();
			rDTO.setId(religiao.getId());
			rDTO.setNome(religiao.getNome());
			rDTO.setAnoInicio(religiao.getAnoInicio() != null ? String.valueOf(religiao.getAnoInicio()) : "");
			rDTO.setAnoFim(religiao.getAnoFim() != null ? String.valueOf(religiao.getAnoFim()) : "");
			rDTO.setDescricao(religiao.getDescricao());
			return rDTO;
		}
		return null;
	}
	
	private MessageDTO isNotValid(ReligiaoCrencasDTO religiaoCrencasDTO, boolean isNew){
		if(!UtilValidator.validateNotEmptyField(religiaoCrencasDTO.getNome())){
			return new MessageDTO(getText("err_religiao_nome_required"), MessageType.ERROR);
		}
		return null;
	}
	
	private ReligiaoCrencas getReligiaoCrencas(ReligiaoCrencas religiao, ReligiaoCrencasDTO religiaoDTO){
		religiao.setNome(religiaoDTO.getNome());
		if(religiaoDTO.getAnoInicio() != null && !"".equals(religiaoDTO.getAnoInicio())){
			religiao.setAnoInicio(Integer.parseInt(religiaoDTO.getAnoInicio()));
		} else{
			religiao.setAnoInicio(null);
		}
		
		if(religiaoDTO.getAnoFim() != null && !"".equals(religiaoDTO.getAnoFim())){
			religiao.setAnoFim(Integer.parseInt(religiaoDTO.getAnoFim()));
		} else{
			religiao.setAnoFim(null);
		}
		
		religiao.setDescricao(religiaoDTO.getDescricao());
		religiao.setActive(true);
		return religiao;
	}

	public MessageDTO createReligiaoCrencas(ReligiaoCrencasDTO religiaoCrencasDTO) {
		MessageDTO isNotValid = isNotValid(religiaoCrencasDTO, true);
		if(isNotValid != null)
			return isNotValid;
		
		try{
			ReligiaoCrencas religiao = new ReligiaoCrencas();
			religiao = getReligiaoCrencas(religiao, religiaoCrencasDTO);
			religiaoCrencasDAO.saveReligiaoCrencas(religiao);
			if(religiao.getId() != null){
				return new MessageDTO(getText("msg_religiao_created"), MessageType.SUCCESS);
			} else {
				return new MessageDTO(getText("err_religiao_not_created"), MessageType.ERROR);
			}
		} catch(Exception e){
			return new MessageDTO(getText("err_religiao_not_created"), MessageType.ERROR);
		}
	}

	public MessageDTO updateReligiaoCrencas(ReligiaoCrencasDTO religiaoCrencasDTO) {
		if(religiaoCrencasDTO.getId() == null){
			return new MessageDTO(getText("err_religiao_not_updated"), MessageType.ERROR);
		}
		MessageDTO isNotValid = isNotValid(religiaoCrencasDTO, true);
		if(isNotValid != null)
			return isNotValid;
		
		try{
			ReligiaoCrencas religiao = religiaoCrencasDAO.getReligiaoCrencasById(religiaoCrencasDTO.getId());
			if(religiao != null){
				religiao = getReligiaoCrencas(religiao, religiaoCrencasDTO);
				religiaoCrencasDAO.updateReligiaoCrencas(religiao);
				if(religiao.getId() != null){
					return new MessageDTO(getText("msg_religiao_updated"), MessageType.SUCCESS);
				}
			}
			return new MessageDTO(getText("err_religiao_not_updated"), MessageType.ERROR);
		} catch(Exception e){
			return new MessageDTO(getText("err_religiao_not_updated"), MessageType.ERROR);
		}
	}

	public MessageDTO deleteReligiaoCrencas(ReligiaoCrencasDTO religiaoCrencasDTO) {
		if(religiaoCrencasDTO.getId() == null)
			return new MessageDTO(getText("err_local_not_deleted"), MessageType.ERROR);
		
		try{
			ReligiaoCrencas religiao = religiaoCrencasDAO.getReligiaoCrencasById(religiaoCrencasDTO.getId());
			if(religiao != null){
				religiao.setActive(false);
				religiaoCrencasDAO.updateReligiaoCrencas(religiao);
				return new MessageDTO(getText("msg_religiao_deleted"), MessageType.SUCCESS);
			}
			return new MessageDTO(getText("err_religiao_not_deleted"), MessageType.ERROR);
		}
		catch(Exception e){
			return new MessageDTO(getText("err_religiao_not_deleted"), MessageType.ERROR);
		}
	}

	public WrapperGrid<ReligiaoCrencasDTO> getReligiaoCrencasList(
			String orderBy, OrderType orderType, int page, int numRows) {

		List<ReligiaoCrencas> list = religiaoCrencasDAO.listReligiaoCrencasWithLimit(page, numRows, 
				orderType.getDescription(), orderBy);
		int total = religiaoCrencasDAO.getTotalOfReligiaoCrencas().intValue();
		List<ReligiaoCrencasDTO> listDTO = new ArrayList<ReligiaoCrencasDTO>(total);
		for(ReligiaoCrencas r : list){
			ReligiaoCrencasDTO rDTO = new ReligiaoCrencasDTO();
			rDTO.setId(r.getId());
			rDTO.setNome(r.getNome());
			rDTO.setAnoInicio(r.getAnoInicio() != null ? String.valueOf(r.getAnoInicio()) : "");
			rDTO.setAnoFim(r.getAnoFim() != null ? String.valueOf(r.getAnoFim()) : "");
			rDTO.setDescricao(r.getDescricao());
			listDTO.add(rDTO);
		}
		return getWrapper(listDTO, orderBy, orderType, page, numRows, total, null);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public List searchReligioes(String word) {
		int min = ProfetasConstants.AUTOCOMPLETE_LENGTH;
        List<AutoCompleteDTO> lista = new ArrayList<AutoCompleteDTO>();
        if (word.length() > min) {
            List<ReligiaoCrencas> religioes = religiaoCrencasDAO.searchReligioes(word);
            if(religioes == null || religioes.isEmpty()){
            	AutoCompleteDTO o = new AutoCompleteDTO();
                o.setLabel(getText("msg_autocomplete_no_results"));
                o.setId(null);
                lista.add(o);
                return lista;
            }
            
            for(ReligiaoCrencas r : religioes){
                AutoCompleteDTO o = new AutoCompleteDTO(r.getId(), r.getNome());
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