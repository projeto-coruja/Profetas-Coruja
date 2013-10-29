package br.unifesp.profetas.business.religiao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unifesp.profetas.business.AbstractBusiness;
import br.unifesp.profetas.business.common.MessageDTO;
import br.unifesp.profetas.business.common.MessageType;
import br.unifesp.profetas.business.common.OrderType;
import br.unifesp.profetas.business.common.WrapperGrid;
import br.unifesp.profetas.persistence.domain.ReligiaoCrencasDAO;
import br.unifesp.profetas.persistence.model.ReligiaoCrencas;
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
			rDTO.setAnoInicio(String.valueOf(religiao.getAnoInicio()));
			rDTO.setAnoFim(String.valueOf(religiao.getAnoFim()));
			rDTO.setDescricao(religiao.getDescricao());
			return rDTO;
		}
		return null;
	}

	public MessageDTO createReligiaoCrencas(ReligiaoCrencasDTO religiaoCrencasDTO) {
		if(!UtilValidator.validateNotEmptyField(religiaoCrencasDTO.getNome())){
			return new MessageDTO(getText("err_religiao_nome_required"), MessageType.ERROR);
		}
		try{
			ReligiaoCrencas religiao = new ReligiaoCrencas();
			religiao.setNome(religiaoCrencasDTO.getNome());
			if(religiaoCrencasDTO.getAnoInicio() != null && !"".equals(religiaoCrencasDTO.getAnoInicio())){
				religiao.setAnoInicio(Integer.parseInt(religiaoCrencasDTO.getAnoInicio()));
			}
			if(religiaoCrencasDTO.getAnoFim() != null && !"".equals(religiaoCrencasDTO.getAnoFim())){
				religiao.setAnoFim(Integer.parseInt(religiaoCrencasDTO.getAnoFim()));
			}
			religiao.setDescricao(religiaoCrencasDTO.getDescricao());
			religiaoCrencasDAO.saveReligiaoCrencas(religiao);
			if(religiao.getId() != null){
				return new MessageDTO(getText("msg_religiao_created"), MessageType.SUCCESS);
			} else {
				return new MessageDTO(getText("err_religiao_not_saved"), MessageType.ERROR);
			}
		} catch(Exception e){
			return new MessageDTO(getText("err_religiao_not_saved"), MessageType.ERROR);
		}
	}

	public MessageDTO updateReligiaoCrencas(ReligiaoCrencasDTO religiaoCrencasDTO) {
		if(religiaoCrencasDTO.getId() == null){
			return new MessageDTO(getText("err_religiao_not_updated"), MessageType.ERROR);
		}
		if(!UtilValidator.validateNotEmptyField(religiaoCrencasDTO.getNome())){
			return new MessageDTO(getText("err_religiao_nome_required"), MessageType.ERROR);
		}
		try{
			ReligiaoCrencas religiao = religiaoCrencasDAO.getReligiaoCrencasById(religiaoCrencasDTO.getId());
			if(religiao != null){
				religiao.setNome(religiaoCrencasDTO.getNome());
				if(religiaoCrencasDTO.getAnoInicio() != null && !"".equals(religiaoCrencasDTO.getAnoInicio())){
					religiao.setAnoInicio(Integer.parseInt(religiaoCrencasDTO.getAnoInicio()));
				}
				if(religiaoCrencasDTO.getAnoFim() != null && !"".equals(religiaoCrencasDTO.getAnoFim())){
					religiao.setAnoFim(Integer.parseInt(religiaoCrencasDTO.getAnoFim()));
				}
				religiao.setDescricao(religiaoCrencasDTO.getDescricao());
				religiaoCrencasDAO.saveReligiaoCrencas(religiao);
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
		try{
			ReligiaoCrencas religiao = new ReligiaoCrencas();
			religiao.setId(religiaoCrencasDTO.getId());
			religiaoCrencasDAO.deleteReligiaoCrencas(religiao);
			return new MessageDTO(getText("msg_religiao_deleted"), MessageType.SUCCESS);
		}
		catch(Exception e){
			return new MessageDTO(getText("err_religiao_not_deleted"), MessageType.ERROR);
		}
	}

	public WrapperGrid<ReligiaoCrencas> getReligiaoCrencasList(
			String orderBy, OrderType orderType, int page, int numRows) {

		List<ReligiaoCrencas> list = religiaoCrencasDAO.listReligiaoCrencas();//TODO: limit
		int total = list == null ? 0 : list.size();//TODO: count
		return getWrapper(list, orderBy, orderType, page, numRows, total, null);
	}
}