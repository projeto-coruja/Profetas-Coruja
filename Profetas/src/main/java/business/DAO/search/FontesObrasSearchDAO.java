package business.DAO.search;

import java.util.List;

import persistence.PersistenceAccess;
import persistence.dto.DTO;
import persistence.dto.FontesObras;
import persistence.util.DataAccessLayerException;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.search.business.DAO.search.FontesObrasNotFoundException;

public class FontesObrasSearchDAO {
	private PersistenceAccess manager;
	
	public FontesObrasSearchDAO(){
		manager= new PersistenceAccess();
	}
	/**
	 * Pesquisa por uma FontesObras
	 * @param titulo - titulo da Fontes/Obras
	 * @throws UnreachableDataBaseException
	 * @throws FontesObrasNotFoundException
	 */
	public FontesObras findExactFontesObras(String titulo) throws FontesObrasNotFoundException, UnreachableDataBaseException{
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("FROM FontesObrasMO"+		
					" where titulo = "+titulo+" "+
					" ORDER BY nome ");
			
			if(resultSet == null) {
				throw new FontesObrasNotFoundException ("Fontes/Obras não encontrado.");
			}
			else{
				
				return (FontesObras) resultSet.get(0);
			}
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Fontes/Obras o banco de dados");
		}
		
	}
	/**
	 * Pesquisa por uma "parcela" do titulo de fontes/obras
	 * @param titulo - titulo da fonte/obra do personagem.
	 * @throws UnreachableDataBaseException
	 * @throws FontesObrasNotFoundException 
	 */
	public List<DTO> findFonteObrasByTitulo(String titulo) throws  UnreachableDataBaseException, FontesObrasNotFoundException {
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("from FontesObrasMO where titulo like '%" + titulo +"%' "
					+ "order by cod, titulo, anoInicio, anoFim");
			
			if(resultSet == null) {
				throw new FontesObrasNotFoundException ("Fontes/Obras não encontrado.");
			}
			else return resultSet;
		
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}
	/**
	 * Pesquisa por uma "parcela" do comentario da fonte/obra 
	 * @param comentario - comentario da fonte/obra.
	 * @throws UnreachableDataBaseException
	 * @throws FontesObrasNotFoundException 
	 */
	
	public List<DTO> findFonteObrasByComentario(String comentario) throws  UnreachableDataBaseException, FontesObrasNotFoundException {
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("from FontesObrasMO where comentario like '%" +comentario +"%' "
					+ "order by titulo");
			
			if(resultSet == null) {
				throw new FontesObrasNotFoundException ("Fontes/Obras não encontrado.");
			}
			else return resultSet;
		
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}
	
	/**
	 * Pesquisa todas as fontes/obras
	 * @throws UnreachableDataBaseException
	 * @throws FontesObrasNotFoundException
	 */
	public List<DTO> findAllFontesObras() throws  UnreachableDataBaseException, FontesObrasNotFoundException  {
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("from FontesObrasMO order by nome");
			if(resultSet == null) {
				throw new FontesObrasNotFoundException("Não existe nenhuma Fontes/Obras cadastrado.");
			}
			else return resultSet;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}
	/**
	 * Pesquisa tadas as fontes/obras
	 * @param comentario - comentario da fonte/obra.
	 * @throws UnreachableDataBaseException
	 * @throws FontesObrasNotFoundException
	 */
	public List<DTO> findAllFontesObras() throws  UnreachableDataBaseException, FontesObrasNotFoundException  {
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("from FontesObrasMO order by nome");
			if(resultSet == null) {
				throw new FontesObrasNotFoundException("Não existe nenhuma Fontes/Obras cadastrado.");
			}
			else return resultSet;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}

}
