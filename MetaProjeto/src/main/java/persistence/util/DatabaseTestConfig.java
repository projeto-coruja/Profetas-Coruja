package persistence.util;

import general.SimpleDate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import persistence.dto.DTO;
import persistence.dto.Group;
import persistence.dto.Permission;
import persistence.dto.User;


public class DatabaseTestConfig {
	
	@Autowired
	private PersistenceAccess pa;
		
	public void initDB() {
		populatePermissionList();
		populateUserAndGroupTables();
	}
	
	
	private void populatePermissionList() {
		List<DTO> old_role_list = pa.findEntity("from PermissionMO");
		List<String> str_old_role_list = new ArrayList<String>();
		if(old_role_list != null) {
			for(DTO d : old_role_list) {
				str_old_role_list.add(((Permission)d).getRolename());
			}
		}
		
		try {
			Resource role_list = new ClassPathResource("/role_list.txt");
			BufferedReader reader = new BufferedReader(new FileReader(role_list.getFile()));
			String rolename = reader.readLine();
			while(rolename != null) {
				if(!rolename.startsWith("--") || str_old_role_list.contains(rolename)){
					pa.saveEntity(new Permission(rolename));
				}
				rolename = reader.readLine();
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: role list not found");
		} catch (IOException e) {
			System.out.println("ERROR: failure trying to read file");
		} catch (IllegalArgumentException e) {
			System.err.println("DTO-to-Entity dynamic generation error");
			e.printStackTrace();
		} catch (InstantiationException e) {
			System.err.println("DTO-to-Entity dynamic generation error");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.err.println("DTO-to-Entity dynamic generation error");
			e.printStackTrace();
		} catch (UpdateEntityException e) {
			System.err.println("DTO-to-Entity dynamic generation error");
			e.printStackTrace();
		}
	}
	
	private void populateUserAndGroupTables() {
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
		
		try {
			pa.saveEntity(g_admin);
			pa.saveEntity(g_user);
			User u = new User("Hueho", "hueho@gmail.com", "74234e98afe7498fb5daf1f36ac2d78acc339464f950703b8c019892f982b90b", g_admin, true, SimpleDate.parse("2012/12/21"));
			pa.saveEntity(u);
		} catch (IllegalArgumentException e) {
			System.err.println("DTO-to-Entity dynamic generation error");
			e.printStackTrace();
		} catch (InstantiationException e) {
			System.err.println("DTO-to-Entity dynamic generation error");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.err.println("DTO-to-Entity dynamic generation error");
			e.printStackTrace();
		} catch (UpdateEntityException e) {
			System.err.println("DTO-to-Entity dynamic generation error");
			e.printStackTrace();
		}
				
	}

}
