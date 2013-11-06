package br.unifesp.profetas.business.fontesobras;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unifesp.profetas.business.AbstractBusiness;
import br.unifesp.profetas.business.common.MessageDTO;
import br.unifesp.profetas.business.common.MessageType;
import br.unifesp.profetas.business.common.OrderType;
import br.unifesp.profetas.business.common.WrapperGrid;
import br.unifesp.profetas.business.local.LocalDTO;
import br.unifesp.profetas.persistence.domain.ClassificacaoDAO;
import br.unifesp.profetas.persistence.domain.FontesObrasDAO;
import br.unifesp.profetas.persistence.domain.LocalDAO;
import br.unifesp.profetas.persistence.model.Classificacao;
import br.unifesp.profetas.persistence.model.FontesObras;
import br.unifesp.profetas.persistence.model.Local;

@Service("mFontesObras")
public class ManagementFontesObrasImpl extends AbstractBusiness implements ManagementFontesObras {
	
	@Autowired private FontesObrasDAO fontesObrasDAO;
	@Autowired private LocalDAO localDAO;
	@Autowired private ClassificacaoDAO classificacaoDAO;

	public FontesObrasDTO getFontesObrasById(Long id) {
		FontesObras fontesObras = fontesObrasDAO.getFontesObrasById(id);
		if(fontesObras != null){
			//SimpleDateFormat dateFormat = new SimpleDateFormat(ProfetasConstants.DATE_FORMAT_SHORT);
			FontesObrasDTO fDTO = new FontesObrasDTO();
			fDTO.setId(fontesObras.getId());
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

	public MessageDTO createFontesObras(FontesObrasDTO fontesObrasDTO) {
		try{
			FontesObras fontesObras = new FontesObras();
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
			
			fontesObrasDAO.saveFontesObras(fontesObras);
			if(fontesObras.getId() != null){
				return new MessageDTO(getText("msg_fontes_saved"), MessageType.SUCCESS);
			} else {
				return new MessageDTO(getText("err_fontes_not_saved"), MessageType.ERROR);
			}
		} catch(Exception e){
			return new MessageDTO(getText("err_fontes_not_saved"), MessageType.ERROR);
		}
	}

	public MessageDTO updateFontesObras(FontesObrasDTO fontesObrasDTO) {
		try{
			FontesObras fontesObras = fontesObrasDAO.getFontesObrasById(fontesObrasDTO.getId());
			if(fontesObras != null){
				fontesObras.setTitulo(fontesObrasDTO.getTitulo());
				fontesObras.setComentarios(fontesObrasDTO.getComentarios());
				fontesObras.setReferenciasCirculacaoObra(fontesObrasDTO.getReferenciasCirculacaoObra());
				fontesObras.setUrl(fontesObrasDTO.getUrl());
				fontesObras.setCopiasManuscritas(fontesObras.getCopiasManuscritas());
				fontesObras.setTraducoes(fontesObrasDTO.getTraducoes());
				fontesObras.setEditor(fontesObrasDTO.getEditor());
				fontesObras.setDataImpressao(null);//TODO:
				fontesObras.setLocalImpressao(new Local(fontesObrasDTO.getIdLocalImpressao()));
				fontesObras.setClassificacao(new Classificacao(fontesObrasDTO.getIdClassificacao()));
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
		try{
			FontesObras fontesObras = fontesObrasDAO.getFontesObrasById(fontesObrasDTO.getId());
			if(fontesObras != null){
				fontesObras.setId(fontesObrasDTO.getId());
				fontesObrasDAO.deleteFontesObras(fontesObras);
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

	public List<Classificacao> getClassificacoes() {
		return classificacaoDAO.listClassificao();
	}
}