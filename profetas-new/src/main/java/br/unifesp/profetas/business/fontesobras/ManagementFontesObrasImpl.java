package br.unifesp.profetas.business.fontesobras;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;

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
import br.unifesp.profetas.util.ProfetasConstants;
import br.unifesp.profetas.util.UtilValidator;

@Service("mFontesObras")
public class ManagementFontesObrasImpl extends AbstractBusiness implements ManagementFontesObras {
	
	private static Logger logger = Logger.getLogger(ManagementFontesObrasImpl.class);
	
	@Autowired private FontesObrasDAO fontesObrasDAO;
	@Autowired private PalavraChaveDAO palavraChaveDAO;
	
	@Autowired private PersonagemDAO personagemDAO;
	
	@Autowired private SessionFactory sessionFactory;

	public FontesObrasDTO getFontesObrasById(Long id) {
		FontesObras fontesObras = fontesObrasDAO.getFontesObrasById(id);
		if(fontesObras != null){
			SimpleDateFormat dateFormat = new SimpleDateFormat(ProfetasConstants.DATE_FORMAT_SHORT);
			FontesObrasDTO fDTO = new FontesObrasDTO();
			fDTO.setId(fontesObras.getId());
			fDTO.setLocalizacao(fontesObras.getLocalizacao());
			fDTO.setAutor(fontesObras.getAutor());
			fDTO.setTitulo(fontesObras.getTitulo());
			fDTO.setComentarios(fontesObras.getComentarios());
			fDTO.setReferenciaCompleta(fontesObras.getReferenciaCompleta());
			fDTO.setReferenciasCirculacaoObra(fontesObras.getReferenciasCirculacaoObra());
			fDTO.setUrl(fontesObras.getUrl());
			fDTO.setCopiasManuscritas(fontesObras.getCopiasManuscritas());
			fDTO.setTraducoes(fontesObras.getTraducoes());
			fDTO.setEditor(fontesObras.getEditor());
			fDTO.setDataImpressao(fontesObras.getDataImpressao() != null ? dateFormat.format(fontesObras.getDataImpressao()) : "");
				Local localImpressao = fontesObras.getLocalImpressao(); 
			fDTO.setIdLocalImpressao(localImpressao != null ? localImpressao.getId() : null);
			fDTO.setProdutor(fontesObras.getProdutor());
			fDTO.setDataProducao(fontesObras.getDataProducao() != null ? dateFormat.format(fontesObras.getDataProducao()) : "");
				Local localProducao = fontesObras.getLocalProducao(); 
			fDTO.setIdLocalProducao(localProducao != null ? localProducao.getId() : null);
				Classificacao classificacao = fontesObras.getClassificacao();
			fDTO.setIdClassificacao(classificacao != null ? classificacao.getId() : null);
				GrupoMovimento gMovimento = fontesObras.getGrupoMovimento();
			fDTO.setIdGruMovimento(gMovimento != null ? gMovimento.getId() : null);
			//leitores
				Set<Personagem> leitoresSet = fontesObras.getLeitores();
			if(!leitoresSet.isEmpty()){
				List<Long> leitores = new ArrayList<Long>(leitoresSet.size());
				for(Personagem l : leitoresSet){
					leitores.add(l.getId());
				}
				if(!leitores.isEmpty()){
					fDTO.setStrLeitores(leitores.toString());
				}
			}
			//autoresCitados
				Set<Personagem> autCitadosSet = fontesObras.getAutoresCitados();
			if(!autCitadosSet.isEmpty()){
				List<Long> autoresCitados = new ArrayList<Long>(autCitadosSet.size());
				for(Personagem a : autCitadosSet){
					autoresCitados.add(a.getId());
				}
				if(!autoresCitados.isEmpty()){
					fDTO.setStrAutCitados(autoresCitados.toString());
				}
			}
			//obrasCitadas
				Set<FontesObras> obrasCitadasSet = fontesObras.getObrasCitadas();
			if(!obrasCitadasSet.isEmpty()){
				List<Long> obrasCitadas = new ArrayList<Long>(obrasCitadasSet.size());
				for(FontesObras c : obrasCitadasSet){
					obrasCitadas.add(c.getId());
				}
				if(!obrasCitadas.isEmpty()){
					fDTO.setStrObrCitadas(obrasCitadas.toString());
				}
			}
			//Palavras chave
				Set<PalavraChave> palavrasChaveSet = fontesObras.getPalavrasChave();
			if(!palavrasChaveSet.isEmpty()){
				List<String> palavrasChave = new ArrayList<String>(palavrasChaveSet.size());
				for(PalavraChave p : palavrasChaveSet){
					palavrasChave.add(p.getPalavraChave());
				}
				if(!palavrasChave.isEmpty()){
					fDTO.setStrPalChave(palavrasChave.toString());
				}
			}
			
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
		fontesObras.setReferenciaCompleta(fontesObrasDTO.getReferenciaCompleta());
		fontesObras.setReferenciasCirculacaoObra(fontesObrasDTO.getReferenciasCirculacaoObra());
		fontesObras.setUrl(fontesObrasDTO.getUrl());
		fontesObras.setCopiasManuscritas(fontesObrasDTO.getCopiasManuscritas());
		fontesObras.setTraducoes(fontesObrasDTO.getTraducoes());
		fontesObras.setEditor(fontesObrasDTO.getEditor());
			Date dataImpressao = UtilValidator.getDateFromString(fontesObrasDTO.getDataImpressao());
		fontesObras.setDataImpressao(dataImpressao);
		if(fontesObrasDTO.getIdLocalImpressao() != null && fontesObrasDTO.getIdLocalImpressao() != -1){
			fontesObras.setLocalImpressao(new Local(fontesObrasDTO.getIdLocalImpressao()));
		}
		fontesObras.setProdutor(fontesObrasDTO.getProdutor());
			Date dataProducao = UtilValidator.getDateFromString(fontesObrasDTO.getDataProducao());
		fontesObras.setDataProducao(dataProducao);
		if(fontesObrasDTO.getIdLocalProducao() != null && fontesObrasDTO.getIdLocalProducao() != -1){
			fontesObras.setLocalProducao(new Local(fontesObrasDTO.getIdLocalProducao()));
		}
		if(fontesObrasDTO.getIdClassificacao() != null && fontesObrasDTO.getIdClassificacao() != -1){
			fontesObras.setClassificacao(new Classificacao(fontesObrasDTO.getIdClassificacao()));
		}
		if(fontesObrasDTO.getIdGruMovimento() != null && fontesObrasDTO.getIdGruMovimento() != -1){
			fontesObras.setGrupoMovimento(new GrupoMovimento(fontesObrasDTO.getIdGruMovimento()));
		}
		
		//
		//Leitores
		if(fontesObrasDTO.getIdLeitores() != null){
			int leitoresLength = fontesObrasDTO.getIdLeitores().length;
			if(leitoresLength > 0){
				List<Personagem> leitores = new ArrayList<Personagem>(leitoresLength);
				for(int i = 0; i < leitoresLength; i++){
					Personagem l = personagemDAO.getPersonagemById(fontesObrasDTO.getIdLeitores()[i]);
					if(l != null){
						leitores.add(l);
					} else {
						logger.error("Leitor: " + fontesObrasDTO.getIdLeitores()[i] + " does not exist");
					}
				}
				fontesObras.setLeitores(new HashSet<Personagem>(leitores));
			}
		}
		//Autores Citados
		if(fontesObrasDTO.getIdAutCitados() != null){
			int autoresLength = fontesObrasDTO.getIdAutCitados().length;
			if(autoresLength > 0){
				List<Personagem> autores = new ArrayList<Personagem>(autoresLength);
				for(int i = 0; i < autoresLength; i++){
					Personagem a = personagemDAO.getPersonagemById(fontesObrasDTO.getIdAutCitados()[i]);
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
					FontesObras o = fontesObrasDAO.getFontesObrasById(fontesObrasDTO.getIdObrCitadas()[i]);
					if(o != null){
						obrasCitadas.add(o);
					} else {
						logger.error("ObraCitado: " + fontesObrasDTO.getIdObrCitadas()[i] + " does not exist");
					}
				}
				fontesObras.setObrasCitadas(new HashSet<FontesObras>(obrasCitadas));
			}
		}
		
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
					//TODO:
					//Palavras Chave ...
					
					if(fontesObras.getPalavrasChave() != null){
						Set<PalavraChave> palavrasNovas = new HashSet<PalavraChave>(), 
								palavrasAntigas = fontesObras.getPalavrasChave();
						int palavrasLength = fontesObrasDTO.getPalavrasChave().length;
						if(palavrasLength > 0){
							for(int i = 0; i < palavrasLength; i++){
								PalavraChave p = new PalavraChave();
								p.setPalavraChave(fontesObrasDTO.getPalavrasChave()[i]);
								p.setFontesObras(fontesObras);
								savePalavraChave(p);
								if(p.getId() != null){
									palavrasNovas.add(p);
								}
							}
							for( PalavraChave lixo : Sets.difference(palavrasAntigas, palavrasNovas)) {
								palavraChaveDAO.deletePalavraChave(lixo);
							}
							fontesObras.setPalavrasChave(palavrasNovas);
						}
					}
					
					return new MessageDTO(getText("msg_fontes_updated"), MessageType.SUCCESS);
				}
			}
			return new MessageDTO(getText("err_fontes_not_updated"), MessageType.ERROR);
		} catch(Exception e){
			return new MessageDTO(getText("err_fontes_not_updated"), MessageType.ERROR);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void savePalavraChave(PalavraChave p) {
		final String rawQuery = "from PalavraChave as pc where pc.palavraChave = :palavra and pc.fontesObras.id = :fonteId";
		Query query = sessionFactory.getCurrentSession().createQuery(rawQuery);
		query.setString("palavra", p.getPalavraChave());
		query.setLong("fonteId", p.getFontesObras().getId());
		List<PalavraChave> result = query.list();
		if(result.isEmpty()) {
			palavraChaveDAO.savePalavraChave(p);
		} else {
			p.setId(result.get(0).getId());
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
			fDTO.setAutor(f.getAutor());
			fDTO.setTitulo(f.getTitulo());
			listDTO.add(fDTO);
		}
		return getWrapper(listDTO, orderBy, orderType, page, numRows, total, null);
	}
}