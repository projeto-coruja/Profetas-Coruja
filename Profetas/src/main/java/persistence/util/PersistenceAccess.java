package persistence.util;

import java.util.List;

import org.hibernate.SessionFactory;
import org.jdto.DTOBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import persistence.dto.DTO;
import persistence.model.EntityModel;

/**
 * A persistence facade which saves, update, find and delete entries from the database. Accepts {@code DTO} instances as input, and convert them
 * to {@code EntityModel} to Hibernate.
 * <br><br>
 * This is probably the most straightforward part of the persistence layer, and its based of the old system in the GraoPara project, so I won't bother commentating.
 * Just be sure that, if any of these throws an exception that is not a {@code HibernateException} or similar, it's a bug in the persistence layer and nowhere else. Probably.
 * 
 * @author Daniel Gracia, Vitor Kawai, Rodney Rick
 * @since Milestone 1
 */
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
