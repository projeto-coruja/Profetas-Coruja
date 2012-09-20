package br.unifesp.coruja.meta.persistence.auth;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class InitialRoleConfigurator implements ApplicationListener<ContextRefreshedEvent> {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(populateRoleList() == false)
			System.out.println("PANIC");
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public boolean populateRoleList() {
		Session s = sessionFactory.getCurrentSession();
		List<String> old_role_list = s.createQuery("select rolename from Role").list();
		SQLQuery populate_query = s.createSQLQuery("INSERT INTO user_roles(id, rolename) VALUES (:p_id, :p_rolename);");
		long counter = 1;
		
		try {
			Resource role_list = new ClassPathResource("/role_list.txt");
			BufferedReader reader = new BufferedReader(new FileReader(role_list.getFile()));
			String rolename = reader.readLine();
			while(rolename != null) {
				if(!rolename.startsWith("--") || old_role_list.contains(rolename)){
					populate_query.setLong("p_id", counter);
					populate_query.setString("p_rolename", rolename);
					populate_query.executeUpdate();
					counter++;
				}
				rolename = reader.readLine();
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: role list not found");
			return false;
		} catch (IOException e) {
			System.out.println("ERROR: failure trying to read file");
			return false;
		}
		
		return true;
	}

}
