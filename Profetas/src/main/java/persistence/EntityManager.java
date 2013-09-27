package persistence;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import persistence.model.EntityModel;
import persistence.util.DataAccessLayerException;
import persistence.util.PersistenceUtility;

/**
 * Classe de acesso a persistencia. 
 */
public class EntityManager {
	
	private Session session;
	
	private Transaction transaction;
	
	public EntityManager(){
		PersistenceUtility.buildIfNeeded();
	}
	
	/**
	 * Salva um objeto no banco de dados.
	 * @param obj - EntityModel
	 * @throws DataAccessLayerException
	 */
	public void save(EntityModel obj) throws DataAccessLayerException{
		try{
			startOperation();
			session.save(obj);
			transaction.commit();
		}catch(HibernateException e){
			handleException(e);
		}finally{
			finishOperation();
		}
	}
	
	/**
	 * Atualiza o objeto no banco de dados.
	 * @param obj - EntityModel
	 * @throws DataAccessLayerException
	 */
	public void update(EntityModel obj) throws DataAccessLayerException{
		try{
			startOperation();
			session.merge(obj);
			transaction.commit();
		}catch(HibernateException e){
			handleException(e);
		}finally{
			finishOperation();
		}
	}
	
	/**
	 * Deleta o objeto do banco de dados.
	 * @param obj - EntityModel
	 * @throws DataAccessLayerException
	 */
	public void delete(EntityModel obj) throws DataAccessLayerException{
		try{
			startOperation();
			session.delete(obj);
			transaction.commit();
		}catch(HibernateException e){
			handleException(e);
		}finally{
			finishOperation();
		}
	}
	
	/**
	 * Busca um objeto a partir do id.
	 * @param table - Classe que o objeto faz parte
	 * @param id - ID do objeto.
	 * @return Objeto instacia de EntityModel.
	 * @throws DataAccessLayerException
	 */
	@SuppressWarnings({ "unchecked" })
	public <T extends EntityModel> T find(Class<T> table, Serializable id) throws DataAccessLayerException{
		T obj = null;
		try{
			startOperation();
			obj = (T) session.get(table, id);
			transaction.commit();
		}catch(HibernateException e){
			handleException(e);
		}finally{
			finishOperation();
		}
		return obj;
	}
	
	/**
	 * Busca um objeto a partir do id natural.
	 * @param table - Classe que o objeto faz parte
	 * @param id - ID natural do objeto.
	 * @return Objeto instacia de EntityModel.
	 * @throws DataAccessLayerException
	 */
	@SuppressWarnings({ "unchecked" })
	public <T extends EntityModel> T findByNaturalId(Class<T> table, Serializable id) throws DataAccessLayerException{
		T obj = null;
		try{
			startOperation();
			obj = (T) session.bySimpleNaturalId(table).load(id);
			transaction.commit();
		}catch(HibernateException e){
			handleException(e);
		}finally{
			finishOperation();
		}
		return obj;
	}

	/**
	 * Busca através de query.
	 * @param criteria - Query SQL.
	 * @return Resultado em forma de lista de objetos.
	 * @throws DataAccessLayerException
	 */
	@SuppressWarnings({ "unchecked"})
	public <T extends EntityModel> List<T> find(String criteria) throws DataAccessLayerException{
		List<Object> obj = null;
		try{
			startOperation();
			Query query = session.createQuery(criteria);
			obj = query.list();
			transaction.commit();
		}catch(HibernateException e){
			handleException(e);
		}finally{
			finishOperation();
		}
		return (List<T>) obj;
	}
	
	/**
	 * Finaliza persistencia.
	 */
	public void finishOperation() {
		PersistenceUtility.close(session);
	}

	/**
	 * Inicia persistencia
	 */
	private void startOperation() {
		session = PersistenceUtility.openSession();
		transaction = session.beginTransaction();
	}
	
	private void handleException(HibernateException e) throws DataAccessLayerException {
		e.printStackTrace();
		PersistenceUtility.rollback(transaction);
		throw new DataAccessLayerException(e);
	}
	
	/**
	 *
	 * @param table	- Tabela que será feito a pesquisa. 
	 * @param criteria - String do critério (ex: profile = 'admin'). Usar "1=1" para retornar o número de entradas.
	 * @return	Quantidade de entradas correspondente ao critério.
	 */
	public Long count(String table, String criteria){
		Query query = null;
		Long result = null;
		try{
			startOperation();
			String queryString = "select count(*) from " + table + " where " + criteria;
			query = session.createQuery(queryString);
			result = (Long) query.list().get(0);
			transaction.commit();
		}catch(HibernateException e){
			handleException(e);
		}finally{
			finishOperation();
		}
		return result;
	}

}
