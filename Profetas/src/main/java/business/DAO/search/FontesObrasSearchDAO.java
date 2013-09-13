package business.DAO.search;

import java.util.List;

import datatype.SimpleDate;

import persistence.PersistenceAccess;
import persistence.dto.DTO;
import persistence.dto.FontesObras;
import persistence.util.DataAccessLayerException;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.model.ClassificationNotFoundException;
import business.exceptions.model.GroupMovementNotFoundException;
import business.exceptions.model.LocalNotFoundException;
import business.exceptions.search.business.DAO.search.FontesObrasNotFoundException;

public class FontesObrasSearchDAO {
	private PersistenceAccess manager;
	
	public FontesObrasSearchDAO(){
		manager= new PersistenceAccess();
	}
	private String getQueryNormalization(String var){
		var.replaceAll("'", "''");
				
		return "LOWER(TRANSLATE("+var+",'áàãâäÁÀÃÂÄéèêëÉÈÊËíìîïÍÌÎÏóòõôöÓÒÕÔÖúùûüÚÙÛÜñÑçÇÿýÝ','aaaaaAAAAAeeeeEEEEiiiiIIIIoooooOOOOOuuuuUUUUnNcCyyY'))";
	}
	public List<DTO> mainSearchAND(String titulo, String comentario, String ref_circ_obra, String url, String copias_manuscritas, String traducoes, 
			SimpleDate dataImpressao, String editor, 
			String grupoMovimento, SimpleDate anoInicio_grupomovimento, SimpleDate anoFim_grupomovimento, String descricao_grupomovimento, String local_grupomovimento,
			double latitude_grupomovimento, double longitude_grupomovimento,
			String localimpressao, double latitude_localimpressao, double longitude_localimpressao,
			String classificacao, List<DTO> palavraChave, List<DTO> obrasCitadas, List<DTO> leitores, List<DTO> personagens,  List<DTO> autores) throws FontesObrasNotFoundException, UnreachableDataBaseException, GroupMovementNotFoundException, LocalNotFoundException, ClassificationNotFoundException{
		//vamos supor que já recebemos a classificação já vem pronta, vamos apenas pesquisar seu id
		dataImpressao.format();
		anoInicio_grupomovimento.format();
		anoFim_grupomovimento.format();
		
		List<DTO> resultSet = null;
		List<DTO> resultgrupoMovimento = null;
		List<DTO> resultclassificacao = null;
		List<DTO> resultTemp =null;
		try {
			//novas modificações comecam aqui
			///procura por um grupo movimento
			GrupoMovimentoSearchDAO dao_grupomovimento = new GrupoMovimentoSearchDAO();	
			resultgrupoMovimento = dao_grupomovimento.findGrupoMovimentoByAll(grupoMovimento,anoInicio_grupomovimento, anoFim_grupomovimento, descricao_grupomovimento, local_grupomovimento, latitude_grupomovimento, longitude_grupomovimento);
			ClassificacaoSearchDAO dao_classificacao = new ClassificacaoSearchDAO();	
			resultclassificacao = dao_classificacao.findClassificacaoByTipo(classificacao);
			LocalSearchDAO dao_local_impressao = new LocalSearchDAO();
			List<DTO> resultlocal_impressao = dao_local_impressao.findLocalByAll(localimpressao, latitude_localimpressao, longitude_localimpressao);
			//novas modificacoes terminam aqui
			
			//resultadoTemp
			//cruzamento de fontes obras e grupo movimento
			String grupomovimento_query = "( ";
			boolean first = true;
			for(DTO l : resultgrupoMovimento){
				if(! first ){
					grupomovimento_query+=" OR ";
				} 
				first = false;
				grupomovimento_query+="grupomovimento_id = "+l.getId()+" ";
				
			}
			grupomovimento_query+=" ) ";
			//cruzamento de fontes obras e classificacao
			String classificacao_query = "( ";
			first = true;
			for(DTO l : resultclassificacao){
				if(! first ){
					classificacao_query+=" OR ";
				} 
				first = false;
				classificacao_query+=" classificacao_id = "+l.getId()+" ";
				
			}
			classificacao_query+=" ) ";
			
			//cruzamento de fontes obras e local impressao
			String localimpressao_query = "( ";
			first = true;
			for(DTO l :resultlocal_impressao){
				if(! first ){
					localimpressao_query+=" OR ";
				} 
				first = false;
				localimpressao_query+="  localimpressao_id = "+l.getId()+" ";
				
			}
			localimpressao_query+=" ) ";
			
			String query= "from FontesObrasMO where titulo like" + getQueryNormalization("'%" + titulo +"%'")
					+ "AND comentario like" + getQueryNormalization("'%" + comentario +"%'")
					+ "AND refverenciasirculacaoobras like" + getQueryNormalization("'%" + ref_circ_obra +"%'")
					+ "AND url like" + getQueryNormalization("'%" + url +"%'")
					+ "AND copiasmasnuscritas like" + getQueryNormalization("'%" + copias_manuscritas +"%'")
					+ "AND traducoes like" + getQueryNormalization("'%" + traducoes +"%'")
					+ "AND dataimpressao =" + getQueryNormalization("'" + dataImpressao  +"'")
					+ "AND editor like" + getQueryNormalization("'%" + editor +"%'")
					+ "AND " + grupomovimento_query
					+ "AND " + classificacao_query
					+ "AND " + localimpressao_query
				
					+ "order by titulo";
			
			resultSet = manager.findEntity(query);
			
			
			
			//montar a query de personagem
			for(DTO p : personagens){
				for(DTO l : resultSet){
					resultTemp= manager.findEntity("FROM fontesobrasmo_personagemmo WHERE personagens_id =" + p.getId() + "AND fontesobrasmo_id="+ l.getId());
				}
			}
			resultSet = resultTemp;
			resultTemp= null;
			
			for(DTO a : autores){
				for(DTO l : resultSet){
					resultTemp=(manager.findEntity("FROM fontesobrasmo_personagemmo WHERE autorescitados_id =" + a.getId() + "AND fontesobrasmo_id="+ l.getId()));
				}
			}
			resultSet = resultTemp;
			resultTemp= null;
			
			for(DTO a : leitores){
				for(DTO l : resultSet){
					resultTemp=(manager.findEntity("FROM fontesobrasmo_personagemmo WHERE leitores_id =" + a.getId() + "AND fontesobrasmo_id="+ l.getId()));
				}
			}
			resultSet = resultTemp;
			resultTemp= null;
			
			for(DTO o : obrasCitadas){
				for(DTO l : resultSet){
					resultTemp=(manager.findEntity("FROM fontesobrasmo_fontesobrasmmo WHERE obrascitadas_id =" + o.getId() + "AND fontesobrasmo_id="+ l.getId()));
				}
			}
			resultSet = resultTemp;
			resultTemp= null;
			
			for(DTO p : palavraChave){
				for(DTO l : resultSet){
					resultTemp=(manager.findEntity("FROM fontesobrasmo_palavrachavemmo WHERE palavrachave_id =" + p.getId() + "AND fontesobrasmo_id="+ l.getId()));
				}
			}
			resultSet = resultTemp;
			resultTemp= null;
			
			
			if(resultSet == null) {
				throw new FontesObrasNotFoundException ("Fontes/Obras não encontrado.");
			}
			else return (List<DTO>)resultSet;
		
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
		
		
		
	}
	
	
	public List<DTO> findFontesObrasByAll(String titulo, String comentario, String ref_circ_obra, String url, String copias_manuscritas, String traducoes, 
			SimpleDate dataImpressao, String editor, 
			String grupoMovimento, SimpleDate anoInicio_grupomovimento, SimpleDate anoFim_grupomovimento, String descricao_grupomovimento, String local_grupomovimento,
			double latitude_grupomovimento, double longitude_grupomovimento,
			String localimpressao, double latitude_localimpressao, double longitude_localimpressao,
			String classificacao) throws FontesObrasNotFoundException, UnreachableDataBaseException, GroupMovementNotFoundException, LocalNotFoundException, ClassificationNotFoundException{
		//vamos supor que já recebemos a classificação já vem pronta, vamos apenas pesquisar seu id
				
		List<DTO> resultSet = null;
		List<DTO> resultgrupoMovimento = null;
		List<DTO> resultclassificacao = null;

		try {
			
			//novas modificações comecam aqui
			///procura por um grupo movimento
			GrupoMovimentoSearchDAO dao_grupomovimento = new GrupoMovimentoSearchDAO();
			resultgrupoMovimento = dao_grupomovimento.findGrupoMovimentoByAll(grupoMovimento,anoInicio_grupomovimento, anoFim_grupomovimento, descricao_grupomovimento, local_grupomovimento, latitude_grupomovimento, longitude_grupomovimento);
			ClassificacaoSearchDAO dao_classificacao = new ClassificacaoSearchDAO();	
			resultclassificacao = dao_classificacao.findClassificacaoByTipo(classificacao);
			LocalSearchDAO dao_local_impressao = new LocalSearchDAO();
			List<DTO> resultlocal_impressao = dao_local_impressao.findLocalByAll(localimpressao, latitude_localimpressao, longitude_localimpressao);
			//novas modificacoes terminam aqui
			String grupomovimento_query = "( ";
			boolean first = true;
			for(DTO l : resultgrupoMovimento){
				if(! first ){
					grupomovimento_query+=" OR ";
				} 
				first = false;
				grupomovimento_query+="grupomovimento_id = "+l.getId()+" ";
				
			}
			grupomovimento_query+=" ) ";
			//cruzamento de fontes obras e classificacao
			String classificacao_query = "( ";
			first = true;
			for(DTO l : resultclassificacao){
				if(! first ){
					classificacao_query+=" OR ";
				} 
				first = false;
				classificacao_query+=" classificacao_id = "+l.getId()+" ";
				
			}
			classificacao_query+=" ) ";
			
			//cruzamento de fontes obras e local impressao
			String localimpressao_query = "( ";
			first = true;
			for(DTO l :resultlocal_impressao){
				if(! first ){
					localimpressao_query+=" OR ";
				} 
				first = false;
				localimpressao_query+="  localimpressao_id = "+l.getId()+" ";
				
			}
			localimpressao_query+=" ) ";
			
			String query= "from FontesObrasMO where titulo like" + getQueryNormalization("'%" + titulo +"%'")
					+ "AND comentario like" + getQueryNormalization("'%" + comentario +"%'")
					+ "AND refverenciasirculacaoobras like" + getQueryNormalization("'%" + ref_circ_obra +"%'")
					+ "AND url like" + getQueryNormalization("'%" + url +"%'")
					+ "AND copiasmasnuscritas like" + getQueryNormalization("'%" + copias_manuscritas +"%'")
					+ "AND traducoes like" + getQueryNormalization("'%" + traducoes +"%'")
					+ "AND dataimpressao =" + getQueryNormalization("'" + dataImpressao  +"'")
					+ "AND editor like" + getQueryNormalization("'%" + editor +"%'")
					+ "AND " + grupomovimento_query
					+ "AND " + classificacao_query
					+ "AND " + localimpressao_query
				
					+ "order by titulo";
			
			resultSet = manager.findEntity(query);
			
			
			if(resultSet == null) {
				throw new FontesObrasNotFoundException ("Fontes/Obras não encontrado.");
			}
			else return (List<DTO>)resultSet;
		
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
		
		
		
	}
	
	/**
	 * Pesquisa por uma  exata fontesobras pesquisando por "id"
	 * @param id - id da fontes/obras.
	 * @throws UnreachableDataBaseException
	 * @throws FontesObrasNotFoundException
	 */
	public FontesObras findExactFontesObrasById(long id) throws FontesObrasNotFoundException, UnreachableDataBaseException{
		
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("FROM FontesObrasMO"+		
					" where id = "+id+" "+
					" ORDER BY titulo");
			
			if(resultSet == null) {
				throw new FontesObrasNotFoundException ("Fontes Obras não encontrado.");
			}
			else{
				
				return (FontesObras) resultSet.get(0);
			}
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
		
	}
	/**
	 * Pesquisa por uma FontesObras
	 * @param titulo - titulo da Fontes/Obras
	 * @throws UnreachableDataBaseException
	 * @throws FontesObrasNotFoundException
	 */
	public FontesObras findExactFontesObras(String titulo, String editor) throws FontesObrasNotFoundException, UnreachableDataBaseException{
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("FROM FontesObrasMO"+		
					" where titulo = '"+titulo+"' AND editor = '" + editor +"'"+ // "o'neil" > "o''neil"
					" ORDER BY titulo ");
			
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
