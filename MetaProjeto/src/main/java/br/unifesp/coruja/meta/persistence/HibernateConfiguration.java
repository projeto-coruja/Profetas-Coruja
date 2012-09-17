package br.unifesp.coruja.meta.persistence;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.dialect.H2Dialect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;

@Configuration
public class HibernateConfiguration {

	@Value("#{dataSource}")
	private DataSource dataSource;

	@Bean
	public AnnotationSessionFactoryBean sessionFactoryBean() {
		Properties props = new Properties();
		props.put("hibernate.dialect", H2Dialect.class.getName());
		props.put("hibernate.format_sql", "true");
		
		
//		props.put("hibernate.hbm2ddl.auto", "create-drop");
//		props.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
//	    props.put("hibernate.connection.username", "coruja");
//	    props.put("hibernate.connection.password", "coruja");
//	    props.put("hibernate.connection.url", "jdbc:mysql://localhost:5432/coruja_metaprojeto");

	    
		AnnotationSessionFactoryBean bean = new AnnotationSessionFactoryBean();
		bean.setAnnotatedClasses(new Class[]{Item.class, Order.class});		
		bean.setHibernateProperties(props);
		bean.setDataSource(this.dataSource);
		bean.setSchemaUpdate(true);
		return bean;
	}

	@Bean
	public HibernateTransactionManager transactionManager() {
		return new HibernateTransactionManager(sessionFactoryBean().getObject());
	}

}
