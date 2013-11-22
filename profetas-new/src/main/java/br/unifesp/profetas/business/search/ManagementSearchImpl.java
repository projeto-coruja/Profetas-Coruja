package br.unifesp.profetas.business.search;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unifesp.profetas.business.AbstractBusiness;
import br.unifesp.profetas.business.common.OrderType;
import br.unifesp.profetas.business.common.WrapperGrid;
import br.unifesp.profetas.business.fontesobras.FontesObrasDTO;
import br.unifesp.profetas.business.personagem.PersonagemDTO;
import br.unifesp.profetas.persistence.domain.FonteObraViewDAO;
import br.unifesp.profetas.persistence.domain.FontesObrasDAO;
import br.unifesp.profetas.persistence.domain.PersonagemDAO;
import br.unifesp.profetas.persistence.domain.PersonagemViewDAO;
import br.unifesp.profetas.persistence.model.FonteObraView;
import br.unifesp.profetas.persistence.model.FontesObras;
import br.unifesp.profetas.persistence.model.Personagem;
import br.unifesp.profetas.persistence.model.PersonagemView;

@Service("managementSearch")
public class ManagementSearchImpl extends AbstractBusiness implements ManagementSearch {
	
	@Autowired private PersonagemViewDAO personagemViewDAO;
	@Autowired private FonteObraViewDAO fonteObraViewDAO;
	@Autowired private PersonagemDAO personagemDAO;
	@Autowired private FontesObrasDAO fontesObrasDAO;

	public WrapperGrid<PersonagemDTO> searchByPersonagens(OrderType orderType, int page, int numRows, String words) {
		List<PersonagemView> list = personagemViewDAO.search(page, numRows, words);
		int total = list == null ? 0 : list.size();//TODO: count
		List<PersonagemDTO> listDTO = new ArrayList<PersonagemDTO>();
		for(PersonagemView p : list){
			Personagem vo = personagemDAO.getPersonagemById(p.getId());
			
			PersonagemDTO pDTO = new PersonagemDTO();
			pDTO.setId(p.getId());
			pDTO.setNome(vo.getNome());
			pDTO.setSobrenome(vo.getSobrenome());
			listDTO.add(pDTO);
		}
		return getWrapper(listDTO, null, orderType, page, numRows, total, null);
	}

	public WrapperGrid<FontesObrasDTO> searchByFontesObras(OrderType orderType, int page, int numRows, String words) {
		List<FonteObraView> list = fonteObraViewDAO.search(page, numRows, words);
		int total = list == null ? 0 : list.size();//TODO: count
		List<FontesObrasDTO> listDTO = new ArrayList<FontesObrasDTO>();
		for(FonteObraView f : list){
			FontesObras vo = fontesObrasDAO.getFontesObrasById(f.getId());
			
			FontesObrasDTO fDTO = new FontesObrasDTO();
			fDTO.setId(f.getId());
			fDTO.setTitulo(vo.getTitulo());
			fDTO.setAutor(vo.getAutor());
			listDTO.add(fDTO);
		}
		return getWrapper(listDTO, null, orderType, page, numRows, total, null);
	}
}