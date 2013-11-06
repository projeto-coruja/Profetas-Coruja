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
import br.unifesp.profetas.business.local.LocalDTO;
import br.unifesp.profetas.persistence.domain.EncontroDAO;
import br.unifesp.profetas.persistence.domain.LocalDAO;
import br.unifesp.profetas.persistence.model.Encontro;
import br.unifesp.profetas.persistence.model.Local;
import br.unifesp.profetas.util.ProfetasConstants;
import br.unifesp.profetas.util.UtilValidator;

@Service("mEncontro")
public class ManagementEncontroImpl extends AbstractBusiness implements ManagementEncontro {
	
	@Autowired private EncontroDAO encontroDAO;
	@Autowired private LocalDAO localDAO;

	public EncontroDTO getEncontroById(Long id) {
		Encontro encontro = encontroDAO.getEncontroById(id);
		if(encontro != null){
			SimpleDateFormat dateFormat = new SimpleDateFormat(ProfetasConstants.DATE_FORMAT_SHORT);
			EncontroDTO eDTO = new EncontroDTO();
			eDTO.setId(encontro.getId());
			eDTO.setData(dateFormat.format(encontro.getData()));
			eDTO.setIdLocal(encontro.getLocal().getId());
			return eDTO;
		}
		return null;
	}

	public MessageDTO createEncontro(EncontroDTO encontroDTO) {
		Date data = UtilValidator.getDateFromString(encontroDTO.getData());
		if(data == null){
			return new MessageDTO(getText("err_encontro_data_required"), MessageType.ERROR);
		}
		if(encontroDTO.getIdLocal() == null){
			return new MessageDTO(getText("err_encontro_local_required"), MessageType.ERROR);
		}
		try{
			Encontro encontro = new Encontro();
			encontro.setData(data);
			encontro.setLocal(new Local(encontroDTO.getIdLocal()));
			encontroDAO.saveEncontro(encontro);
			if(encontro.getId() != null){
				return new MessageDTO(getText("msg_encontro_created"), MessageType.SUCCESS);
			} else {
				return new MessageDTO(getText("err_encontro_not_saved"), MessageType.ERROR);
			}
		} catch(Exception e){
			return new MessageDTO(getText("err_encontro_not_saved"), MessageType.ERROR);
		}
	}

	public MessageDTO updateEncontro(EncontroDTO encontroDTO) {
		if(encontroDTO.getId() == null){
			return new MessageDTO(getText("err_encontro_not_updated"), MessageType.ERROR);
		}
		Date data = UtilValidator.getDateFromString(encontroDTO.getData());
		if(data == null){
			return new MessageDTO(getText("err_encontro_data_required"), MessageType.ERROR);
		}
		if(encontroDTO.getIdLocal() == null){
			return new MessageDTO(getText("err_encontro_local_required"), MessageType.ERROR);
		}
		try{
			Encontro encontro = encontroDAO.getEncontroById(encontroDTO.getId());
			if(encontro != null){
				encontro.setData(data);
				encontro.setLocal(new Local(encontroDTO.getIdLocal()));
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
		try{
			Encontro encontro = new Encontro();
			encontro.setId(encontroDTO.getId());
			encontroDAO.deleteEncontro(encontro);
			return new MessageDTO(getText("msg_encontro_deleted"), MessageType.SUCCESS);
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
			eDTO.setData(e.getData().toString());
			eDTO.setDesclocal(e.getLocal().getNome());
			listDTO.add(eDTO);
		}
		return getWrapper(listDTO, orderBy, orderType, page, numRows, total, null);
	}
	
	public List<LocalDTO> getLocals() {
		List<Local> locals = localDAO.listLocal();
		List<LocalDTO> listDTO = new ArrayList<LocalDTO>();
		for(Local l : locals){
			LocalDTO lDTO = new LocalDTO();
			lDTO.setId(l.getId());
			lDTO.setNome(l.getNome());
			listDTO.add(lDTO);
		}
		return listDTO;
	}
}