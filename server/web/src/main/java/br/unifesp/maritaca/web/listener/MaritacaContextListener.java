package br.unifesp.maritaca.web.listener;

import java.io.File;
import java.util.HashMap;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;

public class MaritacaContextListener implements ServletContextListener {
	
	private static final String PARAM_NAME_CLUSTER	= "cluster";
	private static final String PARAM_NAME_KEYSPACE	= "keyspace";
	private static final String PARAM_NAME_LOG4J		= "log4j-properties-location";

	//private MaritacaInitDAO maritacaInitDAO;	

	public MaritacaContextListener() {
		System.out.println("MaritacaContextListener");
		//this.maritacaInitDAO = new MaritacaInitDAO();
	}
	
	@Override
	public void contextInitialized(ServletContextEvent arg) {
		System.out.println("contextInitialized");		
		initLogger(arg.getServletContext().getInitParameter(PARAM_NAME_LOG4J), arg.getServletContext().getRealPath("/"));
		HashMap<String, String> params = new HashMap<String, String>();
		params.put(PARAM_NAME_CLUSTER, arg.getServletContext().getInitParameter(PARAM_NAME_CLUSTER));
		params.put(PARAM_NAME_KEYSPACE, arg.getServletContext().getInitParameter(PARAM_NAME_KEYSPACE));
		//maritacaInitDAO.createAllEntities(params);
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg) {
		System.out.println("contextDestroyed");
		//maritacaInitDAO.destroyEntityManager();
	}
	
	private void initLogger(String log4jLocation, String webAppPath) {
		if (log4jLocation != null) {
			String log4jProp = webAppPath + log4jLocation;
			File filePropLog4j = new File(log4jProp);
			if (filePropLog4j.exists()) {
				PropertyConfigurator.configure(log4jProp);
				return;
			}
		}
		System.err.println("Not possible to start the logging system from properties file");
		System.err.println("Logging basic configuration");
		BasicConfigurator.configure();
	}	
}