package persistence.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
public class HibernateConfiguration {

	private DataSource dataSource;

	private DataSource getDataSource(Properties props) {
		if (dataSource == null) {
			DriverManagerDataSource newDataSource = new DriverManagerDataSource();
			newDataSource.setDriverClassName(props.getProperty("jdbc.driverClassName"));
			newDataSource.setUrl(props.getProperty("jdbc.url"));
			newDataSource.setUsername(props.getProperty("jdbc.username"));
			newDataSource.setPassword(props.getProperty("jdbc.password"));
			this.dataSource = newDataSource;
		}
		return this.dataSource;
	}
	
	@SuppressWarnings("rawtypes")
	private Class[] generateEntityClassList() {
		ArrayList<Class> class_list = new ArrayList<Class>();
	
		try {
			Resource entity_list = new ClassPathResource("/entity_list.txt");
			BufferedReader reader = new BufferedReader(new FileReader(entity_list.getFile()));
			String text_line = reader.readLine();
			String prefix = null;
			while(text_line != null) {
				if(text_line.startsWith("++")){
					prefix = text_line.substring(2).trim();
				}
				else if(!text_line.startsWith("--")){
					class_list.add(Class.forName(prefix + "." + text_line));
				}
				text_line = reader.readLine();
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return (Class[]) class_list.toArray(new Class[class_list.size()]);
	}

	@Bean
	public LocalSessionFactoryBean sessionFactoryBean() throws IOException {
		Properties jdbc_props = PropertiesLoaderUtils.loadProperties(new ClassPathResource("/jdbc.properties"));
		Properties hibernate_props = PropertiesLoaderUtils.loadProperties(new ClassPathResource("/hibernate.properties"));
		
		LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
		bean.setAnnotatedClasses(generateEntityClassList());
		bean.setHibernateProperties(hibernate_props);
		bean.setDataSource(getDataSource(jdbc_props));
		return bean;
	}

	@Bean
	public HibernateTransactionManager transactionManager() throws IOException {
		return new HibernateTransactionManager(sessionFactoryBean().getObject());
	}

}
