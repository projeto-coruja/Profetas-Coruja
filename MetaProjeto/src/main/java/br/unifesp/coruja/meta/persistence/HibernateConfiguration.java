package br.unifesp.coruja.meta.persistence;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.dialect.PostgreSQL82Dialect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

@Configuration
public class HibernateConfiguration {

	@Value("#{dataSource}")
	private DataSource dataSource;

	@Bean
	public LocalSessionFactoryBean sessionFactoryBean() {
		Properties props = new Properties();
		props.put("hibernate.dialect", PostgreSQL82Dialect.class.getName());
		props.put("hibernate.format_sql", "true");
		props.put("hibernate.hbm2ddl.auto", "create-drop");
		
		//props.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
		props.put("hibernate.connection.driver_class", "org.springframework.jdbc.datasource.DriverManagerDataSource");
		props.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/coruja_metaprojeto");
		props.put("hibernate.connection.username", "coruja");
		props.put("hibernate.connection.password", "coruja");
		
	    LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
		bean.setAnnotatedClasses(new Class[]{Item.class, Order.class});		
		bean.setHibernateProperties(props);
		bean.setDataSource(this.dataSource);
		return bean;
	}

	@Bean
	public HibernateTransactionManager transactionManager() {
		return new HibernateTransactionManager(sessionFactoryBean().getObject());
	}

}
