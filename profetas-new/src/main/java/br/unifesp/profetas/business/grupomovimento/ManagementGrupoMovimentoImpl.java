package br.unifesp.profetas.business.grupomovimento;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unifesp.profetas.business.AbstractBusiness;
import br.unifesp.profetas.business.common.MessageDTO;
import br.unifesp.profetas.business.common.MessageType;
import br.unifesp.profetas.business.common.OrderType;
import br.unifesp.profetas.business.common.WrapperGrid;
import br.unifesp.profetas.business.local.LocalDTO;
import br.unifesp.profetas.persistence.domain.GrupoMovimentoDAO;
import br.unifesp.profetas.persistence.domain.LocalDAO;
import br.unifesp.profetas.persistence.model.GrupoMovimento;
import br.unifesp.profetas.persistence.model.Local;
import br.unifesp.profetas.util.UtilValidator;

@Service("mgrupoMovimento")
public class ManagementGrupoMovimentoImpl extends AbstractBusiness implements ManagementGrupoMovimento {
	
	@Autowired private GrupoMovimentoDAO grupoMovimentoDAO;
	@Autowired private LocalDAO localDAO;

	public GrupoMovimentoDTO getGrupoMovimentoById(Long id) {
		GrupoMovimento gMovimento = grupoMovimentoDAO.getGrupoMovimentoById(id);
		if(gMovimento != null){
			GrupoMovimentoDTO gmDTO = new GrupoMovimentoDTO();
			gmDTO.setId(gMovimento.getId());
			gmDTO.setNome(gMovimento.getNome());
			gmDTO.setAnoInicio(String.valueOf(gMovimento.getAnoInicio()));
			gmDTO.setAnoFim(String.valueOf(gMovimento.getAnoFim()));
			gmDTO.setDescricao(gMovimento.getDescricao());
				List<String> locais = new ArrayList<String>(gMovimento.getLocais().size());
				for(Local l : gMovimento.getLocais()){
					locais.add(String.valueOf(l.getId()));
				}
			gmDTO.setIdLocais(locais.toArray(new String[locais.size()]));
			return gmDTO;
		}
		return null;
	}

	public List<Local> getAllLocal() {
		return localDAO.listLocal();
	}

	public MessageDTO createGrupoMovimento(GrupoMovimentoDTO gMovimentoDTO) {
		if(!UtilValidator.validateNotEmptyField(gMovimentoDTO.getNome())){
			return new MessageDTO(getText("err_religiao_nome_required"), MessageType.ERROR);
		}
		try{
			GrupoMovimento grupoMovimento = new GrupoMovimento();
			grupoMovimento.setNome(gMovimentoDTO.getNome());
			if(gMovimentoDTO.getAnoInicio() != null && !"".equals(gMovimentoDTO.getAnoInicio())){
				grupoMovimento.setAnoInicio(Integer.parseInt(gMovimentoDTO.getAnoInicio()));
			}
			if(gMovimentoDTO.getAnoFim() != null && !"".equals(gMovimentoDTO.getAnoFim())){
				grupoMovimento.setAnoFim(Integer.parseInt(gMovimentoDTO.getAnoFim()));
			}
			grupoMovimento.setDescricao(gMovimentoDTO.getDescricao());
			
			int locais_length = gMovimentoDTO.getIdLocais().length;
			if(locais_length > 0){
				List<Local> locals = new ArrayList<Local>(locais_length);
				for(int i = 0; i < locais_length; i++){
					Local local = new Local();
					local.setId(Long.parseLong(gMovimentoDTO.getIdLocais()[i]));
					locals.add(local);
				}
				grupoMovimento.setLocais(new HashSet<Local>());
			}
			
			grupoMovimentoDAO.saveGrupoMovimento(grupoMovimento);
			if(grupoMovimento.getId() != null){
				return new MessageDTO(getText("msg_grupo_movimento_created"), MessageType.SUCCESS);
			} else {
				return new MessageDTO(getText("err_grupo_movimento_not_saved"), MessageType.ERROR);
			}
		} catch(Exception e){
			return new MessageDTO(getText("err_grupo_movimento_not_saved"), MessageType.ERROR);
		}
	}

	public MessageDTO updateGrupoMovimento(GrupoMovimentoDTO gMovimentoDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	public MessageDTO deleteGrupoMovimento(GrupoMovimentoDTO gMovimentoDTO) {
		try{
			GrupoMovimento grupoMovimento = new GrupoMovimento();
			grupoMovimento.setId(gMovimentoDTO.getId());
			grupoMovimentoDAO.deleteGrupoMovimento(grupoMovimento);
			return new MessageDTO(getText("msg_grupo_movimento_deleted"), MessageType.SUCCESS);
		}
		catch(Exception e){
			return new MessageDTO(getText("err_grupo_movimento_not_deleted"), MessageType.ERROR);
		}
	}

	public WrapperGrid<GrupoMovimentoDTO> getGrupoMovimentoList(String orderBy,
			OrderType orderType, int page, int numRows) {
		List<GrupoMovimento> list = grupoMovimentoDAO.listGrupoMovimento();//TODO: limit
		List<GrupoMovimentoDTO> listGmDTO = new ArrayList<GrupoMovimentoDTO>(list.size());
		for(GrupoMovimento gm : list){
			GrupoMovimentoDTO gmDTO = new GrupoMovimentoDTO();
			gmDTO.setId(gm.getId());
			gmDTO.setNome(gm.getNome());
			gmDTO.setAnoInicio(String.valueOf(gm.getAnoInicio()));
			gmDTO.setAnoFim(String.valueOf(gm.getAnoFim()));
			List<LocalDTO> listLocDTO = new ArrayList<LocalDTO>();//
			List<Local> locals = new ArrayList<Local>(gm.getLocais());
			for(Local l : locals){
				LocalDTO lDTO = new LocalDTO();
				lDTO.setNome(l.getNome());
				listLocDTO.add(lDTO);
			}
			gmDTO.setLocais(listLocDTO);
			listGmDTO.add(gmDTO);
		}
		
		int total = list == null ? 0 : list.size();//TODO: count
		return getWrapper(listGmDTO, orderBy, orderType, page, numRows, total, null);
	}
}