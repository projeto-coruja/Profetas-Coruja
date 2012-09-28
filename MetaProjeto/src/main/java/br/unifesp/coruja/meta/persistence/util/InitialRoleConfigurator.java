package br.unifesp.coruja.meta.persistence.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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

import br.unifesp.coruja.meta.persistence.dto.DTO;
import br.unifesp.coruja.meta.persistence.dto.Group;
import br.unifesp.coruja.meta.persistence.dto.Permission;
import br.unifesp.coruja.meta.persistence.dto.User;

@Component
public class InitialRoleConfigurator implements ApplicationListener<ContextRefreshedEvent> {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private PersistenceAccess pa;
	
	@Transactional
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		populatePermissionList();
		try {
			populateUserAndGroupTables();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UpdateEntityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public void populatePermissionList() {
		Session s = sessionFactory.getCurrentSession();
		List<String> old_role_list = s.createQuery("select rolename from PermissionMO").list();
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
		} catch (IOException e) {
			System.out.println("ERROR: failure trying to read file");
		}
	}
	
	@Transactional
	public void populateUserAndGroupTables() throws IllegalArgumentException, InstantiationException, IllegalAccessException, UpdateEntityException {
		Group g_user = null, g_admin = null;
		
		List<DTO> list_roles = pa.findEntity("from PermissionMO");
		for(DTO dto : list_roles) {
			Permission p = (Permission) dto;
			if(p.getRolename().equals("ROLE_USER")) {
				g_user = new Group("users", new ArrayList<Permission>());
				g_user.getRoles().add(p);
			}
			else if(p.getRolename().equals("ROLE_ADMIN")) {
				g_admin = new Group("admins", new ArrayList<Permission>());
				g_admin.getRoles().add(p);
			}
		}
		
		pa.saveEntity(g_admin);
		pa.saveEntity(g_user);
		
		User u = new User("Hueho", "hueho@gmail.com", "senha", g_admin, true, new Date());
		pa.saveEntity(u);
				
	}

}
