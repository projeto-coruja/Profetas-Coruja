package br.unifesp.profetas.business.classificacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unifesp.profetas.business.AbstractBusiness;
import br.unifesp.profetas.business.common.MessageDTO;
import br.unifesp.profetas.business.common.MessageType;
import br.unifesp.profetas.business.common.OrderType;
import br.unifesp.profetas.business.common.WrapperGrid;
import br.unifesp.profetas.persistence.domain.ClassificacaoDAO;
import br.unifesp.profetas.persistence.domain.PalavraChaveDAO;
import br.unifesp.profetas.persistence.model.Classificacao;
import br.unifesp.profetas.persistence.model.PalavraChave;

@Service("mPalavraChave")
public class ManagementClassificacaoImpl extends AbstractBusiness implements
		ManagementClassificacao {

	@Autowired
	private ClassificacaoDAO classificacaoDAO;

	public ClassificacaoDTO getClassificacaoById(Long id) {
		Classificacao classificacao = classificacaoDAO.getClassificacaoById(id);
		if (classificacao != null) {
			ClassificacaoDTO eDTO = new ClassificacaoDTO(classificacao.getId(),
					classificacao.getTipo());
			return eDTO;
		}
		return null;
	}

	public MessageDTO createClassificacao(ClassificacaoDTO classificacaoDTO) {
		if (classificacaoDTO.getTipo() == null) {
			return new MessageDTO(
					getText("err_classificacao_tipo_required"),
					MessageType.ERROR);
		}
		try {
			Classificacao classificacao = new Classificacao();
			classificacao.setTipo(classificacaoDTO.getTipo());
			classificacaoDAO.saveClassificacao(classificacao);
			if (classificacao.getId() != null) {
				return new MessageDTO(getText("msg_classificacao_created"),
						MessageType.SUCCESS);
			} else {
				return new MessageDTO(getText("err_classificacao_not_saved"),
						MessageType.ERROR);
			}
		} catch (Exception e) {
			return new MessageDTO(getText("err_classificacao_not_saved"),
					MessageType.ERROR);
		}
	}

	public MessageDTO updateClassificacao(ClassificacaoDTO classificacaoDTO) {
		if (classificacaoDTO.getId() == null) {
			return new MessageDTO(getText("err_classificacao_not_updated"),
					MessageType.ERROR);
		}
		if (classificacaoDTO.getTipo() == null) {
			return new MessageDTO(
					getText("err_classificacao_tipo_required"),
					MessageType.ERROR);
		}
		try {
			Classificacao classificacao = classificacaoDAO
					.getClassificacaoById(classificacaoDTO.getId());
			if (classificacao != null) {
				classificacao.setTipo(classificacaoDTO.getTipo());
				classificacaoDAO.updateClassificacao(classificacao);
				if (classificacao.getId() != null) {
					return new MessageDTO(getText("msg_classificacao_updated"),
							MessageType.SUCCESS);
				}
			}
			return new MessageDTO(getText("err_classificacao_not_updated"),
					MessageType.ERROR);
		} catch (Exception e) {
			return new MessageDTO(getText("err_classificacao_not_updated"),
					MessageType.ERROR);
		}
	}

	public MessageDTO deleteClassificacao(ClassificacaoDTO classificacaoDTO) {
		try {
			Classificacao classificacao = new Classificacao();
			classificacao.setId(classificacaoDTO.getId());
			classificacaoDAO.deleteClassificacao(classificacao);
			return new MessageDTO(getText("msg_classificacao_deleted"),
					MessageType.SUCCESS);
		} catch (Exception e) {
			return new MessageDTO(getText("err_classificacao_not_deleted"),
					MessageType.ERROR);
		}
	}

	public WrapperGrid<Classificacao> getClassificacaoList(String orderBy,
			OrderType orderType, int page, int numRows) {
		List<Classificacao> list = classificacaoDAO.listClassificacao();// TODO:
																		// limit
		int total = list == null ? 0 : list.size();// TODO: count
		return getWrapper(list, orderBy, orderType, page, numRows, total, null);
	}
}