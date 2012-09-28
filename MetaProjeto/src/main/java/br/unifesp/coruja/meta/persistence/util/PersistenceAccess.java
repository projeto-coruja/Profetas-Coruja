package br.unifesp.coruja.meta.persistence.util;

import java.util.List;

import org.hibernate.SessionFactory;
import org.jdto.DTOBinder;
import org.springframework.beans.factory.annotation.Autowired;

import br.unifesp.coruja.meta.persistence.dto.DTO;
import br.unifesp.coruja.meta.persistence.model.EntityModel;

public class PersistenceAccess {
	
	@Autowired
	private SessionFactory sf;
	
	@Autowired
	private DTOBinder binder;
	
	@Autowired
	private DTOUtility du;
	
	@SuppressWarnings({"unchecked"})
	public void saveEntity(DTO dto) {
		EntityModel em = binder.extractFromDto(du.findEntityClassForDTO(dto), dto);
		sf.getCurrentSession().save(em);
		dto.setId(em.getId());
	}
	
	public void updateEntity(DTO dto) throws IllegalArgumentException, UpdateEntityException {
		Object entity = sf.getCurrentSession().load(du.findEntityClassForDTO(dto), dto.getId());
		du.updateEntityFromDTO((EntityModel) entity, dto);
		sf.getCurrentSession().update(entity);
	}
	
	@SuppressWarnings("unchecked")
	public List<DTO> findEntity(String query) {
		List<Object> resultSet = sf.getCurrentSession().createQuery(query).list();
		List<DTO> dtoSet = binder.bindFromBusinessObjectList(du.findDTOClassForEntity(resultSet.get(0)), resultSet);
		resultSet = null;
		return dtoSet;
	}
	
	public void deleteEntity(DTO dto) {
		Object dead = sf.getCurrentSession().load(du.findEntityClassForDTO(dto), dto.getId());
		sf.getCurrentSession().delete(dead);
	}

}
