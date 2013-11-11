package br.unifesp.profetas.business.local;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unifesp.profetas.business.AbstractBusiness;
import br.unifesp.profetas.business.common.MessageDTO;
import br.unifesp.profetas.business.common.MessageType;
import br.unifesp.profetas.business.common.OrderType;
import br.unifesp.profetas.business.common.WrapperGrid;
import br.unifesp.profetas.persistence.domain.LocalDAO;
import br.unifesp.profetas.persistence.model.Local;
import br.unifesp.profetas.util.UtilValidator;

@Service("mLocal")
public class ManagementLocalImpl extends AbstractBusiness implements ManagementLocal {
	
	@Autowired private LocalDAO localDAO;

	public LocalDTO getLocalById(Long id) {
		Local local = localDAO.getLocalById(id);
		if(local != null){
			LocalDTO lDTO = new LocalDTO();
			lDTO.setId(local.getId());
			lDTO.setNome(local.getNome());
			lDTO.setLatitude(String.valueOf(local.getLatitude()));
			lDTO.setLongitude(String.valueOf(local.getLongitude()));
			lDTO.setCountry(local.getCountry());
			lDTO.setState(local.getState());
			lDTO.setCity(local.getCity());
			return lDTO;
		}
		return null;
	}
	
	private MessageDTO isNotValid(LocalDTO localDTO, boolean isNew){
		//if(isNew){
			//duplicate ?
		//}
		if(!UtilValidator.validateNotEmptyField(localDTO.getNome())){
			return new MessageDTO(getText("err_local_nome_required"), MessageType.ERROR);
		}
		if(!UtilValidator.validateNotEmptyField(localDTO.getCountry())){
			return new MessageDTO(getText("err_country_nome_required"), MessageType.ERROR);
		}
		return null;
	}
	
	private Local getLocal(Local local, LocalDTO localDTO){
		local.setNome(localDTO.getNome());
		if(localDTO.getLatitude() != null && !"".equals(localDTO.getLatitude())){
			local.setLatitude(Double.parseDouble(localDTO.getLatitude()));
		}
		if(localDTO.getLongitude() != null && !"".equals(localDTO.getLongitude())){
			local.setLongitude(Double.parseDouble(localDTO.getLongitude()));
		}
		local.setCountry(localDTO.getCountry());
		local.setState(localDTO.getState());
		local.setCity(localDTO.getCity());
		local.setActive(true);
		return local;
	}

	public MessageDTO createLocal(LocalDTO localDTO) {
		MessageDTO isNotValid = isNotValid(localDTO, true);
		if(isNotValid != null)
			return isNotValid;
		
		try{
			Local local = new Local();
			local = getLocal(local, localDTO);
			localDAO.saveLocal(local);
			if(local.getId() != null){
				return new MessageDTO(getText("msg_local_created"), MessageType.SUCCESS);
			}
			return new MessageDTO(getText("err_local_not_created"), MessageType.ERROR);
		} catch(Exception e){
			return new MessageDTO(getText("err_local_not_created"), MessageType.ERROR);
		}
	}

	public MessageDTO updateLocal(LocalDTO localDTO) {
		if(localDTO.getId() == null){
			return new MessageDTO(getText("err_local_not_updated"), MessageType.ERROR);
		}
		MessageDTO isNotValid = isNotValid(localDTO, false);
		if(isNotValid != null)
			return isNotValid;
		
		try{
			Local local = localDAO.getLocalById(localDTO.getId());
			if(local != null){
				local = getLocal(local, localDTO);
				localDAO.updateLocal(local);
				if(local.getId() != null){//?
					return new MessageDTO(getText("msg_local_updated"), MessageType.SUCCESS);
				}
			}
			return new MessageDTO(getText("err_local_not_updated"), MessageType.ERROR);
		} catch(Exception e){
			return new MessageDTO(getText("err_local_not_updated"), MessageType.ERROR);
		}
	}

	public MessageDTO deleteLocal(LocalDTO localDTO) {
		if(localDTO.getId() == null)
			return new MessageDTO(getText("err_local_not_deleted"), MessageType.ERROR);
		
		try{
			Local local = localDAO.getLocalById(localDTO.getId());
			if(local != null){
				local.setActive(false);
				localDAO.updateLocal(local);
				return new MessageDTO(getText("msg_local_deleted"), MessageType.SUCCESS);
			}
			return new MessageDTO(getText("err_local_not_deleted"), MessageType.ERROR);
		} catch(Exception e){
			return new MessageDTO(getText("err_local_not_deleted"), MessageType.ERROR);
		}
	}

	public WrapperGrid<LocalDTO> getLocalList(String orderBy,
			OrderType orderType, int page, int numRows) {
		
		List<Local> list = localDAO.listLocal();//TODO: limit
		int total = list == null ? 0 : list.size();//TODO: count
		List<LocalDTO> listDTO = new ArrayList<LocalDTO>();
		for(Local l : list){
			LocalDTO lDTO = new LocalDTO();
			lDTO.setId(l.getId());
			lDTO.setNome(l.getNome());
			listDTO.add(lDTO);
		}
		return getWrapper(listDTO, orderBy, orderType, page, numRows, total, null);
	}
}