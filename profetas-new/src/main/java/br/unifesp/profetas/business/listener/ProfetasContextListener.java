package br.unifesp.profetas.business.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import br.unifesp.profetas.business.init.ProfetasInit;

public class ProfetasContextListener implements ServletContextListener {
	
	@Autowired private ProfetasInit profetasInit;

	public void contextInitialized(ServletContextEvent arg) {
		WebApplicationContextUtils.getRequiredWebApplicationContext(arg.getServletContext()).getAutowireCapableBeanFactory().autowireBean(this);
		profetasInit.createRolesAndProfiles();
	}
	
	public void contextDestroyed(ServletContextEvent arg) {
		
	}
}