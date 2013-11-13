package br.unifesp.profetas.business.encontro;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unifesp.profetas.business.AbstractBusiness;
import br.unifesp.profetas.business.common.MessageDTO;
import br.unifesp.profetas.business.common.MessageType;
import br.unifesp.profetas.business.common.OrderType;
import br.unifesp.profetas.business.common.WrapperGrid;
import br.unifesp.profetas.persistence.domain.EncontroDAO;
import br.unifesp.profetas.persistence.model.Encontro;
import br.unifesp.profetas.persistence.model.Local;
import br.unifesp.profetas.util.ProfetasConstants;
import br.unifesp.profetas.util.UtilValidator;

@Service("mEncontro")
public class ManagementEncontroImpl extends AbstractBusiness implements ManagementEncontro {
	
	@Autowired private EncontroDAO encontroDAO;

	public EncontroDTO getEncontroById(Long id) {
		Encontro encontro = encontroDAO.getEncontroById(id);
		if(encontro != null){
			SimpleDateFormat dateFormat = new SimpleDateFormat(ProfetasConstants.DATE_FORMAT_SHORT);
			EncontroDTO eDTO = new EncontroDTO();
			eDTO.setId(encontro.getId());
			eDTO.setNome(encontro.getNome());
			eDTO.setData(encontro.getData() != null ? dateFormat.format(encontro.getData()) : "");
				Local local = encontro.getLocal();
			eDTO.setIdLocal(local != null ? local.getId() : null);
			return eDTO;
		}
		return null;
	}
	
	private MessageDTO isNotValid(EncontroDTO encontroDTO, boolean isNew){
		if(!UtilValidator.validateNotEmptyField(encontroDTO.getNome())){
			return new MessageDTO(getText("err_encontro_nome_required"), MessageType.ERROR);
		}
		Date data = UtilValidator.getDateFromString(encontroDTO.getData());
		if(data == null){
			return new MessageDTO(getText("err_encontro_data_required"), MessageType.ERROR);
		}
		return null;
	}
	
	private Encontro getEncontro(Encontro encontro, EncontroDTO encontroDTO){
		encontro.setNome(encontroDTO.getNome());
		encontro.setData(UtilValidator.getDateFromString(encontroDTO.getData()));
		if(encontroDTO.getIdLocal() != null && encontroDTO.getIdLocal() != -1){
			encontro.setLocal(new Local(encontroDTO.getIdLocal()));
		}
		encontro.setActive(true);
		return encontro;
	}

	public MessageDTO createEncontro(EncontroDTO encontroDTO) {
		MessageDTO isNotValid = isNotValid(encontroDTO, true);
		if(isNotValid != null)
			return isNotValid;
		
		try{
			Encontro encontro = new Encontro();
			encontro = getEncontro(encontro, encontroDTO);
			encontroDAO.saveEncontro(encontro);
			if(encontro.getId() != null){
				return new MessageDTO(getText("msg_encontro_created"), MessageType.SUCCESS);
			}
			return new MessageDTO(getText("err_encontro_not_created"), MessageType.ERROR);
		} catch(Exception e){
			return new MessageDTO(getText("err_encontro_not_created"), MessageType.ERROR);
		}
	}

	public MessageDTO updateEncontro(EncontroDTO encontroDTO) {
		if(encontroDTO.getId() == null){
			return new MessageDTO(getText("err_encontro_not_updated"), MessageType.ERROR);
		}
		MessageDTO isNotValid = isNotValid(encontroDTO, true);
		if(isNotValid != null)
			return isNotValid;
		
		try{
			Encontro encontro = encontroDAO.getEncontroById(encontroDTO.getId());
			if(encontro != null){
				encontro = getEncontro(encontro, encontroDTO);
				encontroDAO.updateEncontro(encontro);
				if(encontro.getId() != null){
					return new MessageDTO(getText("msg_encontro_updated"), MessageType.SUCCESS);
				}
			}
			return new MessageDTO(getText("err_encontro_not_updated"), MessageType.ERROR);
		} catch(Exception e){
			return new MessageDTO(getText("err_encontro_not_updated"), MessageType.ERROR);
		}
	}

	public MessageDTO deleteEncontro(EncontroDTO encontroDTO) {
		if(encontroDTO.getId() == null)
			return new MessageDTO(getText("err_encontro_not_deleted"), MessageType.ERROR);
		
		try{
			Encontro encontro = encontroDAO.getEncontroById(encontroDTO.getId());
			if(encontro != null){
				encontro.setActive(false);
				encontroDAO.updateEncontro(encontro);
				return new MessageDTO(getText("msg_encontro_deleted"), MessageType.SUCCESS);
			}
			return new MessageDTO(getText("err_encontro_not_deleted"), MessageType.ERROR);
		}
		catch(Exception e){
			return new MessageDTO(getText("err_encontro_not_deleted"), MessageType.ERROR);
		}
	}

	public WrapperGrid<EncontroDTO> getEncontroList(String orderBy,
			OrderType orderType, int page, int numRows) {
		List<Encontro> list = encontroDAO.listEncontro();//TODO: limit
		int total = list == null ? 0 : list.size();//TODO: count
		List<EncontroDTO> listDTO = new ArrayList<EncontroDTO>();
		for(Encontro e : list){
			EncontroDTO eDTO = new EncontroDTO();
			eDTO.setId(e.getId());
			eDTO.setNome(e.getNome());
			eDTO.setData(e.getData() != null ? e.getData().toString() : "");
				Local local = e.getLocal();
			eDTO.setDesclocal(local != null ? local.getNome() : "");
			listDTO.add(eDTO);
		}
		return getWrapper(listDTO, orderBy, orderType, page, numRows, total, null);
	}
}