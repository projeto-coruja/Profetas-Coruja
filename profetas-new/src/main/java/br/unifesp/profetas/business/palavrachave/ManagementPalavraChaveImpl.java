package br.unifesp.profetas.business.palavrachave;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unifesp.profetas.business.AbstractBusiness;
import br.unifesp.profetas.business.common.MessageDTO;
import br.unifesp.profetas.business.common.MessageType;
import br.unifesp.profetas.business.common.OrderType;
import br.unifesp.profetas.business.common.WrapperGrid;
import br.unifesp.profetas.persistence.domain.PalavraChaveDAO;
import br.unifesp.profetas.persistence.model.PalavraChave;

@Service("mPalavraChave")
public class ManagementPalavraChaveImpl extends AbstractBusiness implements
		ManagementPalavraChave {

	@Autowired
	private PalavraChaveDAO palavraChaveDAO;

	public PalavraChaveDTO getPalavraChaveById(Long id) {
		PalavraChave palavraChave = palavraChaveDAO.getPalavraChaveById(id);
		if (palavraChave != null) {
			PalavraChaveDTO eDTO = new PalavraChaveDTO(palavraChave.getId(),
					palavraChave.getPalavraChave());
			return eDTO;
		}
		return null;
	}

	public MessageDTO createPalavraChave(PalavraChaveDTO palavraChaveDTO) {
		if (palavraChaveDTO.getPalavraChave() == null) {
			return new MessageDTO(
					getText("err_palavra_chave_palavra_required"),
					MessageType.ERROR);
		}
		try {
			PalavraChave palavraChave = new PalavraChave();
			palavraChave.setPalavraChave(palavraChaveDTO.getPalavraChave());
			palavraChaveDAO.savePalavraChave(palavraChave);
			if (palavraChave.getId() != null) {
				return new MessageDTO(getText("msg_palavra_chave_created"),
						MessageType.SUCCESS);
			} else {
				return new MessageDTO(getText("err_palavra_chave_not_saved"),
						MessageType.ERROR);
			}
		} catch (Exception e) {
			return new MessageDTO(getText("err_palavra_chave_not_saved"),
					MessageType.ERROR);
		}
	}

	public MessageDTO updatePalavraChave(PalavraChaveDTO palavraChaveDTO) {
		if (palavraChaveDTO.getId() == null) {
			return new MessageDTO(getText("err_palavra_chave_not_updated"),
					MessageType.ERROR);
		}
		if (palavraChaveDTO.getPalavraChave() == null) {
			return new MessageDTO(
					getText("err_palavra_chave_palavra_required"),
					MessageType.ERROR);
		}
		try {
			PalavraChave palavra = palavraChaveDAO
					.getPalavraChaveById(palavraChaveDTO.getId());
			if (palavra != null) {
				palavra.setPalavraChave(palavraChaveDTO.getPalavraChave());
				;
				palavraChaveDAO.updatePalavraChave(palavra);
				if (palavra.getId() != null) {
					return new MessageDTO(getText("msg_palavra_chave_updated"),
							MessageType.SUCCESS);
				}
			}
			return new MessageDTO(getText("err_palavra_chave_not_updated"),
					MessageType.ERROR);
		} catch (Exception e) {
			return new MessageDTO(getText("err_palavra_chave_not_updated"),
					MessageType.ERROR);
		}
	}

	public MessageDTO deletePalavraChave(PalavraChaveDTO palavraChaveDTO) {
		try {
			PalavraChave palavra = new PalavraChave();
			palavra.setId(palavraChaveDTO.getId());
			palavraChaveDAO.deletePalavraChave(palavra);
			return new MessageDTO(getText("msg_palavra_chave_deleted"),
					MessageType.SUCCESS);
		} catch (Exception e) {
			return new MessageDTO(getText("err_palavra_chave_not_deleted"),
					MessageType.ERROR);
		}
	}

	public WrapperGrid<PalavraChave> getPalavraChaveList(String orderBy,
			OrderType orderType, int page, int numRows) {
		List<PalavraChave> list = palavraChaveDAO.listPalavraChave();// TODO:
																		// limit
		int total = list == null ? 0 : list.size();// TODO: count
		return getWrapper(list, orderBy, orderType, page, numRows, total, null);
	}
}