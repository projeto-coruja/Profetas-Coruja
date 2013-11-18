package br.unifesp.profetas.business.personagem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import br.unifesp.profetas.persistence.domain.CorrespondenciaDAO;
import br.unifesp.profetas.persistence.domain.EncontroDAO;
import br.unifesp.profetas.persistence.domain.FontesObrasDAO;
import br.unifesp.profetas.persistence.domain.LocalDAO;
import br.unifesp.profetas.persistence.domain.PersonagemDAO;
import br.unifesp.profetas.persistence.domain.ReligiaoCrencasDAO;
import br.unifesp.profetas.persistence.model.Correspondencia;
import br.unifesp.profetas.persistence.model.Encontro;
import br.unifesp.profetas.persistence.model.FontesObras;
import br.unifesp.profetas.persistence.model.Local;
import br.unifesp.profetas.persistence.model.Personagem;
import br.unifesp.profetas.persistence.model.ReligiaoCrencas;
import br.unifesp.profetas.util.ProfetasConstants;
import br.unifesp.profetas.util.UtilValidator;

@Service("mPersonagem")
public class ManagementPersonagemImpl extends AbstractBusiness implements ManagementPersonagem {

	private static Logger logger = Logger.getLogger(ManagementPersonagemImpl.class);
	
	@Autowired private PersonagemDAO personagemDAO;
	
	@Autowired private ReligiaoCrencasDAO religiaoCrencasDAO;
	@Autowired private EncontroDAO encontroDAO;
	@Autowired private FontesObrasDAO fontesObrasDAO;
	@Autowired private LocalDAO localDAO;
	@Autowired private CorrespondenciaDAO correspondenciaDAO;
	
	public PersonagemDTO getPersonagemById(Long id) {
		Personagem personagem = personagemDAO.getPersonagemById(id);
		if(personagem != null){
			SimpleDateFormat dateFormat = new SimpleDateFormat(ProfetasConstants.DATE_FORMAT_SHORT);
			PersonagemDTO pDTO = new PersonagemDTO();
			pDTO.setId(personagem.getId());
			pDTO.setSobrenome(personagem.getSobrenome());
			pDTO.setNome(personagem.getNome());
			pDTO.setApelido(personagem.getApelido());
				Local localNasc = personagem.getLocalNascimento();
			pDTO.setIdNascimento(localNasc != null ? localNasc.getId() : null);
			pDTO.setDataNascimento(personagem.getDataNascimento() != null ? dateFormat.format(personagem.getDataNascimento()) : "");
				Local localMorte = personagem.getLocalMorte();
			pDTO.setIdMorte(localMorte != null ? localMorte.getId() : null);
			pDTO.setDataMorte(personagem.getDataMorte() != null ? dateFormat.format(personagem.getDataMorte()) : "");
			pDTO.setBiografia(personagem.getBiografia());
			pDTO.setOcupacao(personagem.getOcupacao());
			pDTO.setFormacao(personagem.getFormacao());
			pDTO.setRefBibliografica(personagem.getReferenciaBibliografica());
			//religioes
				Set<ReligiaoCrencas> crencasSet = personagem.getReligioes();
			if(!crencasSet.isEmpty()){
				List<Long> crencas = new ArrayList<Long>(crencasSet.size());
				for(ReligiaoCrencas c : crencasSet){
					crencas.add(c.getId());
				}
				if(!crencas.isEmpty()){
					pDTO.setStrReligioes(crencas.toString());
				}
			}
			//encontros
				Set<Encontro> encontrosSet = personagem.getEncontros();
			if(!encontrosSet.isEmpty()){
				List<Long> encontros = new ArrayList<Long>(encontrosSet.size());
				for(Encontro e : encontrosSet){
					encontros.add(e.getId());
				}
				if(!encontros.isEmpty()){
					pDTO.setStrEncontros(encontros.toString());
				}
			}
			//obras
				Set<FontesObras> obrasSet = personagem.getObras();
			if(!obrasSet.isEmpty()){
				List<Long> obras = new ArrayList<Long>(obrasSet.size());
				for(FontesObras f : obrasSet){
					obras.add(f.getId());
				}
				if(!obras.isEmpty()){
					pDTO.setStrObras(obras.toString());
				}
			}
			//locaisPersonagens
				Set<Local> locaisPersSet = personagem.getLocaisPersonagens();
			if(!locaisPersSet.isEmpty()){
				List<Long> locaisPersonagens = new ArrayList<Long>(locaisPersSet.size());
				for(Local l : locaisPersSet){
					locaisPersonagens.add(l.getId());
				}
				if(!locaisPersonagens.isEmpty()){
					pDTO.setStrLocaisPers(locaisPersonagens.toString());
				}
			}
			//correspondencias
				Set<Correspondencia> correspondenciasSet = personagem.getCorrespondencias();
			if(!correspondenciasSet.isEmpty()){
				List<Long> correspondencias = new ArrayList<Long>(correspondenciasSet.size());
				for(Correspondencia c : correspondenciasSet){
					correspondencias.add(c.getId());
				}
				if(!correspondencias.isEmpty()){
					pDTO.setStrCorrespondencias(correspondencias.toString());
				}
			}
			
			return pDTO;
		}
		return null;
	}
	
	private MessageDTO isNotValid(PersonagemDTO personagemDTO, boolean isNew){
		if(!UtilValidator.validateNotEmptyField(personagemDTO.getNome())){
			return new MessageDTO(getText("err_personagem_nome_required"), MessageType.ERROR);
		}
		return null;
	}
	
	private Personagem getPersonagem(Personagem personagem, PersonagemDTO personagemDTO){
		Date dataNasc  = UtilValidator.getDateFromString(personagemDTO.getDataNascimento());
		Date dataMorte = UtilValidator.getDateFromString(personagemDTO.getDataMorte());
		
		personagem.setNome(personagemDTO.getNome());
		personagem.setSobrenome(personagemDTO.getSobrenome());
		personagem.setApelido(personagemDTO.getApelido());
		if(personagemDTO.getIdNascimento() != null && personagemDTO.getIdNascimento() != -1){
			personagem.setLocalNascimento(new Local(personagemDTO.getIdNascimento()));
		}
		personagem.setDataNascimento(dataNasc);
		if(personagemDTO.getIdMorte() != null && personagemDTO.getIdMorte() != -1){
			personagem.setLocalMorte(new Local(personagemDTO.getIdMorte()));
		}
		personagem.setDataMorte(dataMorte);
		personagem.setBiografia(personagemDTO.getBiografia());
		personagem.setOcupacao(personagemDTO.getOcupacao());
		personagem.setFormacao(personagemDTO.getFormacao());
		personagem.setReferenciaBibliografica(personagemDTO.getRefBibliografica());
		
		//
		//Religioes-crencas
		if(personagemDTO.getIdReligioes() != null){
			int crencasLength = personagemDTO.getIdReligioes().length;
			if(crencasLength > 0){
				List<ReligiaoCrencas> crencas = new ArrayList<ReligiaoCrencas>(crencasLength);
				for(int i = 0; i < crencasLength; i++){
					ReligiaoCrencas r = religiaoCrencasDAO.getReligiaoCrencasById(personagemDTO.getIdReligioes()[i]);
					if(r != null){
						crencas.add(r);
					} else {
						logger.error("Religiao: " + personagemDTO.getIdReligioes()[i] + " does not exist");
					}
				}
				personagem.setReligioes(new HashSet<ReligiaoCrencas>(crencas));
			}
		}
		//Encontros
		if(personagemDTO.getIdEncontros() != null){
			int encontrosLength = personagemDTO.getIdEncontros().length;
			if(encontrosLength > 0){
				List<Encontro> encontros = new ArrayList<Encontro>(encontrosLength);
				for(int i = 0; i < encontrosLength; i++){
					Encontro e = encontroDAO.getEncontroById(personagemDTO.getIdEncontros()[i]);
					if(e != null){
						encontros.add(e);
					} else {
						logger.error("Encontro: " + personagemDTO.getIdEncontros()[i] + " does not exist");
					}
				}
				personagem.setEncontros(new HashSet<Encontro>(encontros));
			}
		}
		//Obras
		if(personagemDTO.getIdObras() != null){
			int obrasLength = personagemDTO.getIdObras().length;
			if(obrasLength > 0){
				List<FontesObras> fontes = new ArrayList<FontesObras>(obrasLength);
				for(int i = 0; i < obrasLength; i++){
					FontesObras f = fontesObrasDAO.getFontesObrasById(personagemDTO.getIdObras()[i]);
					if(f != null){
						fontes.add(f);
					} else{
						logger.error("Fonte/Obra: " + personagemDTO.getIdObras()[i] + " does not exist");
					}
				}
				personagem.setObras(new HashSet<FontesObras>(fontes));
			}
		}
		//Local
		if(personagemDTO.getIdLocaisPers() != null){
			int locaisLength = personagemDTO.getIdLocaisPers().length;
			if(locaisLength > 0){
				List<Local> locais = new ArrayList<Local>(locaisLength);
				for(int i = 0; i < locaisLength; i++){
					Local l = localDAO.getLocalById(personagemDTO.getIdLocaisPers()[i]);
					if(l != null){
						locais.add(l);
					} else{
						logger.error("Local: " + personagemDTO.getIdLocaisPers()[i] + " does not exist");
					}
				}
				personagem.setLocaisPersonagens(new HashSet<Local>(locais));
			}
		}
		//Correspondencia
		if(personagemDTO.getIdCorrespondencias() != null){
			int corLength = personagemDTO.getIdCorrespondencias().length;
			if(corLength > 0){
				List<Correspondencia> correspondencias = new ArrayList<Correspondencia>(corLength);
				for(int i = 0; i < corLength; i++){
					Correspondencia c = correspondenciaDAO.getCorrespondenciaById(personagemDTO.getIdCorrespondencias()[i]);
					if(c != null){
						correspondencias.add(c);
					} else{
						logger.error("Correspondencia: " + personagemDTO.getIdCorrespondencias()[i] + " does not exist");
					}
				}
				personagem.setCorrespondencias(new HashSet<Correspondencia>(correspondencias));
			}
		}
		//
		
		personagem.setActive(true);
		return personagem;
	}

	public MessageDTO createPersonagem(PersonagemDTO personagemDTO) {
		MessageDTO isNotValid = isNotValid(personagemDTO, true);
		if(isNotValid != null)
			return isNotValid;
		
		try{
			Personagem personagem = new Personagem();
			personagem = getPersonagem(personagem, personagemDTO);
			personagemDAO.savePersonagem(personagem);
			if(personagem.getId() != null){
				return new MessageDTO(getText("msg_personagem_created"), MessageType.SUCCESS);
			}
			return new MessageDTO(getText("err_personagem_not_created"), MessageType.ERROR);
		} catch(Exception e){
			return new MessageDTO(getText("err_personagem_not_created"), MessageType.ERROR);
		}
	}

	public MessageDTO updatePersonagem(PersonagemDTO personagemDTO) {
		if(personagemDTO.getId() == null){
			return new MessageDTO(getText("err_personagem_not_updated"), MessageType.ERROR);
		}
		MessageDTO isNotValid = isNotValid(personagemDTO, false);
		if(isNotValid != null)
			return isNotValid;
		
		try{
			Personagem personagem = personagemDAO.getPersonagemById(personagemDTO.getId());
			if(personagem != null){
				personagem = getPersonagem(personagem, personagemDTO);
				personagemDAO.updatePersonagem(personagem);
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
		if(personagemDTO.getId() == null)
			return new MessageDTO(getText("err_personagem_not_deleted"), MessageType.ERROR);
		
		try{
			Personagem personagem = personagemDAO.getPersonagemById(personagemDTO.getId());
			if(personagem != null){
				personagem.setActive(false);
				personagemDAO.updatePersonagem(personagem);
				return new MessageDTO(getText("msg_personagem_deleted"), MessageType.SUCCESS);
			}
			return new MessageDTO(getText("err_personagem_not_deleted"), MessageType.SUCCESS);
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
			pDTO.setId(p.getId());
			pDTO.setNome(p.getNome());
			pDTO.setSobrenome(p.getSobrenome());
			listDTO.add(pDTO);
		}
		return getWrapper(listDTO, orderBy, orderType, page, numRows, total, null);
	}
}