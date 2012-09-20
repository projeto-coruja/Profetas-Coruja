import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class TestPersistence {
	
	@Autowired
	private SessionFactory sessionFactory;
	
    @Autowired
    private ApplicationContext applicationContext;

	@SuppressWarnings("unchecked")
	@Transactional
	@Test
	public void test() {
		Session s = sessionFactory.getCurrentSession();
		List<String> old_role_list = s.createQuery("select rolename from Role").list();
		SQLQuery populate_query = s.createSQLQuery("INSERT INTO user_roles(id, rolename) VALUES (:p_id, :p_rolename);");
		long counter = 1;
		
		try {
			Resource role_list = new ClassPathResource("/role_list.txt");
			BufferedReader reader = new BufferedReader(new FileReader(role_list.getFile()));
			String rolename = reader.readLine();
			while(rolename != null) {
				if(!rolename.startsWith("--") || 
						(old_role_list != null && old_role_list.contains(rolename))){
					populate_query.setLong("p_id", counter);
					populate_query.setString("p_rolename", rolename);
					populate_query.executeUpdate();
					counter++;
				}
				reader.readLine();
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: role list not found");
			fail();
		} catch (IOException e) {
			System.out.println("ERROR: failure trying to read file");
			fail();
		}
	}

}
