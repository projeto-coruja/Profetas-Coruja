package business.Bean.model;

import java.util.List;

import persistence.dto.Classificacao;
import persistence.dto.FontesObras;
import persistence.dto.GrupoMovimento;
import persistence.dto.Local;
import persistence.dto.PalavraChave;
import persistence.dto.Personagem;
import business.DAO.model.CorrespondenciaDAO;
import business.DAO.search.CorrespondenciaSearchDAO;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.model.DuplicateSCorrespondenceException;
import business.exceptions.model.DuplicateSourceWorkException;
import business.exceptions.search.business.DAO.search.FontesObrasNotFoundException;
import datatype.SimpleDate;

public class CorrespondenciaBean {

	CorrespondenciaDAO dao;
	CorrespondenciaSearchDAO search;

	public CorrespondenciaBean() {
		dao = new CorrespondenciaDAO();
	}
	
	private boolean isSet(Object obj){
		if(obj == null)	return false;
		if(obj instanceof String && ((String) obj).isEmpty())	return false;
		return true;
	}
	
	/**
	 * Adiciona uma nova fonte/obra
	 * @param titulo
	 * @param comentarios
	 * @param refverenciasirCulacaoObra
	 * @param uRL
	 * @param copiasManuscritas
	 * @param traducoes
	 * @param dataImpressao
	 * @param editor
	 * @param grupoMovimento
	 * @param localImpressao
	 * @param classificao
	 * @param palavraChave
	 * @param obrasCitadas
	 * @param leitores
	 * @param personagens
	 * @param autoresCitados
	 * @throws UnreachableDataBaseException
	 * @throws DuplicateSourceWorkException
	 */
/*	public void addCorrespondence(Personagem remetente, Personagem destinatario, SimpleDate data, Local local) throws UnreachableDataBaseException, DuplicateSCorrespondenceException {

		try {
			search.findExactFontesObras(titulo, editor);
			throw new DuplicateSourceWorkException();
		} catch (FontesObrasNotFoundException e) {
			FontesObras newSourceWork = new FontesObras(titulo, comentarios,
					refverenciasirCulacaoObra, url, copiasManuscritas, traducoes,
					dataImpressao, editor, grupoMovimento, localImpressao,
					classificao, palavraChave, obrasCitadas, leitores, personagens,
					autoresCitados);
			dao.addSourceWork(newSourceWork);
		} 

	}
	*/
	public void updateSourceWork (long id, String titulo, String comentarios,
			String refverenciasirCulacaoObra, String url,
			String copiasManuscritas, String traducoes,
			SimpleDate dataImpressao, String editor,
			GrupoMovimento grupoMovimento, Local localImpressao,
			Classificacao classificao, List<PalavraChave> palavraChave,
			List<FontesObras> obrasCitadas, List<Personagem> leitores,
			List<Personagem> personagens, List<Personagem> autoresCitados) throws UnreachableDataBaseException{
		
		
		try {
			FontesObras object = search.findExactFontesObrasById(id);
			
			if(isSet(titulo))	object.setTitulo(titulo);
			if(isSet(comentarios))	object.setComentarios(comentarios);
			if(isSet(refverenciasirCulacaoObra))	object.setRefverenciasirCulacaoObra(refverenciasirCulacaoObra);
			if(isSet(url))	object.setURL(url);
			if(isSet(copiasManuscritas))	object.setCopiasManuscritas(copiasManuscritas);
			if(isSet(traducoes))	object.setTraducoes(traducoes);
			if(isSet(dataImpressao))	object.setDataImpressao(dataImpressao);
			if(isSet(editor))	object.setEditor(editor);
			
			if(isSet(grupoMovimento))	object.setGrupoMovimento(grupoMovimento);
			if(isSet(localImpressao))	object.setLocalImpressao(localImpressao);
			if(isSet(classificao))	object.setClassificao(classificao);
			if(isSet(palavraChave))	object.setPalavraChave(palavraChave);
			if(isSet(obrasCitadas))	object.setObrasCitadas(obrasCitadas);
			if(isSet(leitores))	object.setLeitores(leitores);
			if(isSet(personagens))	object.setPersonagens(personagens);
			if(isSet(autoresCitados))	object.setAutoresCitados(autoresCitados);
			
		} catch (FontesObrasNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Remove uma entrada
	 * @param titulo
	 * @param editor
	 * @throws UnreachableDataBaseException
	 */
	public void removeSourceWork (Long id) throws UnreachableDataBaseException{
		try {
			FontesObras check = search.findExactFontesObrasById(id);
			if(check == null)	throw new FontesObrasNotFoundException("Entrada '"+ id +"' não encontrada.");
			//dao.removeSourceWork(check);
		} catch (FontesObrasNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Remove uma entrada
	 * @param id
	 * @throws UnreachableDataBaseException
	 */
	public void removeSourceWork (long id) throws UnreachableDataBaseException{
		try {
			FontesObras check = search.findExactFontesObrasById(id);
			if(check == null)	throw new FontesObrasNotFoundException("Entrada '"+ id +"' não encontrada.");
			//dao.removeSourceWork(check);
		} catch (FontesObrasNotFoundException e) {
			e.printStackTrace();
		}
	}

}
