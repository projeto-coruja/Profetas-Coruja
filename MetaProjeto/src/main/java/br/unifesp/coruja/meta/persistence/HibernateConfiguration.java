package br.unifesp.coruja.meta.persistence;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
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

	@Bean
	public LocalSessionFactoryBean sessionFactoryBean() throws IOException {
		Properties jdbc_props = PropertiesLoaderUtils.loadProperties(new ClassPathResource("/jdbc.properties"));
		Properties hibernate_props = PropertiesLoaderUtils.loadProperties(new ClassPathResource("/hibernate.properties"));
		
		LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
		bean.setAnnotatedClasses(new Class[] { Item.class, Order.class, User.class, Role.class });
		bean.setHibernateProperties(hibernate_props);
		bean.setDataSource(getDataSource(jdbc_props));
		return bean;
	}

	@Bean
	public HibernateTransactionManager transactionManager() throws IOException {
		return new HibernateTransactionManager(sessionFactoryBean().getObject());
	}

}
