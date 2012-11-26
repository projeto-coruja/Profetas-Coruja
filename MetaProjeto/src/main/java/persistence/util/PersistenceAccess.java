package persistence.util;

import java.util.List;

import org.hibernate.SessionFactory;
import org.jdto.DTOBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import persistence.dto.DTO;
import persistence.model.EntityModel;


public class PersistenceAccess {
	
	@Autowired
	private SessionFactory sf;
	
	@Autowired
	private DTOBinder binder;
	
	@Autowired
	private DTOUtility du;
	
	@Transactional
	public void saveEntity(DTO dto) throws IllegalArgumentException, UpdateEntityException, InstantiationException, IllegalAccessException {
		EntityModel em = du.createEmptyEntityInstanceFromDTOType(dto);
		du.updateEntityFromDTO(em, dto);
		sf.getCurrentSession().save(em);
		dto.setId(em.getId());
	}
	
	@Transactional
	public void updateEntity(DTO dto) throws IllegalArgumentException, UpdateEntityException {
		Object entity = sf.getCurrentSession().load(du.findEntityClassForDTO(dto), dto.getId());
		du.updateEntityFromDTO((EntityModel) entity, dto);
		sf.getCurrentSession().merge(entity);
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<DTO> findEntity(String query) {
		List<Object> resultSet = sf.getCurrentSession().createQuery(query).list();
		if(resultSet.isEmpty()) return null;
		else{
			List<DTO> dtoSet = binder.bindFromBusinessObjectList(du.findDTOClassForEntity(resultSet.get(0)), resultSet);
			resultSet = null;
			return dtoSet;
		}
	}
	
	@Transactional
	public void deleteEntity(DTO dto) {
		Object dead = sf.getCurrentSession().load(du.findEntityClassForDTO(dto), dto.getId());
		sf.getCurrentSession().delete(dead);
	}

}
