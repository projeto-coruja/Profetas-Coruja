package br.unifesp.profetas.business.encontro;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unifesp.profetas.business.AbstractBusiness;
import br.unifesp.profetas.persistence.domain.EncontroDAO;
import br.unifesp.profetas.persistence.model.Encontro;
import br.unifesp.profetas.persistence.model.Personagem;

@Service("mEncontro")
public class ManagementEncontroImpl extends AbstractBusiness implements ManagementEncontro {
	
	@Autowired private EncontroDAO encontroDAO;
	@Autowired private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public Set<Encontro> getEncontrosByPersonagem(Personagem personagem) {
		final String rawQuery = "from Encontro as e where active = :active and (e.personagem1.id = :id or e.personagem2.id = :id)";
		Query query = sessionFactory.getCurrentSession().createQuery(rawQuery);
		query.setLong("id", personagem.getId());
		query.setBoolean("active", true);
		try {
			return new HashSet<Encontro>(query.list());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}