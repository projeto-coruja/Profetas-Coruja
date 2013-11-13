package br.unifesp.profetas.business.correspondencia;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unifesp.profetas.business.AbstractBusiness;
import br.unifesp.profetas.business.common.MessageDTO;
import br.unifesp.profetas.business.common.MessageType;
import br.unifesp.profetas.business.common.OrderType;
import br.unifesp.profetas.business.common.WrapperGrid;
import br.unifesp.profetas.persistence.domain.CorrespondenciaDAO;
import br.unifesp.profetas.persistence.model.Correspondencia;
import br.unifesp.profetas.persistence.model.Local;
import br.unifesp.profetas.persistence.model.Personagem;
import br.unifesp.profetas.util.ProfetasConstants;
import br.unifesp.profetas.util.UtilValidator;

@Service("mCorrespondencia")
public class ManagementCorrespondenciaImpl extends AbstractBusiness implements ManagementCorrespondencia {
    
    @Autowired private CorrespondenciaDAO correspondenciaDAO;

    public CorrespondenciaDTO getCorrespondenciaById(Long id) {
        Correspondencia correspondencia = correspondenciaDAO.getCorrespondenciaById(id);
        if(correspondencia != null){
            SimpleDateFormat dateFormat = new SimpleDateFormat(ProfetasConstants.DATE_FORMAT_SHORT);
            CorrespondenciaDTO cDTO = new CorrespondenciaDTO();
            cDTO.setId(correspondencia.getId());
            cDTO.setIdRemetente(correspondencia.getRemetente().getId());
            cDTO.setIdDestinatario(correspondencia.getDestinatario().getId());
            cDTO.setIdLocal(correspondencia.getLocal().getId());
            cDTO.setData(dateFormat.format(correspondencia.getData()));
            return cDTO;
        }
        return null;
    }
    
    private MessageDTO isNotValid(CorrespondenciaDTO correspondenciaDTO, boolean isNew){
        if(correspondenciaDTO.getIdRemetente() == null || correspondenciaDTO.getIdRemetente() != -1){
            return new MessageDTO(getText("err_rementente_required"), MessageType.ERROR);
        }
        if(correspondenciaDTO.getIdDestinatario() == null || correspondenciaDTO.getIdDestinatario() == -1){
            return new MessageDTO(getText("err_destinatario_required"), MessageType.ERROR);
        }
        return null;
    }
    
    private Correspondencia getCorrespondencia(Correspondencia correspondencia, CorrespondenciaDTO correspondenciaDTO){
        correspondencia.setRemetente(new Personagem(correspondenciaDTO.getIdRemetente()));
        correspondencia.setDestinatario(new Personagem(correspondenciaDTO.getIdDestinatario()));
        if(correspondenciaDTO.getIdLocal() != null && correspondenciaDTO.getIdLocal() != -1){
            correspondencia.setLocal(new Local(correspondenciaDTO.getIdLocal()));
        }
        correspondencia.setData(UtilValidator.getDateFromString(correspondenciaDTO.getData()));
        correspondencia.setActive(true);
        return correspondencia;
    }

    public MessageDTO createCorrespondencia(CorrespondenciaDTO correspondenciaDTO) {
        MessageDTO isNotValid = isNotValid(correspondenciaDTO, true);
        if(isNotValid != null)
            return isNotValid;
        
        try{
            Correspondencia correspondencia = new Correspondencia();
            correspondencia = getCorrespondencia(correspondencia, correspondenciaDTO);
            correspondenciaDAO.saveCorrespondencia(correspondencia);
            if(correspondencia.getId() != null){
                return new MessageDTO(getText("msg_correspondencia_created"), MessageType.SUCCESS);
            } 
            return new MessageDTO(getText("err_correspondencia_not_created"), MessageType.ERROR);
        } catch(Exception e){
            return new MessageDTO(getText("err_correspondencia_not_created"), MessageType.ERROR);
        }
    }

    public MessageDTO updateCorrespondencia(CorrespondenciaDTO correspondenciaDTO) {
        if(correspondenciaDTO.getId() == null){
            return new MessageDTO(getText("err_correspondencia_not_updated"), MessageType.ERROR);
        }
        MessageDTO isNotValid = isNotValid(correspondenciaDTO, true);
        if(isNotValid != null)
            return isNotValid;

        try{
            Correspondencia correspondencia = correspondenciaDAO.getCorrespondenciaById(correspondenciaDTO.getId());
            if(correspondencia != null) {
                correspondencia = getCorrespondencia(correspondencia, correspondenciaDTO);
                correspondenciaDAO.updateCorrespondencia(correspondencia);
                if(correspondencia.getId() != null){
                    return new MessageDTO(getText("msg_correspondencia_updated"), MessageType.SUCCESS);
                }
            }
            return new MessageDTO(getText("err_correspondencia_not_updated"), MessageType.ERROR);
        } catch(Exception e){
            return new MessageDTO(getText("err_correspondencia_not_updated"), MessageType.ERROR);
        }
    }

    public MessageDTO deleteCorrespondencia(
            CorrespondenciaDTO correspondenciaDTO) {
        try{
            Correspondencia correspondencia = correspondenciaDAO.getCorrespondenciaById(correspondenciaDTO.getId());
            if(correspondencia != null){
                correspondencia.setActive(false);
                correspondenciaDAO.updateCorrespondencia(correspondencia);
                return new MessageDTO(getText("msg_correspondencia_deleted"), MessageType.SUCCESS);
            }
            return new MessageDTO(getText("err_correspondencia_not_deleted"), MessageType.ERROR);
        }
        catch(Exception e){
            return new MessageDTO(getText("err_correspondencia_not_deleted"), MessageType.ERROR);
        }
    }

    public WrapperGrid<CorrespondenciaDTO> getCorrespondenciaList(String orderBy,
            OrderType orderType, int page, int numRows) {
        List<Correspondencia> list = correspondenciaDAO.listCorrespondencia();//TODO: limit
        int total = list == null ? 0 : list.size();//TODO: count
        List<CorrespondenciaDTO> listDTO = new ArrayList<CorrespondenciaDTO>();
        for(Correspondencia c : list){
            CorrespondenciaDTO cDTO = new CorrespondenciaDTO();
            cDTO.setId(c.getId());
            cDTO.setNomeRemetente(c.getRemetente().getNome() + " " + c.getRemetente().getApelido());
            cDTO.setNomeDestinatario(c.getDestinatario().getNome() + " " + c.getDestinatario().getApelido());
            cDTO.setNomeLocal(c.getLocal().getNome());
            cDTO.setData(c.getData().toString());
            listDTO.add(cDTO);
        }
        return getWrapper(listDTO, orderBy, orderType, page, numRows, total, null);
    }
}
