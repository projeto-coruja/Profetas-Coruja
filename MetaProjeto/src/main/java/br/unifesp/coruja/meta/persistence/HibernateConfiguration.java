package br.unifesp.coruja.meta.persistence;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.dialect.PostgreSQL82Dialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

//import org.springframework.beans.factory.annotation.Value;

@Configuration
public class HibernateConfiguration {

	// @Value("#{dataSource}")
	private DataSource dataSource;

	private DataSource getDataSource() {
		if (dataSource == null) {
			DriverManagerDataSource newDataSource = new DriverManagerDataSource();
			newDataSource.setDriverClassName("org.postgresql.Driver");
			newDataSource.setUrl("jdbc:postgresql://localhost:5432/coruja_metaprojeto");
			newDataSource.setUsername("coruja");
			newDataSource.setPassword("coruja");
			this.dataSource = newDataSource;
		}
		return this.dataSource;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactoryBean() {
		Properties props = new Properties();
		props.put("hibernate.dialect", PostgreSQL82Dialect.class.getName());
		props.put("hibernate.format_sql", "true");
		props.put("hibernate.hbm2ddl.auto", "create-drop");
		
		LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
		bean.setAnnotatedClasses(new Class[] { Item.class, Order.class });
		bean.setHibernateProperties(props);
		bean.setDataSource(getDataSource());
		return bean;
	}

	@Bean
	public HibernateTransactionManager transactionManager() {
		return new HibernateTransactionManager(sessionFactoryBean().getObject());
	}

}
