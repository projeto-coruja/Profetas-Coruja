package br.unifesp.profetas.business.fontesobras;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unifesp.profetas.business.AbstractBusiness;
import br.unifesp.profetas.business.common.MessageDTO;
import br.unifesp.profetas.business.common.MessageType;
import br.unifesp.profetas.business.common.OrderType;
import br.unifesp.profetas.business.common.WrapperGrid;
import br.unifesp.profetas.persistence.domain.FontesObrasDAO;
import br.unifesp.profetas.persistence.domain.PalavraChaveDAO;
import br.unifesp.profetas.persistence.domain.PersonagemDAO;
import br.unifesp.profetas.persistence.model.Classificacao;
import br.unifesp.profetas.persistence.model.FontesObras;
import br.unifesp.profetas.persistence.model.GrupoMovimento;
import br.unifesp.profetas.persistence.model.Local;
import br.unifesp.profetas.persistence.model.PalavraChave;
import br.unifesp.profetas.persistence.model.Personagem;
import br.unifesp.profetas.util.UtilValidator;

@Service("mFontesObras")
public class ManagementFontesObrasImpl extends AbstractBusiness implements ManagementFontesObras {
	
	private static Logger logger = Logger.getLogger(ManagementFontesObrasImpl.class);
	
	@Autowired private FontesObrasDAO fontesObrasDAO;
	@Autowired private PalavraChaveDAO palavraChaveDAO;
	
	@Autowired private PersonagemDAO personagemDAO;

	public FontesObrasDTO getFontesObrasById(Long id) {
		FontesObras fontesObras = fontesObrasDAO.getFontesObrasById(id);
		if(fontesObras != null){
			//SimpleDateFormat dateFormat = new SimpleDateFormat(ProfetasConstants.DATE_FORMAT_SHORT);
			FontesObrasDTO fDTO = new FontesObrasDTO();
			fDTO.setId(fontesObras.getId());
			fDTO.setLocalizacao(fontesObras.getLocalizacao());
			fDTO.setAutor(fontesObras.getAutor());
			fDTO.setTitulo(fontesObras.getTitulo());
			fDTO.setComentarios(fontesObras.getComentarios());
			fDTO.setReferenciasCirculacaoObra(fontesObras.getReferenciasCirculacaoObra());
			fDTO.setUrl(fontesObras.getUrl());
			fDTO.setCopiasManuscritas(fontesObras.getCopiasManuscritas());
			fDTO.setTraducoes(fontesObras.getTraducoes());
			fDTO.setEditor(fontesObras.getEditor());
			fDTO.setDataImpressao(String.valueOf(fontesObras.getDataImpressao()));
			fDTO.setIdLocalImpressao(fontesObras.getLocalImpressao().getId());
			fDTO.setIdClassificacao(fontesObras.getClassificacao().getId());
			return fDTO;
		}
		return null;
	}
	
	private MessageDTO isNotValid(FontesObrasDTO fontesObrasDTO, boolean isNew){
		if(!UtilValidator.validateNotEmptyField(fontesObrasDTO.getTitulo())){
			return new MessageDTO(getText("err_fontes_titulo_required"), MessageType.ERROR);
		}
		return null;
	}
	
	private FontesObras getFontesObras(FontesObras fontesObras, FontesObrasDTO fontesObrasDTO){
		fontesObras.setLocalizacao(fontesObrasDTO.getLocalizacao());
		fontesObras.setAutor(fontesObrasDTO.getAutor());
		fontesObras.setTitulo(fontesObrasDTO.getTitulo());
		fontesObras.setComentarios(fontesObrasDTO.getComentarios());
		fontesObras.setReferenciasCirculacaoObra(fontesObrasDTO.getReferenciasCirculacaoObra());
		fontesObras.setUrl(fontesObrasDTO.getUrl());
		fontesObras.setCopiasManuscritas(fontesObrasDTO.getCopiasManuscritas());
		fontesObras.setTraducoes(fontesObrasDTO.getTraducoes());
		fontesObras.setEditor(fontesObrasDTO.getEditor());
		fontesObras.setDataImpressao(null);//TODO:
		fontesObras.setLocalImpressao(new Local(fontesObrasDTO.getIdLocalImpressao()));
		fontesObras.setClassificacao(new Classificacao(fontesObrasDTO.getIdClassificacao()));
		fontesObras.setGrupoMovimento(new GrupoMovimento(fontesObrasDTO.getIdGruMovimento()));
		fontesObras.setActive(true);
		return fontesObras;
	}

	public MessageDTO createFontesObras(FontesObrasDTO fontesObrasDTO) {
		MessageDTO isNotValid = isNotValid(fontesObrasDTO, true);
		if(isNotValid != null)
			return isNotValid;
		
		try{
			FontesObras fontesObras = new FontesObras();
			fontesObras = getFontesObras(fontesObras, fontesObrasDTO);
			//
			//Leitores
			if(fontesObrasDTO.getIdLeitores() != null){
				int leitoresLength = fontesObrasDTO.getIdLeitores().length;
				if(leitoresLength > 0){
					List<Personagem> leitores = new ArrayList<Personagem>(leitoresLength);
					for(int i = 0; i < leitoresLength; i++){
						Personagem l = personagemDAO.getPersonagemById(Long.parseLong(fontesObrasDTO.getIdLeitores()[i]));
						if(l != null){
							leitores.add(l);
						} else {
							logger.error("Leitor: " + fontesObrasDTO.getIdLeitores()[i] + " does not exist");
						}
					}
					fontesObras.setLeitores(new HashSet<Personagem>(leitores));
				}
			}
			//Personagens
			if(fontesObrasDTO.getIdPersonagens() != null){
				int perLength = fontesObrasDTO.getIdPersonagens().length;
				if(perLength > 0){
					List<Personagem> personagens = new ArrayList<Personagem>(perLength);
					for(int i = 0; i < perLength; i++){
						Personagem p = personagemDAO.getPersonagemById(Long.parseLong(fontesObrasDTO.getIdPersonagens()[i]));
						if(p != null){
							personagens.add(p);
						} else {
							logger.error("Personagem: " + fontesObrasDTO.getIdPersonagens()[i] + " does not exist");
						}
					}
					fontesObras.setPersonagens(new HashSet<Personagem>(personagens));
				}
			}
			//Autores Citados
			if(fontesObrasDTO.getIdAutCitados() != null){
				int autoresLength = fontesObrasDTO.getIdAutCitados().length;
				if(autoresLength > 0){
					List<Personagem> autores = new ArrayList<Personagem>(autoresLength);
					for(int i = 0; i < autoresLength; i++){
						Personagem a = personagemDAO.getPersonagemById(Long.parseLong(fontesObrasDTO.getIdAutCitados()[i]));
						if(a != null){
							autores.add(a);
						} else {
							logger.error("AutorCitado: " + fontesObrasDTO.getIdAutCitados()[i] + " does not exist");
						}
					}
					fontesObras.setAutoresCitados(new HashSet<Personagem>(autores));
				}
			}
			//Obras Citadas
			if(fontesObrasDTO.getIdObrCitadas() != null){
				int obrasLength = fontesObrasDTO.getIdObrCitadas().length;
				if(obrasLength > 0){
					List<FontesObras> obrasCitadas = new ArrayList<FontesObras>(obrasLength);
					for(int i = 0; i < obrasLength; i++){
						FontesObras o = fontesObrasDAO.getFontesObrasById(Long.parseLong(fontesObrasDTO.getIdObrCitadas()[i]));
						if(o != null){
							obrasCitadas.add(o);
						} else {
							logger.error("ObraCitado: " + fontesObrasDTO.getIdObrCitadas()[i] + " does not exist");
						}
					}
					fontesObras.setObrasCitadas(new HashSet<FontesObras>(obrasCitadas));
				}
			}
			
			fontesObrasDAO.saveFontesObras(fontesObras);
			if(fontesObras.getId() != null){
				
				//Palavras Chave
				if(fontesObras.getPalavrasChave() != null){
					int palavrasLength = fontesObrasDTO.getPalavrasChave().length;
					if(palavrasLength > 0){
						List<PalavraChave> palavrasChave = new ArrayList<PalavraChave>(palavrasLength);
						for(int i = 0; i < palavrasLength; i++){
							PalavraChave p = new PalavraChave();
							p.setPalavraChave(fontesObrasDTO.getPalavrasChave()[i]);
							p.setFontesObras(fontesObras);
							palavraChaveDAO.savePalavraChave(p);
							if(p.getId() != null){
								palavrasChave.add(p);
							}
						}						
						fontesObras.setPalavrasChave(new HashSet<PalavraChave>(palavrasChave));
					}
				}
				//
				return new MessageDTO(getText("msg_fontes_created"), MessageType.SUCCESS);
			}
			return new MessageDTO(getText("err_fontes_not_created"), MessageType.ERROR);
		} catch(Exception e){
			return new MessageDTO(getText("err_fontes_not_created"), MessageType.ERROR);
		}
	}

	public MessageDTO updateFontesObras(FontesObrasDTO fontesObrasDTO) {
		if(fontesObrasDTO.getId() == null)
			return new MessageDTO(getText("err_fontes_not_updated"), MessageType.ERROR);
		MessageDTO isNotValid = isNotValid(fontesObrasDTO, false);
		if(isNotValid != null)
			return isNotValid;
		
		try{
			FontesObras fontesObras = fontesObrasDAO.getFontesObrasById(fontesObrasDTO.getId());
			if(fontesObras != null){
				fontesObras = getFontesObras(fontesObras, fontesObrasDTO);
				fontesObrasDAO.updateFontesObras(fontesObras);
				if(fontesObras.getId() != null){
					return new MessageDTO(getText("msg_fontes_updated"), MessageType.SUCCESS);
				}
			}
			return new MessageDTO(getText("err_fontes_not_updated"), MessageType.ERROR);
		} catch(Exception e){
			return new MessageDTO(getText("err_fontes_not_updated"), MessageType.ERROR);
		}
	}

	public MessageDTO deleteFontesObras(FontesObrasDTO fontesObrasDTO) {
		if(fontesObrasDTO.getId() == null)
			return new MessageDTO(getText("err_fontes_not_deleted"), MessageType.ERROR);
		
		try{
			FontesObras fontesObras = fontesObrasDAO.getFontesObrasById(fontesObrasDTO.getId());
			if(fontesObras != null){
				fontesObras.setActive(false);
				fontesObrasDAO.updateFontesObras(fontesObras);
				return new MessageDTO(getText("msg_fontes_deleted"), MessageType.SUCCESS);
			}
			return new MessageDTO(getText("err_fontes_not_deleted"), MessageType.ERROR);
		}
		catch(Exception e){
			return new MessageDTO(getText("err_fontes_not_deleted"), MessageType.ERROR);
		}
	}

	public WrapperGrid<FontesObrasDTO> getFontesObrasList(String orderBy,
			OrderType orderType, int page, int numRows) {
		List<FontesObras> list = fontesObrasDAO.listFontesObras();//TODO: limit
		int total = list == null ? 0 : list.size();//TODO: count
		List<FontesObrasDTO> listDTO = new ArrayList<FontesObrasDTO>();
		for(FontesObras f : list){
			FontesObrasDTO fDTO = new FontesObrasDTO();
			fDTO.setId(f.getId());
			fDTO.setTitulo(f.getTitulo());
			listDTO.add(fDTO);
		}
		return getWrapper(listDTO, orderBy, orderType, page, numRows, total, null);
	}
}