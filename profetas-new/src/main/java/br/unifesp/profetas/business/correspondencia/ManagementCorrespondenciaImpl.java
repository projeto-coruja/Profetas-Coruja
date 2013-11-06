package br.unifesp.profetas.business.correspondencia;

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
import br.unifesp.profetas.business.personagem.PersonagemDTO;
import br.unifesp.profetas.persistence.domain.CorrespondenciaDAO;
import br.unifesp.profetas.persistence.domain.LocalDAO;
import br.unifesp.profetas.persistence.domain.PersonagemDAO;
import br.unifesp.profetas.persistence.model.Correspondencia;
import br.unifesp.profetas.persistence.model.Local;
import br.unifesp.profetas.persistence.model.Personagem;
import br.unifesp.profetas.util.ProfetasConstants;
import br.unifesp.profetas.util.UtilValidator;

@Service("mCorrespondencia")
public class ManagementCorrespondenciaImpl extends AbstractBusiness implements ManagementCorrespondencia {
	
	@Autowired private CorrespondenciaDAO correspondenciaDAO;
	@Autowired private PersonagemDAO personagemDAO;
	@Autowired private LocalDAO localDAO;

	public CorrespondenciaDTO getCorrespondenciaById(Long id) {
		Correspondencia correspondencia = correspondenciaDAO.getCorrespondenciaById(id);
		if(correspondencia != null){
			SimpleDateFormat dateFormat = new SimpleDateFormat(ProfetasConstants.DATE_FORMAT_SHORT);
			CorrespondenciaDTO cDTO = new CorrespondenciaDTO();
			cDTO.setId(correspondencia.getId());
			cDTO.setIdRemetente(correspondencia.getRemetente().getId());
			cDTO.setIdDestinatario(correspondencia.getDestinatario().getId());
			cDTO.setIdLocal(correspondencia.getId());
			cDTO.setData(dateFormat.format(correspondencia.getData()));
			return cDTO;
		}
		return null;
	}

	public MessageDTO createCorrespondencia(CorrespondenciaDTO correspondenciaDTO) {
		Date data  = UtilValidator.getDateFromString(correspondenciaDTO.getData());
		try{
			Correspondencia correspondencia = new Correspondencia();
			correspondencia.setRemetente(new Personagem(correspondenciaDTO.getIdRemetente()));
			correspondencia.setDestinatario(new Personagem(correspondenciaDTO.getIdDestinatario()));
			correspondencia.setLocal(new Local(correspondenciaDTO.getIdLocal()));
			correspondencia.setData(data);
			correspondenciaDAO.saveCorrespondencia(correspondencia);
			if(correspondencia.getId() != null){
				return new MessageDTO(getText("msg_correspondencia_saved"), MessageType.SUCCESS);
			} else {
				return new MessageDTO(getText("err_correspondencia_not_saved"), MessageType.ERROR);
			}
		} catch(Exception e){
			return new MessageDTO(getText("err_correspondencia_not_saved"), MessageType.ERROR);
		}
	}

	public MessageDTO updateCorrespondencia(CorrespondenciaDTO correspondenciaDTO) {
		if(correspondenciaDTO.getId() == null){
			return new MessageDTO(getText("err_correspondencia_not_updated"), MessageType.ERROR);
		}
		Date data  = UtilValidator.getDateFromString(correspondenciaDTO.getData());
		try{
			Correspondencia correspondencia = correspondenciaDAO.getCorrespondenciaById(correspondenciaDTO.getId());
			if(correspondencia != null) {
				correspondencia.setRemetente(new Personagem(correspondenciaDTO.getIdRemetente()));
				correspondencia.setDestinatario(new Personagem(correspondenciaDTO.getIdDestinatario()));
				correspondencia.setLocal(new Local(correspondenciaDTO.getIdLocal()));
				correspondencia.setData(data);
				correspondenciaDAO.saveCorrespondencia(correspondencia);
				if(correspondencia.getId() != null){
					return new MessageDTO(getText("msg_correspondencia_updated"), MessageType.SUCCESS);
				}
			}
			return new MessageDTO(getText("err_correspondencia_not_updated"), MessageType.ERROR);
		} catch(Exception e){
			return new MessageDTO(getText("err_correspondencia_not_updated"), MessageType.ERROR);
		}
	}

	public MessageDTO deleteCorrespondencia(
			CorrespondenciaDTO correspondenciaDTO) {
		try{
			Correspondencia correspondencia = correspondenciaDAO.getCorrespondenciaById(correspondenciaDTO.getId());
			correspondenciaDAO.deleteCorrespondencia(correspondencia);
			return new MessageDTO(getText("msg_correspondencia_deleted"), MessageType.SUCCESS);
		}
		catch(Exception e){
			return new MessageDTO(getText("err_correspondencia_not_deleted"), MessageType.ERROR);
		}
	}

	public WrapperGrid<CorrespondenciaDTO> getCorrespondenciaList(String orderBy,
			OrderType orderType, int page, int numRows) {
		List<Correspondencia> list = correspondenciaDAO.listEncontro();//TODO: limit
		int total = list == null ? 0 : list.size();//TODO: count
		List<CorrespondenciaDTO> listDTO = new ArrayList<CorrespondenciaDTO>();
		for(Correspondencia c : list){
			CorrespondenciaDTO cDTO = new CorrespondenciaDTO();
			cDTO.setId(c.getId());
			cDTO.setNomeRemetente(c.getRemetente().getNome() + " " + c.getRemetente().getApelido());
			cDTO.setNomeDestinatario(c.getDestinatario().getNome() + " " + c.getDestinatario().getApelido());
			cDTO.setNomeLocal(c.getLocal().getNome());
			cDTO.setData(c.getData().toString());
			listDTO.add(cDTO);
		}
		return getWrapper(listDTO, orderBy, orderType, page, numRows, total, null);
	}

	public List<PersonagemDTO> getPersonagems() {
		List<Personagem> personagems = personagemDAO.listPersonagem();
		List<PersonagemDTO> listDTO = new ArrayList<PersonagemDTO>();
		for(Personagem p : personagems){
			PersonagemDTO pDTO = new PersonagemDTO();
			pDTO.setId(p.getId());
			pDTO.setNome(p.getNome());
			pDTO.setApelido(p.getApelido());
			listDTO.add(pDTO);
		}
		return listDTO;
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