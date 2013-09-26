package business.DAO.search;

import java.util.List;

import datatype.SimpleDate;
import persistence.EntityManager;
import persistence.model.IdentifiedEntity;
import persistence.model.FontesObras;
import persistence.util.DataAccessLayerException;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.model.ClassificationNotFoundException;
import business.exceptions.model.GroupMovementNotFoundException;
import business.exceptions.model.LocalNotFoundException;
import business.exceptions.search.business.DAO.search.FontesObrasNotFoundException;

public class FontesObrasSearchDAO {
	private EntityManager manager;
	
	public FontesObrasSearchDAO(){
		manager= new EntityManager();
	}
	private String getQueryNormalization(String var){
		var.replaceAll("'", "''");
				
		return "LOWER(TRANSLATE("+var+",'áàãâäÁÀÃÂÄéèêëÉÈÊËíìîïÍÌÎÏóòõôöÓÒÕÔÖúùûüÚÙÛÜñÑçÇÿýÝ','aaaaaAAAAAeeeeEEEEiiiiIIIIoooooOOOOOuuuuUUUUnNcCyyY'))";
	}
	 public List<IdentifiedEntity> findFontesObrasGeneric(String string) throws FontesObrasNotFoundException, UnreachableDataBaseException{
		 List <IdentifiedEntity> resultSet = null;
		 try {
			 String query= "from FontesObrasMO where titulo like" + getQueryNormalization("'%" + string +"%'")
						+ "OR comentario like" + getQueryNormalization("'%" +string +"%'")
						+ "OR refverenciasirculacaoobras like" + getQueryNormalization("'%" +string+"%'")
						+ "OR url like" + getQueryNormalization("'%" + string +"%'")
						+ "OR copiasmasnuscritas like" + getQueryNormalization("'%" + string +"%'")
						+ "OR traducoes like" + getQueryNormalization("'%" + string +"%'")
						+ "OR editor like" + getQueryNormalization("'%" + string +"%'")
						+ "order by titulo";
				
			resultSet = manager.find(query);
			
			if(resultSet==null){
				throw new FontesObrasNotFoundException ("Fontes/Obras não encontrado.");
			}else return resultSet;
		}catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
		 
	 }
	
	
	public List<IdentifiedEntity> mainSearchAND(String titulo, String comentario, String ref_circ_obra, String url, String copias_manuscritas, String traducoes, 
			SimpleDate dataImpressao, String editor, 
			String grupoMovimento, SimpleDate anoInicio_grupomovimento, SimpleDate anoFim_grupomovimento, String descricao_grupomovimento, String local_grupomovimento,
			double latitude_grupomovimento, double longitude_grupomovimento,
			String localimpressao, double latitude_localimpressao, double longitude_localimpressao,
			String classificacao, List<IdentifiedEntity> palavraChave, List<IdentifiedEntity> obrasCitadas, List<IdentifiedEntity> leitores, List<IdentifiedEntity> personagens,  List<IdentifiedEntity> autores) throws FontesObrasNotFoundException, UnreachableDataBaseException, GroupMovementNotFoundException, LocalNotFoundException, ClassificationNotFoundException{
		//vamos supor que já recebemos a classificação já vem pronta, vamos apenas pesquisar seu id
		dataImpressao.format();
		anoInicio_grupomovimento.format();
		anoFim_grupomovimento.format();
		
		List<IdentifiedEntity> resultSet = null;
		List<IdentifiedEntity> resultgrupoMovimento = null;
		List<IdentifiedEntity> resultclassificacao = null;
		List<IdentifiedEntity> resultTemp =null;
		try {
			//novas modificações comecam aqui
			///procura por um grupo movimento
			GrupoMovimentoSearchDAO dao_grupomovimento = new GrupoMovimentoSearchDAO();	
			resultgrupoMovimento = dao_grupomovimento.findGrupoMovimentoByAll(grupoMovimento,anoInicio_grupomovimento, anoFim_grupomovimento, descricao_grupomovimento, local_grupomovimento, latitude_grupomovimento, longitude_grupomovimento);
			ClassificacaoSearchDAO dao_classificacao = new ClassificacaoSearchDAO();	
			resultclassificacao = dao_classificacao.findClassificacaoByTipo(classificacao);
			LocalSearchDAO dao_local_impressao = new LocalSearchDAO();
			List<IdentifiedEntity> resultlocal_impressao = dao_local_impressao.findLocalByAll(localimpressao, latitude_localimpressao, longitude_localimpressao);
			//novas modificacoes terminam aqui
			
			//resultadoTemp
			//cruzamento de fontes obras e grupo movimento
			String grupomovimento_query = "( ";
			boolean first = true;
			for(IdentifiedEntity l : resultgrupoMovimento){
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
			for(IdentifiedEntity l : resultclassificacao){
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
			for(IdentifiedEntity l :resultlocal_impressao){
				if(! first ){
					localimpressao_query+=" OR ";
				} 
				first = false;
				localimpressao_query+="  localimpressao_id = "+l.getId()+" ";
				
			}
			localimpressao_query+=" ) ";
			
			String query= "from FontesObrasMO where titulo like " + getQueryNormalization("'%" + titulo +"%'")
					+ " AND comentario like " + getQueryNormalization("'%" + comentario +"%'")
					+ " AND refverenciasirculacaoobras like " + getQueryNormalization("'%" + ref_circ_obra +"%'")
					+ " AND url like" + getQueryNormalization("'%" + url +"%'")
					+ " AND copiasmasnuscritas like " + getQueryNormalization("'%" + copias_manuscritas +"%'")
					+ " AND traducoes like " + getQueryNormalization("'%" + traducoes +"%'")
					+ " AND dataimpressao =" + getQueryNormalization("'" + dataImpressao  +"'")
					+ " AND editor like " + getQueryNormalization("'%" + editor +"%'")
					+ " AND " + grupomovimento_query
					+ " AND " + classificacao_query
					+ " AND " + localimpressao_query
				
					+ "order by titulo";
			
			resultSet = manager.find(query);
			
			
			
			//montar a query de personagem
			for(IdentifiedEntity p : personagens){
				for(IdentifiedEntity l : resultSet){
					//resultTemp= manager.findEntity("FROM fontesobrasmo_personagemmo WHERE personagens_id =" + p.getId() + "AND fontesobrasmo_id="+ l.getId());
					resultTemp= manager.find("FROM FontesObras fontes INNER JOIN fontes.personagens list WHERE FontesObrasMO.id =" + p.getId() + "AND list.id="+ l.getId());
				}
			}
			resultSet = resultTemp;
			resultTemp= null;
			
			for(IdentifiedEntity a : autores){
				for(IdentifiedEntity l : resultSet){
					resultTemp=(manager.find("FROM fontesobrasmo_personagemmo WHERE autorescitados_id =" + a.getId() + "AND fontesobrasmo_id="+ l.getId()));
				}
			}
			resultSet = resultTemp;
			resultTemp= null;
			
			for(IdentifiedEntity a : leitores){
				for(IdentifiedEntity l : resultSet){
					resultTemp=(manager.find("FROM fontesobrasmo_personagemmo WHERE leitores_id =" + a.getId() + "AND fontesobrasmo_id="+ l.getId()));
				}
			}
			resultSet = resultTemp;
			resultTemp= null;
			
			for(IdentifiedEntity o : obrasCitadas){
				for(IdentifiedEntity l : resultSet){
					resultTemp=(manager.find("FROM fontesobrasmo_fontesobrasmmo WHERE obrascitadas_id =" + o.getId() + "AND fontesobrasmo_id="+ l.getId()));
				}
			}
			resultSet = resultTemp;
			resultTemp= null;
			
			for(IdentifiedEntity p : palavraChave){
				for(IdentifiedEntity l : resultSet){
					resultTemp=(manager.find("FROM fontesobrasmo_palavrachavemmo WHERE palavrachave_id =" + p.getId() + "AND fontesobrasmo_id="+ l.getId()));
				}
			}
			resultSet = resultTemp;
			resultTemp= null;
			
			
			if(resultSet == null) {
				throw new FontesObrasNotFoundException ("Fontes/Obras não encontrado.");
			}
			else return (List<IdentifiedEntity>)resultSet;
		
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
		
		
		
	}
	
	
	public List<IdentifiedEntity> findFontesObrasByAll(String titulo, String comentario, String ref_circ_obra, String url, String copias_manuscritas, String traducoes, 
			SimpleDate dataImpressao, String editor, 
			String grupoMovimento, SimpleDate anoInicio_grupomovimento, SimpleDate anoFim_grupomovimento, String descricao_grupomovimento, String local_grupomovimento,
			double latitude_grupomovimento, double longitude_grupomovimento,
			String localimpressao, double latitude_localimpressao, double longitude_localimpressao,
			String classificacao) throws FontesObrasNotFoundException, UnreachableDataBaseException, GroupMovementNotFoundException, LocalNotFoundException, ClassificationNotFoundException{
		//vamos supor que já recebemos a classificação já vem pronta, vamos apenas pesquisar seu id
				
		List<IdentifiedEntity> resultSet = null;
		List<IdentifiedEntity> resultgrupoMovimento = null;
		List<IdentifiedEntity> resultclassificacao = null;

		try {
			
			//novas modificações comecam aqui
			///procura por um grupo movimento
			GrupoMovimentoSearchDAO dao_grupomovimento = new GrupoMovimentoSearchDAO();
			resultgrupoMovimento = dao_grupomovimento.findGrupoMovimentoByAll(grupoMovimento,anoInicio_grupomovimento, anoFim_grupomovimento, descricao_grupomovimento, local_grupomovimento, latitude_grupomovimento, longitude_grupomovimento);
			ClassificacaoSearchDAO dao_classificacao = new ClassificacaoSearchDAO();	
			resultclassificacao = dao_classificacao.findClassificacaoByTipo(classificacao);
			LocalSearchDAO dao_local_impressao = new LocalSearchDAO();
			List<IdentifiedEntity> resultlocal_impressao = dao_local_impressao.findLocalByAll(localimpressao, latitude_localimpressao, longitude_localimpressao);
			//novas modificacoes terminam aqui
			String grupomovimento_query = "( ";
			boolean first = true;
			for(IdentifiedEntity l : resultgrupoMovimento){
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
			for(IdentifiedEntity l : resultclassificacao){
				if(! first ){
					classificacao_query+=" OR ";
				} 
				first = false;
				classificacao_query+=" classificao_id = "+l.getId()+" ";
				
			}
			classificacao_query+=" ) ";
			
			//cruzamento de fontes obras e local impressao
			String localimpressao_query = "( ";
			first = true;
			for(IdentifiedEntity l :resultlocal_impressao){
				if(! first ){
					localimpressao_query+=" OR ";
				} 
				first = false;
				localimpressao_query+="  localimpressao_id = "+l.getId()+" ";
				
			}
			localimpressao_query+=" ) ";
			
			String query= "from FontesObrasMO where titulo like " + (titulo == null || titulo.equals("")? "'%_%'":getQueryNormalization("'%" + titulo +"%'")) 
					+ (comentario == null || comentario.equals("")?"":" AND comentario like " + getQueryNormalization("'%" + comentario +"%'"))
					+ ( ref_circ_obra ==null ||  ref_circ_obra.equals(" ")?"":" AND refverenciasirCulacaoObra like " + getQueryNormalization("'%" + ref_circ_obra +"%'"))
					+ (url== null|| url.equals(" ")?"":" AND url like " + getQueryNormalization("'%" + url +"%'"))
					+ (copias_manuscritas ==null || copias_manuscritas.equals("")?"": "AND copiasmasnuscritas like " + getQueryNormalization("'%" + copias_manuscritas +"%'"))
					+ (traducoes==null|| traducoes.equals(" ")?"": " AND traducoes like " + getQueryNormalization("'%" + traducoes +"%'"))
					+ (dataImpressao ==null || dataImpressao.equals(" ")?"":" AND dataimpressao =" + getQueryNormalization("'" + dataImpressao  +"'"))
					+ (editor == null|| editor.equals(" ")?"":" AND editor like " + getQueryNormalization("'%" + editor +"%'"))
					+ (grupomovimento_query ==null|| grupomovimento_query.equals("(  ) ")?"":" AND " + grupomovimento_query)
					+ (classificacao_query==null||classificacao_query.equals("(  ) ")?"":" AND " + classificacao_query)
					+ (localimpressao_query==null||localimpressao_query.equals("(  ) ")?"":" AND " + localimpressao_query)
				
					+ "order by titulo";
			
			resultSet = manager.find(query);
			
			
			if(resultSet == null) {
				throw new FontesObrasNotFoundException ("Fontes/Obras não encontrado.");
			}
			else return (List<IdentifiedEntity>)resultSet;
		
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
		
		List<IdentifiedEntity> resultSet = null;
		try {
			resultSet = manager.find("FROM FontesObras"+		
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
		List<IdentifiedEntity> resultSet = null;
		try {
			resultSet = manager.find("FROM FontesObras"+		
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
	public List<IdentifiedEntity> findFontesObrasByTitulo(String titulo) throws  UnreachableDataBaseException, FontesObrasNotFoundException {
		List<IdentifiedEntity> resultSet = null;
		try {
			resultSet = manager.find("from FontesObrasMO where titulo like '%" + titulo +"%' "
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
	
	public List<IdentifiedEntity> findFontesObrasByComentario(String comentario) throws  UnreachableDataBaseException, FontesObrasNotFoundException {
		List<IdentifiedEntity> resultSet = null;
		try {
			resultSet = manager.find("from FontesObrasMO where comentario like '%" +comentario +"%' "
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
	public List<IdentifiedEntity> findAllFontesObras() throws  UnreachableDataBaseException, FontesObrasNotFoundException  {
		List<IdentifiedEntity> resultSet = null;
		try {
			resultSet = manager.find("from FontesObrasMO order by titulo");
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
	public List<IdentifiedEntity> findFontesObrasByURL(String url) throws  UnreachableDataBaseException, FontesObrasNotFoundException  {
		List<IdentifiedEntity> resultSet = null;
		try {
			resultSet = manager.find("from FontesObrasMO where url like '%" + url +"%' "
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
	public List<IdentifiedEntity> findFontesObrasByCopiasManuscritas(String copiasManuscritas) throws  UnreachableDataBaseException, FontesObrasNotFoundException  {
		List<IdentifiedEntity> resultSet = null;
		try {
			resultSet = manager.find("from FontesObrasMO where copiasManuscritas like '%" + copiasManuscritas +"%' "
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
	public List<IdentifiedEntity> findFontesObrasByTraducoes(String traducoes) throws  UnreachableDataBaseException, FontesObrasNotFoundException  {
		List<IdentifiedEntity> resultSet = null;
		try {
			resultSet = manager.find("from FontesObrasMO where traducoes like '%" + traducoes +"%' "
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
	public List<IdentifiedEntity> findFontesObrasByDataImpressao(SimpleDate dataImpressao) throws  UnreachableDataBaseException, FontesObrasNotFoundException  {
		List<IdentifiedEntity> resultSet = null;
		try {
			resultSet = manager.find("from FontesObrasMO where dataImpressao like '%" + dataImpressao +"%' "
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
	public List<IdentifiedEntity> findFontesObrasByEditor(String editor) throws  UnreachableDataBaseException, FontesObrasNotFoundException  {
		List<IdentifiedEntity> resultSet = null;
		try {
			resultSet = manager.find("from FontesObrasMO where editor like '%" + editor +"%' "
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
	public List<IdentifiedEntity> findFontesObrasByClassificacao(long id) throws  UnreachableDataBaseException, FontesObrasNotFoundException  {
		List<IdentifiedEntity> resultSet = null;
		try {
			resultSet = manager.find("from FontesObrasMO where classificao_id = '" + id +"' "
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
	public List<IdentifiedEntity> findFontesObrasByGrupoMovimento(long id) throws  UnreachableDataBaseException, FontesObrasNotFoundException  {
		List<IdentifiedEntity> resultSet = null;
		try {
			resultSet = manager.find("from FontesObrasMO where grupomovimento_id = '" + id +"' "
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
	public List<IdentifiedEntity> findFontesObrasByLocal(long id) throws  UnreachableDataBaseException, FontesObrasNotFoundException  {
		List<IdentifiedEntity> resultSet = null;
		try {
			resultSet = manager.find("from FontesObrasMO where localimpressao_id = '" + id +"' "
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
