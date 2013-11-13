package br.unifesp.profetas.business.common;

import java.util.List;

import br.unifesp.profetas.business.correspondencia.CorrespondenciaDTO;
import br.unifesp.profetas.business.encontro.EncontroDTO;
import br.unifesp.profetas.business.fontesobras.ClassificacaoDTO;
import br.unifesp.profetas.business.fontesobras.FontesObrasDTO;
import br.unifesp.profetas.business.grupomovimento.GrupoMovimentoDTO;
import br.unifesp.profetas.business.local.LocalDTO;
import br.unifesp.profetas.business.personagem.PersonagemDTO;
import br.unifesp.profetas.business.profile.ProfileDTO;
import br.unifesp.profetas.business.religiao.ReligiaoCrencasDTO;

public interface ManagementCommon {
	
	public List<PersonagemDTO> getPersonagens();

	public List<ProfileDTO> getProfiles();
	
	public List<LocalDTO> getLocals();
	
	public List<ClassificacaoDTO> getClassificacoes();
	
	public List<GrupoMovimentoDTO> getGruMovimentos();
	
	public List<FontesObrasDTO> getObrasCitadas();
	
	public List<ReligiaoCrencasDTO> getReligioes();
	
	public List<EncontroDTO> getEncontros();
	
	public List<CorrespondenciaDTO> getCorrespondencias();
}