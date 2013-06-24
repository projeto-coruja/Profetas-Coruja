package business.DAO.search;

import java.util.List;

import datatype.SimpleDate;

import persistence.PersistenceAccess;
import persistence.dto.Classificacao;
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
	public List<DTO> findFontesObrasByTitulo(String titulo) throws  UnreachableDataBaseException, FontesObrasNotFoundException {
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
	
	public List<DTO> findFontesObrasByComentario(String comentario) throws  UnreachableDataBaseException, FontesObrasNotFoundException {
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
			resultSet = manager.findEntity("from FontesObrasMO order by titulo");
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
	 * Pesquisa  fontes/obras por URL
	 * @param url - url da fonte/obra.
	 * @throws UnreachableDataBaseException
	 * @throws FontesObrasNotFoundException
	 */
	public List<DTO> findFontesObrasByURL(String url) throws  UnreachableDataBaseException, FontesObrasNotFoundException  {
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("from FontesObrasMO where url like '%" + url +"%' "
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
	 * Pesquisa  fontes/obras por copiasManuscritas
	 * @param copiasManuscritas -copiasManuscritas da fonte/obra.
	 * @throws UnreachableDataBaseException
	 * @throws FontesObrasNotFoundException
	 */
	public List<DTO> findFontesObrasByCopiasManuscritas(String copiasManuscritas) throws  UnreachableDataBaseException, FontesObrasNotFoundException  {
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("from FontesObrasMO where copiasManuscritas like '%" + copiasManuscritas +"%' "
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
	 * Pesquisa  fontes/obras por traducoes
	 * @param copiasManuscritas -copiasManuscritas da fonte/obra.
	 * @throws UnreachableDataBaseException
	 * @throws FontesObrasNotFoundException
	 */
	public List<DTO> findFontesObrasByTraducoes(String traducoes) throws  UnreachableDataBaseException, FontesObrasNotFoundException  {
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("from FontesObrasMO where traducoes like '%" + traducoes +"%' "
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
	 * Pesquisa  fontes/obras por dataImpressaos
	 * @param  dataImpressaos - dataImpressao da fonte/obra.
	 * @throws UnreachableDataBaseException
	 * @throws FontesObrasNotFoundException
	 */
	public List<DTO> findFontesObrasByDataImpressao(SimpleDate dataImpressao) throws  UnreachableDataBaseException, FontesObrasNotFoundException  {
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("from FontesObrasMO where dataImpressao like '%" + dataImpressao +"%' "
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
	 * Pesquisa  fontes/obras por editor
	 * @param  dataImpressaos - dataImpressao da fonte/obra.
	 * @throws UnreachableDataBaseException
	 * @throws FontesObrasNotFoundException
	 */
	public List<DTO> findFontesObrasByEditor(String editor) throws  UnreachableDataBaseException, FontesObrasNotFoundException  {
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("from FontesObrasMO where editor like '%" + editor +"%' "
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
	 * Pesquisa  fontes/obras pelo id de Classificação
	 * @param  id -  id  da Classificacao da fonte/obra.
	 * @throws UnreachableDataBaseException
	 * @throws FontesObrasNotFoundException
	 */
	public List<DTO> findFontesObrasByClassificacao(long id) throws  UnreachableDataBaseException, FontesObrasNotFoundException  {
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("from FontesObrasMO where classificao_id = '" + id +"' "
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
	 * Pesquisa  fontes/obras pelo id de grupo movimento
	 * @param  id -id do grupo/movimento.
	 * @throws UnreachableDataBaseException
	 * @throws FontesObrasNotFoundException
	 */
	public List<DTO> findFontesObrasByGrupoMovimento(long id) throws  UnreachableDataBaseException, FontesObrasNotFoundException  {
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("from FontesObrasMO where grupomovimento_id = '" + id +"' "
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
	 * Pesquisa  fontes/obras pelo id do local de impressão
	 * @param  id -id do local de impressão.
	 * @throws UnreachableDataBaseException
	 * @throws FontesObrasNotFoundException
	 */
	public List<DTO> findFontesObrasByLocal(long id) throws  UnreachableDataBaseException, FontesObrasNotFoundException  {
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("from FontesObrasMO where localimpressao_id = '" + id +"' "
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
	
	
	

}//end class
