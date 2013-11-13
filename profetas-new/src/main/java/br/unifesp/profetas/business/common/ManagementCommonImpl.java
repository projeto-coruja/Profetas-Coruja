package br.unifesp.profetas.business.common;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unifesp.profetas.business.correspondencia.CorrespondenciaDTO;
import br.unifesp.profetas.business.encontro.EncontroDTO;
import br.unifesp.profetas.business.fontesobras.ClassificacaoDTO;
import br.unifesp.profetas.business.fontesobras.FontesObrasDTO;
import br.unifesp.profetas.business.grupomovimento.GrupoMovimentoDTO;
import br.unifesp.profetas.business.local.LocalDTO;
import br.unifesp.profetas.business.personagem.PersonagemDTO;
import br.unifesp.profetas.business.profile.ProfileDTO;
import br.unifesp.profetas.business.religiao.ReligiaoCrencasDTO;
import br.unifesp.profetas.persistence.domain.ClassificacaoDAO;
import br.unifesp.profetas.persistence.domain.CorrespondenciaDAO;
import br.unifesp.profetas.persistence.domain.EncontroDAO;
import br.unifesp.profetas.persistence.domain.FontesObrasDAO;
import br.unifesp.profetas.persistence.domain.GrupoMovimentoDAO;
import br.unifesp.profetas.persistence.domain.LocalDAO;
import br.unifesp.profetas.persistence.domain.PersonagemDAO;
import br.unifesp.profetas.persistence.domain.ProfileDAO;
import br.unifesp.profetas.persistence.domain.ReligiaoCrencasDAO;
import br.unifesp.profetas.persistence.model.Classificacao;
import br.unifesp.profetas.persistence.model.Correspondencia;
import br.unifesp.profetas.persistence.model.Encontro;
import br.unifesp.profetas.persistence.model.FontesObras;
import br.unifesp.profetas.persistence.model.GrupoMovimento;
import br.unifesp.profetas.persistence.model.Local;
import br.unifesp.profetas.persistence.model.Personagem;
import br.unifesp.profetas.persistence.model.Profile;
import br.unifesp.profetas.persistence.model.ReligiaoCrencas;

@Service("mCommon")
public class ManagementCommonImpl implements ManagementCommon {

	@Autowired private PersonagemDAO personagemDAO;
	@Autowired private ProfileDAO profileDAO;
	@Autowired private LocalDAO localDAO;
	@Autowired private ClassificacaoDAO classificacaoDAO;
	@Autowired private FontesObrasDAO fontesObrasDAO;
	@Autowired private GrupoMovimentoDAO grupoMovimentoDAO;
	@Autowired private ReligiaoCrencasDAO religiaoCrencasDAO;
	@Autowired private EncontroDAO encontroDAO;
	@Autowired private CorrespondenciaDAO correspondenciaDAO;
	
	public List<PersonagemDTO> getPersonagens() {
		List<Personagem> personagens = personagemDAO.listPersonagem();
		List<PersonagemDTO> listDTO = new ArrayList<PersonagemDTO>();
		for(Personagem p : personagens){
			PersonagemDTO pDTO = new PersonagemDTO();
			pDTO.setId(p.getId());
			pDTO.setNome(p.getNome());
			pDTO.setApelido(p.getApelido());
			listDTO.add(pDTO);
		}
		return listDTO;
	}

	public List<ProfileDTO> getProfiles() {
		List<Profile> profiles = profileDAO.profileList();
		List<ProfileDTO> listDTO = new ArrayList<ProfileDTO>();
		for(Profile p : profiles){
			ProfileDTO pDTO = new ProfileDTO(p.getId(), p.getName());
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

	public List<ClassificacaoDTO> getClassificacoes() {
		List<Classificacao> list = classificacaoDAO.listClassificacao();
		List<ClassificacaoDTO> listDTO = new ArrayList<ClassificacaoDTO>();
		for(Classificacao l : list){
			ClassificacaoDTO cDTO = new ClassificacaoDTO();
			cDTO.setId(l.getId());
			cDTO.setTipo(l.getTipo());
			listDTO.add(cDTO);
		}
		return listDTO;
	}

	public List<GrupoMovimentoDTO> getGruMovimentos() {
		List<GrupoMovimento> gruMovimentos = grupoMovimentoDAO.listGrupoMovimento();
		List<GrupoMovimentoDTO> listDTO = new ArrayList<GrupoMovimentoDTO>();
		for(GrupoMovimento g : gruMovimentos){
			GrupoMovimentoDTO gDTO = new GrupoMovimentoDTO();
			gDTO.setId(g.getId());
			gDTO.setNome(g.getNome());
			listDTO.add(gDTO);
		}
		return listDTO;
	}
	
	public List<FontesObrasDTO> getObrasCitadas() {
		List<FontesObras> fontesObras = fontesObrasDAO.listFontesObras();
		List<FontesObrasDTO> listDTO = new ArrayList<FontesObrasDTO>();
		for(FontesObras f : fontesObras){
			FontesObrasDTO fDTO = new FontesObrasDTO();
			fDTO.setId(f.getId());
			fDTO.setTitulo(f.getTitulo());
			listDTO.add(fDTO);
		}
		return listDTO;
	}

	public List<ReligiaoCrencasDTO> getReligioes() {
		List<ReligiaoCrencas> religioes = religiaoCrencasDAO.listReligiaoCrencas();
		List<ReligiaoCrencasDTO> listDTO = new ArrayList<ReligiaoCrencasDTO>();
		for(ReligiaoCrencas r : religioes){
			ReligiaoCrencasDTO rDTO = new ReligiaoCrencasDTO();
			rDTO.setId(r.getId());
			rDTO.setNome(r.getNome());
			listDTO.add(rDTO);
		}
		return listDTO;
	}

	public List<EncontroDTO> getEncontros() {
		List<Encontro> encontros = encontroDAO.listEncontro();
		List<EncontroDTO> listDTO = new ArrayList<EncontroDTO>();
		for(Encontro e : encontros){
			EncontroDTO eDTO = new EncontroDTO();
			eDTO.setId(e.getId());
			eDTO.setNome(e.getNome());
			listDTO.add(eDTO);
		}
		return listDTO;
	}

	public List<CorrespondenciaDTO> getCorrespondencias() {
		List<Correspondencia> correspondencias = correspondenciaDAO.listCorrespondencia();
		List<CorrespondenciaDTO> listDTO = new ArrayList<CorrespondenciaDTO>();
		for(Correspondencia c : correspondencias){
			CorrespondenciaDTO cDTO = new CorrespondenciaDTO();
			cDTO.setId(c.getId());
			cDTO.setNomeRemetente(c.getRemetente().getNome() + c.getRemetente().getApelido());
			cDTO.setNomeDestinatario(c.getDestinatario().getNome() + c.getDestinatario().getApelido());
			listDTO.add(cDTO);
		}
		return listDTO;
	}
}