package br.unifesp.profetas.business.personagem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unifesp.profetas.business.AbstractBusiness;
import br.unifesp.profetas.business.common.ManagementCommon;
import br.unifesp.profetas.business.common.MessageDTO;
import br.unifesp.profetas.business.common.MessageType;
import br.unifesp.profetas.business.common.OrderType;
import br.unifesp.profetas.business.common.WrapperGrid;
import br.unifesp.profetas.business.local.LocalDTO;
import br.unifesp.profetas.business.profile.ProfileDTO;
import br.unifesp.profetas.persistence.domain.LocalDAO;
import br.unifesp.profetas.persistence.domain.PersonagemDAO;
import br.unifesp.profetas.persistence.model.Local;
import br.unifesp.profetas.persistence.model.Personagem;
import br.unifesp.profetas.persistence.model.Profile;
import br.unifesp.profetas.util.ProfetasConstants;
import br.unifesp.profetas.util.UtilValidator;

@Service("mPersonagem")
public class ManagementPersonagemImpl extends AbstractBusiness implements ManagementPersonagem {

	@Autowired private ManagementCommon mCommon;
	@Autowired private PersonagemDAO personagemDAO;
	@Autowired private LocalDAO localDAO;
	
	public PersonagemDTO getPersonagemById(Long id) {
		Personagem personagem = personagemDAO.getPersonagemById(id);
		if(personagem != null){
			SimpleDateFormat dateFormat = new SimpleDateFormat(ProfetasConstants.DATE_FORMAT_SHORT);
			PersonagemDTO pDTO = new PersonagemDTO();
			pDTO.setId(personagem.getId());
			pDTO.setNome(personagem.getNome());
			pDTO.setApelido(personagem.getApelido());
			pDTO.setIdNascimento(personagem.getLocalNascimento().getId());
			pDTO.setDataNascimento(dateFormat.format(personagem.getDataNascimento()));
			pDTO.setIdMorte(personagem.getLocalMorte().getId());
			pDTO.setDataMorte(dateFormat.format(personagem.getDataMorte()));
			pDTO.setBiografia(personagem.getBiografia());
			pDTO.setOcupacao(personagem.getOcupacao());
			pDTO.setFormacao(personagem.getFormacao());
			return pDTO;
		}
		return null;
	}

	public MessageDTO createPersonagem(PersonagemDTO personagemDTO) {
		Date dataNasc  = UtilValidator.getDateFromString(personagemDTO.getDataNascimento());
		Date dataMorte = UtilValidator.getDateFromString(personagemDTO.getDataMorte());
		
		try{
			Personagem personagem = new Personagem();
			personagem.setNome(personagemDTO.getNome());
			personagem.setApelido(personagemDTO.getApelido());
			personagem.setLocalNascimento(new Local(personagemDTO.getIdNascimento()));
			personagem.setDataNascimento(dataNasc);
			personagem.setLocalMorte(new Local(personagemDTO.getIdMorte()));
			personagem.setDataMorte(dataMorte);
			personagem.setBiografia(personagemDTO.getBiografia());
			personagem.setOcupacao(personagemDTO.getOcupacao());
			personagem.setFormacao(personagemDTO.getFormacao());
			personagemDAO.savePersonagem(personagem);
			if(personagem.getId() != null){
				return new MessageDTO(getText("msg_personagem_saved"), MessageType.SUCCESS);
			} else {
				return new MessageDTO(getText("err_personagem_not_saved"), MessageType.ERROR);
			}
		} catch(Exception e){
			return new MessageDTO(getText("err_personagem_not_saved"), MessageType.ERROR);
		}
	}

	public MessageDTO updatePersonagem(PersonagemDTO personagemDTO) {
		if(personagemDTO.getId() == null){
			return new MessageDTO(getText("err_personagem_not_updated"), MessageType.ERROR);
		}
		Date dataNasc  = UtilValidator.getDateFromString(personagemDTO.getDataNascimento());
		Date dataMorte = UtilValidator.getDateFromString(personagemDTO.getDataMorte());
		
		try{
			Personagem personagem = personagemDAO.getPersonagemById(personagemDTO.getId());
			if(personagem != null){
				personagem.setNome(personagemDTO.getNome());
				personagem.setApelido(personagemDTO.getApelido());
				personagem.setLocalNascimento(new Local(personagemDTO.getIdNascimento()));
				personagem.setDataNascimento(dataNasc);
				personagem.setLocalMorte(new Local(personagemDTO.getIdMorte()));
				personagem.setDataMorte(dataMorte);
				personagem.setBiografia(personagemDTO.getBiografia());
				personagem.setOcupacao(personagemDTO.getOcupacao());
				personagem.setFormacao(personagemDTO.getFormacao());
				personagemDAO.savePersonagem(personagem);
				if(personagem.getId() != null){
					return new MessageDTO(getText("msg_personagem_updated"), MessageType.SUCCESS);
				}
			}
			return new MessageDTO(getText("err_personagem_not_updated"), MessageType.ERROR);
		} catch(Exception e){
			return new MessageDTO(getText("err_personagem_not_updated"), MessageType.ERROR);
		}
	}

	public MessageDTO deletePersonagem(PersonagemDTO personagemDTO) {
		try{
			Personagem personagem = personagemDAO.getPersonagemById(personagemDTO.getId());
			personagemDAO.deletePersonagem(personagem);
			return new MessageDTO(getText("msg_personagem_deleted"), MessageType.SUCCESS);
		}
		catch(Exception e){
			return new MessageDTO(getText("err_personagem_not_deleted"), MessageType.ERROR);
		}
	}

	public WrapperGrid<PersonagemDTO> getPersonagemList(String orderBy,
			OrderType orderType, int page, int numRows) {
		List<Personagem> list = personagemDAO.listPersonagem();//TODO: limit
		int total = list == null ? 0 : list.size();//TODO: count
		List<PersonagemDTO> listDTO = new ArrayList<PersonagemDTO>();
		for(Personagem p : list){
			PersonagemDTO pDTO = new PersonagemDTO();
			pDTO.setNome(p.getNome());
			pDTO.setApelido(p.getApelido());
			listDTO.add(pDTO);
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

	public List<ProfileDTO> getProfiles() {
		return mCommon.profileList();
	}
}